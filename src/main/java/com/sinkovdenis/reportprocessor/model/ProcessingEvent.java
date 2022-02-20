package com.sinkovdenis.reportprocessor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessingEvent {
    private long requestId;
    private ProcessingEventStatus status;
    private String details;
    
    public enum ProcessingEventStatus {
        SUCCESS, ERROR
    }
}
