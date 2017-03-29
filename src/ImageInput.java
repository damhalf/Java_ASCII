import javax.imageio.ImageIO;
import java.util.Scanner;

/**
 * Created by Rain on 27.03.2017.
 */
public class ImageInput {
    static void helpMessage(){
        System.out.println("-h - Lists the available commands");
        System.out.println("-q - Quits from the program");
        System.out.println("-i - Used to indicate the input file");
        System.out.println("-o - Used to name the output file");
        System.out.println("-w - Used to indicate the resize value, defaults");
    }
    public static void main(String[] args) throws Exception {
        String[] commands = args;

        label:
        while (true) {
            Scanner scan = new Scanner(System.in);
            boolean printToConsole = false;
            String inputFile = "";
            String outputFile = "";
            int width = 500;

            for(int i = 0; i < commands.length; i++){
                if(commands[i].equals("-q")){
                    break label;
                }
                else if(commands[i].equals("-h")){
                    helpMessage();
                }
                else if(commands[i].equals("-i")){
                    inputFile = commands[i+1];
                    i++;
                }
                else if(commands[i].equals("-o")){
                    outputFile = commands[i+1];
                    i++;
                }
                else if(commands[i].equals("-w")){
                    width = Integer.parseInt(commands[i+1]);
                    i++;
                }
                else if(commands[i].equals("-c")){
                    printToConsole = true;
                }
            }
            java.io.File file = new java.io.File(inputFile);
            if (file.isFile()) {
                if (ImageIO.read(file) != null) {
                    //Siia peaksid tulema siis sinu funktsioonid.

                } else {
                    System.out.println("The filepath you have provided does not lead to an image, please enter a new path.");
                     commands = scan.nextLine().split(" ");

                }

            }
            else{
                System.out.println("The filepath you have provided does not lead to a file, please enter a new path.");
                commands = scan.nextLine().split(" ");
            }
        }

    }
}
