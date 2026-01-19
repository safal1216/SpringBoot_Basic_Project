package com.example.newproject.service;

import com.example.newproject.api.response.WeatherResponse;
import com.example.newproject.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiToken;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String city){
        String finalApi = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace("<apiKey>",apiToken).replace("<city>",city);
        ResponseEntity<WeatherResponse> responseEntity = restTemplate.exchange(finalApi, HttpMethod.GET,null, WeatherResponse.class);
        return responseEntity.getBody();
    }


}
