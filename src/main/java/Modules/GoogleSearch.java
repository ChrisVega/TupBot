package Modules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GoogleSearch {
    
    private static String key="";
    
    public static String search(String qry) throws MalformedURLException, IOException{
    String link = "";
    URL url = new URL(
            "https://www.googleapis.com/customsearch/v1?key="+key+ "&cx=013036536707430787589:_pqjad5hr1a&q="+ qry + "&alt=json");
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Accept", "application/json");
    BufferedReader br = new BufferedReader(new InputStreamReader(
            (conn.getInputStream())));

    String output;
    System.out.println("Output from Server .... \n");
    while ((output = br.readLine()) != null) {

        if(output.contains("\"link\": \"")){                
            link=output.substring(output.indexOf("\"link\": \"")+("\"link\": \"").length(), output.indexOf("\","));
        }     
    }
    conn.disconnect();  
    return link;
    }
}
