package NoSenGen.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class TemplatesLibraryTest {

    ArrayList<Template> templates = new ArrayList<Template>();
    Template x;
    @BeforeEach
    void setUp() {

        x=new Template("(noun) (verb) a (adj) (noun)");
        templates.add(new Template("(noun) (verb) a (adj) (noun)"));
        templates.add(x);


    }

    @Test
    void randomTemplatePicker() {
        assertEquals(x, templates.get(1));
    }
}