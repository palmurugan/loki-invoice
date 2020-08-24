import React, { Component } from 'react';

import { GridHolder, DataGrid } from '../../../components'

const breadCrumbData = [
    { 'label': 'Bill' },
    { 'label': 'List' }
]
const invoiceColumns = [
    { "id": "name", "label": "Bill Id" },
    { "id": "accountName", "label": "Vendor Name" },
    { "id": "date", "label": "Bill Date" },
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
class BillList extends Component {
    constructor(props) {
        super(props);
    }

    render = () => {
        return (
            <GridHolder breadCrumbData={breadCrumbData}>
                <DataGrid columns={invoiceColumns} apiName="/api/invoices" additionalParam="&invoiceType=Purchase" title="Bill List" addRouter="/bill" />
            </GridHolder>
        );
    }
}

export default BillList;