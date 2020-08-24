import React from 'react';
import {
    Table, TableBody, TableCell, TableContainer, TableRow
} from '@material-ui/core';

const InvoiceSummary = (props) => {
    return (
        <TableContainer>
            <Table aria-label="spanning table">
                <TableBody>
                    <TableRow>
                        <TableCell colSpan={2}>Subtotal</TableCell>
                        <TableCell align="right">{props.total}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell>CGST</TableCell>
                        <TableCell align="right">{}</TableCell>
                        <TableCell align="right">{(props.gstType && props.gstType === 'gst') ? props.tax : props.zeroAmount}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell>SGST</TableCell>
                        <TableCell align="right">{}</TableCell>
                        <TableCell align="right">{(props.gstType && props.gstType === 'gst') ? props.tax : props.zeroAmount}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell>IGST</TableCell>
                        <TableCell align="right">{}</TableCell>
                        <TableCell align="right">{(props.gstType && props.gstType === 'igst') ? props.igst : props.zeroAmount}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell colSpan={2}>Grand Total</TableCell>
                        <TableCell align="right">{props.grandTotal}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell colSpan={2}>Round Off</TableCell>
                        <TableCell align="right">{props.roundUpTotal}</TableCell>
                    </TableRow>
                </TableBody>
            </Table>
        </TableContainer>
    );
}

export default InvoiceSummary;