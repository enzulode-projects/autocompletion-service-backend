package com.enzulode.util;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * Simple trie implementation
 */
public class SimpleTrieImpl implements Trie
{

	/**
	 * Trie root node.
	 */
	private final TrieNode root;

	/**
	 * Trie stored elements amount.
	 */
	private int size;

	/**
	 * Trie constructor.
	 */
	public SimpleTrieImpl()
	{
		this.root = new TrieNode();
		this.size = 0;
	}

	/**
	 * This method inserts new data into the trie.
	 *
	 * @param data data to be inserted into trie
	 */
	@Override
	public void insert(String data)
	{
		if (data == null || data.isEmpty())
			return;

		TrieNode current = root;
		boolean somethingWentWrong = false;

		for (int i = 0; i < data.length(); i++)
		{
			if (current == null)
			{
				somethingWentWrong = true;
				break;
			}

			char ch = data.charAt(i);
			if (!current.containsChild(ch))
			{
				current.setChild(ch, new TrieNode(current, ch));
				current.setEnd(false);
			}

			current = current.getChild(ch);
		}

		if (!somethingWentWrong)
			size++;
	}

	/**
	 * This method inserts multiple data into the trie.
	 *
	 * @param data data to be inserted into trie
	 */
	@Override
	public void insertAll(Collection<String> data)
	{
		if (data == null || data.isEmpty())
			return;

		for (String dataPart : data)
			this.insert(dataPart);
	}

	/**
	 * This method finds all suggestions for the provided prefix.
	 *
	 * @param prefix prefix to search for
	 * @return list of suggestions
	 */
	@Override
	public List<String> findAllForPrefix(String prefix)
	{
		if (prefix == null || prefix.isEmpty())
			return Collections.emptyList();

		TrieNode prefixParentNode = findNodeForPrefix(prefix);
		Deque<TrieNode> stack = new ArrayDeque<>();
		stack.push(prefixParentNode);

		List<String> result = new ArrayList<>();
		while (!stack.isEmpty())
		{
			TrieNode currentNode = stack.pop();
			currentNode
					.findNonNullChildren()
					.forEach(stack::push);

			if (currentNode.isEnd())
			{
				StringBuilder sb = new StringBuilder();
				while (currentNode.parent != null && currentNode.value != null)
				{
					sb.append(currentNode.value);
					currentNode = currentNode.parent;
				}
				result.add(sb.reverse().toString());
			}
		}

		return result;
	}

	/**
	 * This method fetches an amount of stored data from the trie.
	 *
	 * @return amount of stored data
	 */
	@Override
	public int size()
	{
		return this.size;
	}

	/**
	 * This method checks if the trie is empty or not
	 *
	 * @return true if the trie is empty, false otherwise
	 */
	@Override
	public boolean isEmpty()
	{
		return this.root.isEnd() && this.root.findNonNullChildren().isEmpty();
	}

	/**
	 * This method finds node for the specific prefix.
	 *
	 * @param prefix prefix
	 * @return node instance or null
	 */
	private TrieNode findNodeForPrefix(String prefix)
	{
		if (prefix == null || prefix.isEmpty())
			return null;

		int prefixPointer = 0;
		TrieNode current = root;
		TrieNode buffer;
		while (prefixPointer < prefix.length())
		{
			char ch = prefix.charAt(prefixPointer);
			buffer = current;
			current = current.getChild(ch);
			if (current == null)
				return buffer;

			++prefixPointer;
		}

		return current;
	}

	/**
	 * Trie consists of nodes: this class is a trie node representation.
	 */
	@Getter
	@Setter
	private static class TrieNode
	{
		/**
		 * Trie-compatible alphabet size.
		 */
		private static final int ALPHABET_SIZE = 128;

		/**
		 * Current node value.
		 */
		private Character value;

		/**
		 * Current node parent.
		 */
		private TrieNode parent;

		/**
		 * Current node children.
		 */
		private TrieNode[] children;

		/**
		 * Trie-node-end flag. If is true - the node is a trie leaf, false - otherwise.
		 */
		private boolean end;

		/**
		 * Trie root node constructor.
		 */
		public TrieNode()
		{
			this.children = new TrieNode[ALPHABET_SIZE];
			this.parent = null;
			this.value = null;
			this.end = true;
		}

		/**
		 * Trie default node constructor.
		 *
		 * @param parent trie node parent
		 * @param value trie node value
		 */
		public TrieNode(TrieNode parent, char value)
		{
			this.children = new TrieNode[ALPHABET_SIZE];
			this.parent = parent;
			this.value = value;
			this.end = true;
		}

		/**
		 * This method receives node from the trie by the char value.
		 *
		 * @param ch char value to get node for
		 * @return {@link TrieNode} if such node exists, null otherwise
		 */
		public TrieNode getChild(char ch)
		{
			if (ch > ALPHABET_SIZE)
				return null;

			return children[ch];
		}

		/**
		 * Current node child setter.
		 *
		 * @param ch child value
		 * @param node child node instance
		 */
		public void setChild(char ch, TrieNode node)
		{
			if (ch > ALPHABET_SIZE || node == null)
				return;

			children[ch] = node;
		}

		/**
		 * Current node specific child existence checking.
		 *
		 * @param ch child to be found
		 * @return true if the child exists, false otherwise
		 */
		private boolean containsChild(char ch)
		{
			if (ch > ALPHABET_SIZE)
				return false;

			return children[ch] != null;
		}

		/**
		 * This method finds all non-null children for the current node.
		 *
		 * @return a list of non-null children
		 */
		private List<TrieNode> findNonNullChildren()
		{
			return Arrays.stream(children)
					.filter(Objects::nonNull)
					.toList();
		}
	}

}
