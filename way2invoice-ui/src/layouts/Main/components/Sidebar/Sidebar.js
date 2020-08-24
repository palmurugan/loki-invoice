import React from 'react';
import clsx from 'clsx';
import PropTypes from 'prop-types';
import { makeStyles } from '@material-ui/styles';
import { Divider, Drawer } from '@material-ui/core';
import DashboardIcon from '@material-ui/icons/Dashboard';
import PeopleIcon from '@material-ui/icons/People';
import ShoppingBasketIcon from '@material-ui/icons/ShoppingBasket';
import ReceiptIcon from '@material-ui/icons/Receipt';
import FileCopyIcon from '@material-ui/icons/FileCopy';
import PaymentIcon from '@material-ui/icons/Payment';
import DescriptionIcon from '@material-ui/icons/Description';

import { Profile, SidebarNav, UpgradePlan } from './components';

const useStyles = makeStyles(theme => ({
  drawer: {
    width: 240,
    [theme.breakpoints.up('lg')]: {
      marginTop: 48,
      height: 'calc(100% - 64px)'
    }
  },
  root: {
    backgroundColor: theme.palette.white,
    display: 'flex',
    flexDirection: 'column',
    height: '100%',
    padding: theme.spacing(2)
  },
  divider: {
    margin: theme.spacing(2, 0)
  },
  nav: {
    marginBottom: theme.spacing(2)
  }
}));

const Sidebar = props => {
  const { open, variant, onClose, className, ...rest } = props;

  const classes = useStyles();

  const pages = [
    {
      title: 'Dashboard',
      href: '/dashboard',
      icon: <DashboardIcon />
    }, {
      title: 'Accounts',
      href: '/accounts',
      icon: <PeopleIcon />
    }, {
      title: 'Items',
      href: '/items',
      icon: <ShoppingBasketIcon />
    }, {
      title: 'Invoice',
      href: '/invoices',
      icon: <DescriptionIcon />
    }, {
      title: 'Receipt',
      href: '/receipts',
      icon: <ReceiptIcon />
    }, {
      title: 'Bill',
      href: '/bills',
      icon: <FileCopyIcon />
    }, {
      title: 'Payment',
      href: '/payments',
      icon: <PaymentIcon />
    },

  ];
  // 100  <Profile />
  return (
    <Drawer
      anchor="left"
      classes={{ paper: classes.drawer }}
      onClose={onClose}
      open={open}
      variant={variant}
    >
      <div
        {...rest}
        className={clsx(classes.root, className)}
      >
        <SidebarNav
          className={classes.nav}
          pages={pages}
        />
      </div>
    </Drawer>
  );
};

Sidebar.propTypes = {
  className: PropTypes.string,
  onClose: PropTypes.func,
  open: PropTypes.bool.isRequired,
  variant: PropTypes.string.isRequired
};

export default Sidebar;
