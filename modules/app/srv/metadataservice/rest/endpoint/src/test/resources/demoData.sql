INSERT INTO people (external_id, last_name) VALUES (4711, 'Nordhoff-Vergien');
INSERT INTO people (external_id, last_name, first_name) VALUES (4712, 'Jansen', 'Florian');

-- Taxon Id 1137: Carex bohemica
-- Taxon Id 21023: Carex flavella 
INSERT INTO metadata (external_id, taxon_id, date, determiner, recorder) VALUES ('47659', '1137', '1970-03-04', 1, 2);
INSERT INTO metadata (external_id, taxon_id, date, determiner, recorder) VALUES ('47271', '21023','1800-10-09', 2, 1);

INSERT INTO scans (name) VALUES ('Care_bohe_GFW_47659');
INSERT INTO scans (name) VALUES ('Care_flav_GFW_47271');

INSERT INTO metadata_scans (metadata_id, scan_id) VALUES (1, 1);
INSERT INTO metadata_scans (metadata_id, scan_id) VALUES (2, 2);
