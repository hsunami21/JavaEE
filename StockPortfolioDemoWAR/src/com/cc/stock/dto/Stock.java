package com.cc.stock.dto;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Random;

public class Stock {

	protected String symbol;
	protected double value;
	// random number generator for setting initial stock prices
	private static Random rng = new Random(new Date().getTime());

	public double getValue() {
		return value;
	}

	public String getPrice() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		return formatter.format(getValue());
	}

	public double updateValue() {
		// update value to simulate market fluctuations
		double sigma = value / 50.0;
		double mu = value * 1.001;
		double delta = rng.nextGaussian();
		setValue(delta * sigma + mu);
		return getValue();

	}

	public void setValue(double value) {
		if ( value < 0.005 ) { 
			 value = 0.0;
		}
		this.value = value;
	}

	public String getSymbol() {
		return symbol;
	}

	public Stock() {
		super();
	}

	public Stock(String symbol) {
		this();
		this.symbol = symbol;
		this.setValue(rng.nextDouble() * 200.0);
	}

	public String toString() {
		return "Stock " + getSymbol() + ": " + getPrice();
	}

	public static void main(String[] args) {
		Stock a = new Stock("ABC");
		for (int i = 0; i < 12; i++) {
			System.out.println(a);
			a.updateValue();
		}
	}

}
