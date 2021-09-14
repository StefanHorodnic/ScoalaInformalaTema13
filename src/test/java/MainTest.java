import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

public class MainTest {

    @Test
    void works(){
        String[] string = new String[3];
        string[0] = "input.csv";
        string[1] = "5";
        string[2] = "export.csv";
        try{
            Main.main(string);
        }
        catch(Exception e){
            System.out.println("Programul are erori: " + e.getMessage());
        }
        Assertions.assertTrue(Files.exists(Paths.get(string[2])));
    }
}
