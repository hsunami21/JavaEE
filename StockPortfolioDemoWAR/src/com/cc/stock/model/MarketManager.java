package com.cc.stock.model;

import java.util.ArrayList;

import com.cc.stock.dto.Stock;
import com.cc.stock.exceptions.NoSuchStockException;

public interface MarketManager {
	
	ArrayList<Stock> generateMarket();

	ArrayList<Stock> getMarket();

	double getQuote(String symbol) throws NoSuchStockException;

	String getPrice(String symbol) throws NoSuchStockException;
	
	void updateMarket();
}
