package com.sinkovdenis.reportprocessor.publisher;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProcessingEventPublisherTestParams {
    public static final String PROCESSING_EVENTS_TOPIC = "test_topic";
    public static final String ANY_GROUP = "groupId";
}
