package iot.weather.Service;

import iot.weather.Requests.WeatherRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class WeatherService {

    private static final WeatherRequest weatherRequest = new WeatherRequest();

    public String getWeather() throws IOException {
        return weatherRequest.getWeatherFromApi();
    }

    public String getMinAndMaxTemperatureById(Integer id) throws IOException {

        String tempMax;
        String tempMin;

        String jsonString = weatherRequest.getWeatherFromApi();
        JSONObject json = new JSONObject(jsonString);
        JSONArray forecasts = (JSONArray) json.get("forecast");
        JSONObject forecast = (JSONObject) forecasts.get(id);
        JSONObject day = (JSONObject) forecast.get("day");
        JSONObject night = (JSONObject) forecast.get("night");
        String date = forecast.getString("date");
        date = date.replaceAll("-", ".");

        if (Integer.parseInt((String) day.get("tempmax")) >= Integer.parseInt((String) night.get("tempmax"))) {
            tempMax = day.getString("tempmax");
        } else {
            tempMax = night.getString("tempmax");
        }
        if (Integer.parseInt((String) night.get("tempmin")) <= Integer.parseInt((String) day.get("tempmin"))) {
            tempMin = night.getString("tempmin");
        } else {
            tempMin = day.getString("tempmin");
        }

        JSONObject minMaxTemp = new JSONObject();
        minMaxTemp.put("date", date);
        minMaxTemp.put("tempmax", tempMax);
        minMaxTemp.put("tempmin", tempMin);

        return minMaxTemp.toString();
    }

}
