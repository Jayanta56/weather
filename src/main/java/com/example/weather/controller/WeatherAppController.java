package com.example.weather.controller;

import com.example.weather.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.example.weather.model.Location;

import javax.validation.Valid;

@RestController
@RequestMapping ("v1/weather")
public class CustomRestController {

    public final static String apiKey = "155337068a0ce199f5897448461fcdc6" ;

    @Autowired
    LocationRepository locationRepository ;

    public String processAPI(String cityName) {
        RestTemplate restTemplate = new RestTemplate();
        String apiURL = "http://api.openweathermap.org/data/2.5/weather?";

        // String city = "Kharagpur" ;
        String city = cityName ;

        System.out.println("\n\n... Calling the external API for city \"" + city + "\"");

        String finalURL = apiURL + "q=" + city + "&AppID=" + apiKey;

        ResponseEntity<String> response =  restTemplate.getForEntity(finalURL, String.class) ;

        System.out.println("\n URL :: " + finalURL + "\n") ;

        return response.toString() ;
    }

    /* public static void main (String[] args) {
        String response = processAPI() ;
        System.out.println("Response = " + response);
    }*/


    @PostMapping()
    public Location createLocation(@Valid @RequestBody Location location) {
        System.out.println("Creates Repo in Custom-Rest-Controller.");
        return locationRepository.save(location) ;
    }

    public Location makeLocationFromCity (String cityName) {
        Location location = new Location() ;
        location.setCityName(cityName);
        return location ;
    }

    @GetMapping("/city/{name}")
    public String getCities(@PathVariable (value = "name") String cityName) {
        System.out.println("... Consumes the API response ... for City ... " + cityName);
        String response = processAPI(cityName) ;
        Location location =  createLocation(makeLocationFromCity(cityName)) ;
        System.out.println("\nResponse = " + response);
        return response;
    }
}
