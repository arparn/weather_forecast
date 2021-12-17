package iot.weather.Service;

import iot.weather.Requests.WeatherRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class WeatherService {

    private static final WeatherRequest weatherRequest = new WeatherRequest();

    public String getWeather() throws IOException {
        return weatherRequest.getWeatherFromApi();
    }

}
