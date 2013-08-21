package org.motechproject.care.reporting.processors;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.service.Service;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ComputeDeliveryOffsetForChildTest {

    @Mock
    private Service service;

    private ComputeDeliveryOffsetForChild computeDeliveryOffsetForChild;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        computeDeliveryOffsetForChild = new ComputeDeliveryOffsetForChild(service);
    }

    @Test
    public void testComputeDeliveryOffsetWhenMapIsNull() throws Exception {
        computeDeliveryOffsetForChild.compute(null);
        verify(service, never()).getChildCase(null);
    }

    @Test
    public void testComputeDeliveryOffsetWhenCaseIdDoesnotExist() throws Exception {

        final String childCaseId = "12345";

        Map<String, String> formInfoMap = new HashMap<>();

        computeDeliveryOffsetForChild.compute(formInfoMap);

        verify(service, never()).getChildCase(childCaseId);
        assertEquals(null, formInfoMap.get("deliveryOffsetDays"));
    }


    @Test
    public void testComputeDeliveryOffsetWhenChildCaseIdIsNull() throws Exception {

        final String childCaseId = null;

        Map<String, String> formInfoMap = new HashMap<>();
        formInfoMap.put("caseId", childCaseId);

        computeDeliveryOffsetForChild.compute(formInfoMap);

        verify(service, never()).getChildCase(childCaseId);
        assertEquals(null, formInfoMap.get("deliveryOffsetDays"));
    }

    @Test
    public void testComputeDeliveryOffsetWhenServerModifiedIsNull() throws Exception {

        final String caseId = "12345";

        Map<String, String> formInfoMap = new HashMap<>();
        formInfoMap.put("caseId", caseId);
        formInfoMap.put("serverDateModified", null);

        computeDeliveryOffsetForChild.compute(formInfoMap);

        verify(service, never()).getMotherCase(caseId);
        assertEquals(null, formInfoMap.get("deliveryOffsetDays"));
    }


    @Test
    public void testComputeDeliveryOffsetWhenBothEddAndAddAreNull() throws Exception {

        final String caseId = "12345";
        final String serverModified = DateTime.now().toString();

        Map<String, String> formInfoMap = new HashMap<>();
        formInfoMap.put("caseId", caseId);
        formInfoMap.put("serverDateModified", serverModified);

        computeDeliveryOffsetForChild.compute(formInfoMap);

        verify(service).getChildCase(caseId);
        assertEquals(null, formInfoMap.get("deliveryOffsetDays"));
    }

    @Test
    public void testComputeDeliveryOffsetUsingEDDOfMother() throws Exception {

        final String caseId = "12345";
        final String serverModified = DateTime.now().toString();

        Map<String, String> formInfoMap = new HashMap<>();
        formInfoMap.put("caseId", caseId);
        formInfoMap.put("serverDateModified", serverModified);

        MotherCase motherCase = new MotherCase();
        motherCase.setEdd(new DateTime().plusDays(10).toDate());
        motherCase.setAdd(null);

        ChildCase childCase = new ChildCase();
        childCase.setCaseId(caseId);
        childCase.setMotherCase(motherCase);

        when(service.getChildCase(caseId)).thenReturn(childCase);

        computeDeliveryOffsetForChild.compute(formInfoMap);

        verify(service).getChildCase(caseId);
        assertEquals("10", formInfoMap.get("deliveryOffsetDays"));
    }


    @Test
    public void testComputeDeliveryOffsetUsingADDOfMother() throws Exception {

        final String caseId = "12345";
        final String serverModified = DateTime.now().toString();

        Map<String, String> formInfoMap = new HashMap<>();
        formInfoMap.put("caseId", caseId);
        formInfoMap.put("serverDateModified", serverModified);

        MotherCase motherCase = new MotherCase();
        motherCase.setEdd(null);
        motherCase.setAdd(new DateTime().minusDays(10).toDate());

        ChildCase childCase = new ChildCase();
        childCase.setCaseId(caseId);
        childCase.setMotherCase(motherCase);

        when(service.getChildCase(caseId)).thenReturn(childCase);

        computeDeliveryOffsetForChild.compute(formInfoMap);

        verify(service).getChildCase(caseId);
        assertEquals("-10", formInfoMap.get("deliveryOffsetDays"));
    }
}
