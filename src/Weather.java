// Weather.java
// Landon Holland - 1/25/20
// Weather API class for CincyHacks 2020

// hella imports
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class Weather {
    // Private data
    private String apiKey; // dc73c7c1d5b77d6a5e247211f2cc7e1d
    private int zipCode; // 45040

    // Default constructor
    Weather(String apiKey, int zipCode) {
        this.apiKey = apiKey;
        this.zipCode = zipCode;
    }

    // Sets and gets
    public String getApiKey() {
        return apiKey;
    }
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public int getZipCode() {
        return zipCode;
    }
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    // toString
    @Override
    public String toString() {
        return apiKey;
    }

    // All the methods to pull data from the api

    // this is for the loading of the apis
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    // i might've stolen this straight from stackoverflow...
    public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    // my code now lol
    public int findCurrentDescription() throws IOException {
        JSONObject weatherJSON = readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?zip=" + zipCode + ",us&APPID=" + apiKey);

        String weather = weatherJSON.getJSONArray("weather").getJSONObject(0).getString("main");

        // big if test for conversion
        if (weather.equals("Clear")) {
            return 0;
        }
        else if (weather.equals("Clouds")) {
            return 1;
        }
        else if (weather.equals("Drizzle") || weather.equals("Rain")) {
            return 2;
        }
        else if (weather.equals("Thunderstorm")) {
            return 3;
        }
        else if (weather.equals("Snow")) {
            return 4;
        }
        else if (weather.equals("Tornado")) {
            return 5;
        }
        else {
            return 6; // this covers all of the general "Atmosphere" conditions: fog, haze, etc.
        }
    }

    // temp is in kelvin i think
    // yeah it is, it is now outputting fahrenheit
    public int findCurrentTemperature() throws IOException {
        JSONObject weatherJSON = readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?zip=" + zipCode + ",us&APPID=" + apiKey);

        return (int) ((weatherJSON.getJSONObject("main").getDouble("temp") - 273.15) * (9/5) + 32);
    }

    // wind speed is in m/s so we change it to mph
    public int findCurrentWindSpeed() throws IOException {
        JSONObject weatherJSON = readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?zip=" + zipCode + ",us&APPID=" + apiKey);

        return (int) (weatherJSON.getJSONObject("wind").getDouble("speed") * 2.237);
    }

    // we're also going to return the city name for the story
    public String findCurrentCityName() throws IOException {
        JSONObject weatherJSON = readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?zip=" + zipCode + ",us&APPID=" + apiKey);

        return weatherJSON.getString("name");
    }
}
