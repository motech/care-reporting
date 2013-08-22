package org.motechproject.care.reporting.processors;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.service.Service;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ComputeDeliveryOffsetForMotherTest {

    @Mock
    private Service service;

    private ComputeDeliveryOffsetForMother computeDeliveryOffsetForMother;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        computeDeliveryOffsetForMother = new ComputeDeliveryOffsetForMother(service);
    }

    @Test
    public void testComputeDeliveryOffsetWhenMapIsNull() throws Exception {
        computeDeliveryOffsetForMother.compute(null);
        verify(service, never()).getMotherCase(null);
    }

    @Test
    public void testComputeDeliveryOffsetWhenCaseIdDoesnotExist() throws Exception {

        final String caseId = "12345";

        Map<String, String> formInfoMap = new HashMap<>();

        computeDeliveryOffsetForMother.compute(formInfoMap);

        verify(service, never()).getMotherCase(caseId);
        assertEquals(null, formInfoMap.get("deliveryOffsetDays"));
    }


    @Test
    public void testComputeDeliveryOffsetWhenCaseIdIsNull() throws Exception {

        final String caseId = null;

        Map<String, String> formInfoMap = new HashMap<>();
        formInfoMap.put("caseId", caseId);

        computeDeliveryOffsetForMother.compute(formInfoMap);

        verify(service, never()).getMotherCase(caseId);
        assertEquals(null, formInfoMap.get("deliveryOffsetDays"));
    }


    @Test
    public void testComputeDeliveryOffsetWhenServerModifiedIsNull() throws Exception {

        final String caseId = "12345";

        Map<String, String> formInfoMap = new HashMap<>();
        formInfoMap.put("caseId", caseId);
        formInfoMap.put("serverDateModified", null);

        computeDeliveryOffsetForMother.compute(formInfoMap);

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

        computeDeliveryOffsetForMother.compute(formInfoMap);

        verify(service).getMotherCase(caseId);
        assertEquals(null, formInfoMap.get("deliveryOffsetDays"));
    }

    @Test
    public void testComputeDeliveryOffsetUsingEDDForMother() throws Exception {

        final String caseId = "12345";
        final String serverModified = DateTime.now().toString();

        Map<String, String> formInfoMap = new HashMap<>();
        formInfoMap.put("caseId", caseId);
        formInfoMap.put("serverDateModified", serverModified);

        MotherCase motherCase = new MotherCase();
        motherCase.setCaseId(caseId);
        motherCase.setEdd(new DateTime().plusDays(10).toDate());
        motherCase.setAdd(null);

        when(service.getMotherCase(caseId)).thenReturn(motherCase);

        computeDeliveryOffsetForMother.compute(formInfoMap);

        verify(service).getMotherCase(caseId);
        assertEquals("-10", formInfoMap.get("deliveryOffsetDays"));
    }


    @Test
    public void testComputeDeliveryOffsetUsingADDForMother() throws Exception {

        final String caseId = "12345";
        final String serverModified = DateTime.now().toString();

        Map<String, String> formInfoMap = new HashMap<>();
        formInfoMap.put("caseId", caseId);
        formInfoMap.put("serverDateModified", serverModified);

        MotherCase motherCase = new MotherCase();
        motherCase.setCaseId(caseId);
        motherCase.setEdd(null);
        motherCase.setAdd(new DateTime().minusDays(10).toDate());

        when(service.getMotherCase(caseId)).thenReturn(motherCase);

        computeDeliveryOffsetForMother.compute(formInfoMap);

        verify(service).getMotherCase(caseId);
        assertEquals("10", formInfoMap.get("deliveryOffsetDays"));
    }
}
