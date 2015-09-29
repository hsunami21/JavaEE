package com.cc.stock.dto;

import java.text.NumberFormat;

public class StockHolding {

	private double buyValue;	// purchase price
	private int quantity;   	// quantity owned
	private String symbol;
	
	public String getSymbol() {
		return symbol;
	}

	public double getBuyValue() {
		return buyValue;
	}
	
	public void setBuyValue( double value) {
		buyValue=value;
	}

	public String getBuyPrice(){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		return formatter.format(getBuyValue());
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public StockHolding() {
		super();
	}

	public StockHolding( String symbol, double value) {
		this();
		this.symbol = symbol;
		setBuyValue(value);
	}
}
