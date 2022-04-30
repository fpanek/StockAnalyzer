package stockanalyzer.jsonDownloader;


import java.io.BufferedWriter;
import java.io.*;
import java.io.IOException;

public class Json2File {

    public static final String download_directory = "./";

    public static void saveJson2File(String json){

        try {
            BufferedWriter writer;
            //String filename = quotes.getResult().stream().forEach(quote -> System.out.println("Share: " + quote.getLongName());
            String fileName = download_directory + "export.json";

            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(json.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
