import React, { Component } from "react";
import { withStyles } from '@material-ui/styles';

import { Grid, Button, TextField } from '@material-ui/core';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import { Tooltip, IconButton } from '@material-ui/core';
import DeleteIcon from '@material-ui/icons/Delete';
import { Select, MenuItem, FormControl, InputLabel, FormHelperText } from '@material-ui/core';

import OutlinedInput from '@material-ui/core/OutlinedInput';
import InputAdornment from '@material-ui/core/InputAdornment';

const useStyles = () => ({
    addButton: {
        marginTop: '5px'
    },
    mainColumn: {
        width: '400px'
    }, smallColumn: {
        width: 75
    }, tinyColumn: {
        width: 30
    }, mediumColumn: {
        width: 100
    }
});

class EditableGrid extends Component {

    calcLineItemGrandTotal = (price, quantity, tax) => {
        return ((quantity * price)) + (((quantity * price)) * (tax / 100))
    }

    render = () => {
        const { classes, productData, currencyFormatter } = this.props
        return (
            <div>
                <Grid>
                    <TableContainer>
                        <Table aria-label="simple table" size="small" margin="dense">
                            <TableHead>
                                <TableRow>
                                    <TableCell>#</TableCell>
                                    <TableCell>Item</TableCell>
                                    <TableCell>Price</TableCell>
                                    <TableCell>Qty</TableCell>
                                    <TableCell align="right">CGST</TableCell>
                                    <TableCell align="right">SGST</TableCell>
                                    <TableCell align="right">IGST</TableCell>
                                    <TableCell align="right">Tax</TableCell>
                                    <TableCell align="right">Amount</TableCell>
                                    <TableCell align="right">Total</TableCell>
                                    <TableCell>Action</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {this.props.items.map((row, i) => (
                                    <TableRow key={row.name}>
                                        <TableCell>{i + 1}</TableCell>
                                        <TableCell className={classes.mainColumn} component="th" scope="row">
                                            <FormControl fullWidth margin="dense" variant="outlined">
                                                <InputLabel id="itemId">Product</InputLabel>
                                                <Select id="itemId" name="itemId" label="Product" value={row.itemId} onChange={this.props.productChangeHandler(i)}>
                                                    {productData.map((item) => (
                                                        <MenuItem key={item.id} value={item.id}>{item.name}</MenuItem>
                                                    ))}
                                                </Select>
                                            </FormControl>
                                        </TableCell>
                                        <TableCell>
                                            <FormControl fullWidth margin="dense" variant="outlined" className={classes.mediumColumn}>
                                                <InputLabel htmlFor="outlined-adornment-amount">Price</InputLabel>
                                                <OutlinedInput
                                                    id="outlined-adornment-amount"
                                                    value={row.price}
                                                    onChange={this.props.changeHandler(i)}
                                                    startAdornment={<InputAdornment position="start">&#8377;</InputAdornment>}
                                                    labelWidth={60}
                                                />
                                            </FormControl>
                                        </TableCell>
                                        <TableCell>
                                            <TextField type="number" min="1" className={classes.smallColumn} fullWidth label="Quantity*" margin="dense" name="quantity" value={row.quantity}
                                                onChange={this.props.changeHandler(i)} variant="outlined" InputProps={{ inputProps: { min: 1 } }} />
                                        </TableCell>
                                        <TableCell className={classes.tinyColumn} align="right">
                                            <div> {(row.taxRate && this.props.gstType === 'gst') ? (row.taxRate / 2) + '%' : 0 + '%'}</div>
                                        </TableCell>
                                        <TableCell className={classes.tinyColumn} align="right">
                                            <div>{(row.taxRate && this.props.gstType === 'gst') ? (row.taxRate / 2) + '%' : 0 + '%'}</div>
                                        </TableCell>
                                        <TableCell className={classes.tinyColumn} align="right">
                                            <div>{(row.taxRate && this.props.gstType === 'igst') ? (row.taxRate) + '%' : 0 + '%'} </div>
                                        </TableCell>
                                        <TableCell className={classes.tinyColumn} align="right">
                                            <div>{currencyFormatter((row.quantity * row.price) * (row.taxRate / 100))} </div>
                                        </TableCell>

                                        <TableCell align="right">
                                            <div>{currencyFormatter(row.quantity * row.price)} </div>
                                        </TableCell>

                                        <TableCell align="right">
                                            <div>{currencyFormatter(this.calcLineItemGrandTotal(row.price, row.quantity, row.taxRate))} </div>
                                        </TableCell>
                                        <TableCell>
                                            <Tooltip title="Delete">
                                                <IconButton size="small" aria-label="Delete" onClick={this.props.removeHandler(i)}>
                                                    <DeleteIcon />
                                                </IconButton>
                                            </Tooltip>
                                        </TableCell>
                                    </TableRow>
                                ))}
                            </TableBody>
                        </Table>
                    </TableContainer>
                </Grid>
                <Grid justify="flex-end" container spacing={24}>
                    <Button variant="contained" className={classes.addButton} color="primary" onClick={this.props.addHandler}> Add </Button>
                </Grid>
            </div>
        )
    }
}
/*<div>{currencyFormatter(((row.quantity * row.price) * (row.discount / 100)))} </div>*/

export default withStyles(useStyles)(EditableGrid);