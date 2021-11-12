package com.sinkovdenis.reportprocessor.component.report;

import com.sinkovdenis.reportprocessor.GenericNoKafkaTest;
import com.sinkovdenis.reportprocessor.component.report.date.ByDateRefundReportProcessor;
import com.sinkovdenis.reportprocessor.component.report.date.ByDateOrderReportProcessor;
import com.sinkovdenis.reportprocessor.component.report.ids.ByIdsRefundReportProcessor;
import com.sinkovdenis.reportprocessor.component.report.ids.ByIdsOrderReportProcessor;
import com.sinkovdenis.reportprocessor.model.ReportType;
import com.sinkovdenis.reportprocessor.model.request.ByDateReportRequest;
import com.sinkovdenis.reportprocessor.model.request.ByIdsReportRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.sinkovdenis.reportprocessor.TestData.createByDateReportRequest;
import static com.sinkovdenis.reportprocessor.TestData.createByIdsReportRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class ReportProcessorResolverIntegrationTest extends GenericNoKafkaTest {

    @Autowired
    private ReportProcessorResolver resolver;

    @Autowired
    private ByDateRefundReportProcessor byDateRefundReportProcessor;

    @Autowired
    private ByDateOrderReportProcessor byDateOrderReportProcessor;

    @Autowired
    private ByIdsRefundReportProcessor byIdsRefundReportProcessor;

    @Autowired
    private ByIdsOrderReportProcessor byIdsOrderReportProcessor;


    @Test
    public void testResolveBy_refundsReport_byDateRequest() {
        ByDateReportRequest request = createByDateReportRequest(ReportType.REFUNDS_REPORT);
        assertThat(resolver.resolveBy(request)).isEqualTo(Optional.of(byDateRefundReportProcessor));
    }

    @Test
    public void testResolveBy_ordersReport_byDateRequest() {
        ByDateReportRequest request = createByDateReportRequest(ReportType.ORDERS_REPORT);
        assertThat(resolver.resolveBy(request)).isEqualTo(Optional.of(byDateOrderReportProcessor));
    }

    @Test
    public void testResolveBy_refundsReport_byIdsRequest() {
        ByIdsReportRequest request = createByIdsReportRequest(ReportType.REFUNDS_REPORT);
        assertThat(resolver.resolveBy(request)).isEqualTo(Optional.of(byIdsRefundReportProcessor));
    }

    @Test
    public void testResolveBy_ordersReport_byIdsRequest() {
        ByIdsReportRequest request = createByIdsReportRequest(ReportType.ORDERS_REPORT);
        assertThat(resolver.resolveBy(request)).isEqualTo(Optional.of(byIdsOrderReportProcessor));
    }
}
