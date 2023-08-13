package com.enzulode.model;

import lombok.*;

/**
 * Suggestion model.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Suggestion
{
	/**
	 * Suggestion id.
	 */
	private Long id;

	/**
	 * Suggestion text.
	 */
	private String suggestionText;
}
