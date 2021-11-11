package com.sinkovdenis.reportprocessor.component.report;

import com.sinkovdenis.reportprocessor.GenericNoKafkaTest;
import com.sinkovdenis.reportprocessor.component.report.date.ByDateRefundReportProcessor;
import com.sinkovdenis.reportprocessor.component.report.date.ByDateSaleReportProcessor;
import com.sinkovdenis.reportprocessor.component.report.ids.ByIdsRefundReportProcessor;
import com.sinkovdenis.reportprocessor.component.report.ids.ByIdsSaleReportProcessor;
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
    private ByDateSaleReportProcessor byDateSaleReportProcessor;

    @Autowired
    private ByIdsRefundReportProcessor byIdsRefundReportProcessor;

    @Autowired
    private ByIdsSaleReportProcessor byIdsSaleReportProcessor;


    @Test
    public void testResolveBy_refundsReport_byDateRequest() {
        ByDateReportRequest request = createByDateReportRequest(ReportType.REFUNDS_REPORT);
        assertThat(resolver.resolveBy(request)).isEqualTo(Optional.of(byDateRefundReportProcessor));
    }

    @Test
    public void testResolveBy_salesReport_byDateRequest() {
        ByDateReportRequest request = createByDateReportRequest(ReportType.SALES_REPORT);
        assertThat(resolver.resolveBy(request)).isEqualTo(Optional.of(byDateSaleReportProcessor));
    }

    @Test
    public void testResolveBy_refundsReport_byIdsRequest() {
        ByIdsReportRequest request = createByIdsReportRequest(ReportType.REFUNDS_REPORT);
        assertThat(resolver.resolveBy(request)).isEqualTo(Optional.of(byIdsRefundReportProcessor));
    }

    @Test
    public void testResolveBy_salesReport_byIdsRequest() {
        ByIdsReportRequest request = createByIdsReportRequest(ReportType.SALES_REPORT);
        assertThat(resolver.resolveBy(request)).isEqualTo(Optional.of(byIdsSaleReportProcessor));
    }
}
