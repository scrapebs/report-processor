package com.sinkovdenis.reportprocessor.configuration.kafka;

import com.sinkovdenis.reportprocessor.model.GenericReportRequest;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class ReportRequestDeserializer extends JsonDeserializer<GenericReportRequest > {
    public ReportRequestDeserializer() {
        super(GenericReportRequest.class, false);
    }
}
