import React from 'react';
import MuiAlert from '@material-ui/lab/Alert';
import Snackbar from '@material-ui/core/Snackbar';

function Alert(props) {
    return <MuiAlert elevation={6} variant="filled" {...props} />;
}

const Notification = (props) => {
    return (
        <Snackbar open={props.open} autoHideDuration={3000} onClose={props.handleClose}>
            <Alert onClose={props.handleClose} severity={props.notification.severity}> {props.notification.message} </Alert>
        </Snackbar>
    );
}

export default Notification;