package utils.json;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class JSONUtils {

    public static JSONObject getJSONObjetcFromUrl(String url) {
        JSONObject json = new JSONObject();
        try {
            InputStream inputStream = new URL(url).openStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder();
            int cp;
            while((cp = buffer.read()) != -1)
                builder.append((char)cp);
            json = new JSONObject(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
