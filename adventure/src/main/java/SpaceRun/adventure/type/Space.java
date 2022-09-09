package SpaceRun.adventure.type;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 *
 * @author Gaetano Schiralli, Dafne Spaccavento
 */
public class Space {
    public static final String API_KEY = "c9e2d939ef5436338fa344595a9b645a";
    public static final String LINGUA = "it";
    
    /**
     * getWeatherByCity() ti permette di vedere le condizioni meteo di una determinata città.
     * L'API offerta da api.openweathermap.org restituisce un tipo Json contenente tutte le info satellitari riguardo la città
     * da cui però si filtra solo la condizione meteo, tradotta in italiano.
     * @param city
     * @return temp
     */
    public static String getWeatherByCity(String city) {
        String temp = "";
        try{
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target("https://api.openweathermap.org/data/2.5");
            Response resp = target.path("weather")
                    .queryParam("appid", API_KEY)
                    .queryParam("q", city)
                    .queryParam("lang", LINGUA)
                    .request(MediaType.APPLICATION_JSON).get();
            String jsonString = resp.readEntity(String.class); //risposta dell'API
            Gson gson = new Gson(); //open-source Java library
            JsonObject obj= gson.fromJson(jsonString, JsonObject.class);
            JsonArray weatherJsonArray = obj.get("weather").getAsJsonArray();

            JsonObject weatherJsonObject = weatherJsonArray.get(0).getAsJsonObject();
            temp = weatherJsonObject.get("description").getAsString();
            
        }catch(Exception ex){
            temp = "Impossibile vedere il meteo per questa città";
        }     
    return temp;   
    }
    
    /**
     * getPersonInSpace() ti permette di vedere il numero degli umani in orbita terrestre 
     * aggiornato al momento della richiesta.
     * @return num_person
     */
    public static String getPersonInSpace() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://api.open-notify.org");
        Response resp = target.path("astros")
                .request(MediaType.APPLICATION_JSON).get();
                    String jsonString = resp.readEntity(String.class); //risposta dell'API
                    Gson gson = new Gson(); //open-source Java library
                    JsonObject obj= gson.fromJson(jsonString, JsonObject.class);
                    String num_person = obj.get("number").getAsString();
        return num_person;
    }
}