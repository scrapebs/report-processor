package com.sinkovdenis.reportprocessor.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.sinkovdenis.reportprocessor.model.ReportType;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ByDateReportRequest.class, name = "DATE_REQUEST"),
        @JsonSubTypes.Type(value = ByIdsReportRequest.class, name = "IDS_REQUEST")
})
public abstract class GenericReportRequest {
    @NonNull
    private ReportType reportType;
    @NonNull
    private String email;
    private Long requestId;
}