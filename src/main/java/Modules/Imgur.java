package Modules;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Imgur {
    
    public static String getImgurContent(String q) throws Exception {
        String qurey = q;
        qurey = qurey.replaceAll(" ", "+");
        URL url;
        url = new URL("https://api.imgur.com/3/gallery/search/hot/all/0/?q=" + qurey);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Client-ID " + "Imgur api key");

        conn.connect();
        StringBuilder stb = new StringBuilder();

        // Get the response
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            stb.append(line).append("\n");
        }
        rd.close();
        String split = stb.toString();
        split = split.replaceAll("[^A-Za-z0-9 ]", " ");
        String ID;
        String baseURL = "http://i.imgur.com/";
        List<String> items = Arrays.asList(split.split("\\s+"));
        ID = items.get(items.indexOf("id") + 1);
        String adrs = baseURL + ID;
        if(adrs.equalsIgnoreCase(baseURL)||ID==null){
            return "No image found";
        }
        return adrs;
    }
}
