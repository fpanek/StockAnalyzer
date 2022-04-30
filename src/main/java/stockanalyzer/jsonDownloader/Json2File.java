package stockanalyzer.jsonDownloader;

import yahooApi.beans.QuoteResponse;
import yahooApi.beans.YahooResponse;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class Json2File {

    public static final String download_directory = "./Downloads/";

    public void saveJson2File(YahooResponse yahooResponse){
        BufferedWriter writer;
        try {
            QuoteResponse quotes = yahooResponse.getQuoteResponse();
            //String filename = quotes.getResult().stream().forEach(quote -> System.out.println("Share: " + quote.getLongName());
            String fileName = download_directory + "export.json";

            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(quotes);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(writer).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
