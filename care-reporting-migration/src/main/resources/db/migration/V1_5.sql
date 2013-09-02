/*
Creates a read only role 'motech' for the database 'care_reporting'
*/
DO
'BEGIN
   IF NOT EXISTS (
      SELECT *
      FROM   pg_catalog.pg_user
      WHERE  usename = ''motech'') THEN

      CREATE ROLE motech LOGIN PASSWORD ''p@ssw0rd'';
   END IF;
   GRANT CONNECT ON DATABASE care_reporting TO motech;
   
   GRANT USAGE ON SCHEMA report TO motech;
   GRANT SELECT ON ALL TABLES IN SCHEMA report TO motech;
END;'
