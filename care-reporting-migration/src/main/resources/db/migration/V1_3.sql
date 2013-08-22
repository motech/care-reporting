CREATE TABLE report.domain_metadata
(
  id SERIAL PRIMARY KEY,
  table_name varchar(255),
  type varchar(50),
  category varchar(50)
);

INSERT INTO report.domain_metadata(table_name, type, category)
Select table_name, 'form', 'mother' from information_schema.tables where table_schema = 'report' and table_name like '%_form' and table_name NOT like '%_child_%'
UNION ALL
Select table_name, 'form', 'child' from information_schema.tables where table_schema = 'report' and table_name like '%_form' and table_name like '%_child_%'
UNION ALL
Select table_name, 'case', 'mother' from information_schema.tables where table_schema = 'report' and table_name like '%_case' and table_name not like 'child_%'
UNION ALL
Select table_name, 'case', 'child' from information_schema.tables where table_schema = 'report' and table_name like '%_case' and table_name like '%child_%';


CREATE TABLE report.job_metadata
(
  id SERIAL PRIMARY KEY,
  job_name varchar(255),
  last_run timestamp with time zone
);

INSERT INTO report.job_metadata(job_name, last_run)
SELECT 'populate_delivery_offset_days', current_timestamp;
