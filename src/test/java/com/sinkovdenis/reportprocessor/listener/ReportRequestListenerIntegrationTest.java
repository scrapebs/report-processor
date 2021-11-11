package com.sinkovdenis.reportprocessor.listener;

import com.sinkovdenis.reportprocessor.GenericTest;
import com.sinkovdenis.reportprocessor.TestSinglePartitionTopicHelper;
import com.sinkovdenis.reportprocessor.model.ReportType;
import com.sinkovdenis.reportprocessor.model.request.ByDateReportRequest;
import com.sinkovdenis.reportprocessor.model.request.ByIdsReportRequest;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

import static com.sinkovdenis.reportprocessor.TestData.createByDateReportRequest;
import static com.sinkovdenis.reportprocessor.TestData.createByIdsReportRequest;
import static com.sinkovdenis.reportprocessor.configuration.kafka.properties.RequestListenerTestProperties.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@EmbeddedKafka(
        partitions = 1,
        topics = {LISTENER_TOPIC},
        brokerProperties = {
                "transaction.state.log.replication.factor=1",
                "transaction.state.log.min.isr=1"
        }
)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ReportRequestListenerIntegrationTest extends GenericTest {

        @SpyBean
        private ReportRequestListener listener;

        @Autowired
        private EmbeddedKafkaBroker embeddedKafkaBroker;

        @Autowired
        private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;
        
        private TestSinglePartitionTopicHelper helper;

        private static final ByDateReportRequest BY_DATE_REPORT_REQUEST = createByDateReportRequest(ReportType.SALES_REPORT);
        private static final ByIdsReportRequest BY_IDS_REPORT_REQUEST = createByIdsReportRequest(ReportType.SALES_REPORT);

        @Before
        public void setUp() {
                helper = new TestSinglePartitionTopicHelper(embeddedKafkaBroker, TimeUnit.SECONDS.toMillis(1));
                helper.waitListeners(kafkaListenerEndpointRegistry);
        }

        @Test
        @SneakyThrows
        public void testHandle_byDateRequest(){
                doNothing().when(listener).handle(any());
                helper.send(LISTENER_TOPIC, BY_DATE_REPORT_REQUEST);
                TimeUnit.SECONDS.sleep(5L);

                verify(listener).handle(any(ByDateReportRequest.class));
                helper.assertEmpty(LISTENER_TOPIC, LISTENER_GROUP);
                helper.assertOne(LISTENER_TOPIC, NON_LISTENER_GROUP);
        }

        @Test
        @SneakyThrows
        public void testHandle_byIdsRequest(){
                doNothing().when(listener).handle(any());
                helper.send(LISTENER_TOPIC, BY_IDS_REPORT_REQUEST);
                TimeUnit.SECONDS.sleep(5L);

                verify(listener).handle(any(ByIdsReportRequest.class));
                helper.assertEmpty(LISTENER_TOPIC, LISTENER_GROUP);
                helper.assertOne(LISTENER_TOPIC, NON_LISTENER_GROUP);
        }

        @Test
        public void testHandle_deserialization_byDateRequest() {
                doNothing().when(listener).handle(any());
                helper.send(LISTENER_TOPIC, BY_DATE_REPORT_REQUEST);
                ArgumentCaptor<ByDateReportRequest> listenerCaptor = ArgumentCaptor.forClass(ByDateReportRequest.class);

                verify(listener, timeout(TimeUnit.SECONDS.toMillis(15))).handle(listenerCaptor.capture());
                assertThat(listenerCaptor.getValue()).isEqualTo(BY_DATE_REPORT_REQUEST);
        }

        @Test
        public void testHandle_deserialization_byIdsRequest() {
                doNothing().when(listener).handle(any());
                helper.send(LISTENER_TOPIC, BY_IDS_REPORT_REQUEST);
                ArgumentCaptor<ByIdsReportRequest> listenerCaptor = ArgumentCaptor.forClass(ByIdsReportRequest.class);

                verify(listener, timeout(TimeUnit.SECONDS.toMillis(15))).handle(listenerCaptor.capture());
                assertThat(listenerCaptor.getValue()).isEqualTo(BY_IDS_REPORT_REQUEST);
        }
}
