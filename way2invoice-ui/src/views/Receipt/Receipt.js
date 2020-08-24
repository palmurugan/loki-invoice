import React, { Component } from 'react';
import validate from 'validate.js';

import { PageHolder, DynamicForm, Notification } from '../../components'
import { globalService } from '../../service/global-service';

const breadCrumbData = [
    { 'label': 'Receipt' },
    { 'label': 'Create' }
]

const dynamicForm = [
    { component: 'date', name: 'date', label: 'Date', required: 'true', isChildForm: true, parentForm: "payment" },
    { component: 'inputtext', name: 'amount', label: 'Amount', required: 'true', isChildForm: true, parentForm: "payment" },
    { component: 'dropdown', name: 'accountFromId', label: 'Paid By', required: 'true', apiURL: '/api/accounts?accountType=Customer', isChildForm: true, parentForm: "payment" },
    { component: 'dropdown', name: 'accountToId', label: 'Paid To', required: 'true', apiURL: '/api/accounts?accountType=Vendor', isChildForm: true, parentForm: "payment" },
    { component: 'dropdown', name: 'invoiceId', label: 'Invoice', required: 'true', apiURL: '/api/invoices?invoiceType=Sale' },
    { component: 'dropdown', name: 'paymentMethodId', label: 'Payment Type', required: 'true', apiURL: '/api/payment-methods', isChildForm: true, parentForm: "payment" },
    { component: 'inputtext', name: 'description', label: 'Description', isChildForm: true, parentForm: "payment" }
]

const schema = {
    "payment.date": {
        presence: { allowEmpty: false, message: 'is required' }
    }, "payment.amount": {
        presence: { allowEmpty: false, message: 'is required' }
    }, "payment.accountFromId": {
        presence: { allowEmpty: false, message: '^ Customer is required' }
    }, "payment.accountToId": {
        presence: { allowEmpty: false, message: '^ Paid to is required' }
    }, invoiceId: {
        presence: { allowEmpty: false, message: '^ Invoice is required' }
    }, "payment.paymentMethodId": {
        presence: { allowEmpty: false, message: '^ Payment Type is required' }
    }
};
const apiName = '/api/invoice-payments/';
class Receipt extends Component {

    state = {
        values: {
            payment: {}
        },
        errors: {},
        updateMode: false,
        showNotification: false,
        notification: {
            message: '',
            severity: 'success'
        }
    }

    componentDidMount = () => {
        if (this.props.match.params && this.props.match.params.id) {
            globalService.get(apiName + this.props.match.params.id).then(res => {
                this.setState({ values: res.data, updateMode: true });
            });
        }
    }

    formChangeHandler = (event) => {
        if (event.target.name !== 'invoiceId') {
            this.setState({
                values: {
                    ...this.state.values,
                    payment: {
                        ...this.state.values.payment,
                        [event.target.name]: event.target.value
                    }
                }
            });
        } else {
            this.setState({ values: { ...this.state.values, [event.target.name]: event.target.value } });
        }

    }

    resetState = () => this.setState({ values: { payment: {} }, showNotification: true, notification: { message: 'Receipt Created Successfully' } });

    handleSubmit = (e) => {
        e.preventDefault();
        const errors = validate(this.state.values, schema);
        this.setState({ ...this.state.values, errors: errors || {} });
        if (!errors && !this.state.updateMode) {
            globalService.post(apiName, this.state.values).then(res => {
                this.resetState();
            });
        } else if (!errors && this.state.updateMode) {
            globalService.put(apiName, this.state.values).then(res => {
                this.resetState();
                this.props.history.push("/receipts");
            });
        }
    }

    render = () => {
        return (
            <form onSubmit={this.handleSubmit} >
                <PageHolder title="Receipt" breadCrumbData={breadCrumbData} actionEnabled="true" updateMode={this.state.updateMode}>
                    <DynamicForm data={dynamicForm} hasError={(field) => { return this.state.errors[field] ? true : false }}
                        errors={this.state.errors} state={this.state.values} onChangeHandler={this.formChangeHandler}></DynamicForm>
                </PageHolder>
                <Notification notification={this.state.notification}
                    handleClose={() => this.setState({ showNotification: false })} open={this.state.showNotification} />
            </form>
        );
    }
}

export default Receipt;