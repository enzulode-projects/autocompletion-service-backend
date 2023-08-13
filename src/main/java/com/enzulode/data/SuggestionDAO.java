package com.enzulode.data;

import com.enzulode.model.Suggestion;

import java.util.List;
import java.util.Optional;

/**
 * Suggestion data access object contract.
 */
public interface SuggestionDAO
{

	/**
	 * This method finds a suggestion by id.
	 *
	 * @param id suggestion id
	 * @return an optional suggestion
	 */
	Optional<Suggestion> findById(Long id);

	/**
	 * This method fetches all suggestions from the database.
	 *
	 * @return a list of stored suggestions
	 */
	List<Suggestion> findAll();

	/**
	 * This method deletes stored suggestion.
	 *
	 * @param suggestion suggestion to be deleted
	 * @return deletion result: true - deletion succeed, false otherwise
	 */
	boolean deleteSuggestion(Suggestion suggestion);

	/**
	 * This method updates stored suggestion.
	 *
	 * @param suggestion suggestion to be updated
	 * @return update result: true - update succeed, false otherwise
	 */
	boolean updateSuggestion(Suggestion suggestion);

	/**
	 * This method stores new suggestion.
	 *
	 * @param suggestion suggestion to be stored
	 * @return creation result: true - creation succeed, false otherwise
	 */
	boolean createSuggestion(Suggestion suggestion);
}
