package leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
public class IntuitFinancialAdvice{
	
	static String tickerSymbol;
 
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
    	try {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	System.out.print("Enter the company code you want to invest in : ");
		tickerSymbol = br.readLine();
		String name = br.readLine();
		readStocks(tickerSymbol);
		readWiki(name);
		readTopGainers();
		} catch (Exception e) {
			System.err.println("Unable to find data for the company.Try again with correct ticker stock symbol");
        	System.err.println("Eg: AAPL for Apple Inc.");
		}
    }
    
    private static void readStocks(String name){
    	JSONParser parser = new JSONParser();
    	 
        try {
        	String jsonString = readUrl("https://www.quandl.com/api/v3/datasets/WIKI/"+name+".json?start_date=2006-05-01&end_date=2016-07-01&order=asc&column_index=4&collapse=quarterly&transformation=rdiff&api_key=mXwK19A32b4xt3Tipywg");
        	Object obj = parser.parse(jsonString);
        	JSONObject jsonObject = (JSONObject) obj;
        	JSONObject dataset =  (JSONObject)jsonObject.get("dataset");
            JSONArray stockPriceList = (JSONArray) dataset.get("data");
            if(stockPriceList.size()==0){
            	System.err.println("Unable to find data for the company.Try again with correct NASDAQ code!");
            	System.err.println("Eg: AAPL for Apple Inc.");
            	return;
            }
            	
            Iterator<JSONArray> iterator = stockPriceList.iterator();
            List<Double> prices = new ArrayList<>();
            while (iterator.hasNext()) {
            	JSONArray eg = iterator.next();
//            	System.out.println((Double)eg.get(1));
            	prices.add((Double)eg.get(1));
            }
            double percentage = (prices.get(prices.size()-1)-prices.get(0))*100/prices.size();
            System.out.println("::::::::::::::::::::::::::");
            System.out.println("The percentage increase over the time period is "+ percentage);
            System.out.println(":::::Financial Advice:::::");
            if(percentage > 500)
            	System.out.println("Excellent appreciation! Safe to invest!");
            else if (percentage > 50)
            	System.out.println("Good showing! OK to invest.");
            else
            	System.out.println("Shows decent trends.Consult a professional before investing.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void readWiki(String name){
    	try {
    	JSONParser parser = new JSONParser();
    	name = name.replace(" ", "%20");
    	String wikiJson = readUrl("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&exintro&titles=apple%20Inc.&format=json");
    	Object obj = parser.parse(wikiJson);
    	JSONObject jsonObject = (JSONObject) obj;
    	JSONObject author =  (JSONObject)jsonObject.get("query");	
    	JSONObject pages =  (JSONObject)author.get("pages");
    	Set<Object> keys = pages.keySet();
     	String firstKey = (String) (keys.toArray())[0];
     	JSONObject firstPage =  (JSONObject)pages.get(firstKey);
     	
     	// Getting the description of the job here.
     	
     	String extract = (String) firstPage.get("extract");
    	System.out.println(extract.replace("<p>", "").replace("</p>", "").replace("<span>", "")
    			.replace("</span>", "").replace("<b>", "").replace("</b>", ""));
    	} catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void readTopGainers(){
    	try {
    	JSONParser parser = new JSONParser();
    	String topJson = readUrl("https://www.nseindia.com/live_market/dynaContent/live_analysis/gainers/niftyGainers1.json");
    	Object obj = parser.parse(topJson);
    	JSONObject jsonObject = (JSONObject) obj;
    	JSONArray companyList = (JSONArray) jsonObject.get("data");
    	Iterator<JSONObject> iterator = companyList.iterator();
    	System.out.println(":::::List of top Gainers today:::::");
    	List<String> comList = new ArrayList<>();
        while (iterator.hasNext()) {
        	JSONObject jobj = (JSONObject) iterator.next();
        	comList.add((String) jobj.get("symbol"));
            System.out.println(jobj.get("symbol"));
        }
        if(!comList.contains(tickerSymbol)){
        	System.out.println();
        	System.out.println("Your desired company does not figure in the top Gainers List!");
        }
    	} catch (Exception e) {
    		
        }
    }
    
    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read); 
            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}
