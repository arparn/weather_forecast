package iot.weather.Requests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;

public class WeatherRequest {

    public String getWeatherFromApi() throws IOException {

        String url = "http://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        XmlMapper xmlMapper = new XmlMapper();
        JsonNode node = xmlMapper.readTree(client.newCall(request).execute().body().bytes());
        ObjectMapper jsonMapper = new ObjectMapper();
        String json = jsonMapper.writeValueAsString(node);

        return json;
    }

}
