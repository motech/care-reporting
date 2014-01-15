ALTER TABLE report.bp_form
    ADD COLUMN anc1_weight DECIMAL DEFAULT NULL,
    ADD COLUMN anc1_hemoglobin INTEGER DEFAULT NULL,
    ADD COLUMN anc2_weight DECIMAL DEFAULT NULL,
    ADD COLUMN anc2_hemoglobin INTEGER DEFAULT NULL,
    ADD COLUMN anc3_weight DECIMAL DEFAULT NULL,
    ADD COLUMN anc3_hemoglobin INTEGER DEFAULT NULL,
    ADD COLUMN anc4_weight DECIMAL DEFAULT NULL,
    ADD COLUMN anc4_hemoglobin INTEGER DEFAULT NULL,
    ADD COLUMN anaemia TEXT DEFAULT NULL,
    ADD COLUMN rti_sti TEXT DEFAULT NULL,
    ADD COLUMN which_hospital TEXT DEFAULT NULL,
    ADD COLUMN bleeding TEXT DEFAULT NULL,
    ADD COLUMN bp_complications TEXT DEFAULT NULL;

ALTER TABLE report.delivery_mother_form
    ADD COLUMN jsy_money_date DATE DEFAULT NULL,
    ADD COLUMN delivery_complications TEXT DEFAULT NULL,
    ADD COLUMN discharge_date DATE DEFAULT NULL,
    ADD COLUMN discharge_time VARCHAR(25),
    ADD COLUMN who_assisted TEXT DEFAULT NULL,
    ADD COLUMN bleeding TEXT DEFAULT NULL,
    ADD COLUMN home_sba_assist TEXT DEFAULT NULL,
    ADD COLUMN age_current_weight INTEGER DEFAULT NULL,
    ADD COLUMN age_last_weight INTEGER DEFAULT NULL;

ALTER TABLE report.pnc_mother_form
    ADD COLUMN pnc_complications TEXT DEFAULT NULL;

ALTER TABLE report.cf_mother_form
    ADD COLUMN owner_id TEXT DEFAULT NULL,
    ADD COLUMN invalid_groups_transfer TEXT DEFAULT NULL,
    ADD COLUMN new_owner TEXT DEFAULT NULL;

ALTER TABLE report.cf_child_form
    ADD COLUMN owner_id TEXT DEFAULT NULL;

ALTER TABLE report.mother_edit_form
    ADD COLUMN mcts_id INTEGER DEFAULT NULL,
    ADD COLUMN update_mcts_id TEXT DEFAULT NULL,
    ADD COLUMN update_aadhar_number TEXT DEFAULT NULL,
    ADD COLUMN aadhar_number TEXT DEFAULT NULL,
    ADD COLUMN full_mcts_id TEXT DEFAULT NULL;

ALTER TABLE report.mi_form
    ADD COLUMN date_del_fu DATE DEFAULT NULL; -- TODO: DATE or TIMESTAMP WITH TIME ZONE ?

ALTER TABLE report.close_mother_form
    ADD COLUMN owner_id TEXT DEFAULT NULL,
    ADD COLUMN confirm_transfer TEXT DEFAULT NULL,
    ADD COLUMN invalid_groups_transfer TEXT DEFAULT NULL,
    ADD COLUMN new_owner TEXT DEFAULT NULL;

ALTER TABLE report.close_child_form
    ADD COLUMN owner_id TEXT DEFAULT NULL;

ALTER TABLE report.ui_child_form
    ADD COLUMN up_to_date_six_weeks TEXT DEFAULT NULL,
    ADD COLUMN up_to_date_ten_weeks TEXT DEFAULT NULL,
    ADD COLUMN up_to_date_14_weeks TEXT DEFAULT NULL,
    ADD COLUMN up_to_date_two_year TEXT DEFAULT NULL,
    ADD COLUMN immuns_up_to_date TEXT DEFAULT NULL;
