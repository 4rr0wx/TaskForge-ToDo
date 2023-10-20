import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Startup {

    public static void createFile() throws IOException, NoSuchAlgorithmException, InterruptedException {
        File f = new File(FILEPATH_DATA);
        File f2 = new File(FILEPATH_CONFIG);

        if (!f.exists() && !f.isDirectory()) {
            Path newFilePath = Paths.get(FILEPATH_DATA);
            Files.createFile(newFilePath);
            CSVWriter writer = new CSVWriter(new FileWriter(FILEPATH_DATA));
            String[] header = {"name", "deadline"};
            writer.writeNext(header);
            writer.close();
            System.out.println("Data file created!");
            sleep(500);
        } else System.out.println("Data file already exists!");

        if (!f2.exists() && !f2.isDirectory()) {
            Path newFilePath = Paths.get(FILEPATH_CONFIG);
            Files.createFile(newFilePath);
            Properties prop = new Properties();

            prop.setProperty("setupdone", "0");
            prop.setProperty("name", "null");
            prop.setProperty("password", "null");
            prop.setProperty("key", "null");
            prop.store(Files.newOutputStream(newFilePath), null);
            System.out.println("Config file created!");
            sleep(500);
        } else System.out.println("Config file already exists!");
    }

    public static final String FILEPATH_DATA = "./data.csv";
    public static final String FILEPATH_CONFIG = "./config.properties";

    public static void main(String[] args) throws Exception {


        System.out.println("""
                                
                 _______        _    ______                     _______    _____       \s
                |__   __|      | |  |  ____|                   |__   __|  |  __ \\      \s
                   | | __ _ ___| | _| |__ ___  _ __ __ _  ___     | | ___ | |  | | ___ \s
                   | |/ _` / __| |/ /  __/ _ \\| '__/ _` |/ _ \\    | |/ _ \\| |  | |/ _ \\\s
                   | | (_| \\__ \\   <| | | (_) | | | (_| |  __/    | | (_) | |__| | (_) |
                   |_|\\__,_|___/_|\\_\\_|  \\___/|_|  \\__, |\\___|    |_|\\___/|_____/ \\___/\s
                                                    __/ |                              \s
                                                   |___/       \s
                Welcome to TaskForge ToDo Beta 0.1
                Author: Martin Scheifinger
                """);

        System.out.println("Checking if expected files are present");
        System.out.print(".");
        sleep(300);
        System.out.print(".");
        sleep(300);
        System.out.print(".");
        sleep(300);
        System.out.println(".");
        sleep(300);

        File f = new File(FILEPATH_DATA);
        File f2 = new File(FILEPATH_CONFIG);
        if (f.exists() && !f.isDirectory() && f2.exists() && !f2.isDirectory()) {
            System.out.println("All files found!");
            sleep(1000);
            Menu.main(args);
        } else {
            System.out.println("Files missing!");
            sleep(500);
            System.out.println("Creating files");
            sleep(500);
            createFile();
            System.out.println("Please restart the program!");
            System.exit(0);
        }
    }


}

