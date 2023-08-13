package com.enzulode.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link SimpleTrieImpl} related tests
 */
public class SimpleTrieImplTest
{

	/**
	 * This method tests that null string will not be inserted into trie
	 */
	@Test
	@DisplayName("Test SimpleTrieImpl insert() method: inserting null")
	public void test_insert_null_value()
	{
		Trie trie = new SimpleTrieImpl();
		trie.insert(null);
		assertTrue(trie.isEmpty(), "Trie should remain empty, but something went wrong");
	}

	/**
	 * This method tests that empty string will not be inserted into trie
	 */
	@Test
	@DisplayName("Test SimpleTrieImpl insert() method: inserting empty string")
	public void test_insert_empty_string()
	{
		Trie trie = new SimpleTrieImpl();
		trie.insert("");
		assertTrue(trie.isEmpty(), "Trie should remain empty, but something went wrong");
	}

	/**
	 * This method tests that normal string will be inserted into a trie
	 */
	@Test
	@DisplayName("Test SimpleTrieImpl insert() method: inserting normal string")
	public void test_insert_normal_string()
	{
		Trie trie = new SimpleTrieImpl();
		trie.insert("some");
		assertFalse(trie.isEmpty(), "Trie should become not empty, but something went wrong");
	}

	/**
	 * This method tests that trie size increases correctly
	 */
	@Test
	@DisplayName("Test SimpleTrieImpl insert() method normally increases trie size")
	public void test_size_increases_normally_on_insert()
	{
		Trie trie = new SimpleTrieImpl();

		int sizeBefore = trie.size();
		trie.insert("some");
		int sizeAfter = trie.size();

		assertEquals(sizeBefore + 1, sizeAfter, "Size before should be 1 less than size after");
	}

	/**
	 * This method tests that null collection data will not affect the trie
	 */
	@Test
	@DisplayName("Test SimpleTrieImpl insertAll() method: inserting null collection")
	public void test_insertAll_null_value()
	{
		Trie trie = new SimpleTrieImpl();
		trie.insertAll(null);
		assertTrue(trie.isEmpty(), "Trie should remain empty, but something went wrong");
	}

	/**
	 * This method tests that empty collection data will affect the trie
	 *
	 */
	@Test
	@DisplayName("Test SimpleTrieImpl insertAll() method: inserting empty collection")
	public void test_insertAll_empty_collection()
	{
		Trie trie = new SimpleTrieImpl();
		List<String> emptyList = Collections.emptyList();

		int sizeBefore = trie.size();
		trie.insertAll(emptyList);
		int sizeAfter = trie.size();

		assertTrue(trie.isEmpty(), "Trie should remain empty, but something went wrong");
		assertEquals(
				sizeBefore,
				sizeAfter,
				"Trie size before and size after should be equal, but something went wrong"
		);
	}

	/**
	 * This method tests that non-empty collection data will be inserted into trie
	 */
	@Test
	@DisplayName("Test SimpleTrieImpl insertAll() method: inserting non-empty collection")
	public void test_insertAll_non_empty_collection()
	{
		Trie trie = new SimpleTrieImpl();
		List<String> nonEmptyList = List.of("lol", "kek", "dude");

		int sizeBefore = trie.size();
		trie.insertAll(nonEmptyList);
		int sizeAfter = trie.size();

		assertFalse(trie.isEmpty(), "Trie should not remain empty, but something went wrong");
		assertEquals(
				sizeBefore + nonEmptyList.size(),
				sizeAfter,
				"Trie size before and size after should not be equal, but something went wrong"
		);
	}

	/**
	 * This method tests that trie will not have suggestions for a null prefix
	 */
	@Test
	@DisplayName("Test SimpleTrieImpl findAllForPrefix() method: null prefix")
	public void test_findAllForPrefix_null_prefix()
	{
		Trie trie = new SimpleTrieImpl();
		List<String> result = trie.findAllForPrefix(null);

		assertTrue(result.isEmpty(), "Result should be empty, but something went wrong");
	}

	/**
	 * This method tests that trie will not have suggestions for an empty prefix
	 */
	@Test
	@DisplayName("Test SimpleTrieImpl findAllForPrefix() method: empty prefix")
	public void test_findAllForPrefix_empty_prefix()
	{
		Trie trie = new SimpleTrieImpl();
		trie.insertAll(List.of("wow", "dude", "kk"));
		List<String> result = trie.findAllForPrefix("");

		assertTrue(result.isEmpty(), "Result should be empty, but something went wrong");
	}

	/**
	 * This method tests that trie will not have suggestions for normal prefix
	 */
	@Test
	@DisplayName("Test SimpleTrieImpl findAllForPrefix() method: normal prefix")
	public void test_findAllForPrefix_normal_prefix()
	{
		Trie trie = new SimpleTrieImpl();
		trie.insertAll(List.of("wow", "dude", "kk"));
		List<String> result = trie.findAllForPrefix("dud");

		assertFalse(result.isEmpty(), "Result should not be empty, but something went wrong");
		assertEquals(result.size(), 1, "Invalid suggestions were found");
	}

}
