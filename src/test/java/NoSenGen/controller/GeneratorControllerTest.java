package NoSenGen.controller;

import NoSenGen.generator.Generator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class GeneratorControllerTest {

    @Mock
    private Generator generator;

    @Mock
    private Model model;

    private GeneratorController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new GeneratorController(generator);
    }

    @Test
    void showHomePage_ShouldAddMaxSentencesAttributeAndReturnIndexView() {
        // When
        String viewName = controller.showHomePage(model);

        // Then
        verify(model).addAttribute("maxSentences", 20);
        assertEquals("index", viewName);
    }

    @Test
    void generateSentences_ShouldThrowException_WhenApiKeyIsMissing() {
        // When/Then
        Exception exception = assertThrows(RuntimeException.class, () ->
                controller.generateSentences("test", 1, 1, 1, 0, 0)
        );
        assertTrue(exception.getMessage().contains("Api Key is missing"));
    }

    @Test
    void generateSentences_ShouldThrowException_WhenTotalSentencesExceedsMax() {
        // Given
        controller.apiKeyfunction("valid-key");

        // When/Then
        Exception exception = assertThrows(RuntimeException.class, () ->
                controller.generateSentences("test", 1, 21, 7, 7, 7)
        );
        assertTrue(exception.getMessage().contains("Maximum 20 sentences allowed"));
    }

    @Test
    void generateSentences_ShouldThrowException_WhenSumDoesNotMatchTotal() {
        // Given
        controller.apiKeyfunction("valid-key");

        // When/Then
        Exception exception = assertThrows(RuntimeException.class, () ->
                controller.generateSentences("test", 1, 10, 3, 3, 3)
        );
        assertTrue(exception.getMessage().contains("Sum of tense-specific sentences must equal total sentences"));
    }

    @Test
    void semanticTree_ShouldReturnError_WhenApiKeyIsMissing() {
        // When
        ResponseEntity<Map<String, String>> response = controller.semanticTree("test sentence");

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().containsKey("[Error]:"));
        assertEquals("No API key provided", response.getBody().get("[Error]:"));
    }

    @Test
    void apiKeyfunction_ShouldReturnBadRequest_WhenApiKeyIsBlank() {
        // When
        ResponseEntity<String> response = controller.apiKeyfunction("");

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("API key non valida", response.getBody());
    }

    @Test
    void apiKeyfunction_ShouldSaveApiKey_WhenValidKeyProvided() {
        // When
        ResponseEntity<String> response = controller.apiKeyfunction("valid-api-key");

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("API key saved successfully", response.getBody());
    }

    @Test
    void generateSentences_ShouldGenerateCorrectNumberOfSentences_WhenValidInput() throws IOException {
        // Given
        controller.apiKeyfunction("valid-key");
        when(generator.genSentence(anyString(), anyInt(), anyString()))
                .thenReturn("Generated sentence");

        // When
        GeneratorController.GeneratorResponse response =
                controller.generateSentences("test", 1, 3, 1, 1, 1);

        // Then
        assertEquals(1, response.getPastSentences().size());
        assertEquals(1, response.getPresentSentences().size());
        assertEquals(1, response.getFutureSentences().size());
        verify(generator, times(3)).genSentence(anyString(), anyInt(), anyString());
    }
}