CREATE TABLE report.location_dimension (
    id SERIAL PRIMARY KEY
   ,state VARCHAR(50) NOT NULL
   ,district VARCHAR(255) NOT NULL
   ,block VARCHAR(255) NOT NULL
   ,UNIQUE(state, district, block)
);

CREATE INDEX location_state_index ON report.location_dimension USING btree(state);
CREATE INDEX location_district_index ON report.location_dimension USING btree(district);
CREATE INDEX location_block_index ON report.location_dimension USING btree(block);

ALTER TABLE report.flw ADD COLUMN location_id INTEGER REFERENCES report.location_dimension(id);