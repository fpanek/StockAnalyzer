package stockanalyzer.ctrl;

import stockanalyzer.jsonDownloader.Json2File;
import stockanalyzer.ui.YahooAPIException;
import yahooApi.YahooFinance;
import yahooApi.beans.QuoteResponse;
import yahooApi.beans.Result;
import yahooApi.beans.YahooResponse;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;


public class Controller {
		
	public void process(String ticker) throws YahooAPIException {
		System.out.println("Start process");

		DecimalFormat numberFormat = new DecimalFormat("#.00");

		YahooResponse myResponse;
		myResponse = getData(ticker);
		QuoteResponse quotes = myResponse.getQuoteResponse();
		try {
			quotes.getResult().stream().forEach(quote -> System.out.println("Share: " + quote.getLongName() +
					" Regular Market Price " + numberFormat.format(quote.getRegularMarketPrice())));
			System.out.println("Average Regular Market Price: " + numberFormat.format(GetAverageMaretprice(quotes)));
			System.out.println("Highest Regular Market Price: " + numberFormat.format(GetDataMax(quotes)));
			System.out.println("Amount of shares: " + GetAmountOfShares(quotes));
		}catch (NoSuchElementException e){
			throw new YahooAPIException("Element not found: " + ticker);
		}
	}

	public YahooResponse getData(String searchString) throws YahooAPIException {
		YahooFinance yahooFinance = new YahooFinance();
		List<String> tickers = Arrays.asList(searchString);
		YahooResponse response = yahooFinance.getCurrentData(tickers);
		return response;
	}



	public double GetDataMax(QuoteResponse quotes)  {
		double maxValue;
		maxValue = quotes.getResult().stream().map(Result::getRegularMarketPrice).max(Double::compare).get();
		//adfasdff----------------
		Json2File
		return maxValue;
	}

	public double GetAverageMaretprice(QuoteResponse quotes){
		double averageMarketPrice = 100;
		//averageMarketPrice = quotes.getResult().stream().collect(Collectors.groupingBy(Result::getRegularMarketPrice, Result::getQu));
		//averageMarketPrice =
		//ystem.out.println("Average Markte Price: " + quotes.getResult().stream().mapToDouble(Result::getRegularMarketPrice).average());
		averageMarketPrice = quotes.getResult().stream().mapToDouble(Result::getRegularMarketPrice).average().getAsDouble();
		return averageMarketPrice;
	}

	public int GetAmountOfShares(QuoteResponse quotes){
		int amountOfShares;
		amountOfShares = (int) quotes.getResult().stream().count();
		return amountOfShares;
	}

	public void closeConnection() {
		
	}
}
