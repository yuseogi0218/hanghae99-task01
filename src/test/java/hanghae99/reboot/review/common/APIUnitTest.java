package hanghae99.reboot.review.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hanghae99.reboot.review.common.exception.ErrorCode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
public class APIUnitTest {

    protected MockMvc mvc;

    protected ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public MockMvc buildMockMvc(WebApplicationContext context) {
        return MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    public void assertError(ErrorCode expected, ResultActions actual) throws Exception {
        actual
                .andExpect(status().is(expected.getHttpStatus().value()))
                .andExpect(jsonPath("errorCode").value(expected.getCode()))
                .andExpect(jsonPath("message").value(expected.getMessage()));
    }

    public void assertErrorWithMessage(ErrorCode expected, ResultActions actual, String message) throws Exception {
        actual
                .andExpect(status().is(expected.getHttpStatus().value()))
                .andExpect(jsonPath("errorCode").value(expected.getCode()))
                .andExpect(jsonPath("message").value(String.format(expected.getMessage(), message)));
    }
}
