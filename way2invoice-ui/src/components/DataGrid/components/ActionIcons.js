import React from 'react';

import { Tooltip, IconButton } from '@material-ui/core';
import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';

import { Link } from "react-router-dom";

/**
 * @author Palmurugan C
 */
const ActionIcons = (props) => {
    return (
        <div>
            <Link to={props.addRouter + '/' + props.rowId}>
                <Tooltip title="Edit">
                    <IconButton size="small" aria-label="Create">
                        <EditIcon />
                    </IconButton>
                </Tooltip>
            </Link>
            <Tooltip title="Delete">
                <IconButton size="small" aria-label="Create" onClick={props.deleteOperation}>
                    <DeleteIcon />
                </IconButton>
            </Tooltip>
        </div>
    );
}

export default ActionIcons;