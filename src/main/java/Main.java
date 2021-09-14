import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args){

        try{
            Path readFilePath = Paths.get(args[0]);
            List<Person> persons = Persons.readFromCSV(readFilePath);
            int month = Integer.parseInt(args[1]);
            Path writeFilePath = Paths.get(args[2]);

            List<Person> newPersons = persons.stream()
                    .filter(z -> z.getDateOfBirth().getMonthValue() == month)
                    .sorted((Comparator.comparing(Person::getLastName)))
                    .collect(Collectors.toList());

            Persons.writeToCSV(writeFilePath, newPersons);
        }
        catch(Exception e){
            System.out.println("Programul are erori: " + e.getMessage());
        }
    }
}
