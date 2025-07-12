package com.tss.exception;

public class NoSuchMovieExceptionFound extends RuntimeException{
	 @Override
	    public String getMessage() {
	        return "Movies with this id does not exist";
	    }
}
