import React, { Component } from 'react';
import { Button, Box } from '@material-ui/core';
import { withRouter } from 'react-router-dom';
import { DynamicForm, Notification } from '../../../../components';
import validate from 'validate.js';
import { globalService } from '../../../../service/global-service';

const dynamicForm = [
    { component: 'dropdown', name: 'accountTypeId', label: 'Account Type', apiURL: '/api/account-types' },
    { component: 'inputtext', name: 'name', label: 'Account Name' },
    { component: 'dropdown', name: 'accountGroupId', label: 'Account Group', apiURL: '/api/account-groups' },
    { component: 'inputtext', name: 'openingBalance', label: 'Opening Balance' }
]

const schema = {
    name: {
        presence: { allowEmpty: false, message: '^ Account name is required' },
        length: { maximum: 32 }
    }, accountGroupId: {
        presence: { allowEmpty: false, message: '^ Account group is required' }
    }, accountTypeId: {
        presence: { allowEmpty: false, message: '^ Account type is required' }
    }
};
const apiName = '/api/accounts/';

class BasicInfo extends Component {
    constructor(props) {
        super(props);

        this.state = {
            updateMode: false,
            values: {},
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
        if (this.props.match.params && this.props.match.params.id) {
            globalService.get(apiName + this.props.match.params.id).then(res => {
                this.setState({ values: res.data, updateMode: true });
                this.props.tabActivationHandler(true, res.data.id);
            });
        }
    }

    formChangeHandler = (event) => this.setState({ values: { ...this.state.values, [event.target.name]: event.target.value } });

    resetState = (res) => {
        this.setState({ values: res.data, updateMode: true, showNotification: true, notification: { message: 'Account Created Successfully' } });
        this.props.tabActivationHandler(true, res.data.id);
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
                        <Button type="submit" color="primary" variant="contained" onClick={this.handleSubmit}>{this.state.updateMode ? 'Update' : 'Save'}</Button>
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
export default withRouter(BasicInfo);