package com.mongo;


/**
 * 
 * @author suresh
 *
 */
public class PersistenceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4066915488091252347L;


	public PersistenceException() {
		super();
	}

	public PersistenceException(String message) {
		super(message);
	}

	public PersistenceException(Throwable th) {
		super(th);
	}

}
