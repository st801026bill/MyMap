package com.bill.MyMap.exception;

import com.bill.MyMap.enums.ErrorType;

import lombok.Getter;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Getter
@NoArgsConstructor
public class ModuleException extends RuntimeException {

	private ErrorType errorType;
	private Throwable throwable = null;
	private String extendMsg = "";
	
	public ModuleException(ErrorType errorType, Throwable throwable) {
		super(throwable);
		this.errorType = errorType;
		this.throwable = throwable;
	}
	
	public ModuleException(ErrorType errorType, String extendMsg) {
		super();
		this.errorType = errorType;
		this.extendMsg = extendMsg;
	}
	
	public ModuleException(ErrorType errorType) {
		super();
		this.errorType = errorType;
	}
}
