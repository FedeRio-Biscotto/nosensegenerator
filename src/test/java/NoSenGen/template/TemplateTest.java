package NoSenGen.template;

import NoSenGen.myDictionary.MyAdjective;
import NoSenGen.myDictionary.MyNoun;
import NoSenGen.myDictionary.MyVerb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class TemplateTest {

    private Template template;
    private List<MyNoun> nouns;
    private List<MyVerb> verbs;
    private List<MyVerb> verbsNotThirdPerson;
    private List<MyAdjective> adjectives;

    @BeforeEach
    void setUp() {
        nouns = new ArrayList<>();
        verbs = new ArrayList<>();
        verbsNotThirdPerson = new ArrayList<>();
        adjectives = new ArrayList<>();

        // Add test data
        nouns.add(new MyNoun("cat"));
        nouns.add(new MyNoun("dog"));
        verbs.add(new MyVerb("runs"));
        verbs.add(new MyVerb("jumps"));
        verbsNotThirdPerson.add(new MyVerb("run"));
        verbsNotThirdPerson.add(new MyVerb("jump"));
        adjectives.add(new MyAdjective("happy"));
        adjectives.add(new MyAdjective("quick"));
    }

    @Test
    void testTemplateCreationWithoutSentence() {
        template = new Template("(noun) (verb) (adj)");
        assertEquals(1, template.getMissingNouns());
        assertEquals(1, template.getMissingVerbs());
        assertEquals(1, template.getMissingAdjectives());
    }

    @Test
    void testTemplateCreationWithSentence() {
        try (MockedStatic<TemplatesLibrary> mockedStatic = mockStatic(TemplatesLibrary.class)) {
            Template innerTemplate = new Template("(noun) (verb)");
            when(TemplatesLibrary.randomTemplatePicker()).thenReturn(innerTemplate);

            template = new Template("(sentence) and (noun)");
            assertNotNull(template.getTemplate());
        }
    }

    @Test
    void testFillTemplatePresent() {
        template = new Template("(noun) (verb) (adj)");
        String result = template.FillTemplate(nouns, verbs, verbsNotThirdPerson, adjectives);
        assertNotNull(result);
        assertEquals("cat runs happy", result);
    }

    @Test
    void testFillTemplatePast() {
        template = new Template("(noun) (verb) (adj)");
        String result = template.FillTemplate_past(nouns, verbs, adjectives);
        assertNotNull(result);
        assertEquals("cat runs happy", result);
    }

    @Test
    void testFillTemplateFuture() {
        template = new Template("(noun) (verb) (adj)");
        String result = template.FillTemplate_future(nouns, verbs, adjectives);
        assertNotNull(result);
        assertEquals("cat will runs happy", result);
    }

    @Test
    void testFillTemplateWithThirdPersonCheck() {
        template = new Template("(noun) and (noun) (verb)");
        String result = template.FillTemplate(nouns, verbs, verbsNotThirdPerson, adjectives);
        assertNotNull(result);
        assertEquals("cat and dog run", result);
    }

    @Test
    void testGetters() {
        template = new Template("(noun) (verb) (adj) (noun)");
        assertEquals(2, template.getMissingNouns());
        assertEquals(1, template.getMissingVerbs());
        assertEquals(1, template.getMissingAdjectives());
        assertNotNull(template.getTemplate());
    }
}