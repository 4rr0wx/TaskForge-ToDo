import com.opencsv.exceptions.CsvException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Menu {


    public static void setup() throws Exception {
        Properties prop = new Properties();
        prop.load(Files.newInputStream(Paths.get(Startup.FILEPATH_CONFIG)));


        System.out.println("Welcome to TaskForge ToDo");
        System.out.println("I will guide you through the setup process");
        System.out.println("Please follow the instructions on screen");
        System.out.println("-----------------------------------------");
        System.out.println("How is your name?");
        Scanner UserInput = new Scanner(System.in);
        String input = UserInput.nextLine();
        System.out.println("Hello " + input + "!");
        prop.setProperty("name", input);
        System.out.println("Please enter a password");
        input = UserInput.nextLine();
        //TODO: Make Password encryption
        prop.setProperty("password", input);
        prop.setProperty("setupdone", "1");
        prop.store(Files.newOutputStream(Paths.get(Startup.FILEPATH_CONFIG)), null);
        System.out.println("Setup completed!");
        sleep(500);

    }

    public static void menu() throws IOException, CsvException {

        System.out.println("\n\n-----------------------------------------\n\n");
        System.out.println("What do you want to do?");
        System.out.println("1. Add a new task");
        System.out.println("2. Show all tasks");
        System.out.println("3. Delete Task");
        System.out.println("4. Exit");

        //get user input
        Scanner UserInput = new Scanner(System.in);
        String input = UserInput.nextLine();

        //check user input
        switch (input) {
            case "1" -> ToDoManagement.add_ToDo();
            case "2" -> ToDoManagement.show_ToDo();
            case "3" -> System.out.println("You chose 3");
            case "4" -> System.out.println("Goodbye!");
            default -> {
                System.out.println("Invalid input!");
                menu();
            }
        }

    }

    public static void main(String[] args) throws Exception {

        Properties prop = new Properties();
        prop.load(Files.newInputStream(Paths.get(Startup.FILEPATH_CONFIG)));
        if (prop.get("setupdone").equals("0")) {
            System.out.println("Setup not done yet!");
            setup();
            prop.load(Files.newInputStream(Paths.get(Startup.FILEPATH_CONFIG)));
        } else {
            System.out.println("Setup already completed!");
        }

        System.out.println("Welcome " + prop.get("name") + "!");


        String password = prop.get("password").toString();
        while (true) {
            System.out.println("Please enter your password");
            Scanner UserInput = new Scanner(System.in);
            String input = UserInput.nextLine();
            if (input.equals(password)) {
                System.out.println("Password correct!");
                break;
            } else {
                System.out.println("Password incorrect!");
            }
        }

        menu();


    }
}
