import React from 'react';

import { GridHolder, DataGrid } from '../../../components'

const breadCrumbData = [
    { 'label': 'Receipt' },
    { 'label': 'List' }
]
const invoiceColumns = [
    { "id": "invoiceName", "label": "Invoice Id" },
    { "id": "accountFrom", "label": "Paid by", "isChildAttribute": true, "parentAttribute": "payment" },
    { "id": "accountTo", "label": "Paid to", "isChildAttribute": true, "parentAttribute": "payment" },
    { "id": "amount", "label": "Amount", "type": "currency", "isChildAttribute": true, "parentAttribute": "payment" },
    { "id": "paymentMethod", "label": "Mode of Payment", "isChildAttribute": true, "parentAttribute": "payment" },
    { "id": "date", "label": "Receipt Date", "isChildAttribute": true, "parentAttribute": "payment" }
]

/**
 * @author Palmurugan C
 */
const ReceiptList = () => {
    return (
        <GridHolder breadCrumbData={breadCrumbData}>
            <DataGrid columns={invoiceColumns} apiName="/api/invoice-payments" additionalParam="&invoiceType=Sales" title="Receipt List" addRouter="/receipt" />
        </GridHolder>
    );
}

export default ReceiptList;