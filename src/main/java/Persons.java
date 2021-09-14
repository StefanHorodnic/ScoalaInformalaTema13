import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Persons {


    public static List<Person> readFromCSV(Path path) throws Exception {

        List<Person> persons = new ArrayList<>();

        try (BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.US_ASCII)){

            String line = bufferedReader.readLine();

            while(line != null){
                String[] ls =line.split(",");
                persons.add(Persons.createPersonFromCSV(ls));
                line = bufferedReader.readLine();
            }
        }
        catch (Exception e) {
            throw new Exception("Lista de persoane nu a putut fi creata. "+e.getMessage());
        }
        return persons;
    }

    public static void writeToCSV(Path path, List<Person> persons) throws Exception {
        File csvOutputFile = path.toFile();

        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            persons.stream()
                    .map(e -> e.toString())
                    .forEach(pw::println);
        }
        catch(Exception e){
            throw new Exception("Fisierul nu a putut fi creat");
        }
    }

    private static Person createPersonFromCSV(String[] metadata) throws Exception {
        try {
            String firstName = metadata[0];
            String lastName = metadata[1];

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dateOfBirth = LocalDate.parse(metadata[2], formatter);

            return new Person(firstName, lastName, dateOfBirth);
        } catch(Exception e){
            throw new Exception("Persoana nu a putut fi creata.");
        }
    }
}
