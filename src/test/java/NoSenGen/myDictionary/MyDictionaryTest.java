package NoSenGen.myDictionary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
class MyDictionaryTest {

    private MyDictionary dict;

    @BeforeEach
    void setUp() throws IOException {
        dict=new MyDictionary("test");
    }

    @Test
    void getNounTest() {

        MyNoun noun=dict.getNoun();
        assertNotNull(noun);
    }

    @Test
    void getVerbTest() {
        MyVerb verb=dict.getVerb();
        assertNotNull(verb);
    }
    @Test
    void getAdjTest() {
        MyAdjective adj=dict.getAdj();
        assertNotNull(adj);
    }

    @Test
    void getVerbNothirdPerson() {
        MyVerb verb=dict.getVerbNothirdPerson();
        assertNotNull(verb);

    }

    @Test
    void getVerbPast() {
        MyVerb verb=dict.getVerbPast();
        assertNotNull(verb);
    }
}