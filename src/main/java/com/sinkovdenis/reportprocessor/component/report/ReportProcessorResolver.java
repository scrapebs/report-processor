package com.sinkovdenis.reportprocessor.component.report;

import com.sinkovdenis.reportprocessor.model.request.GenericReportRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReportProcessorResolver {

    private final List<ReportProcessor<?>> requestProcessors;

    public <R extends GenericReportRequest, P extends ReportProcessor<R>> Optional<P> resolveBy(@NonNull R request) {
        return (Optional<P>) requestProcessors.stream()
                .filter(r -> r.support(request))
                .findFirst();
    }
}
