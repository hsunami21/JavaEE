package com.cc.stock.model;

import java.util.ArrayList;


import com.cc.stock.dto.Stock;
import com.cc.stock.dto.StockListings;
import com.cc.stock.exceptions.NoSuchStockException;

public class MarketManagerImpl implements MarketManager {
	
	public MarketManagerImpl() {
	}

	@Override
	public ArrayList<Stock> generateMarket() {
		ArrayList<Stock> stockListings = StockListings.getInstance()
				.getMarket();
		return stockListings;
	}

	@Override
	public ArrayList<Stock> getMarket() {
		return StockListings.getInstance().getMarket();
	}

	
	public double getQuote(String symbol) throws NoSuchStockException {
		ArrayList<Stock> market = getMarket();
		for (Stock s : market) {
			if (s.getSymbol().equals(symbol)) {
				return s.getValue();
			}
		}
		throw new NoSuchStockException("Symbol " + " " + symbol
				+ " is not an active stock");

	}

	public String getPrice(String symbol) throws NoSuchStockException {
		ArrayList<Stock> market = getMarket();
		for (Stock s : market) {
			if (s.equals(symbol)) {
				return s.getPrice();
			}
		}
		throw new NoSuchStockException("S);" + " " + symbol
				+ " is not an active stock");

	}

	@Override
	public void updateMarket() {
		StockListings.getInstance().updateMarket();
		
	}
}
