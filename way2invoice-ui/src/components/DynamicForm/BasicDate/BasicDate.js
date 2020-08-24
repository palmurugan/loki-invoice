import React from 'react';

import { TextField } from '@material-ui/core';

const BasicDate = (props) => {
    const basicDateComponent = [];
    if (props.formData.isChildForm && props.formData.isChildForm === true) {
        const name = props.formData.parentForm + '.' + props.formData.name;
        basicDateComponent.push(
            <TextField variant="outlined" margin="dense" fullWidth size="small" type="date" label={props.formData.label} name={props.formData.name}
                value={props.state[props.formData.parentForm][props.formData.name] || ''} onChange={props.onChangeHandler} InputLabelProps={{ shrink: true }}
                error={props.hasError(name)} helperText={props.hasError(name) ? props.errors[name][0] : null} inputProps={{
                    autocomplete: 'off',
                    form: {
                        autocomplete: 'off',
                    },
                }} />
        );
    } else {
        basicDateComponent.push(
            <TextField variant="outlined" margin="dense" fullWidth size="small" type="date" label={props.formData.label} name={props.formData.name}
                value={props.state[props.formData.name] || ''} onChange={props.onChangeHandler} InputLabelProps={{ shrink: true }}
                error={props.hasError(props.formData.name)} helperText={props.hasError(props.formData.name) ? props.errors[props.formData.name][0] : null} inputProps={{
                    autocomplete: 'off',
                    form: {
                        autocomplete: 'off',
                    },
                }} />
        );
    }
    return (
        <React.Fragment>
            {basicDateComponent}
        </React.Fragment>

    );
}
export default BasicDate;