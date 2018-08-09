package com.example.weather.service;

import java.util.List;

public interface WeatherService {
    public String getCityWeather() ;
    public List<String> getMostlySearchedCities() ;
    public List<String> getLeastSearchedCities() ;
    public List<String> getRecentFiveCities() ;
}
