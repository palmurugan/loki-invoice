import React, { Component } from 'react';
import { withStyles } from '@material-ui/core';
import axios from 'axios';

import { Select, MenuItem, FormControl, InputLabel, FormHelperText } from '@material-ui/core';

const useStyles = () => ({

});


class DropDown extends Component {
    constructor(props) {
        super(props);

        this.state = {
            dynamicData: []
        }
    }

    componentDidMount() {
        axios.get(this.props.formData.apiURL).then(res => {
            this.setState({ dynamicData: res.data });
        });
    }

    render() {
        return (
            <FormControl fullWidth margin="dense" variant="outlined" error={this.props.hasError(this.props.name)}>
                <InputLabel id={this.props.formData.name}>{this.props.formData.label}</InputLabel>
                <Select id={this.props.formData.name} name={this.props.formData.name} label={this.props.formData.label} value={this.props.value || ''}
                    onChange={this.props.onChangeHandler} required={this.props.required}>
                    {this.state.dynamicData.map((item) => (
                        <MenuItem key={item.id} value={item.id}>{item.name ? item.name : item.id}</MenuItem>
                    ))}
                </Select>
                <FormHelperText>{this.props.hasError(this.props.name) ? this.props.errors[this.props.name][0] : null}</FormHelperText>
            </FormControl>
        );
    }
}

export default withStyles(useStyles)(DropDown);