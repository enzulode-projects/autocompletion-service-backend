package com.enzulode.controller;

import com.enzulode.model.AutocompletionDTO;
import com.enzulode.service.AutocompletionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Autocompletion request REST controller implementation.
 */
@RestController
@RequestMapping(
		path = "/autocomplete",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class AutocompletionRESTController
{

	/**
	 * Autocompletion service instance.
	 */
	private final AutocompletionService autocompletionService;

	/**
	 * Autocompletion endpoint
	 *
	 * @param dto autocompletion dto
	 * @return result of autocompletion request
	 */
	@PostMapping("/assume")
	@CrossOrigin
	public ResponseEntity<?> assumeAutocomplete(@RequestBody AutocompletionDTO dto)
	{
		if (dto == null || !dto.isValid())
			return ResponseEntity.status(400).body("You have to provide some data");

		List<String> completions = autocompletionService
				.assumeSuggestionsFor(dto.prefix())
				.stream()
				.limit(6)
				.toList();

		return ResponseEntity.ok(completions);
	}
}
