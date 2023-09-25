package leasing.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import leasing.app.contract.ContractRepository;
import leasing.app.customer.CustomerRepository;
import leasing.app.vehicle.VehicleRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BaseTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected DataCreator dataCreator;

    @Autowired
    protected VehicleRepository vehicleRepository;

    @Autowired
    protected CustomerRepository customerRepository;

    @Autowired
    protected ContractRepository contractRepository;

    protected static final Faker faker = new Faker(Locale.ENGLISH);

    @BeforeEach
    public void setup() {
        contractRepository.deleteAll();
        vehicleRepository.deleteAll();
        customerRepository.deleteAll();
    }

    protected <T> T andReturnResult(MvcResult result, Class<T> responseType) throws Exception {
        String json = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        return objectMapper.readValue(json, responseType);
    }

    protected <T> T performAndGetResult(MockMvc mockMvc, Class<T> responseType, ResultMatcher resultMatcher, String url, Object... urlVariables) throws Exception {
        ResultActions resultActions = mockMvc.perform(getRequestBuilder(url, urlVariables)).andExpect(resultMatcher);
        MvcResult mvcResult = resultActions.andReturn();
        return andReturnResult(mvcResult, responseType);
    }

    protected <T> List<T> andReturnResultList(MvcResult result, Class<T> responseType) throws Exception {
        String json = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, responseType);
        return objectMapper.readValue(json, listType);
    }

    protected <T> List<T> performAndGetResultList(MockMvc mockMvc, Class<T> responseType, ResultMatcher resultMatcher, String url, Object... urlVariables) throws Exception {
        ResultActions resultActions = mockMvc.perform(getRequestBuilder(url, urlVariables)).andExpect(resultMatcher);
        MvcResult mvcResult = resultActions.andReturn();
        return andReturnResultList(mvcResult, responseType);
    }

    protected void performAndDelete(MockMvc mockMvc, ResultMatcher resultMatcher, String url, Object... urlVariables) throws Exception {
        mockMvc.perform(deleteRequestBuilder(url, urlVariables))
            .andExpect(resultMatcher);
    }

    protected void performAndPost(MockMvc mockMvc, ResultMatcher resultMatcher, Object requestBody, String url, Object... urlVariables) throws Exception {
        mockMvc.perform(postRequestBuilder(requestBody, url, urlVariables))
            .andExpect(resultMatcher);
    }

    protected void performAndPut(MockMvc mockMvc, ResultMatcher resultMatcher, Object requestBody, String url, Object... urlVariables) throws Exception {
        mockMvc.perform(putRequestBuilder(requestBody, url, urlVariables))
            .andExpect(resultMatcher);
    }

    private MockHttpServletRequestBuilder getRequestBuilder(String url, Object... urlVariables) {
        return MockMvcRequestBuilders.get(url, urlVariables)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON);
    }

    private MockHttpServletRequestBuilder deleteRequestBuilder(String url, Object... urlVariables) {
        return MockMvcRequestBuilders.delete(url, urlVariables)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON);
    }

    private MockHttpServletRequestBuilder postRequestBuilder(Object requestBody, String url, Object... urlVariables) throws Exception {
        return MockMvcRequestBuilders.post(url, urlVariables)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestBody));
    }

    private MockHttpServletRequestBuilder putRequestBuilder(Object requestBody, String url, Object... urlVariables) throws Exception {
        return MockMvcRequestBuilders.put(url, urlVariables)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestBody));
    }

}
