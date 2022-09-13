package com.example.wallet.constants;

public class CustomException extends Exception{

	private static final long serialVersionUID = 1L;
	private int errorCode;
	
	  public CustomException(String message, int errorCode){
	        super(message);
	        this.errorCode = errorCode;
	    }

	    public CustomException(){
	        super();
	    }

	    public int getErrorCode() {
	        return errorCode;
	    }

	    public void setErrorCode(int errorCode) {
	        this.errorCode = errorCode;
	    }


	    public CustomException(String message){
	        super(message);
	    }

	    public CustomException(Exception e){
	        super(e);
	    }
}
