package com.enzulode.model.mapper;

import com.enzulode.model.Suggestion;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Suggestion model mapper.
 */
@Component
public class SuggestionMapper implements RowMapper<Suggestion>
{
	/**
	 * This method maps database rows into java objects.
	 *
	 * @param rs the ResultSet to map (pre-initialized for the current row)
	 * @param rowNum the number of the current row
	 * @return a {@link Suggestion} instance
	 * @throws SQLException something went wrong during data fetching from database row
	 */
	@Override
	public Suggestion mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		Suggestion suggestion = new Suggestion();
		suggestion.setId(rs.getLong("id"));
		suggestion.setSuggestionText(rs.getString("suggestion_text"));
		return suggestion;
	}
}
