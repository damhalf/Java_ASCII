import java.io.FileWriter;
import java.io.IOException;

public class Root {

    public static void main(String args[]) {
        Image image;
        String imgArray[][];

        Arguments arguments = new Arguments();
        arguments.run(args);
        image = new Image(arguments.getImage());
        // You don't get to choose, luminosity grayscale has the best results
        image.toGrayscale("luminosity");
        imgArray = image.getASCII(arguments.getImgWidth());

        // If isCons, only prints results in console
        if (arguments.isCons()) {
            for (String[] y : imgArray) {
                for (String x : y) {
                    System.out.print(x);
                }
                System.out.println();
            }
        // Else saves it to arguments.oFile
        } else {
            try {
                FileWriter writer = new FileWriter(arguments.getoFile());
                for (String[] y : imgArray) {
                    for (String x : y) {
                        writer.write(x);
                    }
                    writer.write("\n");
                }
            // If everything works as intended, we should never hit this block
            } catch (IOException ioExc) {
                System.out.println(ioExc + ": Something went awry.");
                System.exit(1);
            }

        }
    }
}