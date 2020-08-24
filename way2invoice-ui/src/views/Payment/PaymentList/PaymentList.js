import React from 'react';

import { GridHolder, DataGrid } from '../../../components'

const breadCrumbData = [
    { 'label': 'Payment' },
    { 'label': 'List' }
]
const invoiceColumns = [
    { "id": "invoiceName", "label": "Invoice Id" },
    { "id": "accountFrom", "label": "Paid From", "isChildAttribute": true, "parentAttribute": "payment" },
    { "id": "accountTo", "label": "Paid to", "isChildAttribute": true, "parentAttribute": "payment" },
    { "id": "amount", "label": "Amount", "type": "currency", "isChildAttribute": true, "parentAttribute": "payment" },
    { "id": "paymentMethod", "label": "Mode of Payment", "isChildAttribute": true, "parentAttribute": "payment" },
    { "id": "date", "label": "Receipt Date", "isChildAttribute": true, "parentAttribute": "payment" }
]

/**
 * @author Palmurugan C
 */
const PaymentList = () => {
    return (
        <GridHolder breadCrumbData={breadCrumbData}>
            <DataGrid columns={invoiceColumns} apiName="/api/invoice-payments" additionalParam="&invoiceType=Purchase" title="Payment List" addRouter="/payment" />
        </GridHolder>
    );
}

export default PaymentList;