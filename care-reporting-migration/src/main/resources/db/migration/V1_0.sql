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
	   ,age SMALLINT
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
	,server_date_opened TIMESTAMP WITH TIME ZONE
	,family_number	INTEGER
	,hh_number	INTEGER
	,husband_name	VARCHAR(255)
	,last_visit_type	INTEGER
	,mother_alive	BOOLEAN
	,mother_dob	DATE
	,mother_name	VARCHAR(255)
	,close	 BOOLEAN
	,case_closed	BOOLEAN
	,closed_on	DATE
	,add	DATE
	,age	SMALLINT
	,birth_place	VARCHAR(255)
	,complications	BOOLEAN
	,date_next_bp	DATE
	,date_next_cf	DATE
	,date_next_eb	DATE
	,date_next_pnc	DATE
	,eats_meat	BOOLEAN
	,edd	DATE
	,enrolled_in_kilkari	BOOLEAN
	,family_planning_type	VARCHAR(255)
	,how_many_children	SMALLINT
	,interest_in_kilkari	BOOLEAN
	,last_preg_tt	BOOLEAN
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
	,all_pnc_on_time	BOOLEAN
	,date_pnc_1	DATE
	,date_pnc_2	DATE
	,date_pnc_3	DATE
	,first_pnc_time	VARCHAR(255)
	,pnc_1_days_late	INTEGER
	,pnc_2_days_late	INTEGER
	,pnc_3_days_late	INTEGER
	,tt_booster_date	DATE
	,sba	BOOLEAN
	,sba_phone	BOOLEAN
	,accompany	BOOLEAN
	,anc_1_date	DATE
	,anc_2_date	DATE
	,anc_3_date	DATE
	,anc_4_date	DATE
	,clean_cloth	BOOLEAN
	,couple_interested	VARCHAR(15)
	,date_bp_1	DATE
	,date_bp_2	DATE
	,date_bp_3	DATE
	,date_last_visit	DATE
	,delivery_type	VARCHAR(255)
	,ifa_tablets	SMALLINT
	,ifa_tablets_100	DATE
	,materials	BOOLEAN
	,maternal_emergency	BOOLEAN
	,maternal_emergency_number	BOOLEAN
	,phone_vehicle	BOOLEAN
	,saving_money	BOOLEAN
	,tt_1_date	DATE
	,tt_2_date	DATE
	,vehicle	BOOLEAN
	,birth_status	VARCHAR(255)
	,migrate_out_date	DATE
	,migrated_status	VARCHAR(255)
	,status	VARCHAR(255)
	,term VARCHAR(25)
	,date_cf_7 DATE
	,date_del_fu DATE
	,date_next_reg DATE
	,institutional BOOLEAN
	,dob DATE
	,closed BOOLEAN
	,date_closed DATE
    ,creation_time TIMESTAMP WITH TIME ZONE
    ,last_modified_time TIMESTAMP WITH TIME ZONE
);

CREATE TABLE report.child_case (
	id SERIAL PRIMARY KEY
	,case_id	VARCHAR(50)
	,case_name	VARCHAR(255)
	,date_modified	TIMESTAMP WITH TIME ZONE	
	,server_date_modified TIMESTAMP WITH TIME ZONE
	,server_date_opened TIMESTAMP WITH TIME ZONE
    ,mother_id	INTEGER REFERENCES report.mother_case(id)	
	,case_type	VARCHAR(255)
	,owner_id	INTEGER REFERENCES report.flw_group(id)	
	,user_id INTEGER REFERENCES report.flw(id)
	,baby_measles	BOOLEAN	
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
	,child_alive	BOOLEAN	
	,dpt_booster_date	DATE
	,opv_booster_date	DATE
	,date_je DATE
	,date_measles_booster DATE
	,baby_weight DECIMAL
	,name VARCHAR(255)
	,term VARCHAR(50)
	,time_of_birth VARCHAR(25)
	,vit_a_2_date DATE
	,vit_a_3_date DATE
	,closed BOOLEAN
	,date_closed DATE
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
	,age_calc	SMALLINT
	,case_name	VARCHAR(255)
	,case_type	VARCHAR(255)
	,date_last_visit	DATE
	,date_next_reg	DATE
	,family_number	INTEGER
	,hh_number	INTEGER
	,husband_name	VARCHAR(255)
	,last_visit_type	VARCHAR(20)
	,mother_alive	BOOLEAN
	,mother_dob	DATE
	,mother_name	VARCHAR(255)
	,caste	VARCHAR(255)
	,dob	DATE
	,dob_known	BOOLEAN
	,full_name	VARCHAR(255)
	,manual_group	INTEGER REFERENCES report.flw_group(id)
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
	,ppiud	BOOLEAN
	,pptl	BOOLEAN
	,abd_pain	BOOLEAN
	,age_calc	SMALLINT
	,age_calc_adj	SMALLINT
	,age_est	SMALLINT
	,age_est_trigger	VARCHAR(10)
	,close	VARCHAR(10)
	,add	DATE
	,age	SMALLINT
	,birth_place	VARCHAR(255)
	,complications	BOOLEAN
	,date_last_visit	DATE
	,date_next_bp	DATE
	,date_next_cf	DATE
	,date_next_eb	DATE
	,date_next_pnc	DATE
	,eats_meat	BOOLEAN
	,edd	DATE
	,enrolled_in_kilkari	BOOLEAN
	,family_planning_type	VARCHAR(255)
	,how_many_children	SMALLINT
	,interest_in_kilkari	BOOLEAN
	,last_preg_tt	BOOLEAN
	,last_visit_type	VARCHAR(20)
	,lmp	DATE
	,mobile_number	VARCHAR(20)
	,mother_dob	 DATE
	,num_boys	SMALLINT
	,status	VARCHAR(255)
	,child_dob	DATE
	,client_no_register	VARCHAR(10)
	,client_not_pregnant	VARCHAR(10)
	,clinical_exam	BOOLEAN
	,condoms	BOOLEAN
	,continue_preg	BOOLEAN
	,delivery_nature	VARCHAR(255)
	,dob_est	VARCHAR(255)
	,edd_calc	DATE
	,edd_known	BOOLEAN
	,education	VARCHAR(255)
	,fever	BOOLEAN
	,first_pregnancy	BOOLEAN
	,gest_age	SMALLINT
	,good_to_register	BOOLEAN
	,in_district	BOOLEAN
	,injectible	BOOLEAN
	,is_pregnant	BOOLEAN
	,iud_used	BOOLEAN
	,jsy_beneficiary	BOOLEAN
	,jsy_money	BOOLEAN
	,last_preg	INTEGER
	,last_preg_c_section	BOOLEAN
	,last_preg_full_term	BOOLEAN
	,lmp_calc	DATE
	,lmp_known	BOOLEAN
	,missed_period	BOOLEAN
	,mobile_number_whose	VARCHAR(255)
	,nextvisit	VARCHAR(10)
	,nextvisit_bp	VARCHAR(10)
	,nextvisittype	VARCHAR(20)
	,num_children	SMALLINT
	,num_girls	SMALLINT
	,ocp_used	BOOLEAN
	,other_conditions	VARCHAR(255)
	,other_district	VARCHAR(255)
	,other_village	VARCHAR(255)
	,pain_urine	BOOLEAN
	,post_postpartum_fp	BOOLEAN
	,preg_desired	BOOLEAN
	,recently_delivered	BOOLEAN
	,referral_prompt	VARCHAR(255)
	,resident	VARCHAR(255)
	,success	VARCHAR(10)
	,urine_test	BOOLEAN
	,used_fp	BOOLEAN
	,vaginal_discharge	BOOLEAN
	,vegetarian	BOOLEAN
	,where_born	VARCHAR(255)
	,which_hospital	VARCHAR(255)
	,which_village	VARCHAR(255)
    ,creation_time TIMESTAMP WITH TIME ZONE
);

CREATE TABLE report.registration_child_form (
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50)
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.child_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
	,abnormalities	BOOLEAN
	,add_vaccinations	BOOLEAN
	,baby_bcg	BOOLEAN
	,baby_dpt1	BOOLEAN
	,baby_dpt2	BOOLEAN
	,baby_dpt3	BOOLEAN
	,baby_hep_b_0	BOOLEAN
	,baby_hep_b_1	BOOLEAN
	,baby_hep_b_2	BOOLEAN
	,baby_hep_b_3	BOOLEAN
	,baby_measles	BOOLEAN
	,baby_opv0	BOOLEAN
	,baby_opv1	BOOLEAN
	,baby_opv2	BOOLEAN
	,baby_opv3	BOOLEAN
	,baby_vita1	BOOLEAN
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
	,child_have_a_name	BOOLEAN
	,child_name	VARCHAR(255)
	,weight	DECIMAL
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
	,anc_latest_date	DATE
	,anc_latest_num	INTEGER
	,anc1_abdominal_exam	VARCHAR(255)
	,anc1_abnormalities	BOOLEAN
	,anc1_blood_pressure	VARCHAR(255)
	,anc1_date	DATE
	,anc1_facility	VARCHAR(255)
	,anc1_details	BOOLEAN
	,anc2_abdominal_exam	VARCHAR(255)
	,anc2_abnormalities	BOOLEAN
	,anc2_blood_pressure	VARCHAR(255)
	,anc2_date	DATE
	,anc2_facility	VARCHAR(255)
	,anc2_details	BOOLEAN
	,anc3_abdominal_exam	VARCHAR(255)
	,anc3_abnormalities	BOOLEAN
	,anc3_blood_pressure	VARCHAR(255)
	,anc3_date	DATE
	,anc3_facility	VARCHAR(255)
	,anc3_details	BOOLEAN
	,anc4_abdominal_exam	VARCHAR(255)
	,anc4_abnormalities	BOOLEAN
	,anc4_blood_pressure	VARCHAR(255)
	,anc4_date	DATE
	,anc4_facility	VARCHAR(255)
	,anc4_details	BOOLEAN
	,counsel_ifa	BOOLEAN
	,counsel_tt	BOOLEAN
	,eating_extra	BOOLEAN
	,ifa_tablets_issued	SMALLINT
	,reason_no_ifa	VARCHAR(255)
	,received_tt1	BOOLEAN
	,received_tt2	BOOLEAN
	,resting	BOOLEAN
	,tt1_date	DATE
	,tt2_date	DATE
	,tt_booster	BOOLEAN
	,tt_booster_date	DATE
	,using_ifa	BOOLEAN
	,sba	BOOLEAN
	,sba_phone	BOOLEAN
	,accompany	BOOLEAN
	,care_of_home	BOOLEAN
	,clean_cloth	BOOLEAN
	,cord_care	BOOLEAN
	,counsel_home_delivery	BOOLEAN
	,counsel_institutional	BOOLEAN
	,counsel_preparation	BOOLEAN
	,danger_institution	BOOLEAN
	,danger_number	BOOLEAN
	,has_danger_signs	BOOLEAN
	,immediate_breastfeeding	BOOLEAN
	,inform_danger_signs	BOOLEAN
	,materials	BOOLEAN
	,maternal_danger_signs	BOOLEAN
	,now_institutional	BOOLEAN
	,phone_vehicle	BOOLEAN
	,play_birth_preparedness_vid	BOOLEAN
	,play_cord_care_vid	BOOLEAN
	,saving_money	BOOLEAN
	,skin_to_skin	BOOLEAN
	,vehicle	BOOLEAN
	,wrapping	BOOLEAN
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
	,maternal_emergency	BOOLEAN
	,maternal_emergency_number	BOOLEAN
	,tt_1_date	DATE
	,tt_2_date	DATE
	,conceive	BOOLEAN
	,del_fup	INTEGER
	,avail_immediate	BOOLEAN
	,counsel_accessible	BOOLEAN
	,counsel_benefits	BOOLEAN
	,counsel_disqualification	BOOLEAN
	,counsel_institution	BOOLEAN
	,counsel_methods	BOOLEAN
	,counsel_nearest	BOOLEAN
	,counsel_options	BOOLEAN
	,counsel_stay	BOOLEAN
	,immediate_appropriate	BOOLEAN
	,institution_immediate	BOOLEAN
	,postpone_conception	BOOLEAN
	,risk_of_preg	BOOLEAN
	,spacing_methods	BOOLEAN
	,stop_children	VARCHAR(15)
	,ifa_tablets_total	SMALLINT
	,nextvisittype	VARCHAR(20)
	,play_family_planning_vid	BOOLEAN
	,postponing	VARCHAR(15)
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
	,abdominal_pain	BOOLEAN
	,addval	DATE
	,adopt_immediately	BOOLEAN
	,all_pnc_on_time	BOOLEAN
	,bleeding	BOOLEAN
	,complications	BOOLEAN
	,congested	BOOLEAN
	,counsel_breast	BOOLEAN
	,counsel_follow_up_ppiud	BOOLEAN
	,counsel_follow_up_pptl	BOOLEAN
	,counsel_increase_food_bf	BOOLEAN
	,counsel_materal_comp	BOOLEAN
	,counsel_methods	BOOLEAN
	,counsel_neonatal_comp	BOOLEAN
	,counsel_ppfp	BOOLEAN
	,counsel_time_iud	BOOLEAN
	,date_death	DATE
	,date_iud_adopted	DATE
	,date_last_visit	DATE
	,date_next_eb	DATE
	,date_next_pnc	DATE
	,date_pnc_1	DATE
	,date_pnc_2	DATE
	,date_pnc_3	DATE
	,date_tl_adopted	DATE
	,death_village	BOOLEAN
	,discharge	BOOLEAN
	,distension	BOOLEAN
	,eating_well	BOOLEAN
	,family_planning_type	VARCHAR(255)
	,fever	BOOLEAN
	,first_pnc_time	VARCHAR(255)
	,interval_ppfp_interest	BOOLEAN
	,iud	BOOLEAN
	,iud_adopted	BOOLEAN
	,iud_counsel_duration	BOOLEAN
	,iud_counsel_follow_up	BOOLEAN
	,iud_counsel_hospital	BOOLEAN
	,iud_counsel_placement	BOOLEAN
	,iud_counsel_screening	BOOLEAN
	,iud_counsel_side_effects	BOOLEAN
	,last_visit_type	VARCHAR(20)
	,mother_alive	BOOLEAN
	,mother_child_alive	BOOLEAN
	,nextvisittype	VARCHAR(20)
	,num_children	SMALLINT
	,other_issues	BOOLEAN
	,pain_urination	BOOLEAN
	,painful_nipples	BOOLEAN
	,place_death	VARCHAR(255)
	,pnc_1_days_late	INTEGER
	,pnc_2_days_late	INTEGER
	,pnc_3_days_late	INTEGER
	,pnc_visit_num	SMALLINT
	,ppfp_interest	BOOLEAN
	,ppiud_abdominal_pain	BOOLEAN
	,ppiud_bleeding	BOOLEAN
	,ppiud_discharge	BOOLEAN
	,ppiud_fever	BOOLEAN
	,ppiud_problems	BOOLEAN
	,pptl_abdominal_pain	BOOLEAN
	,pptl_excessive_bleeding	BOOLEAN
	,pptl_pain_surgery	BOOLEAN
	,pptl_problems	BOOLEAN
	,problems_breast	BOOLEAN
	,safe	BOOLEAN
	,site_death	VARCHAR(255)
	,tl	BOOLEAN
	,tl_adopted	BOOLEAN
	,tl_consel_incentives	BOOLEAN
	,tl_counsel_follow_up	BOOLEAN
	,tl_counsel_hospital	BOOLEAN
	,tl_counsel_irreversible	BOOLEAN
	,tl_counsel_screening	BOOLEAN
	,tl_counsel_side_effects	BOOLEAN
	,tl_counsel_timing	BOOLEAN
	,why_no_ppffp	VARCHAR(255)
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
	,able_expressed_milk	BOOLEAN
	,adequate_support	BOOLEAN
	,applied_to_stump	BOOLEAN
	,baby_active	BOOLEAN
	,breastfeeding_well	BOOLEAN
	,child_alive	BOOLEAN
	,child_died_village	BOOLEAN
	,child_place_death	VARCHAR(255)
	,child_site_death	VARCHAR(255)
	,chld_date_death	DATE	
	,close	VARCHAR(255)
	,cord_fallen	BOOLEAN
	,correct_position	BOOLEAN
	,counsel_cord_care	BOOLEAN
	,counsel_exclusive_bf	BOOLEAN
	,counsel_express_milk	BOOLEAN
	,counsel_skin	BOOLEAN
	,cousel_bf_correct	BOOLEAN
	,demonstrate_expressed	BOOLEAN
	,demonstrate_skin	BOOLEAN
	,easy_awake	BOOLEAN
	,feed_vigour	BOOLEAN
	,good_latch	BOOLEAN
	,improvements_bf	BOOLEAN
	,observed_bf	BOOLEAN
	,other_milk_to_child	BOOLEAN
	,second_observation	BOOLEAN
	,skin_to_skin	BOOLEAN
	,warm_to_touch	BOOLEAN
	,what_applied	VARCHAR(255)
	,wrapped	BOOLEAN
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
	,addval	DATE
	,adopt_immediately	BOOLEAN
	,ask_ppiud	BOOLEAN
	,aware_of_failure	BOOLEAN
	,bleeding	BOOLEAN
	,complications	BOOLEAN
	,condoms	BOOLEAN
	,counsel_follow_up_ppiud	BOOLEAN
	,counsel_follow_up_pptl	BOOLEAN
	,counsel_menstrual_cycle	BOOLEAN
	,counsel_methods	BOOLEAN
	,counsel_ppfp	BOOLEAN
	,counsel_time_iud	BOOLEAN
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
	,discharge	BOOLEAN
	,distension	BOOLEAN
	,eb_visit_num	SMALLINT
	,family_planning_type	VARCHAR(255)
	,fever	BOOLEAN
	,have_condoms	BOOLEAN
	,headaches	BOOLEAN
	,high_bp	BOOLEAN
	,inj_menstrual_irregularity	BOOLEAN
	,injectable	BOOLEAN
	,intend_to_continue	BOOLEAN
	,interval_ppfp_interest	BOOLEAN
	,iud	BOOLEAN
	,iud_adopted	BOOLEAN
	,iud_counsel_duration	BOOLEAN
	,iud_counsel_follow_up	BOOLEAN
	,iud_counsel_hospital	BOOLEAN
	,iud_counsel_placement	BOOLEAN
	,iud_counsel_screening	BOOLEAN
	,iud_counsel_side_effects	BOOLEAN
	,last_visit_type	VARCHAR(20)
	,menstrual_irregularity	BOOLEAN
	,next_inj_calc	DATE
	,nextvisittype	VARCHAR(20)
	,num_children	SMALLINT
	,ocp	BOOLEAN
	,ocp_continue	BOOLEAN
	,ocp_counsel_regularity	BOOLEAN
	,pain_swelling	BOOLEAN
	,ppfp_interest	BOOLEAN
	,ppiud_abdominal_pain	BOOLEAN
	,ppiud_problems	BOOLEAN
	,pptl_abdominal_pain	BOOLEAN
	,pptl_pain_surgery	BOOLEAN
	,pptl_problems	BOOLEAN
	,regular_periods	BOOLEAN
	,tablets_received	BOOLEAN
	,taken_as_prescribed	BOOLEAN
	,tl	BOOLEAN
	,tl_adopted	BOOLEAN
	,tl_consel_incentives	BOOLEAN
	,tl_counsel_follow_up	BOOLEAN
	,tl_counsel_hospital	BOOLEAN
	,tl_counsel_irreversible	BOOLEAN
	,tl_counsel_screening	BOOLEAN
	,tl_counsel_side_effects	BOOLEAN
	,tl_counsel_timing	BOOLEAN
	,understand_tablets	BOOLEAN
	,using_correctly	BOOLEAN
	,where_replace	BOOLEAN
	,why_no_ppffp	VARCHAR(255)
	,within_42	BOOLEAN
	,date_tl_adopted DATE
	,abdominal_pain BOOLEAN
    ,pain_urination BOOLEAN
    ,ppiud_bleeding BOOLEAN
    ,ppiud_discharge BOOLEAN
    ,ppiud_fever BOOLEAN
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
	,add_vaccinations	BOOLEAN
	,at_night	BOOLEAN
	,baby_bcg	BOOLEAN
	,baby_dpt1	BOOLEAN
	,baby_dpt2	BOOLEAN
	,baby_dpt3	BOOLEAN
	,baby_hep_b_0	BOOLEAN
	,baby_hep_b_1	BOOLEAN
	,baby_hep_b_2	BOOLEAN
	,baby_hep_b_3	BOOLEAN
	,baby_opv0	BOOLEAN
	,baby_opv1	BOOLEAN
	,baby_opv2	BOOLEAN
	,baby_opv3	BOOLEAN
	,bcg_date	DATE
	,breastfeeding	BOOLEAN
	,case_name	VARCHAR(255)
	,child_name	VARCHAR(255)
	,counsel_adequate_bf	BOOLEAN
	,counsel_only_milk	BOOLEAN
	,counsel_stop_bottle	BOOLEAN
	,dpt_1_date	DATE
	,dpt_2_date	DATE
	,dpt_3_date	DATE
	,eating	BOOLEAN
	,emptying	BOOLEAN
	,feeding_bottle	BOOLEAN
	,hep_b_0_date	DATE
	,hep_b_1_date	DATE
	,hep_b_2_date	DATE
	,hep_b_3_date	DATE
	,more_feeding_less_six	BOOLEAN
	,name_update	BOOLEAN
	,not_breasfeeding	VARCHAR(255)
	,on_demand	BOOLEAN
	,opv_0_date	DATE
	,opv_1_date	DATE
	,opv_2_date	DATE
	,opv_3_date	DATE
	,recent_fever	BOOLEAN
	,tea_other	BOOLEAN
	,treated_less_six	BOOLEAN
	,water_or_milk	BOOLEAN
	,UNIQUE(instance_id, case_id)
    ,creation_time TIMESTAMP WITH TIME ZONE
);

CREATE TABLE report.cf_mother_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50) UNIQUE
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.mother_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
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
	,play_comp_feeding_vid	BOOLEAN
	,lastvisit BOOLEAN
    ,date_cf_7 DATE
	,confirm_close BOOLEAN
	,close	BOOLEAN
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
	,add_vaccinations	BOOLEAN
	,amount_good	BOOLEAN
	,baby_bcg	BOOLEAN
	,baby_dpt1	BOOLEAN
	,baby_dpt2	BOOLEAN
	,baby_dpt3	BOOLEAN
	,baby_hep_b_0	BOOLEAN
	,baby_hep_b_1	BOOLEAN
	,baby_hep_b_2	BOOLEAN
	,baby_hep_b_3	BOOLEAN
	,baby_measles	BOOLEAN
	,baby_opv0	BOOLEAN
	,baby_opv1	BOOLEAN
	,baby_opv2	BOOLEAN
	,baby_opv3	BOOLEAN
	,baby_vita1	BOOLEAN
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
	,dal	BOOLEAN
	,eaten_cereal	BOOLEAN
	,egg	BOOLEAN
	,fish	BOOLEAN
	,meat	BOOLEAN
	,milk_curd	BOOLEAN
	,more_feeding_less_six	BOOLEAN
	,name_update	BOOLEAN
	,new_name	VARCHAR(255)
	,number_good	BOOLEAN
	,oil_ghee	BOOLEAN
	,recent_fever	BOOLEAN
	,treated_less_six	BOOLEAN
    ,baby_dpt_booster BOOLEAN
    ,baby_je BOOLEAN
    ,baby_measles_booster BOOLEAN
    ,baby_opv_booster BOOLEAN
    ,baby_vita2 BOOLEAN
    ,baby_vita3 BOOLEAN
    ,date_je DATE
    ,date_measles_booster DATE
    ,dpt_booster_date DATE
    ,opv_booster_date DATE
    ,vit_a_3_date DATE
    ,vit_a_2_date DATE
	,close	VARCHAR(255)
	,UNIQUE(instance_id, case_id)
    ,creation_time TIMESTAMP WITH TIME ZONE
);


CREATE TABLE report.delivery_mother_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50) UNIQUE
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.mother_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
	,PPIUD	BOOLEAN
	,PPTL	BOOLEAN
	,abd_pain	BOOLEAN
	,add	DATE
	,close	VARCHAR(255)
	,birth_place	VARCHAR(25)
	,date_del_fu	DATE
	,date_last_visit	DATE
	,date_next_cf	DATE
	,date_next_eb	DATE
	,date_next_pnc	DATE
	,family_planning_type	VARCHAR(50)
	,last_visit_type	VARCHAR(255)
	,mother_alive	BOOLEAN
	,term	VARCHAR(50)
	,cast_num_children	SMALLINT
	,complications	BOOLEAN
	,date_death	DATE
	,death_village	BOOLEAN
	,delivery_nature	VARCHAR(50)
	,fever	BOOLEAN
	,has_delivered	BOOLEAN
	,how_many_children	SMALLINT
	,ifa_tablets_given	BOOLEAN
	,in_district	BOOLEAN
	,jsy_money	BOOLEAN
	,nextvisittype	VARCHAR(255)
	,notified	DATE
	,num_children	SMALLINT
	,other_conditions	BOOLEAN
	,other_district	VARCHAR(255)
	,other_village	VARCHAR(255)
	,pain_urine	BOOLEAN
	,place_death	VARCHAR(255)
	,post_postpartum_fp	BOOLEAN
	,safe	BOOLEAN
	,site_death	VARCHAR(255)
	,vaginal_discharge	BOOLEAN
	,where_born	VARCHAR(50)
	,which_hospital	VARCHAR(255)
	,which_village	VARCHAR(255)
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
	,abnormalities 	BOOLEAN
	,add_vaccinations 	BOOLEAN
	,baby_bcg 	BOOLEAN
	,baby_hep_b_0 	BOOLEAN
	,baby_opv0 	BOOLEAN
	,breastfed_hour 	BOOLEAN
	,close 	VARCHAR(255)
	,case_name 	VARCHAR(255)
	,case_type 	VARCHAR(255)
	,baby_weight 	BOOLEAN
	,bcg_date 	DATE
	,birth_status 	VARCHAR(255)
	,dob 	DATE
	,gender 	VARCHAR(25)
	,hep_b_0_date 	DATE
	,opv_0_date 	DATE
	,term 	VARCHAR(50)
	,time_of_birth 	VARCHAR(25)
	,child_alive 	BOOLEAN
	,child_breathing 	VARCHAR(25)
	,child_cried 	BOOLEAN
	,child_died_village 	BOOLEAN
	,child_have_a_name 	BOOLEAN
	,child_heartbeats 	VARCHAR(25)
	,child_movement 	BOOLEAN
	,child_name 	VARCHAR(25)
	,child_place_death 	VARCHAR(25)
	,child_site_death 	VARCHAR(50)
	,chld_date_death 	DATE
	,cord_applied 	BOOLEAN
	,cord_cut 	BOOLEAN
	,cord_tied 	BOOLEAN
	,date_first_weight 	DATE
	,date_time_feed 	DATE
	,first_weight 	DECIMAL
	,skin_care 	BOOLEAN
	,what_applied 	VARCHAR(255)
	,wrapped_dried 	BOOLEAN
    ,creation_time TIMESTAMP WITH TIME ZONE
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
	,close	 VARCHAR(20)
	,mother_alive	BOOLEAN
	,status	VARCHAR(255)
	,cast_num_children	SMALLINT
	,date_death	DATE
	,death_village	BOOLEAN
	,num_children	SMALLINT
	,place_death	VARCHAR(255)
	,site_death	VARCHAR(255)
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
	,close	VARCHAR(20)
	,child_alive	BOOLEAN
	,child_died_village	BOOLEAN
	,child_place_death	VARCHAR(255)
	,child_site_death	VARCHAR(255)
	,chld_date_death	DATE
	,UNIQUE(instance_id, case_id)
    ,creation_time TIMESTAMP WITH TIME ZONE
);

CREATE TABLE report.close_mother_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50) UNIQUE
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.mother_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
	,close	 VARCHAR(20)
	,close_mother	BOOLEAN
	,confirm_close	BOOLEAN
	,death_village	BOOLEAN
	,died_village	VARCHAR(255)
	,dupe_reg	BOOLEAN
	,finished_continuum	BOOLEAN
	,num_children	SMALLINT
	,mother_alive VARCHAR(20)
    ,moved BOOLEAN
    ,migrated BOOLEAN
    ,date_learned DATE
    ,date_left DATE
    ,migration_note BOOLEAN
    ,died BOOLEAN
    ,date_death DATE
    ,site_death VARCHAR(255)
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
	,close	VARCHAR(20)
	,child_alive	BOOLEAN
	,close_child	BOOLEAN
	,confirm_close	BOOLEAN
	,date_death	DATE
	,died	BOOLEAN
	,died_village	BOOLEAN
	,dupe_reg	BOOLEAN
	,finished_continuum	BOOLEAN
	,site_death	VARCHAR(255)
	,place_death VARCHAR(255)
	,UNIQUE(instance_id, case_id)
    ,creation_time TIMESTAMP WITH TIME ZONE
);

CREATE TABLE report.refer_mother_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50) UNIQUE
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.mother_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
	,num_children	SMALLINT
	,refer_mother	BOOLEAN
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
	,refer_child	BOOLEAN
	,UNIQUE(instance_id, case_id)
    ,creation_time TIMESTAMP WITH TIME ZONE
);


CREATE TABLE report.ui_mother_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50) UNIQUE
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.mother_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
	,details_available	BOOLEAN
	,tt_1_date	DATE
	,tt_2_date	DATE
	,tt_booster_date	DATE
	,received_tt1	BOOLEAN
	,received_tt2	BOOLEAN
	,up_to_date	VARCHAR(15)
	,num_children	SMALLINT
	,update_mother	BOOLEAN
	,tt_booster DATE
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
	,add_vaccinations	BOOLEAN
	,baby_bcg	BOOLEAN
	,baby_dpt1	BOOLEAN
	,baby_dpt2	BOOLEAN
	,baby_dpt3	BOOLEAN
	,baby_hep_b_0	BOOLEAN
	,baby_hep_b_1	BOOLEAN
	,baby_hep_b_2	BOOLEAN
	,baby_hep_b_3	BOOLEAN
	,baby_measles	BOOLEAN
	,baby_opv0	BOOLEAN
	,baby_opv1	BOOLEAN
	,baby_opv2	BOOLEAN
	,baby_opv3	BOOLEAN
	,baby_vita1	BOOLEAN
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
	,baby_dpt_booster BOOLEAN
    ,baby_je BOOLEAN
    ,baby_measles_booster BOOLEAN
    ,baby_opv_booster BOOLEAN
    ,baby_vita2 BOOLEAN
    ,baby_vita3 BOOLEAN
    ,date_je DATE
    ,date_measles_booster DATE
    ,vit_a_2_date DATE
    ,vit_a_3_date DATE
    ,UNIQUE(instance_id, case_id)
    ,creation_time TIMESTAMP WITH TIME ZONE
);

CREATE TABLE report.abort_form(
	id SERIAL PRIMARY KEY
	,instance_id VARCHAR(50) UNIQUE
	,time_end	TIMESTAMP WITH TIME ZONE
	,time_start	TIMESTAMP WITH TIME ZONE
	,user_id INTEGER REFERENCES report.flw(id)
	,case_id	INTEGER REFERENCES report.mother_case(id)
	,date_modified	TIMESTAMP WITH TIME ZONE
	,abortion_type	VARCHAR(255)
	,close	 VARCHAR(20)
	,birth_status	VARCHAR(255)
	,date_aborted	DATE
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
	,migrate_out_date	DATE
	,migrated_status	VARCHAR(255)
	,status	VARCHAR(255)
	,date_learned	DATE
	,date_left	DATE
	,name	VARCHAR(255)
	,note_given	BOOLEAN
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
	,date_arrived	DATE
	,date_learned	DATE
	,date_of_delivery	DATE
	,name	VARCHAR(255)
	,preg_status	VARCHAR(255)
	,referral_info	VARCHAR(255)
	,abortion_type VARCHAR(255)
    ,date_aborted DATE
    ,migrated_status VARCHAR(255)
    ,creation_time TIMESTAMP WITH TIME ZONE
);