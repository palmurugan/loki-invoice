package com.way2invoice.bms.util;

import com.way2invoice.bms.common.response.WidgetData;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

/**
 * @author palmuruganc
 * <p>
 * The utility for the whole application
 */
public class ApplicationUtil {

    private static final String TAX_TYPE = "taxType";

    private ApplicationUtil() {
    }

    /**
     * Converting any object to Long Ex: String to Long
     *
     * @param object
     * @return
     */
    public static Long convertToLong(Object object) {
        return Long.parseLong(String.valueOf(object));
    }

    /**
     * Rounding any big decimal value to the nearest 0.5
     *
     * @param value the input value to be roundUp
     * @return
     */
    public static BigDecimal roundUp(BigDecimal value) {
        BigDecimal roundedValue = round(value, BigDecimal.valueOf(0.5), RoundingMode.HALF_UP);
        return roundedValue.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * @param value
     * @param rounding
     * @param roundingMode
     * @return the final value with rounding
     */
    private static BigDecimal round(BigDecimal value, BigDecimal rounding,
        RoundingMode roundingMode) {
        return rounding.signum() == 0 ? value :
            (value.divide(rounding, 0, roundingMode)).multiply(rounding);
    }

    /**
     * Util method to format Invoice or Bill Ids
     *
     * @param prefix
     * @param sequence
     * @return
     */
    public static String formatSequenceId(String prefix, String sequence) {
        return String.format("%s-%06d", prefix, Long.parseLong(sequence));
    }

    /**
     * Util method to prepare the widget data based on the key and value
     *
     * @param key
     * @param value
     * @return
     */
    public static Optional<WidgetData> prepareWidgetData(String key, Object value) {
        return Optional.of(new WidgetData(key, value));
    }

    /**
     * Util function to find the tax type based on the stateIds
     *
     * @param companyState
     * @param accountState
     * @return {@link WidgetData} which contains the tax type.
     */
    public static Optional<WidgetData> findTaxType(Long companyState, Long accountState) {
        return companyState.equals(accountState)
            ? Optional.of(new WidgetData(TAX_TYPE, ApplicationConstants.GST)) :
            Optional.of(new WidgetData(TAX_TYPE, ApplicationConstants.IGST));
    }
}
