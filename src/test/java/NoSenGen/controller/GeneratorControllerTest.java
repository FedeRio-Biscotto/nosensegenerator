package NoSenGen.controller;

import NoSenGen.generator.Generator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GeneratorControllerTest {

    @Mock
    private Generator generator;

    @Mock
    private Model model;

    @InjectMocks
    private GeneratorController controller;

    @Test
    void showHomePageShouldAddMaxSentencesAttribute() {
        // When
        String viewName = controller.showHomePage(model);

        // Then
        verify(model).addAttribute("maxSentences", 5);
        assertEquals("index", viewName);
    }

    @Test
    void generateSentencesShouldThrowExceptionWhenTotalSentencesExceedsMax() {
        // Given
        String inputSentence = "test";
        int mode = 1;
        int totalSentences = 6;  // Più del massimo consentito (5)

        // When & Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.generateSentences(inputSentence, mode, totalSentences, 2, 2, 2);
        });

        assertTrue(exception.getMessage().contains("Maximum 5 sentences allowed"));
    }

    @Test
    void generateSentencesShouldThrowExceptionWhenTenseSumDoesNotMatchTotal() {
        // Given
        String inputSentence = "test";
        int mode = 1;
        int totalSentences = 3;
        int past = 1;
        int present = 1;
        int future = 2;  // La somma (4) non corrisponde al totale (3)

        // When & Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.generateSentences(inputSentence, mode, totalSentences, past, present, future);
        });

        assertTrue(exception.getMessage().contains("Sum of tense-specific sentences must equal total sentences"));
    }

    @Test
    void generateSentencesShouldGenerateCorrectNumberOfSentencesForEachTense() throws Exception {
        // Given
        String inputSentence = "test";
        int mode = 1;
        int totalSentences = 3;
        int past = 1;
        int present = 1;
        int future = 1;

        when(generator.genSentence(anyString(), eq(0))).thenReturn("Past sentence");
        when(generator.genSentence(anyString(), eq(1))).thenReturn("Present sentence");
        when(generator.genSentence(anyString(), eq(2))).thenReturn("Future sentence");

        // When
        GeneratorController.GeneratorResponse response = controller.generateSentences(
                inputSentence, mode, totalSentences, past, present, future);

        // Then
        assertEquals(1, response.getPastSentences().size());
        assertEquals(1, response.getPresentSentences().size());
        assertEquals(1, response.getFutureSentences().size());

        assertEquals("Past sentence", response.getPastSentences().get(0));
        assertEquals("Present sentence", response.getPresentSentences().get(0));
        assertEquals("Future sentence", response.getFutureSentences().get(0));
    }

    @Test
    void generateSentencesShouldUseEmptyInputSentenceWhenModeIsTwo() throws Exception {
        // Given
        String inputSentence = "test";
        int mode = 2;
        int totalSentences = 1;
        int past = 1;
        int present = 0;
        int future = 0;

        when(generator.genSentence("", 0)).thenReturn("Random past sentence");

        // When
        GeneratorController.GeneratorResponse response = controller.generateSentences(
                inputSentence, mode, totalSentences, past, present, future);

        // Then
        verify(generator).genSentence("", 0);
        assertEquals(1, response.getPastSentences().size());
        assertEquals("Random past sentence", response.getPastSentences().get(0));
    }
}