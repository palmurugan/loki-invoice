import React, { Component } from 'react';
import { withStyles } from '@material-ui/styles';
import { withRouter } from 'react-router-dom';
/** Core Components */
import {
    Card, CardHeader, CardContent, CardActions, Divider, Grid, Button, TextField,
    Table, TableBody, TableCell, TableContainer, TableRow
} from '@material-ui/core';

/** Loki Components */
import { DropDown, Notification, EditableGrid, BreadCrumb } from '../../components';
import { InvoiceSummary } from '../Common';

/** Services */
import { globalService } from '../../service/global-service';

const useStyles = () => ({
    root: {
        padding: 2
    },
    content: {
        marginLeft: '5px',
        marginRight: '5px'
    },
    action: {
        flex: '1 1 100%',
    }, actionButton: {
        justifyContent: 'flex-end'
    }
});

const crumbs = [
    { 'label': 'Bill' },
    { 'label': 'Create' }
]

const apiName = '/api/invoices/';
const itemAPIName = '/api/items';
const customerDropDown = { component: 'dropdown', name: 'accountId', label: 'Vendor', apiURL: '/api/accounts?accountType=Vendor' };

class Bill extends Component {
    locale = 'en-US'
    currency = 'INR'

    state = {
        values: {
            type: 'P',
            invoiceLines: [
                { itemId: '', quantity: 1, price: 0.00, discount: 0, taxRate: 0, total: 0.00 },
            ]
        },
        gstType: 'gst',
        customers: [],
        products: [],
        updateMode: false,
        showNotification: false,
        notification: {
            message: '',
            severity: ''
        }
    }

    /**
     * This is a method which will load all the initial level data.
     * 
     * This method will get execute before components loaded.
     */
    componentDidMount = () => {
        /** This block of code only for edit mode - Edit Invoice Scenario */
        if (this.props.match.params && this.props.match.params.id) {
            globalService.get(apiName + this.props.match.params.id).then(res => {
                globalService.get('/api/accounts/' + res.data.accountId + '/tax/types').then(taxTypeRes => {
                    this.setState({ ...this.state, gstType: taxTypeRes.data.value, updateMode: true, values: res.data })
                });
            });
        }

        /** This block of code will load all the active products into the state */
        globalService.get(itemAPIName).then(res => {
            this.setState({ ...this.state, products: res.data });
        });
    }

    formatCurrency = (amount) => {
        amount = amount ? amount : 0.00;
        return (new Intl.NumberFormat(this.locale, {
            style: 'currency',
            currency: this.currency,
            minimumFractionDigits: 2,
            maximumFractionDigits: 2
        }).format(amount))
    }

    handleLineItemChange = (elementIndex) => (event) => {
        let invoiceLines = this.state.values.invoiceLines.map((item, i) => {
            if (elementIndex !== i) {
                return item
            } else {
                return { ...item, [event.target.name]: event.target.value }
            }
        });
        this.setState({ values: { ...this.state.values, invoiceLines } });
    }


    accountChangeHandler = (event) => {
        globalService.get('/api/accounts/' + event.target.value + '/tax/types').then(res => {
            this.setState({
                gstType: res.data.value, values: { ...this.state.values, [event.target.name]: event.target.value }
            });
        })
    }

    productChangeHandler = (elementIndex) => (event) => {
        const product = this.state.products.find(element => element.id === event.target.value);
        let invoiceLines = this.state.values.invoiceLines.map((item, i) => {
            if (elementIndex !== i) {
                return item
            } else {
                return { ...item, [event.target.name]: event.target.value, price: product['itemPrice'], discount: 0, taxRate: product['taxValue'] }
            }
        })
        this.setState({ values: { ...this.state.values, invoiceLines } });
    }

    handleAddLineItem = () => {
        this.setState({
            values: {
                ...this.state.values,
                invoiceLines: this.state.values.invoiceLines.concat(
                    [{ itemId: '', quantity: 1, discount: 0, price: 0.00, taxRate: 0, total: 0.00 }]
                )
            }
        })
    }

    handleRemoveLineItem = (elementIndex) => (event) => {
        this.setState({
            ...this.state, values: { ...this.state.values, invoiceLines: this.state.values.invoiceLines.filter((item, i) => { return elementIndex !== i }) }
        });
    }

    invoiceFormChangeHandler = (event) => {
        this.setState({
            values: { ...this.state.values, [event.target.name]: event.target.value }
        });
    };

    invoiceCreationHandler = () => {
        if (!this.state.updateMode) {
            globalService.post('/api/invoices', this.state.values).then(res => {
                this.responseHandler('Bill Creted Successfully', 'success');
                this.props.history.goBack();
            }).catch(err => {
                this.responseHandler('Invalid Bill', 'error');
            });
        } else {
            globalService.put('/api/invoices', this.state.values).then(res => {
                this.responseHandler('Bill Updated Successfully', 'success');
                this.props.history.goBack();
            }).catch(err => {
                this.responseHandler('Invalid Bill', 'error');
            });
        }
    }

    responseHandler = (message, severity) => this.setState({
        ...this.state, showNotification: true,
        notification: { message: message, severity: severity }
    })

    calcLineItemsTotal = () => {
        return this.state.values.invoiceLines.reduce((prev, cur) =>
            (prev + ((cur.quantity * cur.price))), 0)
    }

    calcLineItemsTax = () => {
        return this.state.values.invoiceLines.reduce((prev, cur) =>
            (prev + (((cur.quantity * cur.price)) * (cur.taxRate / 100))), 0)
    }

    calcLineItemCGST = () => {
        let gstAmount = this.calcLineItemsTax();
        return (gstAmount && gstAmount > 0) ? gstAmount / 2 : gstAmount;
    }

    calcGrandTotal = () => {
        return this.calcLineItemsTotal() + this.calcLineItemsTax();
    }

    /**
     * Function to calculate total with roundup
     */
    calcRoundUpTotal = () => {
        let multiply = 1 / 0.5;
        let value = parseFloat((Math.round(this.calcGrandTotal() * multiply) / multiply)).toFixed(2);
        return value;
    }

    render() {
        const { classes } = this.props;
        return (
            <div className={classes.root}>
                <div className={classes.content}>
                    <BreadCrumb data={crumbs} />
                    <Card elevation={0}>
                        <CardHeader title="Bill" />
                        <Divider />
                        <CardContent>
                            <Grid container spacing={3}>
                                <Grid item md={4} xs={4}>
                                    <DropDown formData={customerDropDown} value={this.state.values.accountId} onChangeHandler={this.accountChangeHandler}
                                        hasError={() => { return false }} />
                                </Grid>
                                <Grid item md={4} xs={4}>
                                    <TextField name="date" variant="outlined" margin="dense" fullWidth size="small" label="Bill Date" type="date"
                                        value={this.state.values.date} onChange={this.invoiceFormChangeHandler} InputLabelProps={{ shrink: true }} />
                                </Grid>
                                <Grid item md={4} xs={4}>
                                    <TextField name="dueDate" variant="outlined" margin="dense" fullWidth size="small" label="Due Date" type="date"
                                        value={this.state.values.dueDate} onChange={this.invoiceFormChangeHandler} InputLabelProps={{ shrink: true }} />
                                </Grid>
                            </Grid>

                            <Grid container spacing={3}>
                                <Grid item md={12} xs={12}>
                                    <EditableGrid items={this.state.values.invoiceLines}
                                        changeHandler={this.handleLineItemChange}
                                        addHandler={this.handleAddLineItem}
                                        removeHandler={this.handleRemoveLineItem}
                                        productData={this.state.products}
                                        currencyFormatter={this.formatCurrency}
                                        productChangeHandler={this.productChangeHandler}
                                        gstType={this.state.gstType} />
                                </Grid>
                            </Grid>

                            <Grid container spacing={3}>
                                <Grid item md={7} xs={7}>

                                </Grid>
                                <Grid item md={5} xs={5}>
                                    <InvoiceSummary total={this.formatCurrency(this.calcLineItemsTotal())}
                                        tax={this.formatCurrency(this.calcLineItemCGST())}
                                        igst={this.formatCurrency(this.calcLineItemsTax())}
                                        grandTotal={this.formatCurrency(this.calcGrandTotal())}
                                        roundUpTotal={this.formatCurrency(this.calcRoundUpTotal())}
                                        gstType={this.state.gstType} zeroAmount={this.formatCurrency(0)} />
                                </Grid>
                            </Grid>
                        </CardContent>
                        <CardActions className={classes.actionButton}>
                            <Button color="default" variant="contained" onClick={() => { this.props.history.goBack() }}> Cancel </Button>
                            <Button type="button" color="primary" variant="contained" onClick={this.invoiceCreationHandler}>
                                {this.state.updateMode ? 'Update' : 'Save'}</Button>
                        </CardActions>
                    </Card>
                    <Notification notification={this.state.notification}
                        handleClose={() => this.setState({ showNotification: false })} open={this.state.showNotification} />
                </div>
            </div>
        );
    }
}

export default withRouter(withStyles(useStyles)(Bill));

/*`${(CGST_RATE * 100).toFixed(0)} %`
calcLineItemsTotal = () => {
        return this.state.values.invoiceLines.reduce((prev, cur) =>
            (prev + ((cur.quantity * cur.price)) - ((cur.quantity * cur.price) * (cur.discount / 100))), 0)
    }
*/