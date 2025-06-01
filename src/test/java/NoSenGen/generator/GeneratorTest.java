
package NoSenGen.generator;

import NoSenGen.api.GoogleLanguageAPI;
import NoSenGen.api.GoogleToxicityAPI;
import NoSenGen.template.Template;
import NoSenGen.template.TemplatesLibrary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GeneratorTest {

    private Generator generator;
    private static final String TEST_API_KEY = "test-api-key";
    private static final String TEST_INPUT = "The cat jumps";
    private static final String EXPECTED_OUTPUT = "The happy cat jumps quickly";
    private static final int PRESENT_TENSE = 1;

    @BeforeEach
    void setUp() {
        generator = new Generator();
    }

    @Test
    void genSentence_ShouldReturnValidSentence() throws Exception {
        // Arrange
        Template mockTemplate = mock(Template.class);
        when(mockTemplate.FillTemplate(any(), any(), any(), any()))
                .thenReturn(EXPECTED_OUTPUT);

        try (MockedStatic<GoogleLanguageAPI> mockedLanguageAPI = mockStatic(GoogleLanguageAPI.class);
             MockedStatic<GoogleToxicityAPI> mockedToxicityAPI = mockStatic(GoogleToxicityAPI.class);
             MockedStatic<TemplatesLibrary> mockedTemplateLib = mockStatic(TemplatesLibrary.class)) {

            // Mock all necessary static methods
            mockedTemplateLib.when(TemplatesLibrary::randomTemplatePicker).thenReturn(mockTemplate);
            mockedToxicityAPI.when(() -> GoogleToxicityAPI.isToxicityAcceptable(anyString(), eq(TEST_API_KEY)))
                    .thenReturn(true);

            // Act
            String result = generator.genSentence(TEST_INPUT, PRESENT_TENSE, TEST_API_KEY);

            // Assert
            assertNotNull(result, "Generated sentence should not be null");
            assertEquals(EXPECTED_OUTPUT, result, "Generated sentence should match expected output");
        }
    }
}