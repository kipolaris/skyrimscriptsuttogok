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
 * A class to display an image with overlays aligned around the circumference of a circle in the middle of the image.
 */
public class DoorView extends JPanel{


    /** List to hold all overlay images: */
    private List<Overlay> overlays = new ArrayList<>();

    /** Number of pixels for the width and height of the frame: */
    int frame_size = 500;

    /** Scale factor for the image (a value of 1 means full size, less than 1 means scaled down): */
    double scale = 0.8;

    /**
     * Main method to start the display of images. This method starts the DisplayImage constructor in the AWT event dispatch thread.
     *
     * @param args the command-line arguments (not used)
     */
/*    public static void main(String[] args) {
        SwingUtilities.invokeLater(DoorView::new);
    }*/

    /**
     * The constructor for the DisplayImage class. It sets up the JFrame and scales and draws the background and overlay images.
     */
    private DoorView() {
        // Create a new JFrame (window):
        JFrame frame = new JFrame("Display Image");

        // Calculate the width and height of the background image according to the scale factor:
        int bgWidth = toIntExact(Math.round(frame_size * scale));
        int bgHeight = toIntExact(Math.round(frame_size * scale));

        // Load the original background image and scale it accordingly:
        BufferedImage backgroundOrig = getImage("standard_room.png", frame_size, frame_size);
        Image background = backgroundOrig.getScaledInstance(bgWidth, bgHeight, Image.SCALE_SMOOTH);

        // Create a new ARGB BufferedImage with the dimensions of the background
        // image (this is the buffer where we will draw the background and overlays):
        BufferedImage backgroundBuff = new BufferedImage(bgWidth, bgHeight, BufferedImage.TYPE_INT_ARGB);

        // Draw the background image onto the buffer:
        Graphics2D gBg = backgroundBuff.createGraphics();
        gBg.drawImage(background, 0, 0, null);

        // Calculate the center coordinates and radius of the circle where the overlays will be distributed:
        int centerX = bgWidth / 2;
        int centerY = bgHeight / 2;
        double radius = 0.4 * bgWidth;  // Adjust the radius as needed (0.4 is 40% of the window's width).

        // Add overlays at positions around the circle:
        String[] overlayImages = { "standard_door.png", "invisible_door.png", "oneway_out_door.png", "oneway_in_door.png"};

        int overlaysCount = overlayImages.length;  // The number of overlays that will be distributed around the circle.
        for(int i = 0; i < overlaysCount; i++) {
            // Get the current overlay image:
            String overlayImage = overlayImages[i];

            // Calculate the angle of this overlay around the circle (a full circle is 2*PI radians):
            double angle = Math.PI * 2 * i / overlaysCount;

            // Calculate the x and y position of the overlay with the given angle on the circumference of the circle:
            int overlayX = (int) Math.round(centerX + radius * Math.cos(angle));
            int overlayY = (int) Math.round(centerY + radius * Math.sin(angle));

            // Add the overlay at the calculated position to the list of overlays to be drawn:
            addOverlay(overlayImage, frame_size/10, frame_size/10, overlayX, overlayY, 1.0f);
        }

        // Draw the overlays:
        for(Overlay overlay : overlays) {
            // Load the overlay image:
            BufferedImage overlayImg = getImage(overlay.getPath(), overlay.getWidth(), overlay.getHeight());

            if(overlayImg != null) {
                // Prepare the Graphics2D object for drawing the overlay with transparency:
                Graphics2D g = backgroundBuff.createGraphics();
                g.setComposite(AlphaComposite.SrcOver.derive(overlay.getOpacity()));

                // Calculate the x and y position at the overlay's center:
                int x = overlay.getX() - overlayImg.getWidth() / 2;
                int y = overlay.getY() - overlayImg.getHeight() / 2;

                // Draw the overlay image at the calculated position:
                g.drawImage(overlayImg, x, y, null);

                // Dispose the Graphics2D instance to clean up resources immediately:
                g.dispose();
            }
        }

        // Dispose the Graphics2D instance to clean up resources immediately:
        gBg.dispose();

        // Prepare the JFrame to be displayed:
        JLabel label = new JLabel(new ImageIcon(backgroundBuff));
        label.setOpaque(true);
        label.setBackground(Color.BLACK);
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.setSize(frame_size, frame_size);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Show the JFrame:
        frame.setVisible(true);
    }

    /**
     * Add overlay to the list.
     *
     * @param path Path of the overlay image.
     * @param width Width of the overlay image.
     * @param height Height of the overlay image.
     * @param x X-coordinate of where to draw the overlay.
     * @param y Y-coordinate of where to draw the overlay.
     * @param opacity Opacity of the overlay image (0 is transparent, 1 is opaque).
     */
    private void addOverlay(String path, int width, int height, int x, int y, float opacity) {
        this.overlays.add(new Overlay(path, width, height, x, y, opacity));
    }

    /**
     * Load an image file into a BufferedImage and scale it.
     *
     * @param path The path of the image file to load.
     * @param width The width to scale the image. If width or height are 0, keep original dimensions.
     * @param height The height to scale the image. If width or height are 0, keep original dimensions.
     * @return The image as a BufferedImage. Null, if there was an exception loading the image.
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
     * A class to represent an overlay with its properties and actions.
     */
    class Overlay {
        private String path;
        private int width;
        private int height;
        private int x;
        private int y;
        private float opacity;

        /**
         * Constructor to set the overlay properties.
         *
         * @param path Path of the overlay image.
         * @param width Width of the overlay image.
         * @param height Height of the overlay image.
         * @param x X-coordinate of where to draw the overlay.
         * @param y Y-coordinate of where to draw the overlay.
         * @param opacity Opacity of the overlay image (0 is transparent, 1 is opaque).
         */
        public Overlay(String path, int width, int height, int x, int y, float opacity) {
            this.path = path;
            this.width = width;
            this.height = height;
            this.x = x;
            this.y = y;
            this.opacity = opacity;
        }

        /** Getters */
        public String getPath() { return path; }
        public int getWidth() { return width; }
        public int getHeight() { return height; }
        public int getX() { return x; }
        public int getY() { return y; }
        public float getOpacity() { return opacity; }
    }
}
