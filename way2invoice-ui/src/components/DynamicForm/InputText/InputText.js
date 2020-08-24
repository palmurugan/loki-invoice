import React from 'react';

import { TextField } from '@material-ui/core';

const InputText = (props) => {
    const inputComponent = [];
    if (props.formData.isChildForm && props.formData.isChildForm === true) {
        const name = props.formData.parentForm + '.' + props.formData.name;
        inputComponent.push(
            <TextField fullWidth label={props.formData.label} name={props.formData.name} value={props.state[props.formData.parentForm][props.formData.name] || ''}
                onChange={props.onChangeHandler} margin="dense" variant="outlined"
                error={props.hasError(name)} helperText={props.hasError(name) ? props.errors[name][0] : null} inputProps={{
                    autocomplete: 'off',
                    form: {
                        autocomplete: 'off',
                    },
                }} />
        )
    } else {
        inputComponent.push(
            <TextField fullWidth label={props.formData.label} name={props.formData.name} value={props.state[props.formData.name] || ''}
                onChange={props.onChangeHandler} margin="dense" variant="outlined"
                error={props.hasError(props.formData.name)} helperText={props.hasError(props.formData.name) ? props.errors[props.formData.name][0] : null} inputProps={{
                    autocomplete: 'off',
                    form: {
                        autocomplete: 'off',
                    },
                }} />
        )
    }
    return (
        <React.Fragment>
            {inputComponent}
        </React.Fragment>
    );
}

export default InputText;