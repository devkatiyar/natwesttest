package com.natwest.primenumbers.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

public class PrimeNumberServiceTest {

	private PrimeNumberService primeNumberService;

	@Before
	public void setup() throws Exception {
		primeNumberService = new PrimeNumberService();
	}

	@Test
	public void isPrimeTest() throws Exception {
		Assert.assertEquals(Whitebox.invokeMethod(primeNumberService, "isPrime", 2), true);
		Assert.assertEquals(Whitebox.invokeMethod(primeNumberService, "isPrime", 3), true);
		Assert.assertEquals(Whitebox.invokeMethod(primeNumberService, "isPrime", 4), false);
		Assert.assertEquals(Whitebox.invokeMethod(primeNumberService, "isPrime", 5), true);
		Assert.assertEquals(Whitebox.invokeMethod(primeNumberService, "isPrime", 6), false);
	}

	@Test
	public void testGetAllPrimeNumbers() throws Exception {
		List<Integer> result;
		result = Whitebox.invokeMethod(primeNumberService, "getAllPrimeNumbers", 2);

		Assert.assertEquals(1, result.size());
		Assert.assertEquals(true, result.contains(2));

		result = Whitebox.invokeMethod(primeNumberService, "getAllPrimeNumbers", 3);
		Assert.assertEquals(2, result.size());
		Assert.assertEquals(true, result.contains(2));
		Assert.assertEquals(true, result.contains(3));

		result = Whitebox.invokeMethod(primeNumberService, "getAllPrimeNumbers", 4);
		Assert.assertEquals(2, result.size());
		Assert.assertEquals(true, result.contains(2));
		Assert.assertEquals(true, result.contains(3));
		Assert.assertEquals(false, result.contains(4));

		result = Whitebox.invokeMethod(primeNumberService, "getAllPrimeNumbers", 5);
		Assert.assertEquals(3, result.size());
		Assert.assertEquals(true, result.contains(2));
		Assert.assertEquals(true, result.contains(3));
		Assert.assertEquals(false, result.contains(4));
		Assert.assertEquals(true, result.contains(5));

		result = Whitebox.invokeMethod(primeNumberService, "getAllPrimeNumbers", 6);
		Assert.assertEquals(3, result.size());
		Assert.assertEquals(true, result.contains(2));
		Assert.assertEquals(true, result.contains(3));
		Assert.assertEquals(false, result.contains(4));
		Assert.assertEquals(true, result.contains(5));
		Assert.assertEquals(false, result.contains(6));

	}
}
