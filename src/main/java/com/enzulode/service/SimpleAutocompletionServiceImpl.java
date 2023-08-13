package com.enzulode.service;

import com.enzulode.util.Trie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Simple autocompletion service implementation.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SimpleAutocompletionServiceImpl implements AutocompletionService
{

	/**
	 * Existing trie instance.
	 */
	private final Trie trie;

	/**
	 * Assumes a list of possible suggestions for specific prefix.
	 *
	 * @param prefix prefix
	 * @return a list of suggestions
	 */
	@Override
	public List<String> assumeSuggestionsFor(String prefix)
	{
		log.info("Assuming possible suggestions for prefix '" + prefix + "'");
		return trie.findAllForPrefix(prefix);
	}
}
