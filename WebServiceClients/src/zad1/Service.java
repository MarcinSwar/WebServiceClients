package zad1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

public class Service {
	
	String country;
	//String city;
	
	
	Map<String, String> currencySymbols = new HashMap<String, String>() {
		{
			put("Poland", "PLN");
			put("USA", "USD");
			put("Australia", "AUD");
			put("Canada", "CAD");
			put("Germany", "EUR");
			put("France", "EUR");
			put("Spain", "EUR");
			put("Russia", "RUB");
			put("Sweden", "SEK");
		}
	};
	
	
	

	public Service(String string) {
		country = string;
		//getCurrencyOfTheCountry(country);
	}
	
//	private static String getCurrencyOfTheCountry(String country) {
//		
//		Locale locale = Locale.getDefault();
//				//new Locale(country);
//		System.out.println(locale);
//		Currency currency = Currency.getInstance(locale);
//		System.out.println(currency);
//		return country;
//	}

	public String getWeather(String city) {
		final String APPID = "d8764cfd9e372e9a6df27281b033684e";
		String preparedURL = "http://api.openweathermap.org/data/2.5/weather?q=" + city
			+ ",country=country&mode=json&appid="+APPID;
		String inputLine = "";
		String weatherJSON = "";
		
		try {
			URL openWeather = new URL(preparedURL);
			URLConnection urlconnection = openWeather.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
			while((inputLine = in.readLine()) != null)
				weatherJSON = inputLine;
			in.close();
				
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return weatherJSON;
	}

	public Double getRateFor(String currency) {
		String preparedURL = "https://api.exchangeratesapi.io/latest?base="+currencySymbols.get(country);
		String inputLine="";
		String currencyRateJSON="";
		try {
			URL exchangeRatesApi = new URL(preparedURL);
			URLConnection urlconnection = exchangeRatesApi.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
			while((inputLine = in.readLine()) != null)
				currencyRateJSON = inputLine;
			in.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject jsonCurrency = new JSONObject(currencyRateJSON);
		Double currencyRate = jsonCurrency.getJSONObject("rates").getDouble(currency);
		
		return currencyRate;
	}

	public Double getNBPRate() {
		
		String preparedURL = "http://api.nbp.pl/api/exchangerates/rates/a/" + currencySymbols.get(country) + "/";
		String inputLine = "";
		String nbpRateJson="";
		Double nbpRate=null;
		
		if (currencySymbols.get(country).equals("PLN"))
		{
			return 1.0;
		} else
		{
			
			try {
				URL nbpApi = new URL(preparedURL);
				URLConnection urlconnection = nbpApi.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
				while((inputLine = in.readLine()) != null)
					nbpRateJson = inputLine;
				in.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Pattern pattern = Pattern.compile("[\\d]+[.][\\d]+");
			Matcher matcher = pattern.matcher(nbpRateJson); 

			while (matcher.find())
				nbpRate = Double.parseDouble(matcher.group()); 
			return nbpRate;
			
//			JSONObject jsonNbpRate = new JSONObject(nbpRateJson);
//			JSONObject jsonRates = jsonNbpRate.getJSONObject("rates");
//			//Double nbpRate = jsonNbpRate.getJSONObject("rates").getDouble("mid");
			
		}
	}
}  
