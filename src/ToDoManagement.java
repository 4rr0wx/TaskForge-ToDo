import com.opencsv.*;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class ToDoManagement {


    public static void add_ToDo() throws IOException, CsvException {
        File file = new File(Startup.FILEPATH_DATA);
        FileWriter outputfile = new FileWriter(file, true);
        CSVWriter writer = new CSVWriter(outputfile);
        String[] data = new String[2];
        System.out.println("Adding ToDo");
        System.out.println("What do you want to do?");
        Scanner UserInput = new Scanner(System.in);
        String ToDo_input = UserInput.nextLine();
        data[0] = ToDo_input;
        System.out.println("When is your deadline?");
        String Deadline_input = UserInput.nextLine();
        data[1] = Deadline_input;
        System.out.println("Your ToDo is: " + data[0] + " and your deadline is: " + data[1]);
        writer.writeNext(data);
        writer.close();
        Menu.menu();
    }

    public static void show_ToDo() throws IOException, CsvException {
        File file = new File(Startup.FILEPATH_DATA);
        FileReader filereader = new FileReader(file);
        CSVReader csvReader = new CSVReader(filereader);
        List<String[]> allData = csvReader.readAll();
        allData.remove(0);
        for (String[] row : allData) {
            System.out.println(Arrays.toString(row));
        }
        Menu.menu();
    }

    public static void delete_ToDo(){
        System.out.println("Deleting ToDo");
        System.out.println("Which ToDo do you want to delete?");
        Scanner UserInput = new Scanner(System.in);
        String ToDo_input = UserInput.nextLine();
        

    }
}
