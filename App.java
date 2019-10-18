import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.awt.*;
import java.util.InputMismatchException;;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;


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
            System.out.println("Type the name of command to start(without '.txt' and without ''''):");
            Scanner nameOfCommand = new Scanner(System.in);
            String commandName = nameOfCommand.nextLine();

            File commandToScan = new File("./Commands/WithoutNet/" + commandName + ".txt");
            startingAppsCommand(commandToScan);
        }
        if (showingType == 3) {
            File combineDirectory = new File("./Commands/Both");
            for(File f : combineDirectory.listFiles()) {
                System.out.println(f.getName());
            }
            System.out.println("Type the name of command to start it:");
            Scanner nameOfCommand = new Scanner(System.in);
            String commandName = nameOfCommand.nextLine();
            Path commandRepo = Paths.get("./Commands/Both/" + commandName);
            File urlPart = new File(commandRepo + "/url.txt");
            File filePart = new File(commandRepo + "/file.txt");
            startingCombineCommand(urlPart, filePart);
        }
    }
    public static void choosingTypeOfCommand() {
        System.out.println("Creating your new command!\nChoose the type of the command:\n1.With Net\n2.Without Net\n3.Combine");
        Scanner type = new Scanner(System.in);
        int typeOfCommand = type.nextInt();

        if (typeOfCommand == 1) {
            creatingWithNetCommand();
        }
        if (typeOfCommand == 2) {
            creatingWithoutNetCommand();
        }
        if (typeOfCommand == 3) {
            creatingCombineCommands();
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
    public static void startingUrlCommand(File withURL) {
        try{
            try{
                try{
                    Scanner fileScanner = new Scanner(withURL);
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
    public static void creatingWithoutNetCommand() {
        try{
            System.out.println("Creating command with Internet!\nChoose how many apps or files will be in your command");
            Scanner howManyURL = new Scanner(System.in);
            int howManyUr = howManyURL.nextInt();
            System.out.println("Enter the command name: ");
            Scanner commandName = new Scanner(System.in);
            File command = new File("./Commands/WithoutNet/" + commandName.nextLine() + ".txt");
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(command));
            for(int i = 0; i != howManyUr; i++){
                try{
                    System.out.println("Enter the path to file or app: ");
                    Scanner pathScanner = new Scanner(System.in);
                    String path = pathScanner.nextLine();
                    //Adding url`s to file
                    fileWriter.write(path);
                    fileWriter.newLine();
                }
                catch(InputMismatchException er) {
                    System.out.println("Error");
                }
            }
            fileWriter.close();
            System.out.println("Would you like to test this command?\ny/n");
            Scanner test = new Scanner(System.in);
            String willStart = test.nextLine();
            if (willStart.equals("y")) {
                startingAppsCommand(command);
            }
            if (willStart.equals("n")) {
                showingCommands();
            }
        }
        catch(IOException er) {
            System.out.println("Error");
        }
    }
    public static void startingAppsCommand(File withApps) {
        try{
            try{
                try{
                    Scanner fileScanner = new Scanner(withApps);
                    while(fileScanner.hasNextLine()) {
                        Desktop device = Desktop.getDesktop();
                        File app = new File(fileScanner.nextLine());
                        device.open(app);
                    }
                }
                catch(FileNotFoundException error) {
                    System.out.println("Error");
                }
            }
            catch(IllegalArgumentException er) {
                System.out.println("Error");
            }
        }
        catch(IOException e) {
            System.out.println("Error");
        }
    }
    public static void creatingCombineCommands() {
      try{
        try{
            System.out.println("Type the name of your combine command: ");
            Scanner nameOfCommand = new Scanner(System.in);
            String commandName = nameOfCommand.nextLine();
            Path commandRepo = Paths.get("./Commands/Both/" + commandName);
            Files.createDirectories(commandRepo);
              //Part with url`s
              System.out.println("How many url`s will be in your combine command?");
              Scanner howManySites = new Scanner(System.in);
              int sitesCount = howManySites.nextInt();

              //Part with apps/files
              System.out.println("How many apps and files will be in your combine command?");
              Scanner howManyApps = new Scanner(System.in);
              int appsCount = howManyApps.nextInt();


              //Writing into the .txt file
              File urlPart = new File(commandRepo + "/url.txt");
              File filePart = new File(commandRepo + "/file.txt");
              BufferedWriter urlWriter = new BufferedWriter(new FileWriter(urlPart));
              BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePart));

              for(int i = 0; i != sitesCount; i++) {
                  System.out.println("Enter the url: ");
                  Scanner urlTaker = new Scanner(System.in);
                  String url = urlTaker.nextLine();

                  urlWriter.write(url);
                  urlWriter.newLine();
              }
              urlWriter.close();

              for (int f = 0; f != appsCount; f++) {
                System.out.println("Enter the path to the app/file: ");
                Scanner pathTaker = new Scanner(System.in);
                String path = pathTaker.nextLine();

                fileWriter.write(path);
                fileWriter.newLine();
              }
              fileWriter.close();

              System.out.println("Do you wnat to test this command?\ny\nn");
              Scanner willTest = new Scanner(System.in);
              String testing = willTest.nextLine();

              if (testing.equals("y")) {
                startingCombineCommand(urlPart, filePart);
              }
          }
          catch(InputMismatchException er) {
            System.out.println("Input Mismatch Error");
          }
        }
        catch(IOException e) {
          System.out.println("IO Error");
        }
    }
    public static void startingCombineCommand(File url, File files) {
      try{
        try{
          Scanner urlScanner = new Scanner(url);
          Scanner fileScanner = new Scanner(files);
          Desktop device = Desktop.getDesktop();


          while(urlScanner.hasNextLine()){
            try{
              URI uri = new URI(urlScanner.nextLine());
              device.browse(uri);
            }
            catch(URISyntaxException er) {
              System.out.println("URI Syntax Error!");
            }
          }
          while(fileScanner.hasNextLine()){
            File app = new File(fileScanner.nextLine());
            device.open(app);
          }
        }
        catch(FileNotFoundException error) {
          System.out.println("File Not Found!");
        }
      }
      catch(IOException e) {
        System.out.println("IO Error");
      }
    }
}
