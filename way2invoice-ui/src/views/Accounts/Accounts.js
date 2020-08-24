import React from 'react';
import PropTypes from 'prop-types';
import { makeStyles, useTheme } from '@material-ui/core/styles';
import { AppBar, Tabs, Tab, Typography, Box } from '@material-ui/core';

import { BasicInfo, ContactInfo } from './components';

function AccountTabPanel(props) {
    const { children, value, index, ...other } = props;

    return (
        <div
            role="tabpanel"
            hidden={value !== index}
            id={`full-width-tabpanel-${index}`}
            aria-labelledby={`full-width-tab-${index}`}
            {...other}
        >
            {value === index && (
                <Box p={3}>
                    <Typography>{children}</Typography>
                </Box>
            )}
        </div>
    );
}

AccountTabPanel.propTypes = {
    children: PropTypes.node,
    index: PropTypes.any.isRequired,
    value: PropTypes.any.isRequired,
};

function a11yProps(index) {
    return {
        id: `full-width-tab-${index}`,
        'aria-controls': `full-width-tabpanel-${index}`,
    };
}

const useStyles = makeStyles((theme) => ({
    root: {
        backgroundColor: theme.palette.background.paper,
    },
}));

const Accounts = (props) => {
    const classes = useStyles();
    const theme = useTheme();

    const [value, setValue] = React.useState(0);
    const [accountId, setAccountId] = React.useState(null);
    const [enableContactTab, setEnableContactTab] = React.useState(false);
    const [enableTaxTab, setEnableTaxTab] = React.useState(false);

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    const handleChangeIndex = (index) => {
        setValue(index);
    };

    const handleTabActivation = (value, accountId) => {
        setEnableContactTab(value);
        setEnableTaxTab(value);
        setAccountId(accountId);
    }


    return (
        <div className={classes.root}>
            <AppBar position="static" color="inherit" elevation={0}>
                <Tabs
                    value={value}
                    onChange={handleChange}
                    indicatorColor="primary"
                    textColor="primary"
                    variant="fullWidth"
                    aria-label="full width tabs example"
                >
                    <Tab label="Basic Information" {...a11yProps(0)} />
                    <Tab label="Contact Information" {...a11yProps(1)} disabled={!enableContactTab} />
                    <Tab label="Taxation Information" {...a11yProps(2)} disabled />
                </Tabs>
            </AppBar>
            <AccountTabPanel value={value} index={0} dir={theme.direction}>
                <BasicInfo tabActivationHandler={handleTabActivation} />
            </AccountTabPanel>
            <AccountTabPanel value={value} index={1} dir={theme.direction}>
                <ContactInfo keyId={accountId} />
            </AccountTabPanel>
            <AccountTabPanel value={value} index={2} dir={theme.direction}>
                Item Three
            </AccountTabPanel>
        </div>
    );
}

export default Accounts;