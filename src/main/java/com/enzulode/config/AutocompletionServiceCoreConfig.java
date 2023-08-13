package com.enzulode.config;

import com.enzulode.data.SuggestionDAO;
import com.enzulode.model.Suggestion;
import com.enzulode.util.SimpleTrieImpl;
import com.enzulode.util.Trie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * This class contains core configurations for the autocompletion service application.
 */
@Configuration
@Slf4j
public class AutocompletionServiceCoreConfig
{

	/**
	 * Configuring application trie.
	 *
	 * @return {@link SimpleTrieImpl} instance
	 */
	@Bean
	@DependsOnDatabaseInitialization
	public Trie configureTrie(SuggestionDAO suggestionDAO)
	{
		log.info("Configuring application trie");
		Trie trie = new SimpleTrieImpl();
		List<String> suggestions = suggestionDAO.findAll()
				.stream()
				.map(Suggestion::getSuggestionText)
				.toList();

		trie.insertAll(suggestions);
		return trie;
	}
}
