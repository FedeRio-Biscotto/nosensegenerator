package NoSenGen.generator;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;

class GeneratorTest {

    @Test
    void genSentence() throws IOException {

        Generator gen=new Generator();
        String apiKey= FileReader.class.getResource("src/test/resources/api_key.txt").getFile();
        gen.genSentence("this is the test for the program",1, apiKey);

    }
}