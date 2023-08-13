package com.enzulode.integration.data;

import com.enzulode.data.SuggestionDAO;
import com.enzulode.integration.IntegrationTestBase;
import com.enzulode.model.Suggestion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * SuggestionDAO integration tests.
 */
public class SuggestionDAOIntegrationTest extends IntegrationTestBase
{

	/**
	 * SuggestionDAO instance.
	 */
	@Autowired
	private SuggestionDAO suggestionDAO;

	/**
	 * This method tests that invalid suggestion will not be inserted into the database.
	 */
	@Test
	@DisplayName("Testing SuggestionDAO createSuggestion() method: invalid suggestion")
	public void test_createSuggestion_invalid_suggestion_should_fail()
	{
		Suggestion suggestion = new Suggestion();
		assertFalse(
				suggestionDAO.createSuggestion(suggestion),
				"Should not be inserted, but something went wrong"
		);
	}

	/**
	 * This method tests that valid suggestion will be inserted into the database.
	 */
	@Test
	@DisplayName("Testing SuggestionDAO createSuggestion() method: valid suggestion")
	public void test_createSuggestion_valid_suggestion_should_succeed()
	{
		Suggestion suggestion = new Suggestion();
		suggestion.setSuggestionText("Wow, dude");
		assertTrue(
				suggestionDAO.createSuggestion(suggestion),
				"Should be inserted, but something went wrong"
		);
	}

	/**
	 * This method tests that existing suggestion will not be updated with invalid suggestion.
	 */
	@Test
	@DisplayName("Testing SuggestionDAO updateSuggestion() method: invalid suggestion")
	public void test_updateSuggestion_invalid_suggestion_should_fail()
	{
		Suggestion suggestion = new Suggestion();
		assertFalse(
				suggestionDAO.updateSuggestion(suggestion),
				"Should not be updated, but something went wrong"
		);
	}

	/**
	 * This method tests that existing suggestion will be updated with valid suggestion.
	 */
	@Test
	@DisplayName("Testing SuggestionDAO updateSuggestion() method: valid suggestion")
	public void test_updateSuggestion_valid_suggestion_should_succeed()
	{
		Suggestion suggestion = new Suggestion(1L, "before");
		suggestionDAO.createSuggestion(suggestion);
		suggestion.setSuggestionText("after");
		assertTrue(
				suggestionDAO.updateSuggestion(suggestion),
				"Should be updated, but something went wrong"
		);
	}
}
