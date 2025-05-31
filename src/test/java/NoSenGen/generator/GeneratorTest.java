package NoSenGen.generator;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class GeneratorTest {

    @Test
    void genSentence() throws IOException {

        Generator gen=new Generator();
     FileReader file=new FileReader("src/test/resources/ApiKeySample.txt");
     Scanner c = new Scanner(file);
     String apiKey=c.nextLine();
     assertNotNull(gen.genSentence("this is the test for the Generator",1, apiKey));
    }
}