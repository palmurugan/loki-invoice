import React from 'react';
import { TableBody, TableCell, TableRow } from '@material-ui/core';
import ActionIcons from './ActionIcons';
import DynamicCell from './DynamicCell';

/**
 * @author Palmurugan C
 */
const DynamicBody = (props) => {
    return (
        <TableBody>
            {props.dynamicData.map((row) => (
                <TableRow key={row.id}>
                    {props.columns.map((col) => (
                        <DynamicCell key={col.id + row.id} row={row} col={col} />
                    ))}
                    <TableCell>
                        <ActionIcons addRouter={props.addRouter} deleteOperation={() => { props.deleteOperation(row.id) }}
                            api={props.apiName} rowId={row.id} />
                    </TableCell>
                </TableRow>
            ))}
        </TableBody>
    )
}

export default DynamicBody;