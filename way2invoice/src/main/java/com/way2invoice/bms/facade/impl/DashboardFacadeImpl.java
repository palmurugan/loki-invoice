package com.way2invoice.bms.facade.impl;

import static com.way2invoice.bms.util.ApplicationUtil.prepareWidgetData;

import com.way2invoice.bms.common.response.WidgetData;
import com.way2invoice.bms.domain.enumeration.InvoiceType;
import com.way2invoice.bms.facade.DashboardFacade;
import com.way2invoice.bms.repository.InvoicePaymentRepository;
import com.way2invoice.bms.repository.InvoiceRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * A facade service for dashboard
 */
@Service
@Transactional
public class DashboardFacadeImpl implements DashboardFacade {

    private static final String TOTAL = "total";

    private final InvoiceRepository invoiceRepository;

    private final InvoicePaymentRepository invoicePaymentRepository;

    public DashboardFacadeImpl(InvoiceRepository invoiceRepository,
        InvoicePaymentRepository invoicePaymentRepository) {
        this.invoiceRepository = invoiceRepository;
        this.invoicePaymentRepository = invoicePaymentRepository;
    }

    /**
     * Getting total invoice amount based on the type whether purchase or sales
     *
     * @param invoiceType the invoice type (Purchase/Sales)
     * @return the {@link WidgetData}
     */
    @Override
    public Optional<WidgetData> getTotalAmount(InvoiceType invoiceType) {
        BigDecimal totalAmount = invoiceRepository.findTotalAmount(invoiceType);
        return prepareWidgetData(TOTAL, totalAmount);
    }

    /**
     * Getting total expense
     *
     * @return
     */
    @Override
    public Optional<WidgetData> getTotalExpense() {
        return null;
    }

    /**
     * Getting total profit based on the input params
     *
     * @return {@link WidgetData} the total profit
     */
    @Override
    public Optional<WidgetData> getTotalProfit() {
        BigDecimal totalPurchaseInvoice = invoiceRepository.findTotalAmount(InvoiceType.P);
        BigDecimal totalSalesInvoice = invoiceRepository.findTotalAmount(InvoiceType.S);
        return prepareWidgetData(TOTAL, (totalSalesInvoice.subtract(totalPurchaseInvoice)));
    }
}
