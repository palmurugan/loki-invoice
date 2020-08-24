import React from 'react';
import { Grid, ListItemIcon } from '@material-ui/core';
import InputText from './InputText';
import Select from './Select';
import BasicDate from './BasicDate';

const DynamicForm = (props) => {

    let forms = [];
    props.data.map(item => {
        if (item.component && item.component === 'inputtext') {
            forms.push(
                <Grid item md={6} xs={12}>
                    <InputText hasError={props.hasError} errors={props.errors} state={props.state} onChangeHandler={props.onChangeHandler}
                        formData={item} />
                </Grid>);
        } else if (item.component && item.component === 'dropdown') {
            forms.push(
                <Grid item md={6} xs={12}>
                    <Select state={props.state} hasError={props.hasError} errors={props.errors} onChangeHandler={props.onChangeHandler}
                        formData={item} />
                </Grid>);
        } else if (item.component && item.component === 'date') {
            forms.push(
                <Grid item md={6} xs={12}>
                    <BasicDate hasError={props.hasError} errors={props.errors} state={props.state} onChangeHandler={props.onChangeHandler}
                        formData={item} />
                </Grid>);
        }
    });

    return (
        <Grid container spacing={3}>
            {forms}
        </Grid>
    );
}

export default DynamicForm;