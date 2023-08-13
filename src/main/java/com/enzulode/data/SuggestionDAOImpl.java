package com.enzulode.data;

import com.enzulode.model.Suggestion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Suggestion DAO implementation.
 */
@Component
@Slf4j
public class SuggestionDAOImpl implements SuggestionDAO
{

	/**
	 * Data source instance.
	 */
	private final JdbcTemplate jdbcTemplate;

	/**
	 * JDBC Suggestion row mapper instance.
	 */
	private final RowMapper<Suggestion> suggestionRowMapper;

	/**
	 * Suggestion DAO constructor
	 *
	 * @param dataSource current application data source
	 * @param suggestionRowMapper suggestion row mapper
	 */
	public SuggestionDAOImpl(DataSource dataSource, RowMapper<Suggestion> suggestionRowMapper)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.suggestionRowMapper = suggestionRowMapper;
	}

	/**
	 * This method finds a suggestion by id.
	 *
	 * @param id suggestion id
	 * @return an optional suggestion
	 */
	@Override
	public Optional<Suggestion> findById(Long id)
	{
		try
		{
			var suggestion = jdbcTemplate.queryForObject(
				"SELECT * FROM available_suggestions WHERE id = ? LIMIT 1",
				suggestionRowMapper,
				id
			);
			return Optional.ofNullable(suggestion);
		}
		catch (DataAccessException e)
		{
			log.error("Something went wrong during suggestion fetch", e);
			return Optional.empty();
		}
	}

	/**
	 * This method fetches all suggestions from the database.
	 *
	 * @return a list of stored suggestions
	 */
	@Override
	public List<Suggestion> findAll()
	{
		try
		{
			return jdbcTemplate.query("SELECT * FROM available_suggestions", suggestionRowMapper);
		}
		catch (DataAccessException e)
		{
			log.error("Something went wrong during suggestion fetch", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method deletes stored suggestion.
	 *
	 * @param suggestion suggestion to be deleted
	 * @return deletion result: true - deletion succeed, false otherwise
	 */
	@Override
	public boolean deleteSuggestion(Suggestion suggestion)
	{
		try
		{
			return jdbcTemplate.update("DELETE FROM available_suggestions WHERE id = ?", suggestion.getId()) > 0;
		}
		catch (DataAccessException e)
		{
			log.error("Something went wrong during suggestion deletion", e);
			return false;
		}
	}

	/**
	 * This method updates stored suggestion.
	 *
	 * @param suggestion suggestion to be updated
	 * @return update result: true - update succeed, false otherwise
	 */
	@Override
	public boolean updateSuggestion(Suggestion suggestion)
	{
		try
		{
			return jdbcTemplate.update(
					"UPDATE available_suggestions SET suggestion_text = ? WHERE id = ?",
					suggestion.getSuggestionText(),
					suggestion.getId()
			) > 0;
		}
		catch (DataAccessException e)
		{
			log.error("Something went wrong during suggestion update", e);
			return false;
		}
	}

	/**
	 * This method stores new suggestion.
	 *
	 * @param suggestion suggestion to be stored
	 * @return creation result: true - creation succeed, false otherwise
	 */
	@Override
	public boolean createSuggestion(Suggestion suggestion)
	{
		try
		{
			return jdbcTemplate.update(
					"INSERT INTO available_suggestions(suggestion_text) VALUES (?)",
					suggestion.getSuggestionText()
			) > 0;
		}
		catch (DataAccessException e)
		{
			log.error("Something went wrong during suggestion creation", e);
			return false;
		}
	}
}
