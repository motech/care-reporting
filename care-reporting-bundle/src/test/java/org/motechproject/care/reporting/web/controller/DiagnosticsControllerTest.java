package org.motechproject.care.reporting.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.motechproject.commcare.provider.sync.diagnostics.AllDiagnosticsProbes;
import org.motechproject.commcare.provider.sync.diagnostics.DiagnosticsStatus;
import org.motechproject.commcare.provider.sync.diagnostics.scheduler.DiagnosticsContext;
import org.motechproject.commcare.provider.sync.diagnostics.scheduler.SchedulerDiagnosticsProbe;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DiagnosticsControllerTest {
    private DiagnosticsController diagnosticsController;

    @Mock
    private AllDiagnosticsProbes allDiagnosticsProbes;

    @Mock
    private HttpServletResponse httpServletResponse;
    @Mock
    private ServletOutputStream servletOutputStream;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void shouldReturnCompleteLogAsResponse() throws Exception {
        diagnosticsController = new DiagnosticsController(allDiagnosticsProbes);

        Answer answer = new Answer() {
            public Object answer(InvocationOnMock aInvocation) throws Throwable {
                Writer writer = (Writer) aInvocation.getArguments()[1];
                writer.write("my output");
                return null;
            }
        };
        when(allDiagnosticsProbes.diagnose(any(DiagnosticsContext.class), any(Writer.class))).thenAnswer(answer);

        ResponseEntity<String> responseEntity = diagnosticsController.complete();

        ArgumentCaptor<DiagnosticsContext> captor = ArgumentCaptor.forClass(DiagnosticsContext.class);
        verify(allDiagnosticsProbes).diagnose(captor.capture(), any(Writer.class));
        assertEquals(Arrays.asList("org.motechproject.care.reporting.constants.compute.fields-computefields"),
                captor.getValue().get(SchedulerDiagnosticsProbe.SCHEDULE_LIST_TO_PROBE));

        assertEquals("my output", responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.TEXT_PLAIN, responseEntity.getHeaders().getContentType());
    }

    @Test
    public void shouldReturnSuccessWhenProbesAreSuccessful() throws Exception {
        diagnosticsController = new DiagnosticsController(allDiagnosticsProbes);

        Answer answer = new Answer() {
            public Object answer(InvocationOnMock aInvocation) throws Throwable {
                Writer writer = (Writer) aInvocation.getArguments()[1];
                writer.write("my output");
                return DiagnosticsStatus.PASS;
            }
        };
        when(allDiagnosticsProbes.diagnose(any(DiagnosticsContext.class), any(Writer.class))).thenAnswer(answer);

        ResponseEntity<String> responseEntity = diagnosticsController.result();

        assertEquals("SUCCESS", responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.TEXT_PLAIN, responseEntity.getHeaders().getContentType());
    }

    @Test
    public void shouldReturnSuccessWhenProbesFail() throws Exception {
        diagnosticsController = new DiagnosticsController(allDiagnosticsProbes);

        Answer answer = new Answer() {
            public Object answer(InvocationOnMock aInvocation) throws Throwable {
                Writer writer = (Writer) aInvocation.getArguments()[1];
                writer.write("my output");
                return DiagnosticsStatus.FAIL;
            }
        };
        when(allDiagnosticsProbes.diagnose(any(DiagnosticsContext.class), any(Writer.class))).thenAnswer(answer);

        ResponseEntity<String> responseEntity = diagnosticsController.result();

        assertEquals("FAILED", responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.TEXT_PLAIN, responseEntity.getHeaders().getContentType());
    }

    @Test
    public void shouldReturnSuccessWhenProbesReturnsUnknown() throws Exception {
        diagnosticsController = new DiagnosticsController(allDiagnosticsProbes);

        Answer answer = new Answer() {
            public Object answer(InvocationOnMock aInvocation) throws Throwable {
                Writer writer = (Writer) aInvocation.getArguments()[1];
                writer.write("my output");
                return DiagnosticsStatus.UNKNOWN;
            }
        };
        when(allDiagnosticsProbes.diagnose(any(DiagnosticsContext.class), any(Writer.class))).thenAnswer(answer);

        ResponseEntity<String> responseEntity = diagnosticsController.result();

        assertEquals("FAILED", responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.TEXT_PLAIN, responseEntity.getHeaders().getContentType());
    }


    @Test
    public void shouldReturnSuccessWhenProbesReturnsWarn() throws Exception {
        diagnosticsController = new DiagnosticsController(allDiagnosticsProbes);

        Answer answer = new Answer() {
            public Object answer(InvocationOnMock aInvocation) throws Throwable {
                Writer writer = (Writer) aInvocation.getArguments()[1];
                writer.write("my output");
                return DiagnosticsStatus.WARN;
            }
        };
        when(allDiagnosticsProbes.diagnose(any(DiagnosticsContext.class), any(Writer.class))).thenAnswer(answer);

        ResponseEntity<String> responseEntity = diagnosticsController.result();

        assertEquals("FAILED", responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.TEXT_PLAIN, responseEntity.getHeaders().getContentType());
    }

    @Test
    public void shouldReturnSuccessWhenProbesReturnsNull() throws Exception {
        diagnosticsController = new DiagnosticsController(allDiagnosticsProbes);

        Answer answer = new Answer() {
            public Object answer(InvocationOnMock aInvocation) throws Throwable {
                Writer writer = (Writer) aInvocation.getArguments()[1];
                writer.write("my output");
                return null;
            }
        };
        when(allDiagnosticsProbes.diagnose(any(DiagnosticsContext.class), any(Writer.class))).thenAnswer(answer);

        ResponseEntity<String> responseEntity = diagnosticsController.result();

        assertEquals("FAILED", responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.TEXT_PLAIN, responseEntity.getHeaders().getContentType());
    }
}