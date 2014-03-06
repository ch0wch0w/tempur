package com.cta.tempura.services;

import java.net.URLEncoder;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.json.JSONTokener;

public class CurrencyConverterService {

	private double getExchangeRate(String source, String target) throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		
		String url="http://www.rate-exchange.appspot.com/currency?from=" +
				URLEncoder.encode(source,"UTF-8")+ "&to=" +
				URLEncoder.encode(target,"UTF-8");
		
		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpresponse = httpClient.execute(httpGet);
		
		String result = IOUtils.toString(httpresponse.getEntity().getContent());
		
		JSONTokener jsonTokener = new JSONTokener(result);
		JSONObject jsonObject = new JSONObject(jsonTokener);
		
		return Double.parseDouble(jsonObject.getString("rate"));
		
	}
	public static void main(String[] args) throws Exception {
		CurrencyConverterService currencyConverter = new CurrencyConverterService();
		System.out.println(currencyConverter.getExchangeRate("EUR","USD"));

	}

}
