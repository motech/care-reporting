package parser;

import org.junit.Test;
import org.motechproject.care.reporting.builder.CommcareFormBuilder;
import org.motechproject.care.reporting.builder.FormValueElementBuilder;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class ChildInfoParserTest {

    @Test
    public void testConvertsToCamelCaseAndPopulatesTheMapWithCaseInformation() throws Exception {

        FormValueElement childCase1 = new FormValueElementBuilder()
                                            .addAttribute("case_id", "3e8998ce-b19f-4fa7-b1a1-721b6951e3cf")
                                            .addAttribute("date_modified", "2012-07-21T12:02:59.923+05:30")
                                            .addAttribute("user_id", "89fda0284e008d2e0c980fb13fa0e5bb")
                                            .build();

        FormValueElement childCase1Details = new FormValueElementBuilder()
                                            .addSubElement("cid", "3e8998ce-b19f-4fa7-b1a1-721b6951e3cf")
                                            .addSubElement("index", "0")
                                            .addSubElement("case", childCase1)
                                            .addSubElement("display_child", "OK")
                                            .build();

        FormValueElement childCase2 = new FormValueElementBuilder()
                                            .addAttribute("case_id", "59ab28e0-2d2d-4bc7-933f-09dcacf70d61")
                                            .addAttribute("date_modified", "2012-07-21T12:02:59.923+05:30")
                                            .addAttribute("user_id", "89fda0284e008d2e0c980fb13fa0e5bb")
                                            .build();

        FormValueElement childCase2Details = new FormValueElementBuilder()
                                            .addSubElement("cid", "59ab28e0-2d2d-4bc7-933f-09dcacf70d61")
                                            .addSubElement("index", "1")
                                            .addSubElement("case", childCase2)
                                            .addSubElement("display_child", "OK")
                                            .build();


        CommcareForm commcareForm = new CommcareFormBuilder()
                                            .addSubElement("hh_number", "165")
                                            .addSubElement("family_number", "5")
                                            .addSubElement("child_info", childCase1Details)
                                            .addSubElement("child_info", childCase2Details)
                                            .build();

        List<Map<String,String>> childInfos = new ChildInfoParser().parse(commcareForm);

        assertEquals(2, childInfos.size());

        ReflectionAssert.assertReflectionEquals(getExpectedChild(childInfos.get(0).get("caseId")), childInfos.get(0));
        ReflectionAssert.assertReflectionEquals(getExpectedChild(childInfos.get(1).get("caseId")), childInfos.get(1));
    }

    private HashMap<String, String> getExpectedChild(final String caseId){

        final String index = caseId.equals("59ab28e0-2d2d-4bc7-933f-09dcacf70d61") ? "1" : "0";
        return new HashMap<String, String>() {{
            put("caseId", caseId);
            put("dateModified", "2012-07-21T12:02:59.923+05:30");
            put("cid",caseId);
            put("case",null);
            put("index", index);
            put("displayChild", "OK");
        }};
    }
}

