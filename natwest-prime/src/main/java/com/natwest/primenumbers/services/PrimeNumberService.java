package com.natwest.primenumbers.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class PrimeNumberService {

	private static final Logger lOGGER = LoggerFactory.getLogger(PrimeNumberService.class);
	
	@Cacheable("primeNumbers")
	public List<Integer> getAllPrimeNumbers(int n) {
		lOGGER.info("Calling PrimeNumberService !!! {}",n);
		return IntStream.rangeClosed(2, n).filter(x -> isPrime(x)).boxed().collect(Collectors.toList());
	}

	private boolean isPrime(int number) {
		return IntStream.rangeClosed(2, (int) (Math.sqrt(number))).allMatch(n -> number % n != 0);
	}

}
