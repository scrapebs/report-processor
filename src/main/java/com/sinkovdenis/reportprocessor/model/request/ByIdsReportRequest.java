package com.sinkovdenis.reportprocessor.model.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ByIdsReportRequest extends GenericReportRequest {
    @NonNull
    private List<String> ids;
}
