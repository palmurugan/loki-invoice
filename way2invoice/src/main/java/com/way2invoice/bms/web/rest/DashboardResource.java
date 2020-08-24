package com.way2invoice.bms.web.rest;

import static com.way2invoice.bms.util.ApplicationConstants.PURCHASE;

import com.way2invoice.bms.common.response.WidgetData;
import com.way2invoice.bms.domain.enumeration.InvoiceType;
import com.way2invoice.bms.facade.DashboardFacade;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * A REST controller for dashboard
 */
@RestController
@RequestMapping("/api/dashboard")
public class DashboardResource {

    private final Logger log = LoggerFactory.getLogger(DashboardResource.class);

    private final DashboardFacade dashboardFacade;

    public DashboardResource(DashboardFacade dashboardFacade) {
        this.dashboardFacade = dashboardFacade;
    }

    /**
     * {@code GET /total } :get the total invoice amount based on the type
     *
     * @param invoiceType the invoice type as a input parameter
     * @return {@link WidgetData} {@code 200 OK} or {@code 404 NotFound}
     */
    @GetMapping("/total")
    public ResponseEntity<WidgetData> getTotalAmount(@RequestParam String invoiceType) {
        log.debug("REST request to get the total amount for the invoice type {}", invoiceType);
        InvoiceType invoiceTypeEnum =
            StringUtils.isNotBlank(invoiceType) && PURCHASE.equals(invoiceType)
                ? InvoiceType.P : InvoiceType.S;
        return ResponseUtil.wrapOrNotFound(dashboardFacade.getTotalAmount(invoiceTypeEnum));
    }

    /**
     * {@code GET /profit} :get the total profit amount
     *
     * @return {@link WidgetData} {@code 200 OK} or {@code 404 NotFound}
     */
    @GetMapping("/profit")
    public ResponseEntity<WidgetData> getTotalProfit() {
        log.debug("REST request to get the total profit");
        return ResponseUtil.wrapOrNotFound(dashboardFacade.getTotalProfit());
    }
}
