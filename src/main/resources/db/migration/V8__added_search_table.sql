CREATE TABLE searches
(
  id SERIAL PRIMARY KEY,
  search_string VARCHAR(255)
);

INSERT INTO searches (search_string) VALUES ('test');