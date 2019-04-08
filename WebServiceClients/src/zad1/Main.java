
package zad1;

import java.util.Currency;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import org.json.*;
public class Main {
  public static void main(String[] args) {
    Service s = new Service("Spain");
    String weatherJson = s.getWeather("Warsaw");
    Double rate1 = s.getRateFor("USD");
    Double rate2 = s.getNBPRate();
    // ...
    // część uruchamiająca GUI
    
    
    
    System.out.println(weatherJson);
    System.out.println(rate1);
    System.out.println(rate2);
//    JSONObject jsonWeather = new JSONObject(weatherJson);
//    String countryCode = jsonWeather.getJSONObject("sys").getString("country");
//    System.out.println(countryCode);
//    Locale locale = new Locale(countryCode);
//    System.out.println(locale);
////    Currency currentCurrency = Currency.getInstance(locale);
////    System.out.println(locale.getDisplayName());
////    System.out.println(currentCurrency.getDisplayName());
//    Currency currency = Currency.getInstance(Locale.POLAND);
//    System.out.println(currency);
////    Set keys = jsonWeather.keySet();
//    Iterator iter = keys.iterator();
//    while(iter.hasNext()) {
//    	System.out.println(iter.next());
//    }
    
    //Set keys = weatherJson.key
  }
}
