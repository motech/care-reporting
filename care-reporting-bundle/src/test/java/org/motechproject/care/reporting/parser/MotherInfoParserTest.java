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
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MotherInfoParserTest {

    @Mock
    InfoParser infoParser;

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void shouldMapCaseIdAndDateModified() throws Exception {

        FormValueElement motherCase = new FormValueElementBuilder()
                                            .addAttribute("case_id", "94d5374f-290e-409f-bc57-86c2e4bcc43f")
                                            .addAttribute("date_modified", "2012-07-21T12:02:59.923+05:30")
                                            .addAttribute("user_id", "89fda0284e008d2e0c980fb13fa0e5bb")
                                            .build();

        FormValueElement childCase = new FormValueElementBuilder()
                                            .addSubElement("hh_number", "555")
                                            .addSubElement("age", "1")
                                            .build();

        CommcareForm commcareForm = new CommcareFormBuilder()
                                            .addSubElement("hh_number", "165")
                                            .addSubElement("family_number", "5")
                                            .addSubElement("child_info", childCase)
                                            .build();

        when(infoParser.getCaseElement(commcareForm.getForm())).thenReturn(motherCase);

        Map<String,String> motherInfo = new MotherInfoParser(infoParser).parse(commcareForm);

        assertEquals(2, motherInfo.size());

        HashMap<String, String> expected = new HashMap<String, String>() {{
            put("caseId", "94d5374f-290e-409f-bc57-86c2e4bcc43f");
            put("dateModified", "2012-07-21T12:02:59.923+05:30");
        }};

        verify(infoParser).parse(commcareForm.getForm(), true);
        verify(infoParser).parse(motherCase, true);
        verify(infoParser, never()).parse(childCase, true);

        ReflectionAssert.assertReflectionEquals(expected, motherInfo);
    }
    
    
    @Test
    public void shouldReturnNullIfMotherCaseIsNotFound() {
        CommcareForm commcareForm = new CommcareFormBuilder()
                .addSubElement("hh_number", "165")
                .addSubElement("family_number", "5")
                .build();

        Map<String,String> motherInfo = new MotherInfoParser(infoParser).parse(commcareForm);

        assertNull(motherInfo);

        verify(infoParser, never()).parse(any(FormValueElement.class), eq(true));
    }
}

