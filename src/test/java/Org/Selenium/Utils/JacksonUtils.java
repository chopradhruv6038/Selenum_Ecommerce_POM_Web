package Org.Selenium.Utils;

import Org.Selenium.Objects.RegistrationAndLoginDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class JacksonUtils {

    public static RegistrationAndLoginDetails deserializeJsonForRegistration(InputStream is, RegistrationAndLoginDetails registrationAndLoginDetails) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(is, registrationAndLoginDetails.getClass());

    }
}
