package org.example.weatherforecast.util;

public class WeatherConstants {
    public static final long WEATHER_CACHE_TIMEOUT_IN_HOURS = 1;
    public static final long LOCATION_CACHE_TIMEOUT_IN_HOURS = 2;
    public static final String LOCATION_CACHE_NAME = "location";
    public static final String WEATHER_CACHE_NAME = "weather";
    public static final String FAILED_TO_IDENTIFY_LOCATION = "FAILED TO IDENTIFY THE GEOLOCATION";
    public static final String FAILED_TO_IDENTIFY_WEATHER = "FAILED TO GET THE WEATHER DATA";
    public static final String SOMETHING_WENT_WRONG = "Internal server error";
    public static final String LOCATION_RETRY = "locationRetry";
    public static final String WEATHER_RETRY = "weatherRetry";
    public static final String WEATHER_CACHE_KEY = "#obj.getId()";
}
