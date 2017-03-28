import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Damhalf on 27.03.2017.
 */
public class Root {

    public static void main(String[] args) {

        if (args.length > 0) {
            try {
                File file = new File(args[0]);
                BufferedImage image = ImageIO.read(file);
            } catch (IOException exception) {
                System.out.println(args[0] + " is not a valid image.");
            }
        }
    }

}