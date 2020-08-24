import React, { Component } from 'react';
import Link from '@material-ui/core/Link';
import { withStyles } from '@material-ui/styles';
import { Paper, Breadcrumbs } from '@material-ui/core';

const useStyles = () => ({
    breadcrumb: {
        marginBottom: 6,
        marginLeft: 5,
        marginTop: 5,
        padding: 8
    }
});

class BreadCrumb extends Component {

    render() {
        let crumbs = [];
        const { classes } = this.props;
        this.props.data.map((link, i) => {
            this.props.data.length === i + 1 ? crumbs.push(<Link color="textPrimary" href="#" key={link.label}>{link.label}</Link>)
                : crumbs.push(<Link color="inherit" href="#" key={link.label}>{link.label}</Link>);
        });
        return (
            <div>
                <Paper elevation={0}>
                    <Breadcrumbs aria-label="breadcrumb" className={classes.breadcrumb}>
                        {crumbs.map((crumb) => (
                            crumb
                        ))}
                    </Breadcrumbs>
                </Paper>

            </div>
        );
    }
}

export default withStyles(useStyles)(BreadCrumb);