package NoSenGen.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GoogleLanguageAPITest {

    private static final String TEST_SENTENCE = "The cat runs fast";
    private static final String TEST_API_KEY = "test-api-key";
    private static final String MOCK_API_RESPONSE = """
            {
                "tokens": [
                    {
                        "text": {"content": "The"},
                        "partOfSpeech": {"tag": "PRON", "person": "FIRST", "tense": "PRESENT"}
                    },
                    {
                        "text": {"content": "cat"},
                        "partOfSpeech": {"tag": "NOUN", "person": "FIRST", "tense": "PRESENT"}
                    },
                    {
                        "text": {"content": "runs"},
                        "partOfSpeech": {"tag": "VERB", "person": "THIRD", "tense": "PRESENT"}
                    },
                    {
                        "text": {"content": "fast"},
                        "partOfSpeech": {"tag": "ADJ", "person": "FIRST", "tense": "PRESENT"}
                    }
                ]
            }""";

    @BeforeEach
    void setUp() {
        // Pulisco tutte le collezioni statiche prima di ogni test
        GoogleLanguageAPI.getNouns().clear();
        GoogleLanguageAPI.getVerbs().clear();
        GoogleLanguageAPI.getVerbs_thirdperson().clear();
        GoogleLanguageAPI.getVerbs_past().clear();
        GoogleLanguageAPI.getAdj().clear();
    }

    @Test
    void testLanguageApi() throws Exception {
        try (MockedStatic<GoogleLanguageAPI> mockedStatic = mockStatic(GoogleLanguageAPI.class, CALLS_REAL_METHODS)) {
            // Simulo il metodo callAPI protetto
            mockedStatic.when(() -> GoogleLanguageAPI.callAPI(TEST_SENTENCE, TEST_API_KEY))
                    .thenReturn(MOCK_API_RESPONSE);

            // Richiamo il metodo da testare
            GoogleLanguageAPI.languageApi(TEST_SENTENCE, TEST_API_KEY);

            // Verifico i risultati
            assertEquals(2, GoogleLanguageAPI.getNouns().size(), "Dovrebbe avere 2 sostantivi (incluso il pronome)");
            assertEquals(0, GoogleLanguageAPI.getVerbs().size(), "Non dovrebbe avere verbi regolari");
            assertEquals(1, GoogleLanguageAPI.getVerbs_thirdperson().size(), "Dovrebbe avere 1 verbo in terza persona");
            assertEquals(0, GoogleLanguageAPI.getVerbs_past().size(), "Non dovrebbe avere verbi al passato");
            assertEquals(1, GoogleLanguageAPI.getAdj().size(), "Dovrebbe avere 1 aggettivo");

            // Verifico le parole specifiche
            assertTrue(GoogleLanguageAPI.getNouns().stream()
                    .anyMatch(noun -> noun.getMyNoun().equals("cat")), "Dovrebbe contenere 'cat' come sostantivo");
            assertTrue(GoogleLanguageAPI.getVerbs_thirdperson().stream()
                    .anyMatch(verb -> verb.getMyVerb().equals("runs")), "Dovrebbe contenere 'runs' come verbo in terza persona");
            assertTrue(GoogleLanguageAPI.getAdj().stream()
                    .anyMatch(adj -> adj.getAdj().equals("fast")), "Dovrebbe contenere 'fast' come aggettivo");
        }
    }

    @Test
    void testSemanticTree() throws Exception {
        try (MockedStatic<GoogleLanguageAPI> mockedStatic = mockStatic(GoogleLanguageAPI.class, CALLS_REAL_METHODS)) {
            // Simulo il metodo callAPI protetto
            mockedStatic.when(() -> GoogleLanguageAPI.callAPI(TEST_SENTENCE, TEST_API_KEY))
                    .thenReturn(MOCK_API_RESPONSE);

            // Richiamo il metodo da testare
            String result = GoogleLanguageAPI.semanticTree(TEST_SENTENCE, TEST_API_KEY);

            // Verifico che il risultato contenga il formato previsto
            String expectedOutput = """
                    The | PRON
                    cat | NOUN
                    runs | VERB
                    fast | ADJ
                    """.trim();

            assertEquals(expectedOutput, result.trim(), "L'output dell'albero semantico deve corrispondere al formato previsto");
        }
    }
}