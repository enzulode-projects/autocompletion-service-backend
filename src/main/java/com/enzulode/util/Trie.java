package com.enzulode.util;

import java.util.Collection;
import java.util.List;

/**
 * This interface declares a contract for trie data structure.
 */
public interface Trie
{

	/**
	 * This method inserts new data into the trie.
	 *
	 * @param data data to be inserted into trie
	 */
	void insert(String data);

	/**
	 * This method inserts multiple data into the trie.
	 *
	 * @param data data to be inserted into trie
	 */
	void insertAll(Collection<String> data);

	/**
	 * This method finds all suggestions for the provided prefix.
	 *
	 * @param prefix prefix to search for
	 * @return list of suggestions
	 */
	List<String> findAllForPrefix(String prefix);

	/**
	 * This method fetches an amount of stored data from the trie
	 *
	 * @return amount of stored data
	 */
	int size();

	/**
	 * This method checks if the trie is empty or not
	 *
	 * @return true if the trie is empty, false otherwise
	 */
	boolean isEmpty();
}
