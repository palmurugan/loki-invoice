import React, { Component } from 'react'
import { withStyles } from '@material-ui/styles';

import BreadCrumb from '../../components/BreadCrumb';
import DataGrid from '../../components/DataGrid';

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
    { "id": "accountType", "label": "Account Type" },
    { "id": "accountGroup", "label": "Account Group" },
    { "id": "openingBalance", "label": "Opening Balance", "type": "currency" },
    { "id": "status", "label": "Status" }
]

const breadCrumbData = [
    { "label": "Account" },
    { "label": "List" }
]

/**
 * @author Palmurugan C
 */
class CustomerList extends Component {
    render() {
        const { classes } = this.props;
        return (
            <div className={classes.root}>
                <div className={classes.content}>
                    <BreadCrumb data={breadCrumbData} />
                    <DataGrid columns={customerColumns} apiName="/api/accounts" title="Account List" addRouter="/account" />
                </div>
            </div>
        );
    }
}

export default withStyles(useStyles)(CustomerList);