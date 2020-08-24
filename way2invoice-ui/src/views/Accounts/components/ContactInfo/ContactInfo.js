import React, { Component } from 'react';
import { Button, Box } from '@material-ui/core';
import { withRouter } from 'react-router-dom';
import { DynamicForm, Notification } from '../../../../components';
import validate from 'validate.js';
import { globalService } from '../../../../service/global-service';

const dynamicForm = [
    { component: 'inputtext', name: 'address1', label: 'Address' },
    { component: 'inputtext', name: 'city', label: 'City' },
    { component: 'dropdown', name: 'countryId', label: 'Country', apiURL: '/api/countries' },
    { component: 'dropdown', name: 'stateId', label: 'State', apiURL: '/api/states/1' },
    { component: 'inputtext', name: 'zipcode', label: 'Zipcode' },
    { component: 'inputtext', name: 'phone', label: 'Phone' }
]

const schema = {
    address1: {
        presence: { allowEmpty: false, message: '^ Address is required' },
    }, city: {
        presence: { allowEmpty: false, message: 'is required' }
    }, countryId: {
        presence: { allowEmpty: false, message: '^ Country is required' }
    }, stateId: {
        presence: { allowEmpty: false, message: '^ State is required' }
    }, zipcode: {
        presence: { allowEmpty: false, message: 'is required' }
    }, phone: {
        presence: { allowEmpty: false, message: 'is required' }
    }
};
const apiName = '/api/account-contacts/';

class ContactInfo extends Component {
    constructor(props) {
        super(props);
        this.state = {
            updateMode: false,
            values: { accountsId: this.props.keyId, status: 'ACTIVE' },
            errors: {},
            showNotification: false,
            notification: {
                message: '',
                severity: 'success'
            }
        }
        this.formChangeHandler = this.formChangeHandler.bind(this);
    }

    componentDidMount = () => {
        if (this.props.keyId) {
            globalService.get('/api/accounts/' + this.props.keyId + '/contact/').then(res => {
                this.setState({ values: res.data, updateMode: true });
            });
        }
    }

    formChangeHandler = (event) => this.setState({ values: { ...this.state.values, [event.target.name]: event.target.value } });

    resetState = (res) => {
        this.setState({ values: res.data, updateMode: true, showNotification: true, notification: { message: 'Contacts Updated Successfully' } });
    }

    handleSubmit = (e) => {
        e.preventDefault();
        const errors = validate(this.state.values, schema);
        this.setState({ ...this.state.values, errors: errors || {} });
        if (!errors && !this.state.updateMode) {
            globalService.post(apiName, this.state.values).then(res => {
                this.resetState(res);
            });
        } else if (!errors && this.state.updateMode) {
            globalService.put(apiName, this.state.values).then(res => {
                this.resetState(res);
            });
        }
    }
    render = () => {
        return (
            <React.Fragment>
                <DynamicForm data={dynamicForm} hasError={(field) => { return this.state.errors[field] ? true : false }}
                    errors={this.state.errors} state={this.state.values} onChangeHandler={this.formChangeHandler}></DynamicForm>
                <Box display="flex" flexDirection="row-reverse" bgcolor="background.paper">
                    <Box p={1} bgcolor="white">
                        <Button type="submit" color="primary" variant="contained" onClick={this.handleSubmit}>Update</Button>
                    </Box>
                    <Box p={1} bgcolor="white">
                        <Button color="default" variant="contained" onClick={() => { this.props.history.goBack() }}> Back </Button>
                    </Box>
                </Box>
                <Notification notification={this.state.notification}
                    handleClose={() => this.setState({ showNotification: false })} open={this.state.showNotification} />
            </React.Fragment>
        );
    }
}

export default withRouter(ContactInfo);