package hello.stockpriceanalyzer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class YahooFinance implements StockSourceAPI {

    @Value("${yahoo.api.url.prefix}")
    private String urlPrefix;
    @Value("${yahoo.api.key}")
    private String apiKey;

    public YahooFinance() {}

    @Override
    public Stock provideStockInformation(String stockSymbol) throws IOException, ParseException {
        Map<Long, Double> stockInfo = new HashMap<>();

        HttpURLConnection connection = getHttpURLConnection(createUrl(stockSymbol));

        String stockData = loadStringData(connection);

        JSONObject wholeJsonObject = parseJson(stockSymbol, stockData);

        return new Stock(stockSymbol, loadInfoToMap(wholeJsonObject));
    }

    private URL createUrl(String stockSymbol) throws MalformedURLException {
        String ulrStr = urlPrefix + stockSymbol;
        return new URL(ulrStr);
    }

    private HttpURLConnection getHttpURLConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("x-api-key", apiKey);
        connection.setRequestMethod("GET");
        return connection;
    }

    private String loadStringData(HttpURLConnection connection) throws IOException {
        InputStreamReader streamReader = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(streamReader);
        return br.readLine();
    }

    private JSONObject parseJson(String stockSymbol, String stringData) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject obj = (JSONObject) jsonParser.parse(stringData);
        return (JSONObject) obj.get(stockSymbol);
    }

    private Map<Long, Double> loadInfoToMap(JSONObject jsonObject) throws ParseException {
        Map<Long, Double> stockInfo = new HashMap<>();

        JSONArray timestamp = (JSONArray) jsonObject.get("timestamp");
        JSONArray price = (JSONArray) jsonObject.get("close");
        for (int i = 0; i < timestamp.size(); ++i) {
            stockInfo.put((Long) timestamp.get(i), (Double) price.get(i));
        }
        return stockInfo;
    }

}
