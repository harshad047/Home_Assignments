package com.tss.exception;

public class MovieStoreFullException extends RuntimeException {
	public String getMessage()
	{
		return "Movie Store is full";
	}
}
