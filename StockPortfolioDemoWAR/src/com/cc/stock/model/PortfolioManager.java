package com.cc.stock.model;

import java.util.ArrayList;

import com.cc.stock.dto.StockHolding;
import com.cc.stock.exceptions.InsufficientFundsException;
import com.cc.stock.exceptions.InvalidQuantityException;
import com.cc.stock.exceptions.NoSuchStockException;

public interface PortfolioManager {

	ArrayList<StockHolding> getPortfolio();

	ArrayList<StockHolding> buyStock(String symbol, int quantity)
			throws NoSuchStockException, InvalidQuantityException,
			InsufficientFundsException;

	ArrayList<StockHolding> sellStock(String symbol, int quantity)
			throws NoSuchStockException, InvalidQuantityException,
			InsufficientFundsException;

	void endGame();

}
