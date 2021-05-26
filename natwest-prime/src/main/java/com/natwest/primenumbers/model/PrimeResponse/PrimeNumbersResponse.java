package com.natwest.primenumbers.model.PrimeResponse;

import java.util.List;

public class PrimeNumbersResponse {
	private int initial;
	private List<Integer> primes;

	
	
	public PrimeNumbersResponse(int initial, List<Integer> primes) {
		super();
		this.initial = initial;
		this.primes = primes;
	}

	public int getInitial() {
		return initial;
	}

//	public void setInitial(int initial) {
//		this.initial = initial;
//	}

	public List<Integer> getPrimes() {
		return primes;
	}

//	public void setPrimes(List<Integer> primes) {
//		this.primes = primes;
//	}

}
