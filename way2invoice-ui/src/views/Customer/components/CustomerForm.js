import React, { Component } from 'react';

import { Link as RouterLink, withRouter } from 'react-router-dom';
import { withStyles } from '@material-ui/core';
import axios from 'axios';

import { customerService } from '../../../service/customer-service';

import {
    Card, CardHeader, CardContent, CardActions, Divider, Grid,
    Button, TextField
} from '@material-ui/core';
import MuiAlert from '@material-ui/lab/Alert';
import Snackbar from '@material-ui/core/Snackbar';

import DropDown from '../../../components/DropDown';
import validate from 'validate.js';

function Alert(props) {
    return <MuiAlert elevation={6} variant="filled" {...props} />;
}

const useStyles = () => ({
    root: {},
    actions: {
        justifyContent: 'flex-end'
    }, formControl: {
        margin: '1px',
        minWidth: 120,
    },
});

const schema = {
    name: {
        presence: { allowEmpty: false, message: 'is required' },
        length: {
            maximum: 32
        }
    }, category: {
        presence: { allowEmpty: false, message: 'is required' }
    }, address1: {
        presence: { allowEmpty: false, message: 'is required' }
    }, city: {
        presence: { allowEmpty: false, message: 'is required' }
    }, state: {
        presence: { allowEmpty: false, message: 'is required' }
    }, country: {
        presence: { allowEmpty: false, message: 'is required' }
    }, billingType: {
        presence: { allowEmpty: false, message: 'is required' }
    }, billingCycle: {
        presence: { allowEmpty: false, message: 'is required' }
    }, currency: {
        presence: { allowEmpty: false, message: 'is required' }
    }, company: {
        presence: { allowEmpty: false, message: 'is required' }
    }, zipcode: {
        presence: { allowEmpty: false, message: 'is required' }
    }
};

class CustomerForm extends Component {
    constructor(props) {
        super(props);

        this.state = {
            updateMode: false,
            values: {},
            errors: {},
            isOpen: false
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleClose = this.handleClose.bind(this);
        this.handleBack = this.handleBack.bind(this);
    }

    componentDidMount = () => {
        if (this.props.params && this.props.params.id) {
            axios.get('/api/customers/' + this.props.params.id).then(res => {
                let data = customerService.resolveResponse(res.data);
                this.setState({
                    values: data,
                    updateMode: true
                });
            });
        }
    }

    handleChange(event) {
        this.setState({
            values: { ...this.state.values, [event.target.name]: event.target.value }
        });
    };

    hasError(field) {
        return this.state.errors[field] ? true : false;
    }

    handleClose = (event, reason) => {
        this.setState({
            isOpen: false
        })
    }

    handleSubmit(e) {
        e.preventDefault();
        const errors = validate(this.state.values, schema);
        this.setState({
            ...this.state.values,
            errors: errors || {}
        });
        if (!errors) {
            // TODO move this code to service
            if (!this.state.updateMode) {
                axios.post('/api/customers', customerService.prepareRequest(this.state.values)).then(res => {
                    this.setState({
                        isOpen: true,
                        values: {}
                    });
                })
            } else {
                var req = customerService.prepareRequest(this.state.values);
                axios.put('/api/customers', req).then(res => {
                    this.setState({
                        isOpen: true,
                        values: {}
                    });
                    this.props.history.push("/customers");
                })
            }

        }
    }

    handleBack() {
        this.props.history.push("/customers");
    }

    render() {
        const { classes } = this.props;
        return (
            <form onSubmit={this.handleSubmit}>
                <Card>
                    <CardHeader subheader="The customer creation form"
                        title="Customer" />
                    <Divider />
                    <CardContent>
                        <Grid container spacing={3}>
                            <Grid item md={6} xs={12}>
                                <TextField fullWidth label="Name*" margin="dense" name="name" onChange={this.handleChange} value={this.state.values.name || ''} variant="outlined"
                                    error={this.hasError('name')} helperText={this.hasError('name') ? this.state.errors.name[0] : null} inputProps={{
                                        autocomplete: 'off',
                                        form: {
                                            autocomplete: 'off',
                                        },
                                    }} />
                            </Grid>
                            <Grid item md={6} xs={12}>
                                <DropDown name='category' label='Category*' value={this.state.values.category} updateStateProp={this.handleChange}
                                    hasError={this.hasError('category')} errors={this.state.errors.category} apiName='/api/customer-categories' />
                            </Grid>
                            <Grid item md={6} xs={12}>
                                <TextField fullWidth label="Company*" margin="dense" name="company" onChange={this.handleChange} value={this.state.values.company || ''} variant="outlined"
                                    error={this.hasError('company')} helperText={this.hasError('company') ? this.state.errors.company[0] : null} inputProps={{
                                        autocomplete: 'off',
                                        form: {
                                            autocomplete: 'off',
                                        },
                                    }} />
                            </Grid>
                            <Grid item md={6} xs={12}>
                                <DropDown name='currency' label='Currency*' value={this.state.values.currency} updateStateProp={this.handleChange}
                                    hasError={this.hasError('currency')} errors={this.state.errors.currency} apiName='/api/currencies' />
                            </Grid>
                            <Grid item md={6} xs={12}>
                                <TextField fullWidth label="Addrss1*" margin="dense" name="address1" onChange={this.handleChange} value={this.state.values.address1 || ''} variant="outlined"
                                    error={this.hasError('address1')} helperText={this.hasError('address1') ? this.state.errors.address1[0] : null} />
                            </Grid>
                            <Grid item md={6} xs={12}>
                                <TextField fullWidth label="Address2" margin="dense" name="address2" onChange={this.handleChange} value={this.state.values.address2 || ''} variant="outlined" />
                            </Grid>
                            <Grid item md={6} xs={12}>
                                <TextField fullWidth label="City*" margin="dense" name="city" onChange={this.handleChange} value={this.state.values.city || ''} variant="outlined"
                                    error={this.hasError('city')} helperText={this.hasError('city') ? this.state.errors.address1[0] : null} />
                            </Grid>
                            <Grid item md={6} xs={12}>
                                <TextField fullWidth label="State*" margin="dense" name="state" onChange={this.handleChange} value={this.state.values.state || ''} variant="outlined"
                                    error={this.hasError('state')} helperText={this.hasError('state') ? this.state.errors.address1[0] : null} />
                            </Grid>
                            <Grid item md={6} xs={12}>
                                <TextField fullWidth label="Country*" margin="dense" name="country" onChange={this.handleChange} value={this.state.values.country || ''} variant="outlined"
                                    error={this.hasError('country')} helperText={this.hasError('country') ? this.state.errors.address1[0] : null} />
                            </Grid>
                            <Grid item md={6} xs={12}>
                                <TextField fullWidth label="Zipcode *" margin="dense" name="zipcode" onChange={this.handleChange} value={this.state.values.zipcode || ''} variant="outlined"
                                    error={this.hasError('zipcode')} helperText={this.hasError('zipcode') ? this.state.errors.zipcode[0] : null} />
                            </Grid>
                            <Grid item md={6} xs={12}>
                                <DropDown name='billingType' label='Billing Type*' value={this.state.values.billingType} updateStateProp={this.handleChange}
                                    hasError={this.hasError('billingType')} errors={this.state.errors.billingType} apiName='/api/billing-types' />
                            </Grid>
                            <Grid item md={6} xs={12}>
                                <DropDown name='billingCycle' label='Billing Cycle*' value={this.state.values.billingCycle} updateStateProp={this.handleChange}
                                    hasError={this.hasError('billingCycle')} errors={this.state.errors.billingCycle} apiName='/api/billing-periods' />
                            </Grid>
                            <Grid item md={6} xs={12}>
                                <TextField fullWidth label="Bill Credit Limit" margin="dense" name="billCreditLimit" onChange={this.handleChange} value={this.state.values.billCreditLimit || ''} variant="outlined" />
                            </Grid>
                            <Grid item md={6} xs={12}>
                                <TextField fullWidth label="Credit Limit" margin="dense" name="creditLimit" onChange={this.handleChange} value={this.state.values.creditLimit || ''} variant="outlined" />
                            </Grid>
                        </Grid>
                    </CardContent>
                    <Divider />
                    <CardActions className={classes.actions}>
                        <Button color="default" variant="contained" onClick={this.handleBack}> Cancel </Button>
                        <Button type="submit" color="primary" variant="contained"> {!this.state.updateMode ? 'Save' : 'Update'} </Button>
                    </CardActions>

                    <Snackbar open={this.state.isOpen} autoHideDuration={3000} onClose={this.handleClose}>
                        <Alert onClose={this.handleClose} severity="success"> Customer {!this.state.updateMode ? 'created successfully!' : 'updated successfully!'} </Alert>
                    </Snackbar>
                </Card>
            </form>
        );
    }
}

export default withRouter(withStyles(useStyles)(CustomerForm));