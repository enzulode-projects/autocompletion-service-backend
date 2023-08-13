package com.enzulode.service;

import java.util.List;

/**
 * This interface declares autocompletion service contract.
 */
public interface AutocompletionService
{

	/**
	 * Assumes a list of possible suggestions for specific prefix.
	 *
	 * @param prefix prefix
	 * @return a list of suggestions
	 */
	List<String> assumeSuggestionsFor(String prefix);
}
