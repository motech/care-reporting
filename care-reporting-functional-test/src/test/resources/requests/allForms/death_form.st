<?xml version='1.0' ?>
<data uiVersion="1" version="1" name="Death Registration" xmlns:cc="http://commcarehq.org/xforms" xmlns="http://bihar.commcarehq.org/pregnancy/death">
    <n0:meta xmlns:n0="http://openrosa.org/jr/xforms">
        <n0:deviceID>4LL73X5ZSNPDA3JCFKLLJ6BT1</n0:deviceID>
        <n0:timeStart>2012-09-25T20:16:02.480+05</n0:timeStart>
        <n0:timeEnd>2012-09-25T20:17:19.189+05</n0:timeEnd>
        <n0:username>9661091416</n0:username>
        <n0:userID>$userId$</n0:userID>
        <n0:instanceID>$instanceId$</n0:instanceID>
        <cc:appVersion>v2.0.0alpha (990ba3-e6e3c5-unvers-2.1.0-Nokia/S40-native-input) #48 b:2012-Jul-26 r:2012-Jul-28</cc:appVersion>
    </n0:meta>
    <n1:case case_id="$caseId$" date_modified="2012-09-25T20:17:19.189+05:30" user_id="$userId$" xmlns:n1="http://commcarehq.org/case/transaction/v2">
        <n1:update>
            <n1:mother_alive>no</n1:mother_alive>
            <n1:status>death</n1:status>
        </n1:update>
        <n1:close />
    </n1:case>
    <num_children>1</num_children>
    <children>$child1caseId$</children>
    <child_info>
        <index>0</index>
        <cid>$child1caseId$</cid>
        <child_alive>no</child_alive>
        <chld_date_death>2013-05-22</chld_date_death>
        <child_site_death>home</child_site_death>
        <child_died_village>no</child_died_village>
        <child_place_death>&#2340;&#2352;&#2367;&#2351;&#2366;&#2350;&#2366;</child_place_death>
        <n2:case case_id="$child1caseId$" date_modified="2013-04-23T17:50:48.924+05:30" user_id="$userId$" xmlns:n2="http://commcarehq.org/case/transaction/v2">
            <n2:close />
        </n2:case>
    </child_info>
    <date_death>2012-09-25</date_death>
    <site_death>transit_death</site_death>
    <death_village>no</death_village>
    <place_death>TestVillage</place_death>
    <cast_num_children>1</cast_num_children>
    <success>OK</success>
</data>