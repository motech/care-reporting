<?xml version='1.0' ?>
<data uiVersion="1" version="1" name="Update Vaccinations" xmlns:jrm="http://dev.commcarehq.org/jr/xforms" xmlns:cc="http://commcarehq.org/xforms" xmlns="http://bihar.commcarehq.org/pregnancy/update_vaccinations">
    <n0:meta xmlns:n0="http://openrosa.org/jr/xforms">
        <n0:deviceID>6E75JVISMJXAWW438TTXPTKZV</n0:deviceID>
        <n0:timeStart>2012-11-23T12:55:26.070+05:30</n0:timeStart>
        <n0:timeEnd>2012-11-23T12:55:55.064+05:30</n0:timeEnd>
        <n0:username>8969811649</n0:username>
        <n0:userID>$userId$</n0:userID>
        <n0:instanceID>$instanceId$</n0:instanceID>
        <cc:appVersion>v2.0.0alpha (990ba3-e6e3c5-unvers-2.1.0-Nokia/S40-native-input) #48 b:2012-Jul-26 r:2012-Jul-28</cc:appVersion>
    </n0:meta>
    <details_available>yes</details_available>
    <update_mother>yes</update_mother>
    <mother_caseid>$caseId$</mother_caseid>
    <mother>
        <n1:case case_id="$caseId$" date_modified="2012-11-23T12:55:55.064+05:30" user_id="$userId$" xmlns:n1="http://commcarehq.org/case/transaction/v2">
            <n1:update>
                <n1:tt_booster_date>2012-09-05</n1:tt_booster_date>
            </n1:update>
        </n1:case>
        <up_to_date>OK</up_to_date>
        <tt_booster>yes</tt_booster>
    </mother>
    <num_children>1</num_children>
    <children>$child1caseId$</children>
    <child_info>
        <display_child>OK</display_child>
        <add_vaccinations>yes</add_vaccinations>
        <cid>$child1caseId$</cid>
        <index>0</index>
        <n1:case case_id="$child1caseId$" date_modified="2013-01-26T15:29:16.643+05:30" user_id="$userId$" xmlns:n1="http://commcarehq.org/case/transaction/v2">
            <n1:update>
                <n1:bcg_date>2012-07-09</n1:bcg_date>
                <n1:opv_0_date>2012-07-09</n1:opv_0_date>
                <n1:opv_1_date>2012-10-03</n1:opv_1_date>
                <n1:opv_2_date>2012-12-14</n1:opv_2_date>
                <n1:dpt_1_date>2012-10-03</n1:dpt_1_date>
                <n1:dpt_2_date>2012-12-14</n1:dpt_2_date>
                <n1:hep_b_0_date>2012-07-09</n1:hep_b_0_date>
                <n1:hep_b_1_date>2012-10-03</n1:hep_b_1_date>
                <n1:hep_b_2_date>2012-12-14</n1:hep_b_2_date>
                <n1:baby_measles />
            </n1:update>
        </n1:case>
        <baby_bcg>yes</baby_bcg>
        <baby_opv0>yes</baby_opv0>
        <baby_opv1>yes</baby_opv1>
        <baby_opv2>yes</baby_opv2>
        <baby_opv3>no</baby_opv3>
        <baby_dpt1>yes</baby_dpt1>
        <baby_dpt2>yes</baby_dpt2>
        <baby_dpt3>no</baby_dpt3>
        <baby_hep_b_0>yes</baby_hep_b_0>
        <baby_hep_b_1>yes</baby_hep_b_1>
        <baby_hep_b_2>yes</baby_hep_b_2>
        <baby_hep_b_3>no</baby_hep_b_3>
    </child_info>
    <success>OK</success>
</data>