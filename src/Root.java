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

        try {
            FileWriter writer = new FileWriter(arguments.getoFile());

            // If isCons, also print results in console
            if (arguments.isCons()) {
                for (String[] y : imgArray) {
                    for (String x : y) {
                        writer.write(x);
                        System.out.print(x);
                    }
                    writer.write("\n");
                    System.out.println();
                }
            // Else just save it to arguments.oFile
            } else {
                for (String[] y : imgArray) {
                    for (String x : y) {
                        writer.write(x);
                    }
                    writer.write("\n");
                }
            }
        // If everything works as intended, we should never hit this block
        } catch (IOException ioExc) {
            System.out.println(ioExc + ": Something went awry.");
            System.exit(1);
        }
    }
}
