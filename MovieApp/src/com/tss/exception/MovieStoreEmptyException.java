package com.tss.exception;

public class MovieStoreEmptyException extends RuntimeException{
	
	public String getMessage()
	{
		return "Movie Store is Empty";
	}

}
