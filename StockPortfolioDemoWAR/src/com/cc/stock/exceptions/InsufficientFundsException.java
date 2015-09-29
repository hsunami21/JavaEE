package com.cc.stock.exceptions;

public class InsufficientFundsException extends Exception {

	private static final long serialVersionUID = 1L;

	public InsufficientFundsException() {
		// TODO Auto-generated constructor stub
	}

	public InsufficientFundsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InsufficientFundsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InsufficientFundsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
