
CREATE TABLE report.aww_child_reg (
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
    ,mcts_id INTEGER
    ,child_mcts_id INTEGER
    ,mobile_number INTEGER
    ,mobile_number_whose VARCHAR(20)
    ,eats_meat VARCHAR(20)
    ,invalid_owner VARCHAR (20)
    ,dob_known VARCHAR(20)
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

CREATE TABLE report.aww_growth_monitoring_1 (
    id SERIAL PRIMARY KEY,
    case_id INTEGER REFERENCES report.child_case (id),
    user_id INTEGER REFERENCES report.flw (id),
    instance_id VARCHAR(50) UNIQUE,
    app_version VARCHAR(255),
    date_modified TIMESTAMP WITH TIME ZONE,
    server_date_modified TIMESTAMP WITH TIME ZONE,
    delivery_offset_days INTEGER,
    time_start TIMESTAMP WITH TIME ZONE,
    time_end TIMESTAMP WITH TIME ZONE,
    creation_time TIMESTAMP WITH TIME ZONE,
    current_growth VARCHAR(20),
    last_growth_1 VARCHAR(20),
    last_growth_2 VARCHAR(20),
    last_growth_3 VARCHAR(20),
    current_weight_date DATE,
    last_weight_date DATE,
    current_weight DECIMAL,
    last_weight VARCHAR(20),
    take_weight VARCHAR(20),
    child_weight VARCHAR(20),
    show_grade VARCHAR(20),
    requires_attention VARCHAR(20),
    success VARCHAR(20),
    calc_grade INTEGER,
    calc_growth VARCHAR(20),
    child_gender VARCHAR(20),
    child_age INTEGER,
    dob DATE,
    gender VARCHAR(20),
    change_from_normal VARCHAR(20),
    change_from_muw VARCHAR(20),
    change_from_suw VARCHAR(20),
    age_last_weight INTEGER
);

CREATE TABLE report.aww_growth_monitoring_2 (
    id SERIAL PRIMARY KEY,
    case_id INTEGER REFERENCES report.child_case (id),
    user_id INTEGER REFERENCES report.flw (id),
    instance_id VARCHAR(50) UNIQUE,
    app_version VARCHAR(255),
    date_modified TIMESTAMP WITH TIME ZONE,
    server_date_modified TIMESTAMP WITH TIME ZONE,
    delivery_offset_days INTEGER,
    time_start TIMESTAMP WITH TIME ZONE,
    time_end TIMESTAMP WITH TIME ZONE,
    creation_time TIMESTAMP WITH TIME ZONE,
    current_growth VARCHAR(20),
    last_growth_1 VARCHAR(20),
    last_growth_2 VARCHAR(20),
    last_growth_3 VARCHAR(20),
    current_weight_date DATE,
    last_weight_date DATE,
    current_weight DECIMAL,
    last_weight VARCHAR(20),
    take_weight VARCHAR(20),
    child_weight VARCHAR(20),
    show_grade VARCHAR(20),
    requires_attention VARCHAR(20),
    success VARCHAR(20),
    calc_grade INTEGER,
    calc_growth VARCHAR(20),
    child_gender VARCHAR(20),
    child_age INTEGER,
    dob DATE,
    gender VARCHAR(20),
    change_from_normal VARCHAR(20),
    change_from_muw VARCHAR(20),
    change_from_suw VARCHAR(20),
    age_last_weight INTEGER
);

CREATE TABLE report.aww_mother_thr (
    id SERIAL PRIMARY KEY,
    case_id INTEGER REFERENCES report.mother_case (id),
    user_id INTEGER REFERENCES report.flw (id),
    instance_id VARCHAR(50) UNIQUE,
    app_version VARCHAR(255),
    date_modified TIMESTAMP WITH TIME ZONE,
    server_date_modified TIMESTAMP WITH TIME ZONE,
    delivery_offset_days INTEGER,
    time_start TIMESTAMP WITH TIME ZONE,
    time_end TIMESTAMP WITH TIME ZONE,
    creation_time TIMESTAMP WITH TIME ZONE,
    collect_ration VARCHAR(20),
    distribute_ration VARCHAR(20),
    days_ration_given INTEGER,
    cause_not_given VARCHAR(20),
    success VARCHAR(20),
    addval VARCHAR(20),
    mother_rations VARCHAR(20),
    mother_name VARCHAR(255)
);

CREATE TABLE report.aww_close_child (
    id SERIAL PRIMARY KEY,
    case_id INTEGER REFERENCES report.child_case (id),
    user_id INTEGER REFERENCES report.flw (id),
    instance_id VARCHAR(50) UNIQUE,
    app_version VARCHAR(255),
    date_modified TIMESTAMP WITH TIME ZONE,
    server_date_modified TIMESTAMP WITH TIME ZONE,
    delivery_offset_days INTEGER,
    time_start TIMESTAMP WITH TIME ZONE,
    time_end TIMESTAMP WITH TIME ZONE,
    creation_time TIMESTAMP WITH TIME ZONE,
    close_child VARCHAR(20),
    child_over_six VARCHAR(20),
    dupe_reg VARCHAR(20),
    died VARCHAR(20),
    date_death DATE,
    site_death VARCHAR(255),
    died_village VARCHAR(20),
    place_death VARCHAR(255),
    confirm_close VARCHAR(20),
    yes_closed_message VARCHAR(20),
    no_closed_message VARCHAR(20),
    child_alive VARCHAR(20),
    success VARCHAR(20),
    child_name VARCHAR(255),
    dob DATE,
    close_child_case VARCHAR(20)
);
