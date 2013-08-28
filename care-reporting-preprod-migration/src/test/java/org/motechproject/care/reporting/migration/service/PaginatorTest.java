package org.motechproject.care.reporting.migration.service;

import com.google.gson.JsonArray;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.care.reporting.migration.common.Constants;
import org.motechproject.care.reporting.migration.common.PaginatedResult;
import org.motechproject.care.reporting.migration.common.PaginationOption;
import org.motechproject.care.reporting.migration.common.ResponseParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PaginatorTest {

    @Mock
    private PaginationScheme scheme;
    @Mock
    private ResponseParser parser;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldInitializePaginationOptionWithOffset0ForFirstPage() {
        Map<String,String> parameters = new HashMap<>();
        Paginator paginator = new Paginator(parameters, scheme, parser);

        when(scheme.nextPage(eq(parameters), any(PaginationOption.class))).thenReturn("testresponse");
        when(parser.parse("testresponse")).thenReturn(new PaginatedResult(null, null));
        paginator.nextPage();

        ArgumentCaptor<PaginationOption> optionCaptor = ArgumentCaptor.forClass(PaginationOption.class);
        verify(scheme).nextPage(eq(parameters), optionCaptor.capture());
        PaginationOption paginationOption = optionCaptor.getValue();
        assertEquals(100, paginationOption.getLimit());
        assertEquals(0, paginationOption.getOffset());
    }

    @Test
    public void shouldFetchNextPage() {
        Map<String,String> parameters = new HashMap<>();
        Paginator paginator = new Paginator(parameters, scheme, parser);
        PaginatedResult paginatedResult = new PaginatedResult(new JsonArray(), new PaginationOption(100, 100));
        when(parser.parse(anyString())).thenReturn(paginatedResult);

        paginator.nextPage();
        paginator.nextPage();

        ArgumentCaptor<PaginationOption> optionCaptor = ArgumentCaptor.forClass(PaginationOption.class);
        verify(scheme, times(2)).nextPage(eq(parameters), optionCaptor.capture());
        List<PaginationOption> allValues = optionCaptor.getAllValues();
        assertEquals(2, allValues.size());
        assertEquals(100, allValues.get(0).getLimit());
        assertEquals(0, allValues.get(0).getOffset());

        assertEquals(100, allValues.get(1).getLimit());
        assertEquals(100, allValues.get(1).getOffset());
    }

    @Test
    public void shouldReturnNullIfNoPage() {
        Map<String,String> parameters = new HashMap<>();
        Paginator paginator = new Paginator(parameters, scheme, parser);
        PaginatedResult paginatedResult = new PaginatedResult(new JsonArray(), null);
        when(parser.parse(anyString())).thenReturn(paginatedResult);

        paginator.nextPage();
        PaginatedResult lastPage = paginator.nextPage();

        ArgumentCaptor<PaginationOption> optionCaptor = ArgumentCaptor.forClass(PaginationOption.class);
        verify(scheme, times(1)).nextPage(eq(parameters), optionCaptor.capture());
        PaginationOption paginationOption = optionCaptor.getValue();
        assertEquals(100, paginationOption.getLimit());
        assertEquals(0, paginationOption.getOffset());

        assertNull(lastPage);
    }

    @Test
    public void shouldOverrideDefaultLimitAndOffsetIfProvidedInParams() {

        Map<String,String> parameters = new HashMap<String,String>(){{
                put(Constants.OFFSET, "2000");
                put(Constants.LIMIT, "1000");
        }};
        Paginator paginator = new Paginator(parameters, scheme, parser);
        when(scheme.nextPage(eq(parameters), any(PaginationOption.class))).thenReturn("testresponse");
        when(parser.parse("testresponse")).thenReturn(new PaginatedResult(null, null));
        paginator.nextPage();


        paginator.nextPage();
        ArgumentCaptor<PaginationOption> optionCaptor = ArgumentCaptor.forClass(PaginationOption.class);
        verify(scheme).nextPage(eq(parameters), optionCaptor.capture());

        assertEquals(2000, optionCaptor.getValue().getOffset());
        assertEquals(1000, optionCaptor.getValue().getLimit());
    }

}
