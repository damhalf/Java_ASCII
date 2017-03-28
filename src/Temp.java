import javax.imageio.ImageIO;
import java.io.File;
import java.io.StreamCorruptedException;

public class Temp {
    public static final String ANSI_PURPLE = "\u001B[35m";

    public static void main(String[] args) throws Exception {
        boolean console = true;
        File file = new File("Untitled.png");
        Image image = new Image(file);
        image.toGrayscale("luminosity");
        String output[][] = image.getASCII(500);
        if (console) {
            for (String[] y : output) {
                for (String x : y) {
                    System.out.print(x);
                }
                System.out.println();
            }
        }
        ImageIO.write(image.getImage(), "jpg", new File("luminosity.jpg"));
    }
}
