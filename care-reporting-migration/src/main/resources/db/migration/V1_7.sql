
CREATE TABLE report.aww_reg_child_form (
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
    ,child_alive VARCHAR(20)
    ,gender VARCHAR(20)
    ,child_name VARCHAR(255)
    ,age_est_months INTEGER
    ,age_est_years INTEGER
    ,child_mcts_id INTEGER
    ,invalid_owner VARCHAR (20)
    ,full_child_mcts_id INTEGER
    ,success VARCHAR(20)
    ,owner_id_calc VARCHAR(255)
    ,UNIQUE(instance_id, case_id)
);

CREATE TABLE report.aww_reg_mother_form (
    id SERIAL PRIMARY KEY
    ,instance_id VARCHAR(50) UNIQUE
    ,user_id INTEGER REFERENCES report.flw(id)
    ,case_id INTEGER REFERENCES report.mother_case(id)
    ,app_version VARCHAR(255)
    ,delivery_offset_days INTEGER
    ,time_start TIMESTAMP WITH TIME ZONE
    ,time_end TIMESTAMP WITH TIME ZONE
    ,date_modified TIMESTAMP WITH TIME ZONE
    ,server_date_modified TIMESTAMP WITH TIME ZONE
    ,creation_time TIMESTAMP WITH TIME ZONE
    ,dob DATE
    ,father_name VARCHAR(255)
    ,mother_name VARCHAR(255)
    ,hh_number VARCHAR(255)
    ,ward_number VARCHAR(255)
    ,family_number VARCHAR(255)
    ,aadhar_number VARCHAR(255)
    ,mcts_id INTEGER
    ,mobile_number VARCHAR(20)
    ,mobile_number_whose VARCHAR(20)
    ,eats_meat VARCHAR(20)
    ,invalid_owner VARCHAR (20)
    ,dob_known VARCHAR(20)
    ,dob_entered DATE
    ,show_age VARCHAR(20)
    ,age_calc INTEGER
    ,update_mcts_id VARCHAR(20)
    ,update_aadhar_number VARCHAR(20)
    ,full_mcts_id INTEGER
    ,caste VARCHAR(20)
    ,resident VARCHAR(20)
    ,success VARCHAR(20)
    ,owner_id_calc VARCHAR(255)
);

CREATE TABLE report.aww_growth_monitoring_1_child_form (
    id SERIAL PRIMARY KEY,
    case_id INTEGER REFERENCES report.child_case (id),
    user_id INTEGER REFERENCES report.flw (id),
    instance_id VARCHAR(50),
    app_version VARCHAR(255),
    date_modified TIMESTAMP WITH TIME ZONE,
    server_date_modified TIMESTAMP WITH TIME ZONE,
    delivery_offset_days INTEGER,
    time_start TIMESTAMP WITH TIME ZONE,
    time_end TIMESTAMP WITH TIME ZONE,
    creation_time TIMESTAMP WITH TIME ZONE,
    current_growth VARCHAR(50),
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
    calc_grade VARCHAR(50),
    calc_growth VARCHAR(50),
    child_gender VARCHAR(20),
    child_age INTEGER,
    dob DATE,
    gender VARCHAR(20),
    change_from_normal VARCHAR(20),
    change_from_muw VARCHAR(20),
    change_from_suw VARCHAR(20),
    age_last_weight INTEGER,
    UNIQUE(instance_id, case_id)

);

CREATE TABLE report.aww_growth_monitoring_2_child_form (
    id SERIAL PRIMARY KEY,
    case_id INTEGER REFERENCES report.child_case (id),
    user_id INTEGER REFERENCES report.flw (id),
    instance_id VARCHAR(50),
    app_version VARCHAR(255),
    date_modified TIMESTAMP WITH TIME ZONE,
    server_date_modified TIMESTAMP WITH TIME ZONE,
    delivery_offset_days INTEGER,
    time_start TIMESTAMP WITH TIME ZONE,
    time_end TIMESTAMP WITH TIME ZONE,
    creation_time TIMESTAMP WITH TIME ZONE,
    current_growth VARCHAR(50),
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
    calc_grade VARCHAR(50),
    calc_growth VARCHAR(50),
    child_gender VARCHAR(20),
    child_age INTEGER,
    dob DATE,
    gender VARCHAR(20),
    change_from_normal VARCHAR(20),
    change_from_muw VARCHAR(20),
    change_from_suw VARCHAR(20),
    age_last_weight INTEGER,
    UNIQUE(instance_id, case_id)
);

CREATE TABLE report.aww_thr_mother_form (
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

CREATE TABLE report.aww_close_child_form (
    id SERIAL PRIMARY KEY,
    case_id INTEGER REFERENCES report.child_case (id),
    user_id INTEGER REFERENCES report.flw (id),
    instance_id VARCHAR(50),
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
    close_child_case VARCHAR(20),
    UNIQUE(instance_id, case_id)
);

CREATE TABLE report.aww_edit_child_form (
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
    ,update_child_name VARCHAR(20)
    ,new_child_name VARCHAR(255)
    ,update_child_dob VARCHAR (20)
    ,new_child_dob DATE
    ,success VARCHAR(20)
    ,UNIQUE(instance_id, case_id)
);

CREATE TABLE report.aww_update_vaccinations_child_form (
    id SERIAL PRIMARY KEY,
    case_id INTEGER REFERENCES report.child_case (id),
    user_id INTEGER REFERENCES report.flw (id),
    instance_id VARCHAR(50),
    app_version VARCHAR(255),
    date_modified TIMESTAMP WITH TIME ZONE,
    server_date_modified TIMESTAMP WITH TIME ZONE,
    delivery_offset_days INTEGER,
    time_start TIMESTAMP WITH TIME ZONE,
    time_end TIMESTAMP WITH TIME ZONE,
    creation_time TIMESTAMP WITH TIME ZONE,
    details_available VARCHAR(20),
    details_needed VARCHAR(50),
    add_vaccinations VARCHAR(20),
    bcg_date DATE,
    opv_0_date DATE,
    opv_1_date DATE,
    opv_2_date DATE,
    opv_3_date DATE,
    opv_booster_date DATE,
    dpt_1_date DATE,
    dpt_2_date DATE,
    dpt_3_date DATE,
    dpt_booster_date DATE,
    hep_b_0_date DATE,
    hep_b_1_date DATE,
    hep_b_2_date DATE,
    hep_b_3_date DATE,
    measles_date DATE,
    date_measles_booster DATE,
    vit_a_1_date DATE,
    vit_a_2_date DATE,
    vit_a_3_date DATE,
    date_je DATE,
    up_to_date VARCHAR(20),
    baby_bcg VARCHAR(20),
    baby_opv_0 VARCHAR(20),
    baby_opv_1 VARCHAR(20),
    baby_opv_2 VARCHAR(20),
    baby_opv_3 VARCHAR(20),
    baby_opv_booster VARCHAR(20),
    baby_dpt_1 VARCHAR(20),
    baby_dpt_2 VARCHAR(20),
    baby_dpt_3 VARCHAR(20),
    baby_dpt_booster VARCHAR(20),
    baby_hep_b_0 VARCHAR(20),
    baby_hep_b_1 VARCHAR(20),
    baby_hep_b_2 VARCHAR(20),
    baby_hep_b_3 VARCHAR(20),
    baby_measles VARCHAR(20),
    baby_measles_booster VARCHAR(20),
    baby_vita_1 VARCHAR(20),
    baby_vita_2 VARCHAR(20),
    baby_vita_3 VARCHAR(20),
    baby_je VARCHAR(20),
    up_to_date_six_weeks VARCHAR(20),
    up_to_date_ten_weeks VARCHAR(20),
    up_to_date_14_weeks VARCHAR(20),
    up_to_date_one_year VARCHAR(20),
    up_to_date_two_year VARCHAR(20),
    success VARCHAR(20),
    immuns_up_to_date VARCHAR(20),
    child_name VARCHAR(255),
    dob DATE,
    UNIQUE(instance_id, case_id)
);

CREATE TABLE report.aww_thr_child_form (
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
    ,collect_ration VARCHAR(20)
    ,child_distribute_ration VARCHAR(20)
    ,child_days_ration_given INTEGER
    ,child_amount_given VARCHAR(20)
    ,child_cause_not_given VARCHAR(50)
    ,success VARCHAR (20)
    ,child_name VARCHAR(255)
    ,mother_name VARCHAR(255)
    ,UNIQUE(instance_id, case_id)
);

CREATE TABLE report.growth_monitoring_child_form (
    id SERIAL PRIMARY KEY,
    case_id INTEGER REFERENCES report.child_case (id),
    user_id INTEGER REFERENCES report.flw (id),
    instance_id VARCHAR(50),
    app_version VARCHAR(255),
    date_modified TIMESTAMP WITH TIME ZONE,
    server_date_modified TIMESTAMP WITH TIME ZONE,
    delivery_offset_days INTEGER,
    time_start TIMESTAMP WITH TIME ZONE,
    time_end TIMESTAMP WITH TIME ZONE,
    creation_time TIMESTAMP WITH TIME ZONE,
    take_weight VARCHAR(20),
    child_weight VARCHAR(20),
    show_grade VARCHAR(20),
    requires_attention VARCHAR(20),
    success VARCHAR(20),
    calc_grade VARCHAR(50),
    calc_growth VARCHAR(50),
    child_gender VARCHAR(20),
    child_age INTEGER,
    dob DATE,
    gender VARCHAR(20),
    change_from_normal VARCHAR(20),
    change_from_muw VARCHAR(20),
    change_from_suw VARCHAR(20),
    UNIQUE(instance_id, case_id)
);

CREATE TABLE report.aww_preschool_activities_form (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES report.flw (id),
    instance_id VARCHAR(50) UNIQUE,
    app_version VARCHAR(255),
    date_modified TIMESTAMP WITH TIME ZONE,
    server_date_modified TIMESTAMP WITH TIME ZONE,
    delivery_offset_days INTEGER,
    time_start TIMESTAMP WITH TIME ZONE,
    time_end TIMESTAMP WITH TIME ZONE,
    creation_time TIMESTAMP WITH TIME ZONE,
    menu VARCHAR(255),
    activity VARCHAR(255),
    success VARCHAR(20),
    num_children INTEGER,
    child_ids TEXT
);

CREATE TABLE report.aww_preschool_activities_child_form (
    id SERIAL PRIMARY KEY,
    form_id INTEGER REFERENCES report.aww_preschool_activities_form (id) ON DELETE CASCADE,
    case_id INTEGER REFERENCES report.child_case (id),
    user_id INTEGER REFERENCES report.flw (id),
    instance_id VARCHAR(50),
    app_version VARCHAR(255),
    date_modified TIMESTAMP WITH TIME ZONE,
    server_date_modified TIMESTAMP WITH TIME ZONE,
    delivery_offset_days INTEGER,
    time_start TIMESTAMP WITH TIME ZONE,
    time_end TIMESTAMP WITH TIME ZONE,
    creation_time TIMESTAMP WITH TIME ZONE,
    caseid VARCHAR(255),
    child_attend VARCHAR(20),
    breakfast VARCHAR(20),
    participated VARCHAR(20),
    lunch VARCHAR(20),
    CONSTRAINT aww_preschool_activities_child_form_uk UNIQUE (instance_id, case_id)
);
