CREATE TABLE report.aww_reg_child (
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50)
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id INTEGER REFERENCES report.child_case(id)
	,app_version VARCHAR(255)
	,delivery_offset_days INTEGER
	,time_start TIMESTAMP WITH TIME ZONE
	,time_end TIMESTAMP WITH TIME ZONE
	,date_modified TIMESTAMP WITH TIME ZONE
	,server_date_modified TIMESTAMP WITH TIME ZONE
	,creation_time TIMESTAMP WITH TIME ZONE
	,dob DATE
	,child_alive BOOLEAN
	,gender	VARCHAR(20)
	,child_name	VARCHAR(255)
	,husband_name VARCHAR(255)
	,mother_name VARCHAR(255)
	,hh_number VARCHAR(255)
	,ward_number VARCHAR(255)
    ,family_number VARCHAR(255)
    ,aadhar_number VARCHAR(255)
    ,mcts_id INTEGER (20)
    ,child_mcts_id INTEGER(20)
    ,mobile_number INTEGER
    ,mobile_number_whose VARCHAR(20)
    ,eats_meat VARCHAR(20)
    ,invalid_owner VARCHAR (20)
    ,dob_known VARCHAR (20)
    ,dob_entered DATE
    ,show_age BOOLEAN
    ,age_calc INTEGER
    ,age_est_months INTEGER
    ,age_est_years INTEGER
    ,father_name VARCHAR(255)
    ,update_mcts_id VARCHAR(20)
    ,update_aadhar_number VARCHAR(20)
    ,full_child_mcts_id INTEGER
    ,full_mcts_id INTEGER
    ,caste VARCHAR(20)
    ,resident VARCHAR(20)
    ,success VARCHAR(20)
    ,owner_id_calc VARCHAR(255)
	,UNIQUE(instance_id, case_id)
);