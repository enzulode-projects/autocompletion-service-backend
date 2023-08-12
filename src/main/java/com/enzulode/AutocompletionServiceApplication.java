package com.enzulode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Autocompletion service main class
 */
@SpringBootApplication
public class AutocompletionServiceApplication
{

	/**
	 * Autocompletion service application startup entry point.
	 *
	 * @param args command line arguments
	 */
	public static void main(String[] args)
	{
		SpringApplication.run(AutocompletionServiceApplication.class, args);
	}
}
