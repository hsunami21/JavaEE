package com.cc.stock.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class StockListings {
	private static StockListings stockListings = null;
	private static int marketSize = 8;
	private ArrayList<String> symbolsUsed = null;
	private ArrayList<Stock> market = null;

	private String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static Random rng = new Random(new Date().getTime());

	private String getStockSymbol() {
		char[] chars = new char[3];
		chars[0] = letters.charAt(rng.nextInt(25));
		chars[1] = letters.charAt(rng.nextInt(25));
		chars[2] = letters.charAt(rng.nextInt(25));
		return new String(chars);
	}

	private StockListings() {
		market = new ArrayList<Stock>();
		symbolsUsed = new ArrayList<String>();
		int numStocks = 0;
		while (numStocks < marketSize) {
			String symbol = getStockSymbol();
			// make sure every symbol is unique
			boolean used = false;
			for (String s : symbolsUsed) {
				if (symbol.equals(s)) {
					used = true;
					break;
				}
			}
			if (! used) {
				// symbol is unique, add stock to market
				numStocks++;
				symbolsUsed.add(symbol);
				market.add(new Stock(symbol));
			}
		}
	}

	public ArrayList<Stock> getMarket() {
		return market;
	}

	public ArrayList<Stock> updateMarket() {
		if (market != null || market.size() > 0) {
			for (Stock s : market) {
				s.updateValue();
			}
		}
		return market;
	}

	public void setMarket(ArrayList<Stock> market) {
		this.market = market;
	}

	public static StockListings getInstance() {
		if (stockListings == null) {
			stockListings = new StockListings();
		} else { 
			stockListings.updateMarket();
		}		
		return stockListings;
	}

	public static void main(String[] args) {
		StockListings stockListings = StockListings.getInstance();
		ArrayList<Stock> newMarket = stockListings.getMarket();
		System.out.println("Generated Stocks");
		for (Stock s : newMarket) {
			System.out.println(s);
		}
		System.out.println("Updated Stocks");
		newMarket = stockListings.updateMarket();
		for (Stock s : newMarket) {
			System.out.println(s);
		}
	}
}
