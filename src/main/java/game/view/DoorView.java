package game.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.toIntExact;

/**
 * Egy osztály, amely egy képet jelenít meg, kör alakban elhelyezett átfedésekkel a kép közepén.
 */
public class DoorView extends JPanel{
    /** Lista az összes átfedés képek tárolására: */
    private List<Overlay> overlays = new ArrayList<>();

    /** Az ablak szélessége és magassága pixelben: */
    int frame_size = 500;


    /** A kép méretezési tényezője (1 érték teljes méret, 1-nél kisebb érték lecsökkentett méret): */
    double scale = 0.6;

    /**
     * Fő metódus a képek megjelenítésének indításához. Ez a metódus elindítja a DisplayImage konstruktort az AWT eseménykezelő szálon.
     *
     * @param args a parancssori argumentumok (nem használt)
     */
   public static void main(String[] args) {
        SwingUtilities.invokeLater(DoorView::new);
    }

    /**
     * A DisplayImage osztály konstruktora. Beállítja a JFrame-et és méretezi, majd kirajzolja a háttér- és átfedésképeket.
     */
    DoorView() {
        // Új JFrame (ablak) létrehozása:
        JFrame frame = new JFrame("Display Image");

        // A háttérkép szélességének és magasságának kiszámítása a méretezési tényező alapján:
        int bgWidth = toIntExact(Math.round(frame_size * scale));
        int bgHeight = toIntExact(Math.round(frame_size * scale));

        // Az eredeti háttérkép betöltése és megfelelő méretezése:
        BufferedImage backgroundOrig = getImage("src/pics/standard_room.png", frame_size, frame_size);
        Image background = backgroundOrig.getScaledInstance(bgWidth, bgHeight, Image.SCALE_SMOOTH);

        // Új ARGB BufferedImage létrehozása a háttérkép méreteivel (ebbe a bufferbe rajzoljuk a háttér- és átfedésképeket):
        BufferedImage backgroundBuff = new BufferedImage(bgWidth, bgHeight, BufferedImage.TYPE_INT_ARGB);

        // A háttérkép rajzolása a bufferre:
        Graphics2D gBg = backgroundBuff.createGraphics();
        gBg.drawImage(background, 0, 0, null);

        // A kör középpontjának koordinátáinak és sugarának kiszámítása, ahol az átfedések eloszlanak:
        int centerX = bgWidth / 2;
        int centerY = bgHeight / 2;
        double radius = 0.4 * bgWidth;

        // Átfedések hozzáadása a kör körüli pozíciókban:
        String[] overlayImages = { "src/pics/standard_door.png", "src/pics/invisible_door.png", "src/pics/oneway_out_door.png", "src/pics/oneway_in_door.png"};

        int overlaysCount = overlayImages.length;  // Az átfedések száma, amelyeket elosztunk a kör körül.
        for(int i = 0; i < overlaysCount; i++) {
            // Az aktuális átfedés képének megszerzése:
            String overlayImage = overlayImages[i];

            // Az átfedés szögének kiszámítása a kör körül (a teljes kör 2*PI radián):
            double angle = Math.PI * 2 * i / overlaysCount;

            // Az átfedés x és y pozíciójának kiszámítása a kör kerületén adott szög alapján:
            int overlayX = (int) Math.round(centerX + radius * Math.cos(angle));
            int overlayY = (int) Math.round(centerY + radius * Math.sin(angle));
            int overlayWidth = (int) Math.round((frame_size / 10) * scale);
            int overlayHeight = (int) Math.round((frame_size / 10) * scale);
            addOverlay(overlayImage, overlayWidth, overlayHeight, overlayX, overlayY, 1.0f);
        }

        // Az átfedések rajzolása:
        for(Overlay overlay : overlays) {
            // Az átfedés képének betöltése:
            BufferedImage overlayImg = getImage(overlay.getPath(), overlay.getWidth(), overlay.getHeight());
            if(overlayImg != null) {
                // A Graphics2D objektum előkészítése az átfedés rajzolásához átlátszósággal:
                Graphics2D g = backgroundBuff.createGraphics();
                g.setComposite(AlphaComposite.SrcOver.derive(overlay.getOpacity()));

                // Az x és y pozíció kiszámítása az átfedés közepénél:
                int x = overlay.getX() - overlayImg.getWidth() / 2;
                int y = overlay.getY() - overlayImg.getHeight() / 2;

                // Az átfedés képének rajzolása a kiszámított pozícióban:
                g.drawImage(overlayImg, x, y, null);

                // A Graphics2D példány eldobása az erőforrások azonnali felszabadításához:
                g.dispose();
            }
        }

        gBg.dispose();

        this.setSize(frame_size, frame_size);
        JLabel imageLabel = new JLabel(new ImageIcon(backgroundBuff));

        JComboBox<String> box = new JComboBox<>(new String[]{"Option 1", "Option 2", "Option 3"});
        box.setMaximumSize(box.getPreferredSize());

        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        comboPanel.add(box);
        comboPanel.setOpaque(false);


        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        textPanel.setOpaque(false);

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        centerPanel.add(textPanel);
        centerPanel.add(comboPanel);

        imageLabel.setLayout(new BorderLayout());
        imageLabel.add(centerPanel, BorderLayout.CENTER);

        this.add(imageLabel);
        this.setOpaque(false);
        this.validate();
        this.repaint();

        // A JFrame megjelenítése:
        frame.setVisible(true);
    }

    /**
     * Átfedés hozzáadása a listához.
     *
     * @param path Az átfedés képének elérési útja.
     * @param width Az átfedés képének szélessége.
     * @param height Az átfedés képének magassága.
     * @param x Az x-koordináta, ahol az átfedést rajzoljuk.
     * @param y Az y-koordináta, ahol az átfedést rajzoljuk.
     * @param opacity Az átfedés képének átlátszósága (0 átlátszó, 1 átlátszatlan).
     */
    private void addOverlay(String path, int width, int height, int x, int y, float opacity) {
        this.overlays.add(new Overlay(path, width, height, x, y, opacity));
    }

    /**
     * Kép betöltése fájlból BufferedImage-ként és méretezése.
     *
     * @param path A betöltendő kép fájljának elérési útja.
     * @param width A kép szélessége méretezés után. Ha a szélesség vagy magasság 0, megtartja az eredeti méreteket.
     * @param height A kép magassága méretezés után. Ha a szélesség vagy magasság 0, megtartja az eredeti méreteket.
     * @return A kép BufferedImage formátumban. Null, ha hiba történt a kép betöltése során.
     */
    private BufferedImage getImage(String path, int width, int height) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(path));
            if (width > 0 && height > 0) {
                Image tmp = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = scaledImage.createGraphics();
                g2d.drawImage(tmp, 0, 0, null);
                g2d.dispose();
                return scaledImage;
            } else {
                return originalImage;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Egy osztály, amely egy átfedést reprezentál a tulajdonságaival és műveleteivel.
     */
    class Overlay {
        private String path;
        private int width;
        private int height;
        private int x;
        private int y;
        private float opacity;

        /**
         * Konstruktor az átfedés tulajdonságainak beállításához.
         *
         * @param path Az átfedés képének elérési útja.
         * @param width Az átfedés képének szélessége.
         * @param height Az átfedés képének magassága.
         * @param x Az x-koordináta, ahol az átfedést rajzoljuk.
         * @param y Az y-koordináta, ahol az átfedést rajzoljuk.
         * @param opacity Az átfedés képének átlátszósága (0 átlátszó, 1 átlátszatlan).
         */
        public Overlay(String path, int width, int height, int x, int y, float opacity) {
            this.path = path;
            this.width = width;
            this.height = height;
            this.x = x;
            this.y = y;
            this.opacity = opacity;
        }

        /** Getterek */
        public String getPath() { return path; }
        public int getWidth() { return width; }
        public int getHeight() { return height; }
        public int getX() { return x; }
        public int getY() { return y; }
        public float getOpacity() { return opacity; }
    }
}