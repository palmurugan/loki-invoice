import React, { Component } from 'react'
import { withStyles } from '@material-ui/styles';

import BreadCrumb from '../../../components/BreadCrumb';
import DataGrid from '../../../components/DataGrid';

const useStyles = () => ({
    root: {
        padding: 2
    },
    content: {
        marginLeft: '5px'
    }, breadcrumb: {
        marginBottom: '10px',
        marginLeft: '5px'
    }
});

const customerColumns = [
    { "id": "name", "label": "Name" },
    { "id": "openingBalance", "label": "Opening Balance", "type": "currency" },
    { "id": "accountGroup", "label": "Vendor Group" },
    { "id": "status", "label": "Status" }
]

const breadCrumbData = [
    { "label": "Customer" },
    { "label": "List" }
]

/**
 * @author Palmurugan C
 */
class VendorList extends Component {
    render() {
        const { classes } = this.props;
        return (
            <div className={classes.root}>
                <div className={classes.content}>
                    <BreadCrumb data={breadCrumbData} />
                    <DataGrid columns={customerColumns} apiName="/api/accounts" additionalParam="&accountType=Vendor" title="Vendor List" addRouter="/vendor" />
                </div>
            </div>
        );
    }
}

export default withStyles(useStyles)(VendorList);