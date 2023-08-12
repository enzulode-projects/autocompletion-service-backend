package com.enzulode.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link AutocompletionDTO} related tests
 */
public class AutocompletionDTOTest
{

	/**
	 * This method tests that dto will be invalid with null prefix
	 */
	@Test
	@DisplayName("Testing AutocompletionDTO isValid method: null string")
	public void test_isValid_for_null_string()
	{
		var dto = new AutocompletionDTO(null);
		assertFalse(dto.isValid(), "DTO should be invalid, but something went wrong");
	}

	/**
	 * This method tests that dto will be invalid with an empty prefix
	 */
	@Test
	@DisplayName("Testing AutocompletionDTO isValid method: empty string")
	public void test_isValid_for_empty_string()
	{
		var dto = new AutocompletionDTO("");
		assertFalse(dto.isValid(), "DTO should be invalid, but something went wrong");
	}

	/**
	 * This method tests that dto will be valid with a normal prefix
	 */
	@Test
	@DisplayName("Testing AutocompletionDTO isValid method: valid string")
	public void test_isValid_for_valid_string()
	{
		var dto = new AutocompletionDTO("something valid");
		assertTrue(dto.isValid(), "DTO should be valid, but something went wrong");
	}
}
