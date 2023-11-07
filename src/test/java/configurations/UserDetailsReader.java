package configurations;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class UserDetailsReader {
    public User getUser() throws IOException, ParseException {

        ObjectMapper objectMapper = new ObjectMapper();
        JSONParser parser = new JSONParser();

        String jSonString = FileUtils.readFileToString(new File("src/test/resources/RegistrationTestData.json"), StandardCharsets.UTF_8);
        Object obj = parser.parse(jSonString);
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray UserJsonArray = (JSONArray) jsonObject.get("rows");
        User user = objectMapper.readValue(UserJsonArray.get(1).toString(), User.class);
        return user;
    }

}
