package hello.stockpriceanalyzer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Stock implements StockSource {

    private final Map<Long, Double> stockInfo;
    private final String stockSymbol;

    public Stock(String stockSymbol) {
        this.stockSymbol = stockSymbol;
        this.stockInfo = new HashMap<>();
    }

    @Override
    public Map<Long, Double> provideStockInformation() throws IOException, ParseException {
        HttpURLConnection connection = getHttpURLConnection(createUrl());

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        String result = br.readLine();

        JSONObject wholeJsonObject = parseJson(result);
        loadInfoToMap(wholeJsonObject);

        return this.stockInfo;
    }

    private URL createUrl() throws MalformedURLException {
        String ulrStr = "https://yfapi.net/v8/finance/spark?interval=1d&range=6mo&symbols=" + this.stockSymbol;
        return new URL(ulrStr);
    }

    private HttpURLConnection getHttpURLConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("x-api-key", "irh3LNcQYs7i1GkOvXSSZ82aS4dk36ga6Nb2EMVw");
        connection.setRequestMethod("GET");
        return connection;
    }

    private JSONObject parseJson(String result) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject obj = (JSONObject) jsonParser.parse(result);
        return (JSONObject) obj.get(this.stockSymbol);
    }

    private void loadInfoToMap(JSONObject jsonObject) throws ParseException {
        JSONArray timestamp = (JSONArray) jsonObject.get("timestamp");
        JSONArray price = (JSONArray) jsonObject.get("close");
        for (int i = 0; i < timestamp.size(); ++i) {
            this.stockInfo.put((Long) timestamp.get(i), (Double) price.get(i));
        }
    }

}
