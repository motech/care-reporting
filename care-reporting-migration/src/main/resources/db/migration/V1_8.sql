ALTER TABLE report.aww_reg_child_form
    ALTER COLUMN full_child_mcts_id TYPE TEXT;

ALTER TABLE report.aww_reg_mother_form
    ALTER COLUMN update_mcts_id TYPE TEXT,
    ALTER COLUMN full_mcts_id TYPE TEXT;

ALTER TABLE report.aww_growth_monitoring_1_child_form
    ALTER COLUMN current_growth TYPE TEXT,
    ALTER COLUMN last_growth_1 TYPE TEXT,
    ALTER COLUMN last_growth_2 TYPE TEXT,
    ALTER COLUMN last_growth_3 TYPE TEXT,
    ALTER COLUMN last_weight TYPE TEXT,
    ALTER COLUMN take_weight TYPE TEXT,
    ALTER COLUMN child_weight TYPE TEXT,
    ALTER COLUMN show_grade TYPE TEXT,
    ALTER COLUMN requires_attention TYPE TEXT,
    ALTER COLUMN success TYPE TEXT,
    ALTER COLUMN calc_grade TYPE TEXT,
    ALTER COLUMN calc_growth TYPE TEXT,
    ALTER COLUMN child_gender TYPE TEXT,
    ALTER COLUMN gender TYPE TEXT,
    ALTER COLUMN change_from_normal TYPE TEXT,
    ALTER COLUMN change_from_muw TYPE TEXT,
    ALTER COLUMN change_from_suw TYPE TEXT;

ALTER TABLE report.aww_growth_monitoring_2_child_form
    ALTER COLUMN current_growth TYPE TEXT,
    ALTER COLUMN last_growth_1 TYPE TEXT,
    ALTER COLUMN last_growth_2 TYPE TEXT,
    ALTER COLUMN last_growth_3 TYPE TEXT,
    ALTER COLUMN last_weight TYPE TEXT,
    ALTER COLUMN take_weight TYPE TEXT,
    ALTER COLUMN child_weight TYPE TEXT,
    ALTER COLUMN show_grade TYPE TEXT,
    ALTER COLUMN requires_attention TYPE TEXT,
    ALTER COLUMN success TYPE TEXT,
    ALTER COLUMN calc_grade TYPE TEXT,
    ALTER COLUMN calc_growth TYPE TEXT,
    ALTER COLUMN child_gender TYPE TEXT,
    ALTER COLUMN gender TYPE TEXT,
    ALTER COLUMN change_from_normal TYPE TEXT,
    ALTER COLUMN change_from_muw TYPE TEXT,
    ALTER COLUMN change_from_suw TYPE TEXT;

ALTER TABLE report.growth_monitoring_child_form
    ALTER COLUMN take_weight TYPE TEXT,
    ALTER COLUMN child_weight TYPE TEXT,
    ALTER COLUMN show_grade TYPE TEXT,
    ALTER COLUMN requires_attention TYPE TEXT,
    ALTER COLUMN calc_grade TYPE TEXT,
    ALTER COLUMN calc_growth TYPE TEXT,
    ALTER COLUMN child_gender TYPE TEXT,
    ALTER COLUMN gender TYPE TEXT,
    ALTER COLUMN change_from_normal TYPE TEXT,
    ALTER COLUMN change_from_muw TYPE TEXT,
    ALTER COLUMN change_from_suw TYPE TEXT;
