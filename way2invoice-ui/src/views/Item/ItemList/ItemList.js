import React, { Component } from 'react';
import { withStyles } from '@material-ui/styles';

import { DataGrid, BreadCrumb } from '../../../components';

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

const itemColumns = [
    { "id": "code", "label": "Code" },
    { "id": "name", "label": "Name" },
    { "id": "unit", "label": "Unit" },
    { "id": "itemPrice", "label": "Price", "type": "currency" },
    { "id": "taxName", "label": "Tax" },
    { "id": "description", "label": "Description" },
]

const breadCrumbData = [
    { 'label': 'Item' },
    { 'label': 'List' }
]

/**
 * Item List Component for listing all the items
 */
class ItemList extends Component {
    render = () => {
        const { classes } = this.props;
        return (
            <div className={classes.root}>
                <div className={classes.content}>
                    <BreadCrumb data={breadCrumbData} />
                    <DataGrid columns={itemColumns} apiName="/api/items" title="Item List" addRouter="/item" />
                </div>
            </div>
        );
    }
}

export default withStyles(useStyles)(ItemList);