import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';

import { PageHolder, DynamicForm, Notification } from '../../components'
import { globalService } from '../../service/global-service';
import validate from 'validate.js';

const breadCrumbData = [
    { 'label': 'Item' },
    { 'label': 'Create' }
]

const dynamicForm = [
    { component: 'dropdown', name: 'itemTypeId', label: 'Type', required: 'true', apiURL: '/api/item-types' },
    { component: 'inputtext', name: 'name', label: 'Name', required: 'true' },
    { component: 'inputtext', name: 'code', label: 'Code', required: 'true' },
    { component: 'inputtext', name: 'description', label: 'Description' },
    { component: 'inputtext', name: 'itemPrice', label: 'Price', required: 'true' },
    { component: 'dropdown', name: 'unitId', label: 'Unit', required: 'true', apiURL: '/api/units' },
    { component: 'dropdown', name: 'taxId', label: 'Tax', required: 'true', apiURL: '/api/taxes' },
]

const schema = {
    itemTypeId: {
        presence: { allowEmpty: false, message: '^ Type is required' }
    },
    name: {
        presence: { allowEmpty: false, message: 'is required' }
    }, code: {
        presence: { allowEmpty: false, message: 'is required' }
    }, itemPrice: {
        presence: { allowEmpty: false, message: 'is required' }
    }, unitId: {
        presence: { allowEmpty: false, message: 'is required' }
    }
};

const apiName = '/api/items/';

/**
 * Item Component to perform the create/update operation
 */
class Item extends Component {
    constructor(props) {
        super(props);

        this.state = {
            values: {},
            errors: {},
            updateMode: false,
            showNotification: false,
            notification: {
                message: '',
                severity: 'success'
            }
        }

        this.handleSubmit = this.handleSubmit.bind(this);
        this.formChangeHandler = this.formChangeHandler.bind(this);

    }

    componentDidMount() {
        if (this.props.match.params && this.props.match.params.id) {
            globalService.get(apiName + this.props.match.params.id).then(res => {
                this.setState({ values: res.data, updateMode: true });
            });
        }
    }

    formChangeHandler = (event) => this.setState({ values: { ...this.state.values, [event.target.name]: event.target.value } });

    resetState = () => this.setState({ values: { itemTypeId: 1 }, showNotification: true, notification: { message: 'Item Created Successfully' } });

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
                this.props.history.push("/items");
            });
        }
    }

    render = () => {
        return (
            <form onSubmit={this.handleSubmit}>
                <PageHolder title="Item" breadCrumbData={breadCrumbData} actionEnabled="true" updateMode={this.state.updateMode}>
                    <DynamicForm data={dynamicForm} hasError={(field) => { return this.state.errors[field] ? true : false }}
                        errors={this.state.errors} state={this.state.values} onChangeHandler={this.formChangeHandler}></DynamicForm>
                </PageHolder>
                <Notification notification={this.state.notification}
                    handleClose={() => this.setState({ showNotification: false })} open={this.state.showNotification} />
            </form>
        );
    }
}
export default withRouter(Item);