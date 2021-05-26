package com.natwest.primenumbers.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.natwest.primenumbers.model.PrimeResponse.PrimeNumbersResponse;
import com.natwest.primenumbers.services.PrimeNumberService;

@RestController
@RequestMapping("/v1/api/primes")
public class PrimeNumbersController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PrimeNumbersController.class);

	@Autowired
	private PrimeNumberService primeNumberService;

	/**
	 * Fetch all prime Numbers with the initial.
	 * <code>http://.../v1/api/primes/{initial}</code> will return all prime
	 * numbers up given initial
	 * 
	 * @param initial
	 * @return json of initial and prime numbers
	 */
	@RequestMapping(value = "/{initial}", method = RequestMethod.GET  ,produces = { "application/json", "application/xml" })
	public ResponseEntity<PrimeNumbersResponse> getAllPrimeNumbers(@PathVariable("initial") int initial) {
		List<Integer> primeNumberList;
		if (initial <= 1) {
			return new ResponseEntity("Initial Must be Greater Than One", HttpStatus.OK);
		}

		try {
			primeNumberList = primeNumberService.getAllPrimeNumbers(initial);
		} catch (Exception ex) {
			LOGGER.warn("Exception raised getAllPrimeNumbers REST Call {0}", ex);
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		final PrimeNumbersResponse primeNumbersResponse = new PrimeNumbersResponse(initial, primeNumberList);
		return new ResponseEntity(primeNumbersResponse, HttpStatus.OK);

	}

}
