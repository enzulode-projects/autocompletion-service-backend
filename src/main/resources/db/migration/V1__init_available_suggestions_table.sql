CREATE TABLE available_suggestions(
    id BIGSERIAL PRIMARY KEY,
    suggestion_text VARCHAR(127) NOT NULL UNIQUE
);