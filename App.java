import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.awt.*;


public class App{
    public static File firstStarted;
    public static void main(String[] args) {
        firstStarted = new File("./Commands/started.txt");
        starting();
    }
    public static void starting() {
        System.out.println("Hello! It`s an app to create and use your own commands\nLet`s start!");
        try{
            if (firstStarted.exists()) {
                showingCommands();
            }
            if (firstStarted.createNewFile()) {
                firstStart();
            }
        }
        catch(IOException e) {
            System.out.println("Error");
        }
    }
    public static void firstStart() {
        System.out.println("It`s the first start!\nLet`s create your first command!\nType 'Create New' to create new command!");
        Scanner scanner = new Scanner(System.in);

        if (scanner.nextLine().equals("Create New")) {
            choosingTypeOfCommand();
        }
        else{
            System.out.println("Try one more time!");
            firstStart();
        }
    }
    public static void showingCommands() {
        System.out.println("Here is your commands!");
        File commandsDirectory = new File("./Commands");
        for( File f : commandsDirectory.listFiles()){
            System.out.println( f.getName() );
        }
        System.out.println("Choose the type of command to show\n2.With Net\n2.Without Net\n3.Combine");
        ystem.out.println("It`s the first start!\nLet`s create your first command!\nType 'Create New' to create new command!");
        Scanner scanner = new Scanner(System.in);

        if (scanner.nextLine().equals("Create New")) {
            choosingTypeOfCommand();
        }
        else{
            System.out.println("Try one more time!");
            showingCommands();
        }
        Scanner typeToShow = new Scanner(System.in);
        int showingType = typeToShow.nextInt();
        if (showingType == 1) {
            File withNetDirectory = new File("./Commands/WithNet");
            for(File f : withNetDirectory.listFiles()) {
                System.out.println(f.getName());
            }
            System.out.println("Type the name of command to start(without '.txt'):");
            Scanner nameOfCommand = new Scanner(System.in);
            String commandName = nameOfCommand.nextLine();

            File commandToScan = new File("./Commands/WithNet/" + commandName + ".txt");
            startingUrlCommand(commandToScan);
        } 
        if (showingType == 2) {
            File withoutNetDirectory = new File("./Commands/WithoutNet");
            for(File f : withoutNetDirectory.listFiles()) {
                System.out.println(f.getName());
            }
        } 
        if (showingType == 1) {
            File combineDirectory = new File("./Commands/Both");
            for(File f : combineDirectory.listFiles()) {
                System.out.println(f.getName());
            }
        }
    }
    public static void choosingTypeOfCommand() {
        System.out.println("Creating your new command!\nChoose the type of the command:\n1.With Net\n2.Without Net\n3.Combine");
        Scanner type = new Scanner(System.in);
        int typeOfCommand = type.nextInt();

        if (typeOfCommand == 1) {
            creatingWithNetCommand();
        }
    }
    public static void creatingWithNetCommand() {
        try{
            System.out.println("Creating command with Internet!\nChoose how many url`s will be in your command");
            Scanner howManyURL = new Scanner(System.in);
            int howManyUr = howManyURL.nextInt();
            System.out.println("Enter the command name: ");
            Scanner commandName = new Scanner(System.in);
            File command = new File("./Commands/WithNet/" + commandName.nextLine() + ".txt");
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(command));
            for(int i = 0; i != howManyUr; i++){
                System.out.println("Enter the URL: ");
                Scanner urlScanner = new Scanner(System.in);
                String url = urlScanner.nextLine();
                //Adding url`s to file
                fileWriter.write(url);
                fileWriter.newLine();
            }
            fileWriter.close();
            System.out.println("Would you like to test this command?\ny/n");
            Scanner test = new Scanner(System.in);
            String willStart = test.nextLine();
            if (willStart.equals("y")) {
                startingUrlCommand(command);
            }
            if (willStart.equals("n")) {
                showingCommands();
            }
        }
        catch(IOException er) {
            System.out.println("Error");
        }        
    }
    public static void startingUrlCommand(File withCommand) {
        try{
            try{
                try{
                    Scanner fileScanner = new Scanner(withCommand);
                    while(fileScanner.hasNextLine()) {
                        Desktop device = Desktop.getDesktop();
                        URI forBrowsing = new URI(fileScanner.nextLine());
                        device.browse(forBrowsing);
                    }
                }
                catch(FileNotFoundException error) {
                    System.out.println("Error");
                }
            }
            catch(URISyntaxException er) {
                System.out.println("Error");
            }
        }
        catch(IOException e) {
            System.out.println("Error");
        }
    }
}