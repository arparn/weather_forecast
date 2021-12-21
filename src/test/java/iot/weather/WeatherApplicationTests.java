package iot.weather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@AutoConfigureMockMvc
@SpringBootTest
class WeatherApplicationTests {

    @Autowired
    MockMvc mockTest;

    @Test
    void testGetWeather() throws Exception {
        String url = "http://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        XmlMapper xmlMapper = new XmlMapper();
        JsonNode node = xmlMapper.readTree(client.newCall(request).execute().body().bytes());
        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonTest = jsonMapper.writeValueAsString(node);

        mockTest.perform(MockMvcRequestBuilders.get("/weather").contentType(TEXT_PLAIN)
                .accept(TEXT_PLAIN)).andExpect(content().string(jsonTest));
    }

}
