package com.way2invoice.bms.facade;

import com.way2invoice.bms.common.response.WidgetData;
import com.way2invoice.bms.domain.enumeration.InvoiceType;
import java.util.Optional;

public interface DashboardFacade {

    /**
     * Get total income based on the parameters
     *
     * @return {@link WidgetData}
     */
    Optional<WidgetData> getTotalAmount(InvoiceType invoiceType);

    /**
     * Get total expense based on the parameters
     *
     * @return
     */
    Optional<WidgetData> getTotalExpense();

    /**
     * Get total profit based on the parameters
     *
     * @return
     */
    Optional<WidgetData> getTotalProfit();
}
