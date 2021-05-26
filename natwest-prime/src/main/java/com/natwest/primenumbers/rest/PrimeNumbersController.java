package com.natwest.primenumbers.rest;

import java.util.List;

import org.apache.commons.lang3.time.StopWatch;
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

	private static final String VALIDATION_ERROR_MSG = "Initial Must be Greater Than One";

	/**
	 * Fetch all prime Numbers with the initial.
	 * <code>http://.../v1/api/primes/{initial}</code> will return all prime
	 * numbers up given initial
	 * 
	 * @param initial
	 * @return json of initial and prime numbers
	 */
	@RequestMapping(value = "/{initial}", method = RequestMethod.GET, produces = { "application/json",
			"application/xml" })
	public ResponseEntity<PrimeNumbersResponse> getAllPrimeNumbers(@PathVariable("initial") int initial) {
		StopWatch watch = new StopWatch();
		watch.start();
		List<Integer> primeNumberList;
		if (initial <= 1) {
			LOGGER.warn("getAllPrimeNumbers REST Call {0} , {1}", VALIDATION_ERROR_MSG, initial);
			return new ResponseEntity(VALIDATION_ERROR_MSG, HttpStatus.OK);
		}

		try {
			primeNumberList = primeNumberService.getAllPrimeNumbers(initial);
		} catch (Exception ex) {
			LOGGER.error("Exception raised getAllPrimeNumbers REST Call {0}", ex);
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		final PrimeNumbersResponse primeNumbersResponse = new PrimeNumbersResponse(initial, primeNumberList);

		watch.stop();
		LOGGER.info("getAllPrimeNumbers Request Completed Successfully total Time Taken " + watch.getTime()+ " Millisecond");
		return new ResponseEntity(primeNumbersResponse, HttpStatus.OK);

	}

}
