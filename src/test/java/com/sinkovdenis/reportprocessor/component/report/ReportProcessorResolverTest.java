package com.sinkovdenis.reportprocessor.component.report;

import com.sinkovdenis.reportprocessor.model.request.GenericReportRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReportProcessorResolverTest  {

    @Mock
    private List<ReportProcessor<?>> reportProcessorList;

    @Mock
    private ReportProcessor<?> reportProcessor;

    @Mock
    private GenericReportRequest request;

    @InjectMocks
    private ReportProcessorResolver resolver;

    @Test
    public void testResolveBy() {
        when(reportProcessor.support(request)).thenReturn(true);
        when(reportProcessorList.stream()).thenReturn(Stream.of(reportProcessor));
        assertThat(resolver.resolveBy(request)).isEqualTo(Optional.of(reportProcessor));
    }

    @Test
    public void testResolveBy_notSupport() {
        when(reportProcessor.support(request)).thenReturn(false);
        when(reportProcessorList.stream()).thenReturn(Stream.of(reportProcessor));
        assertThat(resolver.resolveBy(request)).isNotPresent();
    }
}
