import React from 'react';
import { makeStyles } from '@material-ui/styles';
import { Grid } from '@material-ui/core';

import {
  TotalSales,
  TotalPurchase,
  TasksProgress,
  TotalProfit,
  LatestSales,
  UsersByDevice,
  LatestProducts,
  LatestOrders, TotalExpense
} from './components';

const useStyles = makeStyles(theme => ({
  root: {
    padding: 8
  }
}));

const Dashboard = () => {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <Grid container spacing={1}>
        <Grid item lg={3} sm={6} xl={3} xs={12}>
          <TotalSales />
        </Grid>
        <Grid item lg={3} sm={6} xl={3} xs={12}>
          <TotalPurchase />
        </Grid>
        <Grid item lg={3} sm={6} xl={3} xs={12}>
          <TotalExpense />
        </Grid>
        <Grid item lg={3} sm={6} xl={3} xs={12}>
          <TotalProfit />
        </Grid>
        <Grid item lg={6} md={12} xl={9} xs={12}>
          <LatestSales />
        </Grid>
        <Grid item lg={6} md={6} xl={3} xs={12}>
          <LatestSales />
        </Grid>
        {
          /**<UsersByDevice />
           <Grid item lg={4} md={6} xl={3} xs={12}>
                    <LatestProducts />
                  </Grid>
                  <Grid item lg={8} md={12} xl={9} xs={12}>
                    <LatestOrders />
                  </Grid>
           */
        }
      </Grid>
    </div>
  );
};

export default Dashboard;
