import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Arguments {
    private BufferedImage image;
    private File oFile;
    private int imgWidth;
    private boolean boolCons;
    private boolean boolHelp;
    private boolean boolQuit;

    public BufferedImage getImage() {
        return image;
    }

    public File getoFile() {
        return oFile;
    }

    public int getImgWidth() {
        return imgWidth;
    }

    public boolean isCons() {
        return boolCons;
    }

    public boolean isQuit() {
        return boolQuit;
    }

    /*  Checks whether all the arguments fit into expected models and
        assigns their values to retrievable variables.
     */
    private boolean parsed(String args[]) {
        varReset();

        if (args.length < 0) {
            return false;
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-i")) {
                i++;
                try {
                    image = ImageIO.read(new File(args[i]));
                } catch (IOException ioExc) {
                    System.out.println(ioExc + ": " + args[i] + " is not a valid file.");
                    return false;
                }
            } else if (args[i].equals("-o")) {
                i++;
                oFile = new File(args[i]);
                oFile.getParentFile().mkdirs();
                try {
                    oFile.createNewFile();
                } catch (IOException ioExc) {
                    // File exists, but we'll just overwrite it.
                }
            } else if (args[i].equals("-w")) {
                i++;
                try {
                    imgWidth = Integer.parseInt(args[i]);
                } catch (NumberFormatException nfExc) {
                    System.out.println(nfExc + ": -w expects an integer.");
                    return false;
                }
                if (imgWidth < 100 || 500 < imgWidth) {
                    System.out.println("-w expects an integer in range 100 to 500.");
                    return false;
                }
            } else if (args[i].equals("-c")) {
                boolCons = true;
            } else if (args[i].equals("-h")) {
                boolHelp = true;
            } else if (args[i].equals("-q")) {
                boolQuit = true;
            } else {
                System.out.println(args[i] + " is not an expected argument.");
                return false;
            }
        }
        if (image != null) {
            return true;
        }
        return false;
    }

    private static void printHelp() {
        System.out.printf("This code creates a really basic ASCII of the input image.\n" +
                "Expected arguments:\n" +
                "-i <String>: Input image name\n\n" +
                "Optional arguments:\n" +
                "-o <String>: Output file name, defaults to \"YourWaifuIsGarbage.txt\" in the user.dir\n" +
                "\tWARNING! Code does not provide a cleanup for created directories!\n" +
                "-w <Integer>: The count of characters on the final image\n" +
                "-c: Prints the result in console only\n" +
                "-h: Acts all passive aggressive but still helps out\n" +
                "-q: Quits the program\n\n");
    }

    /*  Sets variables to default values and
        deletes the old YourWaifuIsGarbage.txt
     */
    private void varReset() {
        image = null;
        if (oFile != null) {
            oFile.delete();
        }
        oFile = new File("YourWaifuIsGarbage.txt");
        imgWidth = 500;
        boolCons = false;
        boolHelp = false;
        boolQuit = false;
    }

    /*  Runs until a proper set of arguments is given.
        Handles -h & -q!
     */
    public void run(String args[]) {
        Scanner scanner = new Scanner(System.in);
        while (!parsed(args)) {
            if(boolHelp) printHelp();
            if(boolQuit) System.exit(0);

            System.out.println("Please input a proper set of arguments (\"-h\" for help):");
            args = scanner.nextLine().split(" ");
        }
        scanner.close();
    }
}
