package NoSenGen.generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {

    private Generator generator;
    private static final String TEST_API_KEY = "test-api-key";
    private static final String TEST_INPUT = "The cat jumps";
    private static final int PRESENT_TENSE = 1;

    @BeforeEach
    void setUp() {
        generator = new Generator();
    }

    @Test
    void genSentence_PresentTenseValidInput_ReturnsNonEmptyString() throws Exception {
        // Arrange
        // The generator is already initialized with firstSentence = true

        // Act
        String result = generator.genSentence(TEST_INPUT, PRESENT_TENSE, TEST_API_KEY);

        // Assert
        assertNotNull(result, "Generated sentence should not be null");
        assertFalse(result.isEmpty(), "Generated sentence should not be empty");
        assertFalse(result.startsWith("[Error]"), "Generated sentence should not contain error message");
        assertFalse(result.startsWith("Toxic Phrase"), "Generated sentence should not be toxic");
    }
}