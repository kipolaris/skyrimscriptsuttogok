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

public class DoorView extends JPanel {
    private List<Overlay> overlays = new ArrayList<>();
    int frame_size = 500;
    double scale = 0.5;

    public DoorView(int size) {
        this.frame_size = size;
        int bgWidth = toIntExact(Math.round(frame_size * scale));
        int bgHeight = toIntExact(Math.round(frame_size * scale));

        BufferedImage backgroundOrig = getImage("src/pics/standard_room.png", frame_size, frame_size);
        Image background = backgroundOrig.getScaledInstance(bgWidth, bgHeight, Image.SCALE_SMOOTH);

        BufferedImage backgroundBuff = new BufferedImage(bgWidth, bgHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D gBg = backgroundBuff.createGraphics();
        gBg.drawImage(background, 0, 0, null);

        int centerX = bgWidth / 2;
        int centerY = bgHeight / 2;
        double radius = 0.4 * bgWidth;

        String[] overlayImages = { "src/pics/standard_door.png", "src/pics/invisible_door.png", "src/pics/oneway_out_door.png", "src/pics/oneway_in_door.png"};

        int overlaysCount = overlayImages.length;
        for(int i = 0; i < overlaysCount; i++) {
            String overlayImage = overlayImages[i];
            double angle = Math.PI * 2 * i / overlaysCount;
            int overlayX = (int) Math.round(centerX + radius * Math.cos(angle));
            int overlayY = (int) Math.round(centerY + radius * Math.sin(angle));
            // Apply scaling to overlay dimensions here.
            int overlayWidth = (int) Math.round((frame_size / 10) * scale);
            int overlayHeight = (int) Math.round((frame_size / 10) * scale);
            addOverlay(overlayImage, overlayWidth, overlayHeight, overlayX, overlayY, 1.0f);
        }

        for(Overlay overlay : overlays) {
            BufferedImage overlayImg = getImage(overlay.getPath(), overlay.getWidth(), overlay.getHeight());
            if(overlayImg != null) {
                Graphics2D g = backgroundBuff.createGraphics();
                g.setComposite(AlphaComposite.SrcOver.derive(overlay.getOpacity()));
                int x = overlay.getX() - overlayImg.getWidth() / 2;
                int y = overlay.getY() - overlayImg.getHeight() / 2;
                g.drawImage(overlayImg, x, y, null);
                g.dispose();
            }
        }
        gBg.dispose();

        this.setPreferredSize(new Dimension(frame_size, frame_size));
        this.setSize(frame_size, frame_size);
        JLabel label = new JLabel(new ImageIcon(backgroundBuff));
        this.add(label);
        this.setOpaque(false);

        this.validate();
        this.repaint();
    }

    private void addOverlay(String path, int width, int height, int x, int y, float opacity) {
        this.overlays.add(new Overlay(path, width, height, x, y, opacity));
    }

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

    class Overlay {
        private String path;
        private int width;
        private int height;
        private int x;
        private int y;
        private float opacity;

        public Overlay(String path, int width, int height, int x, int y, float opacity) {
            this.path = path;
            this.width = width;
            this.height = height;
            this.x = x;
            this.y = y;
            this.opacity = opacity;
        }

        public String getPath() { return path; }
        public int getWidth() { return width; }
        public int getHeight() { return height; }
        public int getX() { return x; }
        public int getY() { return y; }
        public float getOpacity() { return opacity; }
    }
}