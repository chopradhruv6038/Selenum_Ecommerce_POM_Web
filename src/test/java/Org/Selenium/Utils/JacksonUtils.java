package Org.Selenium.Utils;

import Org.Selenium.Objects.RegistrationDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class JacksonUtils {

    public static RegistrationDetails deserializeJsonForRegistration(InputStream is, RegistrationDetails registrationDetails) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(is, registrationDetails.getClass());

    }
}
