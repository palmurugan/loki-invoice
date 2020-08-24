import React, { Component } from 'react';

import { GridHolder, DataGrid } from '../../../components'

const breadCrumbData = [
    { 'label': 'Invoice' },
    { 'label': 'List' }
]
const invoiceColumns = [
    { "id": "name", "label": "Invoice Id" },
    { "id": "accountName", "label": "Customer Name" },
    { "id": "date", "label": "Invoice Date" },
    { "id": "dueDate", "label": "Due Date" },
    { "id": "discount", "label": "Discount", "type": "currency" },
    { "id": "total", "label": "Grand Total", "type": "currency" },
    { "id": "carriedBalance", "label": "Carried Balance", "type": "currency" },
    { "id": "balance", "label": "Balance", "type": "currency" },
    { "id": "status", "label": "Status" }
]
/**
 * A InvoiceList component
 */
class InvoiceList extends Component {
    constructor(props) {
        super(props);
    }

    render = () => {
        return (
            <GridHolder breadCrumbData={breadCrumbData}>
                <DataGrid columns={invoiceColumns} apiName="/api/invoices" additionalParam="&invoiceType=Sale" title="Invoice List" addRouter="/invoice" />
            </GridHolder>
        );
    }
}

export default InvoiceList;