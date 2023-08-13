package com.enzulode.integration;

import com.enzulode.initializer.Postgres;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

/**
 * Base class for all integration tests.
 */
@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(initializers = {Postgres.Initializer.class})
public class IntegrationTestBase
{

	/**
	 * Integration tests environment setup.
	 */
	@BeforeAll
	public static void init()
	{
		Postgres.CONTAINER.start();
	}
}
