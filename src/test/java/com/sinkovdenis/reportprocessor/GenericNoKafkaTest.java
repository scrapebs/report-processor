package com.sinkovdenis.reportprocessor;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("kafka_off")
public abstract class GenericNoKafkaTest extends GenericTest{
}
