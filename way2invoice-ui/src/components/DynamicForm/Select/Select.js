import React from 'react';

import { DropDown } from '../../../components';

const Select = (props) => {
    const selectComponent = [];
    if (props.formData.isChildForm && props.formData.isChildForm === true) {
        selectComponent.push(
            <DropDown name={props.formData.parentForm + "." + props.formData.name} value={props.state[props.formData.parentForm][props.formData.name] || ''} onChangeHandler={props.onChangeHandler}
                formData={props.formData} hasError={props.hasError} errors={props.errors} />
        );
    } else {
        selectComponent.push(
            <DropDown name={props.formData.name} value={props.state[props.formData.name] || ''} onChangeHandler={props.onChangeHandler}
                formData={props.formData} hasError={props.hasError} errors={props.errors} />
        );
    }
    return (
        <React.Fragment>
            {selectComponent}
        </React.Fragment>
    );
}

export default Select;