package com.armandorv.sharemytrip.business.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 4661139464973376388L;

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message) {
		super(message);
	}
	
	
}
