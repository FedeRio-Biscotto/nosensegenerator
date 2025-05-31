
package NoSenGen.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


class GoogleToxicityAPITest {

    private static final String TEST_API_KEY = "test-api-key";
    private HttpClient mockHttpClient;
    private HttpResponse<String> mockResponse;

    @BeforeEach
    void setUp() {
        mockHttpClient = Mockito.mock(HttpClient.class);
        mockResponse = Mockito.mock(HttpResponse.class);
    }


    @Test
    void testIsToxicityAcceptable() throws IOException, InterruptedException {
        // Prepare mock response for acceptable content
        String acceptableJsonResponse = """
            {
                "moderationCategories": [
                    {
                        "name": "HARM_CATEGORY_TOXICITY",
                        "confidence": 0.1
                    }
                ]
            }
            """;

        try (MockedStatic<HttpClient> httpClientMock = Mockito.mockStatic(HttpClient.class)) {
            httpClientMock.when(HttpClient::newHttpClient).thenReturn(mockHttpClient);
            Mockito.when(mockResponse.statusCode()).thenReturn(200);
            Mockito.when(mockResponse.body()).thenReturn(acceptableJsonResponse);

            boolean result = GoogleToxicityAPI.isToxicityAcceptable("Hello world", TEST_API_KEY);
            assertTrue(result, "Content should be acceptable");
        }catch (Exception e){

        }

    }
}