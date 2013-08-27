CREATE TABLE report.flw (
    id SERIAL PRIMARY KEY
    ,flw_id VARCHAR(50) UNIQUE
    ,default_phone_number VARCHAR(20)
    ,email VARCHAR(255)
    ,first_name VARCHAR(255)
    ,last_name VARCHAR(255)
    ,phone_number_1 VARCHAR(20)
    ,phone_number_2 VARCHAR(20)
    ,asset_id VARCHAR(255)
    ,awc_code VARCHAR(255)
    ,role VARCHAR(255)
    ,subcentre VARCHAR(255)
    ,user_type VARCHAR(255)
    ,username VARCHAR(255)
    ,population VARCHAR(255)
    ,education VARCHAR(255)
    ,state VARCHAR(255)
    ,district VARCHAR(255)
    ,block VARCHAR(255)
    ,panchayat VARCHAR(255)
    ,village VARCHAR(255)
    ,ward VARCHAR(255)
    ,caste VARCHAR(255)
    ,dob DATE
    ,ictcordinator VARCHAR(255)
    ,remarks TEXT
    ,creation_time TIMESTAMP WITH TIME ZONE
    ,last_modified_time TIMESTAMP WITH TIME ZONE
);

CREATE TABLE report.flw_group (
	id SERIAL PRIMARY KEY
    ,group_id VARCHAR(50) UNIQUE
    ,case_sharing BOOLEAN
    ,domain VARCHAR(255)
    ,awc_code VARCHAR(255)
    ,name VARCHAR(255)
    ,reporting BOOLEAN
    ,creation_time TIMESTAMP WITH TIME ZONE
    ,last_modified_time TIMESTAMP WITH TIME ZONE
);

CREATE TABLE report.flw_group_map (
	id SERIAL PRIMARY KEY
    ,flw_id INTEGER REFERENCES report.flw(id)
    ,group_id INTEGER REFERENCES report.flw_group(id)
);

CREATE TABLE report.mother_case (
	id SERIAL PRIMARY KEY
	,case_id	VARCHAR(50) UNIQUE
	,case_name	VARCHAR(255)
	,case_type	VARCHAR(255)
	,owner_id	INTEGER REFERENCES report.flw_group(id)
	,user_id INTEGER REFERENCES report.flw(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
	,server_date_modified TIMESTAMP WITH TIME ZONE
	,family_number	INTEGER
	,hh_number	INTEGER
	,husband_name	VARCHAR(255)
    ,last_visit_type	VARCHAR(20)
	,mother_alive	VARCHAR(20)
	,mother_dob	DATE
	,mother_name	VARCHAR(255)
	,add	DATE
	,age	SMALLINT
	,birth_place	VARCHAR(255)
	,complications	VARCHAR(20)
	,date_next_bp	DATE
	,date_next_cf	DATE
	,date_next_eb	DATE
	,date_next_pnc	DATE
	,eats_meat	VARCHAR(20)
	,edd	DATE
	,enrolled_in_kilkari	VARCHAR(20)
	,family_planning_type	VARCHAR(255)
	,how_many_children	SMALLINT
	,interest_in_kilkari	VARCHAR(20)
	,last_preg_tt	VARCHAR(20)
	,lmp	DATE
	,mobile_number	VARCHAR(20)
	,num_boys	SMALLINT	
	,date_cf_1	DATE
	,date_cf_2	DATE
	,date_cf_3	DATE
	,date_cf_4	DATE
	,date_cf_5	DATE
	,date_cf_6	DATE
	,date_eb_1	DATE
	,date_eb_2	DATE
	,date_eb_3	DATE
	,date_eb_4	DATE
	,date_eb_5	DATE
	,date_eb_6	DATE
	,all_pnc_on_time	VARCHAR(20)
	,date_pnc_1	DATE
	,date_pnc_2	DATE
	,date_pnc_3	DATE
	,first_pnc_time	VARCHAR(255)
	,pnc_1_days_late	INTEGER
	,pnc_2_days_late	INTEGER
	,pnc_3_days_late	INTEGER
	,tt_booster_date	DATE
	,sba	VARCHAR(20)
	,sba_phone	VARCHAR(20)
	,accompany	VARCHAR(20)
	,anc_1_date	DATE
	,anc_2_date	DATE
	,anc_3_date	DATE
	,anc_4_date	DATE
	,clean_cloth	VARCHAR(20)
	,couple_interested	VARCHAR(15)
	,date_bp_1	DATE
	,date_bp_2	DATE
	,date_bp_3	DATE
	,date_last_visit	DATE
	,delivery_type	VARCHAR(255)
	,ifa_tablets	SMALLINT
	,ifa_tablets_100	DATE
	,materials	VARCHAR(20)
	,maternal_emergency	VARCHAR(20)
	,maternal_emergency_number	VARCHAR(20)
	,phone_vehicle	VARCHAR(20)
	,saving_money	VARCHAR(20)
	,tt_1_date	DATE
	,tt_2_date	DATE
	,vehicle	VARCHAR(20)
	,birth_status	VARCHAR(255)
	,migrate_out_date	DATE
	,migrated_status	VARCHAR(255)
	,status	VARCHAR(255)
	,term VARCHAR(25)
	,date_cf_7 DATE
	,date_del_fu DATE
	,date_next_reg DATE
	,institutional VARCHAR(255)
	,mobile_number_whose VARCHAR(255)
	,bp_visit_num INTEGER
	,ward_number INTEGER
	,eb_visit_num INTEGER
	,pnc_visit_num INTEGER
	,cf_visit_num INTEGER
	,dob DATE
	,closed BOOLEAN
	,closed_by INTEGER REFERENCES report.flw(id)
	,closed_on	TIMESTAMP WITH TIME ZONE
    ,creation_time TIMESTAMP WITH TIME ZONE
    ,last_modified_time TIMESTAMP WITH TIME ZONE
);

CREATE TABLE report.child_case (
	id SERIAL PRIMARY KEY
	,case_id	VARCHAR(50)
	,case_name	VARCHAR(255)
	,date_modified	TIMESTAMP WITH TIME ZONE	
	,server_date_modified TIMESTAMP WITH TIME ZONE
    ,mother_id	INTEGER REFERENCES report.mother_case(id)
	,case_type	VARCHAR(255)
	,owner_id	INTEGER REFERENCES report.flw_group(id)	
	,user_id INTEGER REFERENCES report.flw(id)
	,baby_measles	VARCHAR(20)	
	,bcg_date	DATE
	,birth_status	VARCHAR(255)
	,dob	DATE
	,dpt_1_date	DATE
	,dpt_2_date	DATE
	,dpt_3_date	DATE
	,gender	VARCHAR(15)
	,hep_b_0_date	DATE
	,hep_b_1_date	DATE
	,hep_b_2_date	DATE
	,hep_b_3_date	DATE
	,measles_date	DATE
	,opv_0_date	DATE
	,opv_1_date	DATE
	,opv_2_date	DATE
	,opv_3_date	DATE
	,vit_a_1_date	DATE	
	,child_alive	VARCHAR(20)	
	,dpt_booster_date	DATE
	,opv_booster_date	DATE
	,date_je DATE
	,date_measles_booster DATE
	,baby_weight VARCHAR(20)
	,name VARCHAR(255)
	,term VARCHAR(50)
	,time_of_birth VARCHAR(25)
	,vit_a_2_date DATE
	,vit_a_3_date DATE
	,cord_fallen VARCHAR(20)
	,closed BOOLEAN
	,closed_on TIMESTAMP WITH TIME ZONE
	,closed_by INTEGER REFERENCES report.flw(id)
    ,creation_time TIMESTAMP WITH TIME ZONE
    ,last_modified_time TIMESTAMP WITH TIME ZONE
);

CREATE TABLE report.new_form (
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50) UNIQUE
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.mother_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
    ,server_date_modified TIMESTAMP WITH TIME ZONE
	,app_version VARCHAR(255)
	,age_calc	SMALLINT
	,case_name	VARCHAR(255)
	,case_type	VARCHAR(255)
	,date_last_visit	DATE
	,date_next_reg	DATE
	,family_number	INTEGER
	,hh_number	INTEGER
	,husband_name	VARCHAR(255)
	,last_visit_type	VARCHAR(20)
	,mother_alive	VARCHAR(20)
	,mother_dob	DATE
	,mother_name	VARCHAR(255)
	,caste	VARCHAR(255)
	,dob	DATE
	,dob_known	VARCHAR(20)
	,full_name	VARCHAR(255)
	,manual_group	VARCHAR(255)
	,delivery_offset_days INTEGER
    ,creation_time TIMESTAMP WITH TIME ZONE
);

CREATE TABLE report.registration_mother_form (
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50) UNIQUE
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.mother_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
    ,server_date_modified TIMESTAMP WITH TIME ZONE
	,app_version VARCHAR(255)
	,ppiud	VARCHAR(20)
	,pptl	VARCHAR(20)
	,abd_pain	VARCHAR(20)
	,age_calc	SMALLINT
	,age_calc_adj	SMALLINT
	,age_est	SMALLINT
	,age_est_trigger	VARCHAR(10)
	,add	DATE
	,age	SMALLINT
	,birth_place	VARCHAR(255)
	,complications	VARCHAR(20)
	,date_last_visit	DATE
	,date_next_bp	DATE
	,date_next_cf	DATE
	,date_next_eb	DATE
	,date_next_pnc	DATE
	,eats_meat	VARCHAR(20)
	,edd	DATE
	,enrolled_in_kilkari	VARCHAR(20)
	,family_planning_type	VARCHAR(255)
	,how_many_children	SMALLINT
	,interest_in_kilkari	VARCHAR(20)
	,last_preg_tt	VARCHAR(20)
	,last_visit_type	VARCHAR(20)
	,lmp	DATE
	,mobile_number	VARCHAR(20)
	,mother_dob	 DATE
	,num_boys	SMALLINT
	,status	VARCHAR(255)
	,child_dob	DATE
	,client_no_register	VARCHAR(10)
	,client_not_pregnant	VARCHAR(10)
	,clinical_exam	VARCHAR(20)
	,condoms	VARCHAR(20)
	,continue_preg	VARCHAR(20)
	,delivery_nature	VARCHAR(255)
	,dob_est	VARCHAR(255)
	,edd_calc	DATE
	,edd_known	VARCHAR(20)
	,education	VARCHAR(255)
	,fever	VARCHAR(20)
	,first_pregnancy	VARCHAR(20)
	,gest_age	SMALLINT
	,good_to_register	VARCHAR(20)
	,in_district	VARCHAR(20)
	,injectible	VARCHAR(20)
	,is_pregnant	VARCHAR(20)
	,iud_used	VARCHAR(20)
	,jsy_beneficiary	VARCHAR(20)
	,jsy_money	VARCHAR(20)
	,last_preg	INTEGER
	,last_preg_c_section	VARCHAR(20)
	,last_preg_full_term	VARCHAR(20)
	,lmp_calc	DATE
	,lmp_known	VARCHAR(20)
	,missed_period	VARCHAR(20)
	,mobile_number_whose	VARCHAR(255)
	,nextvisit	VARCHAR(10)
	,nextvisit_bp	VARCHAR(10)
	,nextvisittype	VARCHAR(20)
	,num_children	SMALLINT
	,num_girls	SMALLINT
	,ocp_used	VARCHAR(20)
	,other_conditions	VARCHAR(255)
	,other_district	VARCHAR(255)
	,other_village	VARCHAR(255)
	,pain_urine	VARCHAR(20)
	,post_postpartum_fp	VARCHAR(20)
	,preg_desired	VARCHAR(20)
	,recently_delivered	VARCHAR(20)
	,referral_prompt	VARCHAR(255)
	,resident	VARCHAR(255)
	,urine_test	VARCHAR(20)
	,used_fp	VARCHAR(20)
	,vaginal_discharge	VARCHAR(20)
	,vegetarian	VARCHAR(20)
	,where_born	VARCHAR(255)
    ,which_hospital	VARCHAR(255)
    ,which_village	VARCHAR(255)
    ,close	BOOLEAN
    ,delivery_offset_days INTEGER
    ,creation_time TIMESTAMP WITH TIME ZONE
    ,children	VARCHAR(20)
);

CREATE TABLE report.registration_child_form (
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50)
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.child_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
    ,server_date_modified TIMESTAMP WITH TIME ZONE
	,app_version VARCHAR(255)
	,abnormalities	VARCHAR(20)
	,add_vaccinations	VARCHAR(20)
	,baby_bcg	VARCHAR(20)
	,baby_dpt1	VARCHAR(20)
	,baby_dpt2	VARCHAR(20)
	,baby_dpt3	VARCHAR(20)
	,baby_hep_b_0	VARCHAR(20)
	,baby_hep_b_1	VARCHAR(20)
	,baby_hep_b_2	VARCHAR(20)
	,baby_hep_b_3	VARCHAR(20)
	,baby_measles	VARCHAR(20)
	,baby_opv0	VARCHAR(20)
	,baby_opv1	VARCHAR(20)
	,baby_opv2	VARCHAR(20)
	,baby_opv3	VARCHAR(20)
	,baby_vita1	VARCHAR(20)
	,case_name	VARCHAR(255)
	,case_type	VARCHAR(255)
	,bcg_date	DATE
	,birth_status	VARCHAR(255)
	,dob	DATE
	,dpt_1_date	DATE
	,dpt_2_date	DATE
	,dpt_3_date	DATE
	,gender	VARCHAR(15)
	,hep_b_0_date	DATE
	,hep_b_1_date	DATE
	,hep_b_2_date	DATE
	,hep_b_3_date	DATE
	,measles_date	DATE
	,opv_0_date	DATE
	,opv_1_date	DATE
	,opv_2_date	DATE
	,opv_3_date	DATE
	,vit_a_1_date	DATE
	,child_have_a_name	VARCHAR(20)
	,child_name	VARCHAR(255)
	,weight	DECIMAL
  	,delivery_offset_days INTEGER
    ,creation_time TIMESTAMP WITH TIME ZONE
	,UNIQUE(instance_id, case_id)
);


CREATE TABLE report.bp_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50) UNIQUE
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.mother_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
    ,server_date_modified TIMESTAMP WITH TIME ZONE
	,app_version VARCHAR(255)
	,anc_latest_date	DATE
	,anc_latest_num	INTEGER
	,anc1_abdominal_exam	VARCHAR(255)
	,anc1_abnormalities	VARCHAR(20)
	,anc1_blood_pressure	VARCHAR(255)
	,anc1_date	DATE
	,anc1_facility	VARCHAR(255)
	,anc1_details	VARCHAR(20)
	,anc2_abdominal_exam	VARCHAR(255)
	,anc2_abnormalities	VARCHAR(20)
	,anc2_blood_pressure	VARCHAR(255)
	,anc2_date	DATE
	,anc2_facility	VARCHAR(255)
	,anc2_details	VARCHAR(20)
	,anc3_abdominal_exam	VARCHAR(255)
	,anc3_abnormalities	VARCHAR(20)
	,anc3_blood_pressure	VARCHAR(255)
	,anc3_date	DATE
	,anc3_facility	VARCHAR(255)
	,anc3_details	VARCHAR(20)
	,anc4_abdominal_exam	VARCHAR(255)
	,anc4_abnormalities	VARCHAR(20)
	,anc4_blood_pressure	VARCHAR(255)
	,anc4_date	DATE
	,anc4_facility	VARCHAR(255)
	,anc4_details	VARCHAR(20)
	,counsel_ifa	VARCHAR(20)
	,counsel_tt	VARCHAR(20)
	,eating_extra	VARCHAR(20)
	,ifa_tablets_issued	SMALLINT
	,reason_no_ifa	VARCHAR(255)
	,received_tt1	VARCHAR(20)
	,received_tt2	VARCHAR(20)
	,resting	VARCHAR(20)
	,tt1_date	DATE
	,tt2_date	DATE
	,tt_booster	VARCHAR(20)
	,tt_booster_date	DATE
	,using_ifa	VARCHAR(20)
	,sba	VARCHAR(20)
	,sba_phone	VARCHAR(20)
	,accompany	VARCHAR(20)
	,care_of_home	VARCHAR(20)
	,clean_cloth	VARCHAR(20)
	,cord_care	VARCHAR(20)
	,counsel_home_delivery	VARCHAR(20)
	,counsel_institutional	VARCHAR(20)
	,counsel_preparation	VARCHAR(20)
	,danger_institution	VARCHAR(20)
	,danger_number	VARCHAR(20)
	,has_danger_signs	VARCHAR(20)
	,immediate_breastfeeding	VARCHAR(20)
	,inform_danger_signs	VARCHAR(20)
	,materials	VARCHAR(20)
	,maternal_danger_signs	VARCHAR(20)
	,now_institutional	VARCHAR(20)
	,phone_vehicle	VARCHAR(20)
	,play_birth_preparedness_vid	VARCHAR(20)
	,play_cord_care_vid	VARCHAR(20)
	,saving_money	VARCHAR(20)
	,skin_to_skin	VARCHAR(20)
	,vehicle	VARCHAR(20)
	,wrapping	VARCHAR(20)
	,bp_visit_num	SMALLINT
	,anc_1_date	DATE
	,anc_2_date	DATE
	,anc_3_date	DATE
	,anc_4_date	DATE
	,couple_interested	VARCHAR(255)
	,date_bp_1	DATE
	,date_bp_2	DATE
	,date_bp_3	DATE
	,date_last_visit	DATE
	,date_next_bp	DATE
	,delivery_type	VARCHAR(255)
	,ifa_tablets	SMALLINT
	,ifa_tablets_100	DATE
	,last_visit_type	VARCHAR(20)
	,maternal_emergency	VARCHAR(20)
	,maternal_emergency_number	VARCHAR(20)
	,tt_1_date	DATE
	,tt_2_date	DATE
	,conceive	VARCHAR(20)
	,del_fup	DATE
	,avail_immediate	VARCHAR(20)
	,counsel_accessible	VARCHAR(20)
	,counsel_benefits	VARCHAR(20)
	,counsel_disqualification	VARCHAR(20)
	,counsel_institution	VARCHAR(20)
	,counsel_methods	VARCHAR(20)
	,counsel_nearest	VARCHAR(20)
	,counsel_options	VARCHAR(20)
	,counsel_stay	VARCHAR(20)
	,immediate_appropriate	VARCHAR(20)
	,institution_immediate	VARCHAR(20)
	,postpone_conception	VARCHAR(20)
	,risk_of_preg	VARCHAR(20)
	,spacing_methods	VARCHAR(20)
	,stop_children	VARCHAR(15)
	,ifa_tablets_total	SMALLINT
	,nextvisittype	VARCHAR(20)
	,play_family_planning_vid	VARCHAR(20)
	,postponing	VARCHAR(15)
	,institutional VARCHAR(255)
	,delivery_offset_days INTEGER
    ,creation_time TIMESTAMP WITH TIME ZONE
);


CREATE TABLE report.pnc_mother_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50) UNIQUE
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.mother_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
    ,server_date_modified TIMESTAMP WITH TIME ZONE
	,app_version VARCHAR(255)
	,abdominal_pain	VARCHAR(20)
	,addval	DATE
	,adopt_immediately	VARCHAR(20)
	,all_pnc_on_time	VARCHAR(20)
	,bleeding	VARCHAR(20)
	,complications	VARCHAR(20)
	,congested	VARCHAR(20)
	,counsel_breast	VARCHAR(20)
	,counsel_follow_up_ppiud	VARCHAR(20)
	,counsel_follow_up_pptl	VARCHAR(20)
	,counsel_increase_food_bf	VARCHAR(20)
	,counsel_materal_comp	VARCHAR(20)
	,counsel_methods	VARCHAR(20)
	,counsel_neonatal_comp	VARCHAR(20)
	,counsel_ppfp	VARCHAR(20)
	,counsel_time_iud	VARCHAR(20)
	,date_death	DATE
	,date_iud_adopted	DATE
	,date_last_visit	DATE
	,date_next_eb	DATE
	,date_next_pnc	DATE
	,date_next_cf	DATE
	,date_pnc_1	DATE
	,date_pnc_2	DATE
	,date_pnc_3	DATE
	,date_tl_adopted	DATE
	,death_village	VARCHAR(20)
	,discharge	VARCHAR(20)
	,distension	VARCHAR(20)
	,eating_well	VARCHAR(20)
	,family_planning_type	VARCHAR(255)
	,fever	VARCHAR(20)
	,first_pnc_time	VARCHAR(255)
	,interval_ppfp_interest	VARCHAR(20)
	,iud	VARCHAR(20)
	,iud_adopted	VARCHAR(20)
	,iud_counsel_duration	VARCHAR(20)
	,iud_counsel_follow_up	VARCHAR(20)
	,iud_counsel_hospital	VARCHAR(20)
	,iud_counsel_placement	VARCHAR(20)
	,iud_counsel_screening	VARCHAR(20)
	,iud_counsel_side_effects	VARCHAR(20)
	,last_visit_type	VARCHAR(20)
	,mother_alive	VARCHAR(20)
	,mother_child_alive	VARCHAR(20)
	,nextvisittype	VARCHAR(20)
	,num_children	SMALLINT
	,other_issues	VARCHAR(20)
	,pain_urination	VARCHAR(20)
	,painful_nipples	VARCHAR(20)
	,place_death	VARCHAR(255)
	,pnc_1_days_late	INTEGER
	,pnc_2_days_late	INTEGER
	,pnc_3_days_late	INTEGER
	,pnc_visit_num	SMALLINT
	,ppfp_interest	BOOLEAN
	,ppiud_abdominal_pain	VARCHAR(20)
	,ppiud_bleeding	VARCHAR(20)
	,ppiud_discharge	VARCHAR(20)
	,ppiud_fever	VARCHAR(20)
	,ppiud_problems	VARCHAR(20)
	,pptl_abdominal_pain	VARCHAR(20)
	,pptl_excessive_bleeding	VARCHAR(20)
	,pptl_pain_surgery	VARCHAR(20)
	,pptl_problems	VARCHAR(20)
	,problems_breast	VARCHAR(20)
	,safe	VARCHAR(20)
	,site_death	VARCHAR(255)
	,tl	VARCHAR(20)
	,tl_adopted	VARCHAR(20)
	,tl_consel_incentives	VARCHAR(20)
	,tl_counsel_follow_up	VARCHAR(20)
	,tl_counsel_hospital	VARCHAR(20)
	,tl_counsel_irreversible	VARCHAR(20)
	,tl_counsel_screening	VARCHAR(20)
	,tl_counsel_side_effects	VARCHAR(20)
	,tl_counsel_timing	VARCHAR(20)
	,why_no_ppffp	VARCHAR(255)
	,delivery_offset_days INTEGER
    ,creation_time TIMESTAMP WITH TIME ZONE
);

CREATE TABLE report.pnc_child_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50)
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.child_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
    ,server_date_modified TIMESTAMP WITH TIME ZONE
	,app_version VARCHAR(255)
	,able_expressed_milk	VARCHAR(20)
	,adequate_support	VARCHAR(20)
	,applied_to_stump	VARCHAR(20)
	,baby_active	VARCHAR(20)
	,breastfeeding_well	VARCHAR(20)
	,child_alive	VARCHAR(20)
	,child_died_village	VARCHAR(20)
	,child_place_death	VARCHAR(255)
	,child_site_death	VARCHAR(255)
	,chld_date_death	DATE	
	,cord_fallen	VARCHAR(20)
	,correct_position	VARCHAR(20)
	,counsel_cord_care	VARCHAR(20)
	,counsel_exclusive_bf	VARCHAR(20)
	,counsel_express_milk	VARCHAR(20)
	,counsel_skin	VARCHAR(20)
	,cousel_bf_correct	VARCHAR(20)
	,demonstrate_expressed	VARCHAR(20)
	,demonstrate_skin	VARCHAR(20)
	,easy_awake	VARCHAR(20)
	,feed_vigour	VARCHAR(20)
	,good_latch	VARCHAR(20)
	,improvements_bf	VARCHAR(20)
	,observed_bf	VARCHAR(20)
	,other_milk_to_child	VARCHAR(20)
	,second_observation	VARCHAR(20)
	,skin_to_skin	VARCHAR(20)
	,warm_to_touch	VARCHAR(20)
	,what_applied	VARCHAR(255)
	,wrapped	VARCHAR(20)
    ,close	BOOLEAN
    ,delivery_offset_days INTEGER
	,UNIQUE(instance_id, case_id)
    ,creation_time TIMESTAMP WITH TIME ZONE
);

CREATE TABLE report.ebf_mother_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50) UNIQUE
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.mother_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
    ,server_date_modified TIMESTAMP WITH TIME ZONE
	,app_version VARCHAR(255)
	,addval	DATE
	,adopt_immediately	VARCHAR(20)
	,ask_ppiud	VARCHAR(20)
	,aware_of_failure	VARCHAR(20)
	,bleeding	VARCHAR(20)
	,complications	VARCHAR(20)
	,condoms	VARCHAR(20)
	,counsel_follow_up_ppiud	VARCHAR(20)
	,counsel_follow_up_pptl	VARCHAR(20)
	,counsel_menstrual_cycle	VARCHAR(20)
	,counsel_methods	VARCHAR(20)
	,counsel_ppfp	VARCHAR(20)
	,counsel_time_iud	VARCHAR(20)
	,date_eb_1	DATE
	,date_eb_2	DATE
	,date_eb_3	DATE
	,date_eb_4	DATE
	,date_eb_5	DATE
	,date_eb_6	DATE
	,date_iud_adopted	DATE
	,date_last_inj	DATE
	,date_last_visit	DATE
	,date_next_cf	DATE
	,date_next_eb	DATE
	,discharge	VARCHAR(20)
	,distension	VARCHAR(20)
	,eb_visit_num	SMALLINT
	,family_planning_type	VARCHAR(255)
	,fever	VARCHAR(20)
	,have_condoms	VARCHAR(20)
	,headaches	VARCHAR(20)
	,high_bp	VARCHAR(20)
	,inj_menstrual_irregularity	VARCHAR(20)
	,injectable	VARCHAR(20)
	,intend_to_continue	VARCHAR(20)
	,interval_ppfp_interest	VARCHAR(20)
	,iud	VARCHAR(20)
	,iud_adopted	VARCHAR(20)
	,iud_counsel_duration	VARCHAR(20)
	,iud_counsel_follow_up	VARCHAR(20)
	,iud_counsel_hospital	VARCHAR(20)
	,iud_counsel_placement	VARCHAR(20)
	,iud_counsel_screening	VARCHAR(20)
	,iud_counsel_side_effects	VARCHAR(20)
	,last_visit_type	VARCHAR(20)
	,menstrual_irregularity	VARCHAR(20)
	,next_inj_calc	DATE
	,nextvisittype	VARCHAR(20)
	,num_children	SMALLINT
	,ocp	VARCHAR(20)
	,ocp_continue	VARCHAR(20)
	,ocp_counsel_regularity	VARCHAR(20)
	,pain_swelling	VARCHAR(20)
	,ppfp_interest	BOOLEAN
	,ppiud_abdominal_pain	VARCHAR(20)
	,ppiud_problems	VARCHAR(20)
	,pptl_abdominal_pain	VARCHAR(20)
	,pptl_pain_surgery	VARCHAR(20)
	,pptl_problems	VARCHAR(20)
	,regular_periods	VARCHAR(20)
	,tablets_received	VARCHAR(20)
	,taken_as_prescribed	VARCHAR(20)
	,tl	VARCHAR(20)
	,tl_adopted	VARCHAR(20)
	,tl_consel_incentives	VARCHAR(20)
	,tl_counsel_follow_up	VARCHAR(20)
	,tl_counsel_hospital	VARCHAR(20)
	,tl_counsel_irreversible	VARCHAR(20)
	,tl_counsel_screening	VARCHAR(20)
	,tl_counsel_side_effects	VARCHAR(20)
	,tl_counsel_timing	VARCHAR(20)
	,understand_tablets	VARCHAR(20)
	,using_correctly	VARCHAR(20)
	,where_replace	VARCHAR(20)
	,why_no_ppffp	VARCHAR(255)
	,within_42	VARCHAR(20)
	,date_tl_adopted DATE
	,abdominal_pain VARCHAR(20)
    ,pain_urination VARCHAR(20)
    ,ppiud_bleeding VARCHAR(20)
    ,ppiud_discharge VARCHAR(20)
    ,ppiud_fever VARCHAR(20)
    ,delivery_offset_days INTEGER
    ,creation_time TIMESTAMP WITH TIME ZONE
);

CREATE TABLE report.ebf_child_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50)
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.child_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
    ,server_date_modified TIMESTAMP WITH TIME ZONE
	,app_version VARCHAR(255)
	,add_vaccinations	VARCHAR(20)
	,at_night	VARCHAR(20)
	,baby_bcg	VARCHAR(20)
	,baby_dpt1	VARCHAR(20)
	,baby_dpt2	VARCHAR(20)
	,baby_dpt3	VARCHAR(20)
	,baby_hep_b_0	VARCHAR(20)
	,baby_hep_b_1	VARCHAR(20)
	,baby_hep_b_2	VARCHAR(20)
	,baby_hep_b_3	VARCHAR(20)
	,baby_opv0	VARCHAR(20)
	,baby_opv1	VARCHAR(20)
	,baby_opv2	VARCHAR(20)
	,baby_opv3	VARCHAR(20)
	,bcg_date	DATE
	,breastfeeding	VARCHAR(20)
	,case_name	VARCHAR(255)
	,child_name	VARCHAR(255)
	,counsel_adequate_bf	VARCHAR(20)
	,counsel_only_milk	VARCHAR(20)
	,counsel_stop_bottle	VARCHAR(20)
	,dpt_1_date	DATE
	,dpt_2_date	DATE
	,dpt_3_date	DATE
	,eating	VARCHAR(20)
	,emptying	VARCHAR(20)
	,feeding_bottle	VARCHAR(20)
	,hep_b_0_date	DATE
	,hep_b_1_date	DATE
	,hep_b_2_date	DATE
	,hep_b_3_date	DATE
	,more_feeding_less_six	VARCHAR(20)
	,name_update	VARCHAR(20)
	,not_breasfeeding	VARCHAR(255)
	,on_demand	VARCHAR(20)
	,opv_0_date	DATE
	,opv_1_date	DATE
	,opv_2_date	DATE
	,opv_3_date	DATE
	,recent_fever	VARCHAR(20)
	,tea_other	VARCHAR(20)
	,treated_less_six	VARCHAR(20)
	,water_or_milk	VARCHAR(20)
	,delivery_offset_days INTEGER
    ,creation_time TIMESTAMP WITH TIME ZONE
    ,UNIQUE(instance_id, case_id)
);

CREATE TABLE report.cf_mother_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50) UNIQUE
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.mother_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
    ,server_date_modified TIMESTAMP WITH TIME ZONE
	,app_version VARCHAR(255)
	,date_cf_1	DATE
	,date_cf_2	DATE
	,date_cf_3	DATE
	,date_cf_4	DATE
	,date_cf_5	DATE
	,date_cf_6	DATE
	,date_last_visit	DATE
	,date_next_cf	DATE
	,last_visit_type	VARCHAR(20)
	,cf_visit_num	SMALLINT
	,num_children	SMALLINT
	,play_comp_feeding_vid	VARCHAR(20)
	,lastvisit VARCHAR(20)
    ,date_cf_7 DATE
	,confirm_close VARCHAR(20)
    ,close	BOOLEAN
    ,delivery_offset_days INTEGER
    ,creation_time TIMESTAMP WITH TIME ZONE
);

CREATE TABLE report.cf_child_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50)
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.child_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
    ,server_date_modified TIMESTAMP WITH TIME ZONE
	,app_version VARCHAR(255)
	,add_vaccinations	VARCHAR(20)
	,amount_good	VARCHAR(20)
	,baby_bcg	VARCHAR(20)
	,baby_dpt1	VARCHAR(20)
	,baby_dpt2	VARCHAR(20)
	,baby_dpt3	VARCHAR(20)
	,baby_hep_b_0	VARCHAR(20)
	,baby_hep_b_1	VARCHAR(20)
	,baby_hep_b_2	VARCHAR(20)
	,baby_hep_b_3	VARCHAR(20)
	,baby_measles	VARCHAR(20)
	,baby_opv0	VARCHAR(20)
	,baby_opv1	VARCHAR(20)
	,baby_opv2	VARCHAR(20)
	,baby_opv3	VARCHAR(20)
	,baby_vita1	VARCHAR(20)
	,bcg_date	DATE
	,case_name	VARCHAR(255)
	,dpt_1_date	DATE
	,dpt_2_date	DATE
	,dpt_3_date	DATE
	,hep_b_0_date	DATE
	,hep_b_1_date	DATE
	,hep_b_2_date	DATE
	,hep_b_3_date	DATE
	,measles_date	DATE
	,opv_0_date	DATE
	,opv_1_date	DATE
	,opv_2_date	DATE
	,opv_3_date	DATE
	,vit_a_1_date	DATE
	,dal	VARCHAR(20)
	,eaten_cereal	VARCHAR(20)
	,egg	VARCHAR(20)
	,fish	VARCHAR(20)
	,meat	VARCHAR(20)
	,milk_curd	VARCHAR(20)
	,more_feeding_less_six	VARCHAR(20)
	,name_update	VARCHAR(20)
	,new_name	VARCHAR(255)
	,number_good	VARCHAR(20)
	,oil_ghee	VARCHAR(20)
	,recent_fever	VARCHAR(20)
	,treated_less_six	VARCHAR(20)
    ,baby_dpt_booster VARCHAR(20)
    ,baby_je VARCHAR(20)
    ,baby_measles_booster VARCHAR(20)
    ,baby_opv_booster VARCHAR(20)
    ,baby_vita2 VARCHAR(20)
    ,baby_vita3 VARCHAR(20)
    ,date_je DATE
    ,date_measles_booster DATE
    ,dpt_booster_date DATE
    ,opv_booster_date DATE
    ,vit_a_3_date DATE
    ,vit_a_2_date DATE
	,close	BOOLEAN
    ,delivery_offset_days INTEGER
    ,creation_time TIMESTAMP WITH TIME ZONE
    ,UNIQUE(instance_id, case_id)
);


CREATE TABLE report.delivery_mother_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50) UNIQUE
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.mother_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
    ,server_date_modified TIMESTAMP WITH TIME ZONE
	,app_version VARCHAR(255)
	,PPIUD	VARCHAR(20)
	,PPTL	VARCHAR(20)
	,abd_pain	VARCHAR(20)
	,add	DATE
	,birth_place	VARCHAR(25)
	,date_del_fu	DATE
	,date_last_visit	DATE
	,date_next_cf	DATE
	,date_next_eb	DATE
	,date_next_pnc	DATE
	,family_planning_type	VARCHAR(50)
	,last_visit_type	VARCHAR(255)
	,mother_alive	VARCHAR(20)
	,term	VARCHAR(50)
	,cast_num_children	SMALLINT
	,complications	VARCHAR(20)
	,date_death	DATE
	,death_village	VARCHAR(20)
	,delivery_nature	VARCHAR(50)
	,fever	VARCHAR(20)
	,has_delivered	VARCHAR(20)
	,how_many_children	SMALLINT
	,ifa_tablets_given	VARCHAR(20)
	,in_district	VARCHAR(20)
	,jsy_money	VARCHAR(20)
	,nextvisittype	VARCHAR(255)
	,notified	DATE
	,num_children	SMALLINT
	,other_conditions	VARCHAR(20)
	,other_district	VARCHAR(255)
	,other_village	VARCHAR(255)
	,pain_urine	VARCHAR(20)
	,place_death	VARCHAR(255)
	,post_postpartum_fp	VARCHAR(20)
	,safe	VARCHAR(20)
	,site_death	VARCHAR(255)
	,vaginal_discharge	VARCHAR(20)
	,where_born	VARCHAR(50)
	,which_hospital	VARCHAR(255)
	,which_village	VARCHAR(255)
    ,close	BOOLEAN
    ,delivery_offset_days INTEGER
    ,creation_time TIMESTAMP WITH TIME ZONE
);

CREATE TABLE report.delivery_child_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50)
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.child_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
    ,server_date_modified TIMESTAMP WITH TIME ZONE
	,app_version VARCHAR(255)
	,abnormalities 	VARCHAR(20)
	,add_vaccinations 	VARCHAR(20)
	,baby_bcg 	VARCHAR(20)
	,baby_hep_b_0 	VARCHAR(20)
	,baby_opv0 	VARCHAR(20)
	,breastfed_hour 	VARCHAR(20)
	,case_name 	VARCHAR(255)
	,case_type 	VARCHAR(255)
	,baby_weight 	VARCHAR(20)
	,bcg_date 	DATE
	,birth_status 	VARCHAR(255)
	,dob 	DATE
	,gender 	VARCHAR(25)
	,hep_b_0_date 	DATE
	,opv_0_date 	DATE
	,term 	VARCHAR(50)
	,time_of_birth 	VARCHAR(25)
	,child_alive 	VARCHAR(20)
	,child_breathing 	VARCHAR(25)
	,child_cried 	VARCHAR(20)
	,child_died_village 	VARCHAR(20)
	,child_have_a_name 	VARCHAR(20)
	,child_heartbeats 	VARCHAR(25)
	,child_movement 	VARCHAR(20)
	,child_name 	VARCHAR(25)
	,child_place_death 	VARCHAR(25)
	,child_site_death 	VARCHAR(50)
	,chld_date_death 	DATE
	,cord_applied 	VARCHAR(20)
	,cord_cut 	VARCHAR(20)
	,cord_tied 	VARCHAR(20)
	,date_first_weight 	DATE
	,date_time_feed 	DATE
	,first_weight 	DECIMAL
	,skin_care 	VARCHAR(20)
	,what_applied 	VARCHAR(255)
	,wrapped_dried 	VARCHAR(20)
    ,close	BOOLEAN
    ,creation_time TIMESTAMP WITH TIME ZONE
    ,delivery_offset_days INTEGER
	,UNIQUE(instance_id, case_id)
);

CREATE TABLE report.death_mother_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50) UNIQUE
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.mother_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
    ,server_date_modified TIMESTAMP WITH TIME ZONE
	,app_version VARCHAR(255)
	,mother_alive	VARCHAR(20)
	,status	VARCHAR(255)
	,cast_num_children	SMALLINT
	,date_death	DATE
	,death_village	VARCHAR(20)
	,num_children	SMALLINT
	,place_death	VARCHAR(255)
	,site_death	VARCHAR(255)
    ,close	BOOLEAN
    ,delivery_offset_days INTEGER
    ,creation_time TIMESTAMP WITH TIME ZONE
);

CREATE TABLE report.death_child_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50)
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.child_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
    ,server_date_modified TIMESTAMP WITH TIME ZONE
	,app_version VARCHAR(255)
	,child_alive	VARCHAR(20)
	,child_died_village	VARCHAR(20)
	,child_place_death	VARCHAR(255)
	,child_site_death	VARCHAR(255)
	,chld_date_death	DATE
	,delivery_offset_days INTEGER
    ,creation_time TIMESTAMP WITH TIME ZONE
    ,close	BOOLEAN
    ,UNIQUE(instance_id, case_id)
);

CREATE TABLE report.close_mother_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50) UNIQUE
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.mother_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
    ,server_date_modified TIMESTAMP WITH TIME ZONE
	,app_version VARCHAR(255)
	,close_mother	VARCHAR(20)
	,confirm_close	VARCHAR(20)
	,death_village	VARCHAR(20)
	,died_village	VARCHAR(255)
	,place_death VARCHAR(255)
	,dupe_reg	VARCHAR(20)
	,finished_continuum	VARCHAR(20)
	,num_children	SMALLINT
	,mother_alive VARCHAR(20)
    ,moved VARCHAR(20)
    ,migrated VARCHAR(20)
    ,date_learned DATE
    ,date_left DATE
    ,migration_note VARCHAR(20)
    ,died VARCHAR(20)
    ,date_death DATE
    ,site_death VARCHAR(255)
    ,status VARCHAR(255)
    ,close	BOOLEAN
    ,delivery_offset_days INTEGER
    ,creation_time TIMESTAMP WITH TIME ZONE
);

CREATE TABLE report.close_child_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50)
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.child_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
    ,server_date_modified TIMESTAMP WITH TIME ZONE
	,app_version VARCHAR(255)
	,child_alive	VARCHAR(20)
	,close_child	VARCHAR(20)
	,confirm_close	VARCHAR(20)
	,date_death	DATE
	,died	VARCHAR(20)
	,died_village	VARCHAR(20)
	,dupe_reg	VARCHAR(20)
	,finished_continuum	VARCHAR(20)
	,site_death	VARCHAR(255)
	,place_death VARCHAR(255)
	,delivery_offset_days INTEGER
    ,creation_time TIMESTAMP WITH TIME ZONE
    ,close	BOOLEAN
    ,UNIQUE(instance_id, case_id)
);

CREATE TABLE report.refer_mother_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50) UNIQUE
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.mother_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
    ,server_date_modified TIMESTAMP WITH TIME ZONE
	,app_version VARCHAR(255)
	,num_children	SMALLINT
	,refer_mother	VARCHAR(20)
	,delivery_offset_days INTEGER
    ,creation_time TIMESTAMP WITH TIME ZONE
);

CREATE TABLE report.refer_child_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50)
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.child_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
    ,server_date_modified TIMESTAMP WITH TIME ZONE
	,app_version VARCHAR(255)
	,refer_child	VARCHAR(20)
	,delivery_offset_days INTEGER
    ,creation_time TIMESTAMP WITH TIME ZONE
	,UNIQUE(instance_id, case_id)
);


CREATE TABLE report.ui_mother_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50) UNIQUE
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.mother_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
    ,server_date_modified TIMESTAMP WITH TIME ZONE
	,app_version VARCHAR(255)
	,details_available	VARCHAR(20)
	,tt_1_date	DATE
	,tt_2_date	DATE
	,tt_booster_date	DATE
	,received_tt1	VARCHAR(20)
	,received_tt2	VARCHAR(20)
	,up_to_date	VARCHAR(15)
	,num_children	SMALLINT
	,update_mother	VARCHAR(20)
	,tt_booster VARCHAR(20)
	,delivery_offset_days INTEGER
    ,creation_time TIMESTAMP WITH TIME ZONE
);

CREATE TABLE report.ui_child_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50)
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.child_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
    ,server_date_modified TIMESTAMP WITH TIME ZONE
	,app_version VARCHAR(255)
	,add_vaccinations	VARCHAR(20)
	,baby_bcg	VARCHAR(20)
	,baby_dpt1	VARCHAR(20)
	,baby_dpt2	VARCHAR(20)
	,baby_dpt3	VARCHAR(20)
	,baby_hep_b_0	VARCHAR(20)
	,baby_hep_b_1	VARCHAR(20)
	,baby_hep_b_2	VARCHAR(20)
	,baby_hep_b_3	VARCHAR(20)
	,baby_measles	VARCHAR(20)
	,baby_opv0	VARCHAR(20)
	,baby_opv1	VARCHAR(20)
	,baby_opv2	VARCHAR(20)
	,baby_opv3	VARCHAR(20)
	,baby_vita1	VARCHAR(20)
	,bcg_date	DATE
	,dpt_1_date	DATE
	,dpt_2_date	DATE
	,dpt_3_date	DATE
	,dpt_booster_date	DATE
	,hep_b_0_date	DATE
	,hep_b_1_date	DATE
	,hep_b_2_date	DATE
	,hep_b_3_date	DATE
	,measles_date	DATE
	,opv_0_date	DATE
	,opv_1_date	DATE
	,opv_2_date	DATE
	,opv_3_date	DATE
	,opv_booster_date	DATE
	,vit_a_1_date	DATE
	,baby_dpt_booster VARCHAR(20)
    ,baby_je VARCHAR(20)
    ,baby_measles_booster VARCHAR(20)
    ,baby_opv_booster VARCHAR(20)
    ,baby_vita2 VARCHAR(20)
    ,baby_vita3 VARCHAR(20)
    ,date_je DATE
    ,date_measles_booster DATE
    ,vit_a_2_date DATE
    ,vit_a_3_date DATE
	,delivery_offset_days INTEGER
    ,creation_time TIMESTAMP WITH TIME ZONE
    ,UNIQUE(instance_id, case_id)
);

CREATE TABLE report.abort_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50) UNIQUE
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.mother_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
    ,server_date_modified TIMESTAMP WITH TIME ZONE
	,app_version VARCHAR(255)
	,abortion_type	VARCHAR(255)
	,birth_status	VARCHAR(255)
	,date_aborted	DATE
    ,close	BOOLEAN
	,delivery_offset_days INTEGER
    ,creation_time TIMESTAMP WITH TIME ZONE
);

CREATE TABLE report.mo_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50) UNIQUE
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.mother_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
    ,server_date_modified TIMESTAMP WITH TIME ZONE
	,app_version VARCHAR(255)
	,migrate_out_date	DATE
	,migrated_status	VARCHAR(255)
	,status	VARCHAR(255)
	,date_learned	DATE
	,date_left	DATE
	,name	VARCHAR(255)
	,note_given	VARCHAR(20)
	,delivery_offset_days INTEGER
    ,creation_time TIMESTAMP WITH TIME ZONE
);

CREATE TABLE report.mi_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50) UNIQUE
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.mother_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
    ,server_date_modified TIMESTAMP WITH TIME ZONE
	,app_version VARCHAR(255)
	,date_arrived	DATE
	,date_learned	DATE
	,date_of_delivery	DATE
	,name	VARCHAR(255)
	,preg_status	VARCHAR(255)
	,referral_info	VARCHAR(255)
	,abortion_type VARCHAR(255)
    ,date_aborted DATE
    ,migrated_status VARCHAR(255)
	,delivery_offset_days INTEGER
	,status VARCHAR(255)
    ,creation_time TIMESTAMP WITH TIME ZONE
);

CREATE TABLE report.move_beneficiary_form(
   id SERIAL PRIMARY KEY
  ,instance_id VARCHAR(50) UNIQUE
  ,time_end TIMESTAMP WITH TIME ZONE
  ,time_start TIMESTAMP WITH TIME ZONE
  ,user_id INTEGER REFERENCES report.flw(id)
  ,case_id	INTEGER REFERENCES report.mother_case(id)
  ,date_modified	TIMESTAMP WITH TIME ZONE
  ,server_date_modified TIMESTAMP WITH TIME ZONE
  ,app_version VARCHAR(255)
  ,creation_time TIMESTAMP WITH TIME ZONE
  ,confirm_move VARCHAR(20)
  ,new_ward INTEGER
  ,new_awcc INTEGER
  ,confirm_again VARCHAR(20)
  ,delivery_offset_days INTEGER
);

CREATE TABLE report.mother_edit_form(
   id SERIAL PRIMARY KEY
  ,instance_id VARCHAR(50) UNIQUE
  ,time_end TIMESTAMP WITH TIME ZONE
  ,time_start TIMESTAMP WITH TIME ZONE
  ,user_id INTEGER REFERENCES report.flw(id)
  ,case_id	INTEGER REFERENCES report.mother_case(id)
  ,date_modified	TIMESTAMP WITH TIME ZONE
  ,server_date_modified TIMESTAMP WITH TIME ZONE
  ,app_version VARCHAR(255)
  ,creation_time TIMESTAMP WITH TIME ZONE
  ,case_name VARCHAR(255)
  ,age INTEGER
  ,mother_name VARCHAR(255)
  ,update_mother_name VARCHAR(20)
  ,hh_number INTEGER
  ,update_hh_number VARCHAR(20)
  ,mother_dob DATE
  ,update_mother_dob VARCHAR(20)
  ,husband_name VARCHAR(255)
  ,update_husband_name VARCHAR(20)
  ,family_number INTEGER
  ,update_family_number VARCHAR(20)
  ,mobile_number VARCHAR(20)
  ,update_mobile_number VARCHAR(20)
  ,mobile_number_whose VARCHAR(255)
  ,update_mobile_number_whose VARCHAR(20)
  ,ward_number INTEGER
  ,update_ward_number VARCHAR(20)
  ,delivery_offset_days INTEGER
);

