import React from 'react';
import { makeStyles } from '@material-ui/styles';

import BreadCrumb from '../../components/BreadCrumb';

const useStyles = makeStyles({
    root: {
        padding: 2
    },
    content: {
        marginLeft: 5,
        marginRight: 5
    }
});

/**
 * GridHolder
 */
const GridHolder = (props) => {
    const classes = useStyles();
    return (
        <div className={classes.root}>
            <div className={classes.content}>
                <BreadCrumb data={props.breadCrumbData} />

                {props.children}
            </div>
        </div>
    )
}

export default GridHolder;