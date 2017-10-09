CREATE TABLE people (
   id SERIAL PRIMARY KEY,
   external_id INTEGER NOT NULL UNIQUE,
   last_name VARCHAR(150) NOT NULL,
   first_name VARCHAR(150)
);

CREATE TABLE metadata (
   id SERIAL PRIMARY KEY,
   external_id VARCHAR(255) NOT NULL,
   taxon_id INTEGER NOT NULL,
   determiner INTEGER NOT NULL REFERENCES people (id),
   recorder INTEGER NOT NULL REFERENCES people (id)
);

CREATE TABLE scans (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL
);

CREATE TABLE metadata_scans (
   id SERIAL PRIMARY KEY,
   metadata_id INTEGER NOT NULL REFERENCES metadata (id),
   scan_id INTEGER NOT NULL REFERENCES scans (id)
);