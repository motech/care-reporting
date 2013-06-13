package org.motechproject.care.reporting.parser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.care.reporting.builder.CommcareFormBuilder;
import org.motechproject.care.reporting.builder.FormValueElementBuilder;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;
import org.unitils.reflectionassert.ReflectionAssert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.motechproject.care.reporting.builder.FormValueElementBuilder.getFVE;

public class ChildInfoParserTest {

    @Mock
    InfoParser infoParser;

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void shouldPopulateCaseInformation() throws Exception {

        FormValueElement childCase1 = new FormValueElementBuilder()
                .addAttribute("case_id", "3e8998ce-b19f-4fa7-b1a1-721b6951e3cf")
                .addAttribute("date_modified", "2012-07-21T12:02:59.923+05:30")
                .build();

        FormValueElement childCase1Details = getFVE("case", childCase1);

        FormValueElement childCase2 = new FormValueElementBuilder()
                .addAttribute("case_id", "59ab28e0-2d2d-4bc7-933f-09dcacf70d61")
                .addAttribute("date_modified", "2012-07-21T12:02:59.923+05:30")
                .build();

        FormValueElement childCase2Details = getFVE("case", childCase2);

        CommcareForm commcareForm = new CommcareFormBuilder()
                .addSubElement("child_info", childCase1Details)
                .addSubElement("child_info", childCase2Details)
                .build();

        List<Map<String, String>> childInfos = new ChildInfoParser(infoParser).parse(commcareForm);

        assertEquals(2, childInfos.size());

        verify(infoParser).parse(childCase1Details, true);
        verify(infoParser).parse(childCase1, true);
        verify(infoParser).parse(childCase2Details, true);
        verify(infoParser).parse(childCase2, true);

        ReflectionAssert.assertReflectionEquals(getExpectedChild(childInfos.get(0).get("caseId")), childInfos.get(0));
        ReflectionAssert.assertReflectionEquals(getExpectedChild(childInfos.get(1).get("caseId")), childInfos.get(1));
    }

    private HashMap<String, String> getExpectedChild(final String caseId) {

        return new HashMap<String, String>() {{
            put("caseId", caseId);
            put("dateModified", "2012-07-21T12:02:59.923+05:30");
        }};
    }

    @Test
    public void shouldChildFormInfoOverwriteChildCaseInfo(){
        FormValueElement childCase1 = new FormValueElementBuilder()
                .addAttribute("case_id", "3e8998ce-b19f-4fa7-b1a1-721b6951e3cf")
                .addAttribute("date_modified", "2012-07-21T12:02:59.923+05:30")
                .build();

        FormValueElement childCase1Details = getFVE("case", childCase1);


        CommcareForm commcareForm = new CommcareFormBuilder()
                .addSubElement("child_info", childCase1Details)
                .build();


        final HashMap<String, String> expected = new HashMap<String, String>() {{
            put("caseId", "3e8998ce-b19f-4fa7-b1a1-721b6951e3cf");
            put("dateModified", "2012-07-21T12:02:59.923+05:30");
            put("age", "22");
        }};

        final HashMap<String, String> childCaseDetails = new HashMap<String, String>() {{
            put("age", "21");
        }};
        final HashMap<String, String> childFormDetails = new HashMap<String, String>() {{
            put("age", "22");
        }};

        when(infoParser.parse(childCase1, true)).thenReturn(childCaseDetails);
        when(infoParser.parse(childCase1Details, true)).thenReturn(childFormDetails);

        List<Map<String, String>> childInfos = new ChildInfoParser(infoParser).parse(commcareForm);

        assertEquals(1, childInfos.size());

        verify(infoParser).parse(childCase1Details, true);
        verify(infoParser).parse(childCase1, true);

        ReflectionAssert.assertReflectionEquals(expected, childInfos.get(0));
    }
}

