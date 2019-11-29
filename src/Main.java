import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - 800) / 2);
        int y = (int) ((screenSize.getHeight() - 400) / 2);
        FrameMain frame = new FrameMain(x, y);
    }
}
