import React from 'react';
import { TableCell } from '@material-ui/core';


/**
 * @author Palmurugan C
 */
const DynamicCell = (props) => {
    const locale = 'en-US'
    const currency = 'INR'
    const cell = [];

    const currencyFormat = (amount) => {
        return (new Intl.NumberFormat(locale, {
            style: 'currency',
            currency: currency,
            minimumFractionDigits: 2,
            maximumFractionDigits: 2
        }).format(amount))
    }

    /**First condition is mainly for child attributes and the second one is for main attributes */
    if (props.col.isChildAttribute && props.col.isChildAttribute === true) {
        if (props.col.type && props.col.type === 'currency') {
            cell.push(<TableCell key={props.key} align="right"> {currencyFormat(props.row[props.col.parentAttribute][props.col.id])}</TableCell>)
        } else {
            cell.push(<TableCell key={props.key}> {props.row[props.col.parentAttribute][props.col.id]}</TableCell>)
        }
    } else {
        if (props.col.type && props.col.type === 'currency') {
            cell.push(<TableCell key={props.key} align="right"> {currencyFormat(props.row[props.col.id])} </TableCell>)
        } else {
            cell.push(<TableCell key={props.key}> {props.row[props.col.id]} </TableCell>)
        }
    }

    return (
        <React.Fragment>
            {cell}
        </React.Fragment>
    )
}

export default DynamicCell;