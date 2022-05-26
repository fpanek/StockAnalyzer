package stockanalyzer.downloader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

public class ParallelDownloader extends Downloader{

    public int process(List<String> tickers) {
        List<Future<String>> futures = new ArrayList<>();
        int counter = 0;
        //TODO mmhh question? interestingly also a fixed threadpool with 1 thread was faster then the serial downloader -> why?..
        ExecutorService executor = Executors.newCachedThreadPool();
        for (String ticker : tickers) {
            futures.add(executor.submit(() -> saveJson2File(ticker)));
        }

        try{
            for (Future f : futures) {
                f.get();
                counter ++;
            }


        }catch (Exception e){
            e.printStackTrace();
        }



        try{
            executor.shutdown();
            if(!executor.awaitTermination(3, TimeUnit.SECONDS)){
                executor.shutdownNow();
            }

        }catch (InterruptedException e){
            e.printStackTrace();
            executor.shutdownNow();
        }

        return counter;
    }
}
