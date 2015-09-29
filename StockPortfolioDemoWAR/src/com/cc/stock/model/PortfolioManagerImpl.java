package com.cc.stock.model;

import java.util.ArrayList;

import com.cc.stock.dto.StockHolding;
import com.cc.stock.exceptions.InsufficientFundsException;
import com.cc.stock.exceptions.InvalidQuantityException;
import com.cc.stock.exceptions.NoSuchStockException;

public class PortfolioManagerImpl implements PortfolioManager {
    private ArrayList<StockHolding> stockHoldings;
    private MarketManager marketManager;
    
    public PortfolioManagerImpl() {
        stockHoldings = new ArrayList<StockHolding> ();
        StockHolding cash = new StockHolding( "Cash", 1000.0);
        cash.setQuantity(1);
        stockHoldings.add(cash);
        marketManager = new MarketManagerImpl();
    }
   
    public ArrayList<StockHolding> getPortfolio() {
    	return stockHoldings;
    } 
    
    public double getCash() {
    	double cash = 0.0;
    	for ( StockHolding s : stockHoldings ) {
    		if (s.getSymbol().equals("Cash")) {
    			cash = s.getBuyValue();
    			break;
    		}
    	}
    	return cash;
    }
    
    
    public double setCash(double value) {
    	for ( StockHolding s : stockHoldings ) {
    		if (s.getSymbol().equals("Cash")) {
    			s.setBuyValue(Math.max ( value, 0.0));
    			break;
    		}
    	}
    	return 0.0;
    }
    
    public ArrayList<StockHolding> buyStock( String symbol, int quantity ) throws NoSuchStockException, InvalidQuantityException, InsufficientFundsException {
    	if (quantity < 1 ) {
    		throw new InvalidQuantityException("Cannot purchase " + quantity + " units of a stock");
    	}
    	double value = marketManager.getQuote(symbol);
    	double cost = quantity * value;
    	if ( cost > getCash()) {
    		throw new InsufficientFundsException("Insufficent funds for requested purchase");
    	}
    	// withdraw cash
    	setCash( getCash()- cost);
    	// update quantity if buying more of stock already in portfolio
    	for (StockHolding sh : stockHoldings ) {
    		if (sh.getSymbol().equals(symbol)) {
    			sh.setBuyValue(value);
    			sh.setQuantity(sh.getQuantity()+ quantity);
    			return stockHoldings;
    		}
    	}
    	// add new stock to portfolio
    	StockHolding sh = new StockHolding(symbol, value);
    	sh.setQuantity(quantity);
    	stockHoldings.add(sh);    
    	return stockHoldings;
    }
    
    public ArrayList<StockHolding> sellStock( String symbol, int quantity ) throws NoSuchStockException, InvalidQuantityException, InsufficientFundsException {
    	if (quantity < 1 ) {
    		throw new InvalidQuantityException("Cannot sell " + quantity + " units of a stock");
    	}
    	// reduce holdings of stock in portfolio
    	for (StockHolding sh : stockHoldings ) {
    		if (sh.getSymbol().equals(symbol)) {
    			int owned = sh.getQuantity();
    			if ( owned < quantity ) {
    				throw new InvalidQuantityException("Cannot sell more units of stock than you own: " +
    						owned + " < " + quantity);
    			} else if (owned > quantity) {
    				sh.setQuantity( owned - quantity );
    			} else { // owned == quantity
    				stockHoldings.remove(sh);
    			}
    	    	// add sale value to cash
    	    	setCash( getCash() + quantity * marketManager.getQuote(symbol));
    	    	return stockHoldings;
    		} 
    	} 
    	// stock not found in portfolio
    	throw new InvalidQuantityException("Cannot sell stock that you do not own: " + symbol);
    }
    
    public void endGame() {
    	stockHoldings = null;
    }
    
}