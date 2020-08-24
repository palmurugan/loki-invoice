import React from 'react';
import { withRouter } from 'react-router-dom';

import { Card, CardHeader, CardContent, CardActions, Divider, Button } from '@material-ui/core';
import { makeStyles } from '@material-ui/styles';

import BreadCrumb from '../../components/BreadCrumb';

const useStyles = makeStyles({
    root: {
        padding: 2
    },
    content: {
        marginLeft: 5,
        marginRight: 5
    },
    action: {
        flex: '1 1 100%',
    }, actionButton: {
        justifyContent: 'flex-end'
    }
});

const PageHolder = (props) => {
    const classes = useStyles();

    let cardAction = '';
    if (props.actionEnabled && props.actionEnabled === 'true') {
        cardAction = <CardActions className={classes.actionButton}>
            <Button color="default" variant="contained" onClick={() => { props.history.goBack() }}> Cancel </Button>
            <Button type="submit" color="primary" variant="contained"> {props.updateMode && props.updateMode === true ? 'Update' : 'Save'} </Button>
        </CardActions>
    }
    return (
        <div className={classes.root}>
            <div className={classes.content}>
                <BreadCrumb data={props.breadCrumbData} />
                <Card elevation={0}>
                    <CardHeader
                        title={props.title} />
                    <Divider />
                    <CardContent>
                        {props.children}
                    </CardContent>
                    {cardAction}
                </Card>
            </div>
        </div>

    )
}

export default withRouter(PageHolder);