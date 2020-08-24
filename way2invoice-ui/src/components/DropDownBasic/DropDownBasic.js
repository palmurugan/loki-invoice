import React from 'react';
import { Select, MenuItem, FormControl, InputLabel, FormHelperText } from '@material-ui/core';

/**
 * @author Palmurugan C
 * 
 * Dropdown basic component
 */
const DropDownBasic = (props) => {
    return (
        <FormControl fullWidth margin="dense" variant="outlined" error={props.hasError}>
            <InputLabel id={props.name}>{props.label}</InputLabel>
            <Select id={props.name} name={props.name} label={props.label} value={props.value || ''} onChange={props.onChangeHandler}>
                {props.data.map((item) => (
                    <MenuItem key={item[props.code]} value={item[props.code]}>{item[props.description]}</MenuItem>
                ))}
            </Select>
        </FormControl>
    )
}

export default DropDownBasic;