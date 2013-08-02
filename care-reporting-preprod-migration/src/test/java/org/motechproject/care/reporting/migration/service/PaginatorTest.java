package org.motechproject.care.reporting.migration.service;

import com.google.gson.JsonArray;
import org.apache.commons.httpclient.NameValuePair;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.care.reporting.migration.common.PaginatedResult;
import org.motechproject.care.reporting.migration.common.PaginationOption;
import org.motechproject.care.reporting.migration.common.ResponseParser;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
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
        NameValuePair[] parameters = {};
        Paginator paginator = new Paginator(parameters, scheme, parser);

        paginator.nextPage();

        ArgumentCaptor<PaginationOption> optionCaptor = ArgumentCaptor.forClass(PaginationOption.class);
        verify(scheme).nextPage(eq(parameters), optionCaptor.capture());
        PaginationOption paginationOption = optionCaptor.getValue();
        assertEquals(100, paginationOption.getLimit());
        assertEquals(0, paginationOption.getOffset());
    }

    @Test
    public void shouldFetchNextPage() {
        NameValuePair[] parameters = {};
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
        NameValuePair[] parameters = {};
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
}
