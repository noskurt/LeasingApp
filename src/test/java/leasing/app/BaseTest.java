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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@SpringBootTest
@AutoConfigureMockMvc
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

    protected <T> List<T> andReturnResultList(MvcResult result, Class<T> elementType) throws Exception {
        String json = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, elementType);
        return objectMapper.readValue(json, listType);
    }

}
