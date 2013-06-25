package org.motechproject.care.reporting.converter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.service.CareService;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class FlwConverterTest {
    @Mock
    private CareService careService;
    private FlwConverter flwConverter;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        flwConverter = new FlwConverter(careService);
    }

    @Test
    public void shouldQueryCareServiceForFlw() throws Exception {
        Flw flw = new Flw();
        when(careService.getOrCreateFlw("89fda0284e008d2e0c980fb13f96c45a")).thenReturn(flw);
        Object output = flwConverter.convert(Flw.class, "89fda0284e008d2e0c980fb13f96c45a");
        assertSame(flw, output);
    }

    @Test
    public void shouldThrowExceptionIfTryingToConvertedNonStringIdValue() throws Exception {
        Object convertedValue = flwConverter.convert(Flw.class, new Object());
        assertNull(convertedValue);
    }

    @Test
    public void shouldReturnNullIfValuePassedIsNull() throws Exception {
        Object convertedValue = flwConverter.convert(Flw.class, null);
        assertNull(convertedValue);
    }
}
