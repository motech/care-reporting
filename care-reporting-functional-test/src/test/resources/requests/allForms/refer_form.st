<?xml version='1.0' ?>
<data uiVersion="1" version="1" name="Referral" xmlns:cc="http://commcarehq.org/xforms" xmlns="http://bihar.commcarehq.org/pregnancy/refer">
    <n0:meta xmlns:n0="http://openrosa.org/jr/xforms">
        <n0:deviceID>X2AKWGGOTZMPBKOPOTSDSFHZ7</n0:deviceID>
        <n0:timeStart>2013-05-09T11:10:06.814+05:30</n0:timeStart>
        <n0:timeEnd>2013-05-09T11:10:35.093+05:30</n0:timeEnd>
        <n0:username>8969819076</n0:username>
        <n0:userID>$userId$</n0:userID>
        <n0:instanceID>$instanceId$</n0:instanceID>
        <cc:appVersion>v2.0.0alpha (990ba3-e6e3c5-unvers-2.1.0-Nokia/S40-native-input) #48 b:2012-Jul-26 r:2012-Jul-28</cc:appVersion>
    </n0:meta>
    <n1:case case_id="$caseId$" date_modified="2013-05-09T11:10:35.093+05:30" user_id="$userId$" xmlns:n1="http://commcarehq.org/case/transaction/v2" />
    <num_children>1</num_children>
    <children>$child1caseId$</children>
    <child_info>
        <index>0</index>
        <cid>$child1caseId$</cid>
        <refer_child>yes</refer_child>
        <n2:case case_id="$child1caseId$" date_modified="2013-05-23T14:31:17.370+05:30" user_id="$userId$" xmlns:n2="http://commcarehq.org/case/transaction/v2" />
    </child_info>
    <refer_mother>yes</refer_mother>
    <referral_info>OK</referral_info>
    <success>OK</success>
</data>