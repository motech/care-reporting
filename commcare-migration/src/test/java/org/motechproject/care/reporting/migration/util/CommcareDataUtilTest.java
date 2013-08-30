package org.motechproject.care.reporting.migration.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommcareDataUtilTest {

    @Test
    public void shouldConvertJsonToXml() {
        String json = "{\n" +
                "    \"form\": {\n" +
                "        \"@attr\": \"attrValue\",\n" +
                "        \"#value\": \"formValue\",\n" +
                "        \"element1\": \"element1Value\",\n" +
                "        \"booleanElement\": false,\n" +
                "        \"intElement\": 42,\n" +
                "        \"childElement\": [{\n" +
                "                \"@attr\": \"childElement1AttrValue\",\n" +
                "                \"#someValue\": \"childElement1Value\",\n" +
                "                \"childChild\": {\n" +
                "                    \"@attr\": \"childChildAttrValue\"\n" +
                "                }\n" +
                "            }, {\n" +
                "                \"@attr\": \"childElement2AttrValue\",\n" +
                "                \"#someValue\": \"childElement2Value\"\n" +
                "            },\n" +
                "            \"childElement3Value\",\n" +
                "            true,\n" +
                "            5\n" +
                "        ]\n" +
                "    }\n" +
                "}";
        String expectedXml = "<data attr=\"attrValue\">formValue<element1>element1Value</element1>%n" +
                "<booleanElement>false</booleanElement>%n" +
                "<intElement>42</intElement>%n" +
                "<childElement attr=\"childElement1AttrValue\">childElement1Value<childChild attr=\"childChildAttrValue\"/>%n" +
                "</childElement>%n" +
                "<childElement attr=\"childElement2AttrValue\">childElement2Value</childElement>%n" +
                "<childElement>childElement3Value</childElement>%n" +
                "<childElement>true</childElement>%n" +
                "<childElement>5</childElement>%n" +
                "</data>%n";

        String actualXml = new CommcareDataUtil().toFormXml((JsonObject) new JsonParser().parse(json));
        assertEquals(format(expectedXml), actualXml);
    }

    @Test
    public void shouldParseCaseJsonForCreateUpdate() {
        String json = "{\n" +
                "    \"case_id\": \"361816d9-a98e-41d9-9d67-af1e18a26ea7\",\n" +
                "    \"closed\": false,\n" +
                "    \"date_closed\": null,\n" +
                "    \"date_modified\": \"1900-01-01\",\n" +
                "    \"id\": \"361816d9-a98e-41d9-9d67-af1e18a26ea7\",\n" +
                "    \"indices\": {\n" +
                "        \"mother_id\": {\n" +
                "            \"case_id\": \"dafadd3f-5f76-44ee-8734-36e49cda189a\",\n" +
                "            \"case_type\": \"cc_bihar_pregnancy\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"properties\": {\n" +
                "        \"baby_measles\": \"\",\n" +
                "        \"baby_weight\": \"yes\",\n" +
                "        \"bcg_date\": \"2013-02-13\",\n" +
                "        \"birth_status\": \"live_birth\",\n" +
                "        \"case_name\": \"प्रमोद मुखिया\",\n" +
                "        \"case_type\": \"cc_bihar_newborn\",\n" +
                "        \"date_opened\": \"2013-02-08T13:32:00\",\n" +
                "        \"dob\": \"2012-12-22\",\n" +
                "        \"dpt_1_date\": \"2013-02-13\",\n" +
                "        \"dpt_2_date\": \"2013-03-13\",\n" +
                "        \"dpt_3_date\": \"2013-05-08\",\n" +
                "        \"external_id\": null,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"hep_b_0_date\": \"2013-03-13\",\n" +
                "        \"hep_b_1_date\": \"2013-02-13\",\n" +
                "        \"hep_b_2_date\": \"2013-03-13\",\n" +
                "        \"opv_0_date\": \"2012-12-22\",\n" +
                "        \"opv_1_date\": \"2013-02-13\",\n" +
                "        \"opv_2_date\": \"2013-03-13\",\n" +
                "        \"opv_3_date\": \"2013-05-08\",\n" +
                "        \"owner_id\": \"89fda0284e008d2e0c980fb13fc0e87f\",\n" +
                "        \"time_of_birth\": \"\"\n" +
                "    },\n" +
                "    \"resource_uri\": \"\",\n" +
                "    \"server_date_modified\": \"1900-01-01\",\n" +
                "    \"server_date_opened\": null,\n" +
                "    \"user_id\": \"89fda0284e008d2e0c980fb13f9e0967\",\n" +
                "    \"xform_ids\": [\"9b74590f-1be0-45c5-93bc-d8df61678af3\", \"7102b6c1-3be7-4d49-aadc-c55eef3e4683\", \"dd6236a6-47c1-4d6c-9f27-6ea0b31d9754\", \"f190d114-1228-46e6-b8dd-1718618f5013\", \"2bed0d49-9f8a-45ff-a419-11ddce24a868\", \"7a237029-9da8-48e6-8bef-820d9a32aa55\", \"7c1d6164-659e-44b5-8cf8-2c56eeeca91d\", \"2cdfae58-4488-4a20-ace4-be714028797b\", \"d2561227-5219-4d04-8a41-813bf2e47a5d\", \"93a7a3d3-4db7-4c8e-82c0-25a413bbcbf4\", \"5162854f-403d-4456-8405-615e83a21a53\", \"853083bb-65ac-49c2-98b8-ac553eca10af\"]\n" +
                "}";

        String expectedXml = "<case xmlns=\"http://commcarehq.org/case/transaction/v2\" case_id=\"361816d9-a98e-41d9-9d67-af1e18a26ea7\" date_modified=\"1900-01-01\" user_id=\"89fda0284e008d2e0c980fb13f9e0967\">%n" +
                "<create>%n" +
                "<case_type>cc_bihar_newborn</case_type>%n" +
                "<case_name>प्रमोद मुखिया</case_name>%n" +
                "<owner_id>89fda0284e008d2e0c980fb13fc0e87f</owner_id>%n" +
                "</create>%n" +
                "<update>%n" +
                "<baby_measles/>%n" +
                "<baby_weight>yes</baby_weight>%n" +
                "<bcg_date>2013-02-13</bcg_date>%n" +
                "<birth_status>live_birth</birth_status>%n" +
                "<date_opened>2013-02-08T13:32:00</date_opened>%n" +
                "<dob>2012-12-22</dob>%n" +
                "<dpt_1_date>2013-02-13</dpt_1_date>%n" +
                "<dpt_2_date>2013-03-13</dpt_2_date>%n" +
                "<dpt_3_date>2013-05-08</dpt_3_date>%n" +
                "<gender>male</gender>%n" +
                "<hep_b_0_date>2013-03-13</hep_b_0_date>%n" +
                "<hep_b_1_date>2013-02-13</hep_b_1_date>%n" +
                "<hep_b_2_date>2013-03-13</hep_b_2_date>%n" +
                "<opv_0_date>2012-12-22</opv_0_date>%n" +
                "<opv_1_date>2013-02-13</opv_1_date>%n" +
                "<opv_2_date>2013-03-13</opv_2_date>%n" +
                "<opv_3_date>2013-05-08</opv_3_date>%n" +
                "<time_of_birth/>%n" +
                "</update>%n" +
                "<index>%n" +
                "<mother_id case_type=\"cc_bihar_pregnancy\">dafadd3f-5f76-44ee-8734-36e49cda189a</mother_id>%n" +
                "</index>%n" +
                "</case>%n";

        CaseXmlPair cases = new CommcareDataUtil().toCaseXml((JsonObject) new JsonParser().parse(json));
        assertFalse(cases.hasClosedAction());
        assertEquals(format(expectedXml), cases.getCreateUpdateAction());
    }

    @Test
    public void shouldParseCaseJsonForClose() {
        String json = "{\n" +
                "    \"case_id\": \"361816d9-a98e-41d9-9d67-af1e18a26ea7\",\n" +
                "    \"closed\": true,\n" +
                "    \"date_closed\": null,\n" +
                "    \"date_modified\": \"1900-01-01\",\n" +
                "    \"id\": \"361816d9-a98e-41d9-9d67-af1e18a26ea7\",\n" +
                "    \"indices\": {\n" +
                "        \"mother_id\": {\n" +
                "            \"case_id\": \"dafadd3f-5f76-44ee-8734-36e49cda189a\",\n" +
                "            \"case_type\": \"cc_bihar_pregnancy\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"properties\": {\n" +
                "        \"baby_measles\": \"\",\n" +
                "        \"baby_weight\": \"yes\",\n" +
                "        \"bcg_date\": \"2013-02-13\",\n" +
                "        \"birth_status\": \"live_birth\",\n" +
                "        \"case_name\": \"प्रमोद मुखिया\",\n" +
                "        \"case_type\": \"cc_bihar_newborn\",\n" +
                "        \"date_opened\": \"2013-02-08T13:32:00\",\n" +
                "        \"dob\": \"2012-12-22\",\n" +
                "        \"dpt_1_date\": \"2013-02-13\",\n" +
                "        \"dpt_2_date\": \"2013-03-13\",\n" +
                "        \"dpt_3_date\": \"2013-05-08\",\n" +
                "        \"external_id\": null,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"hep_b_0_date\": \"2013-03-13\",\n" +
                "        \"hep_b_1_date\": \"2013-02-13\",\n" +
                "        \"hep_b_2_date\": \"2013-03-13\",\n" +
                "        \"opv_0_date\": \"2012-12-22\",\n" +
                "        \"opv_1_date\": \"2013-02-13\",\n" +
                "        \"opv_2_date\": \"2013-03-13\",\n" +
                "        \"opv_3_date\": \"2013-05-08\",\n" +
                "        \"owner_id\": \"89fda0284e008d2e0c980fb13fc0e87f\",\n" +
                "        \"time_of_birth\": \"\"\n" +
                "    },\n" +
                "    \"resource_uri\": \"\",\n" +
                "    \"server_date_modified\": \"1900-01-01\",\n" +
                "    \"server_date_opened\": null,\n" +
                "    \"user_id\": \"89fda0284e008d2e0c980fb13f9e0967\",\n" +
                "    \"xform_ids\": [\"9b74590f-1be0-45c5-93bc-d8df61678af3\", \"7102b6c1-3be7-4d49-aadc-c55eef3e4683\", \"dd6236a6-47c1-4d6c-9f27-6ea0b31d9754\", \"f190d114-1228-46e6-b8dd-1718618f5013\", \"2bed0d49-9f8a-45ff-a419-11ddce24a868\", \"7a237029-9da8-48e6-8bef-820d9a32aa55\", \"7c1d6164-659e-44b5-8cf8-2c56eeeca91d\", \"2cdfae58-4488-4a20-ace4-be714028797b\", \"d2561227-5219-4d04-8a41-813bf2e47a5d\", \"93a7a3d3-4db7-4c8e-82c0-25a413bbcbf4\", \"5162854f-403d-4456-8405-615e83a21a53\", \"853083bb-65ac-49c2-98b8-ac553eca10af\"]\n" +
                "}";

        String expectedXml = "<case xmlns=\"http://commcarehq.org/case/transaction/v2\" case_id=\"361816d9-a98e-41d9-9d67-af1e18a26ea7\" date_modified=\"1900-01-01\" user_id=\"89fda0284e008d2e0c980fb13f9e0967\">%n" +
                "<create>%n" +
                "<case_type>cc_bihar_newborn</case_type>%n" +
                "<case_name>प्रमोद मुखिया</case_name>%n" +
                "<owner_id>89fda0284e008d2e0c980fb13fc0e87f</owner_id>%n" +
                "</create>%n" +
                "<update>%n" +
                "<baby_measles/>%n" +
                "<baby_weight>yes</baby_weight>%n" +
                "<bcg_date>2013-02-13</bcg_date>%n" +
                "<birth_status>live_birth</birth_status>%n" +
                "<date_opened>2013-02-08T13:32:00</date_opened>%n" +
                "<dob>2012-12-22</dob>%n" +
                "<dpt_1_date>2013-02-13</dpt_1_date>%n" +
                "<dpt_2_date>2013-03-13</dpt_2_date>%n" +
                "<dpt_3_date>2013-05-08</dpt_3_date>%n" +
                "<gender>male</gender>%n" +
                "<hep_b_0_date>2013-03-13</hep_b_0_date>%n" +
                "<hep_b_1_date>2013-02-13</hep_b_1_date>%n" +
                "<hep_b_2_date>2013-03-13</hep_b_2_date>%n" +
                "<opv_0_date>2012-12-22</opv_0_date>%n" +
                "<opv_1_date>2013-02-13</opv_1_date>%n" +
                "<opv_2_date>2013-03-13</opv_2_date>%n" +
                "<opv_3_date>2013-05-08</opv_3_date>%n" +
                "<time_of_birth/>%n" +
                "</update>%n" +
                "<index>%n" +
                "<mother_id case_type=\"cc_bihar_pregnancy\">dafadd3f-5f76-44ee-8734-36e49cda189a</mother_id>%n" +
                "</index>%n" +
                "</case>%n";

        String expectedCloseXml = "<case xmlns=\"http://commcarehq.org/case/transaction/v2\" case_id=\"361816d9-a98e-41d9-9d67-af1e18a26ea7\" date_modified=\"1900-01-01\" user_id=\"89fda0284e008d2e0c980fb13f9e0967\">%n" +
                "<close/>%n" +
                "</case>%n";

        CaseXmlPair cases = new CommcareDataUtil().toCaseXml((JsonObject) new JsonParser().parse(json));
        assertTrue(cases.hasClosedAction());
        assertEquals(format(expectedXml), cases.getCreateUpdateAction());
        assertEquals(format(expectedCloseXml), cases.getCloseAction());
    }
}