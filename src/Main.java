import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;

    public Main() {
        super("BOF");
        setUndecorated(true); // Remove window decorations (title bar, borders)
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window to full-screen

        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();

        if (device.isFullScreenSupported()) {
            device.setFullScreenWindow(this); // Set the window to full-screen
        } else {
            System.err.println("Full-screen mode not supported.");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(WIDTH, HEIGHT);
            setLocationRelativeTo(null);
        }

        Game play = new Game();
        ((Component) play).setFocusable(true);
        getContentPane().add(play);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main run = new Main();
        });
    }
}
