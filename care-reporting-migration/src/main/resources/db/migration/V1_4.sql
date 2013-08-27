CREATE OR REPLACE FUNCTION report.populate_delivery_offset_days() RETURNS integer AS $$
DECLARE
    result RECORD;
BEGIN
	FOR result IN Select table_name from report.domain_metadata where type = 'form' and category='mother' LOOP
		EXECUTE 'UPDATE report.' || result.table_name || ' AS v SET delivery_offset_days = CASE WHEN mc.add IS NOT NULL AND v.server_date_modified IS NOT NULL THEN extract(day from (date_trunc(''day'', v.server_date_modified) - date_trunc(''day'', mc.add)))
												     WHEN mc.edd IS NOT NULL  AND v.server_date_modified IS NOT NULL THEN extract(day from (date_trunc(''day'', v.server_date_modified) - date_trunc(''day'', mc.edd)))
												     ELSE null
												     END FROM report.mother_case mc
												     INNER JOIN report.job_metadata md ON mc.last_modified_time >= md.last_run
												     WHERE mc.id = v.case_id AND md.job_name = ''populate_delivery_offset_days''';
	END LOOP;

	FOR result IN Select table_name from report.domain_metadata where type = 'form' and category='child' LOOP
		EXECUTE 'UPDATE report.' || result.table_name || ' AS v SET delivery_offset_days = CASE WHEN mc.add IS NOT NULL  AND v.server_date_modified IS NOT NULL THEN extract(day from (date_trunc(''day'', v.server_date_modified) - date_trunc(''day'', mc.add)))
												     WHEN mc.edd IS NOT NULL  AND v.server_date_modified IS NOT NULL THEN extract(day from (date_trunc(''day'', v.server_date_modified) - date_trunc(''day'', mc.edd)))
												     ELSE null
												     END 
												     FROM report.child_case cc
												     INNER JOIN report.mother_case mc ON cc.mother_id = mc.id
												     INNER JOIN report.job_metadata md ON ( mc.last_modified_time >= md.last_run  OR cc.last_modified_time >= md.last_run )
												     WHERE cc.id = v.case_id AND md.job_name = ''populate_delivery_offset_days''';
	END LOOP;

	UPDATE  report.job_metadata
	SET last_run = current_timestamp
	WHERE job_name = 'populate_delivery_offset_days';

	RETURN 1;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION report.populate_computed_fields() RETURNS integer AS $$
BEGIN
    IF NOT pg_try_advisory_lock(1) THEN
        RETURN -1;
    END IF;

    PERFORM report.populate_delivery_offset_days();

    PERFORM pg_advisory_unlock(1);

    RETURN 1;
END
$$ LANGUAGE plpgsql;