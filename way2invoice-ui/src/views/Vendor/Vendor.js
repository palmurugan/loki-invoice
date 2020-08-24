import React, { Component } from 'react';

import { withRouter } from 'react-router-dom';
import { PageHolder, Notification, DynamicForm } from '../../components';
import validate from 'validate.js';

import { globalService } from '../../service/global-service';

const breadCrumbData = [
    { 'label': 'Vendor' },
    { 'label': 'Create' }
]

const dynamicForm = [
    { component: 'inputtext', name: 'name', label: 'Name' },
    { component: 'dropdown', name: 'accountGroupId', label: 'Customer Group', apiURL: '/api/account-groups' },
    { component: 'inputtext', name: 'openingBalance', label: 'Opening Balance' }
]

const schema = {
    name: {
        presence: { allowEmpty: false, message: 'is required' },
        length: { maximum: 32 }
    }, accountGroupId: {
        presence: { allowEmpty: false, message: '^ Vendor group is required' }
    }
};

const apiName = '/api/accounts/';

class Vendor extends Component {

    constructor(props) {
        super(props);

        this.state = {
            updateMode: false,
            values: { accountTypeId: 3 },
            errors: {},
            showNotification: false,
            notification: {
                message: '',
                severity: 'success'
            }
        }
        this.formChangeHandler = this.formChangeHandler.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount = () => {
        if (this.props.match.params && this.props.match.params.id) {
            globalService.get(apiName + this.props.match.params.id).then(res => {
                this.setState({ values: res.data, updateMode: true });
            });
        }
    }

    formChangeHandler = (event) => this.setState({ values: { ...this.state.values, [event.target.name]: event.target.value } });

    resetState = () => this.setState({ values: { accountTypeId: 3 }, showNotification: true, notification: { message: 'Vendor Created Successfully' } });

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
                this.props.history.goBack();
            });
        }
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <PageHolder title="Vendor" breadCrumbData={breadCrumbData} actionEnabled="true" updateMode={this.state.updateMode}>
                    <DynamicForm data={dynamicForm} hasError={(field) => { return this.state.errors[field] ? true : false }}
                        errors={this.state.errors} state={this.state.values} onChangeHandler={this.formChangeHandler}></DynamicForm>
                </PageHolder>
                <Notification notification={this.state.notification}
                    handleClose={() => this.setState({ showNotification: false })} open={this.state.showNotification} />
            </form>
        );
    }
}

export default withRouter(Vendor);