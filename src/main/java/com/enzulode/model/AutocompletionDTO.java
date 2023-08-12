package com.enzulode.model;

/**
 * This record is a representation of an autocompletion request body.
 * The main purpose is to store data from the client side.
 *
 * @param prefix requested autocompletion prefix
 */
public record AutocompletionDTO(String prefix)
{
	/**
	 * This method let you know if the DTO is valid or not.
	 *
	 * @return true if the DTO is valid (prefix is not null and is not empty), false otherwise
	 */
	public boolean isValid()
	{
		return prefix != null && !prefix.isEmpty();
	}
}
