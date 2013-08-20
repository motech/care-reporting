With domain_meta_info as (
Select table_name, 'form' as type, 'mother' as category from information_schema.tables where table_schema = 'report' and table_name like '%_form' and table_name NOT like '%_child_%'
UNION ALL
Select table_name, 'form', 'child' from information_schema.tables where table_schema = 'report' and table_name like '%_form' and table_name like '%_child_%'
UNION ALL
Select table_name, 'case', 'mother' from information_schema.tables where table_schema = 'report' and table_name like '%_case' and table_name not like 'child_%'
UNION ALL
Select table_name, 'case', 'child' from information_schema.tables where table_schema = 'report' and table_name like '%_case' and table_name like '%child_%'
)
Select row_number() over() as id, * into report.domain_metadata  from domain_meta_info;

With job_meta_info as (
Select 'populate_delivery_offset_days' as job_name, current_timestamp as last_run
)
Select row_number() over() as id, * into report.job_metadata  from job_meta_info;