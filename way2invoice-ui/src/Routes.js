import React from 'react';
import { Switch, Redirect } from 'react-router-dom';

import { RouteWithLayout } from './components';
import { Main as MainLayout, Minimal as MinimalLayout } from './layouts';

import {
  SignIn as SignInView,
  Dashboard as DashboardView,
  UserList as UserListView,
  CustomerList as AccountListView,
  Customer as AccountsView,
  Invoice as InvoiceView,
  ItemList as ItemListView,
  Item as ItemView,
  InvoiceList as InvoiceListView,
  ReceiptList as ReceiptListView,
  Receipt as ReceiptView,
  VendorList as VendorListView,
  Vendor as VendorView,
  PaymentList as PaymentListView,
  Payment as PaymentView,
  BillList as BillListView,
  Bill as BillView
} from './views';

const Routes = () => {
  return (
    <Switch>
      <Redirect exact from="/" to="/sign-in" />
      <RouteWithLayout component={SignInView} exact layout={MinimalLayout} path="/sign-in" />

      <RouteWithLayout component={DashboardView} exact layout={MainLayout} path="/dashboard" />

      <RouteWithLayout component={AccountListView} exact layout={MainLayout} path="/accounts" />
      <RouteWithLayout component={AccountsView} exact layout={MainLayout} path="/account" />
      <RouteWithLayout component={AccountsView} exact layout={MainLayout} path="/account/:id" />

      <RouteWithLayout component={ItemListView} exact layout={MainLayout} path="/items" />
      <RouteWithLayout component={ItemView} exact layout={MainLayout} path="/item" />
      <RouteWithLayout component={ItemView} exact layout={MainLayout} path="/item/:id" />

      <RouteWithLayout component={InvoiceListView} exact layout={MainLayout} path="/invoices" />
      <RouteWithLayout component={InvoiceView} exact layout={MainLayout} path="/invoice" />
      <RouteWithLayout component={InvoiceView} exact layout={MainLayout} path="/invoice/:id" />

      <RouteWithLayout component={BillListView} exact layout={MainLayout} path="/bills" />
      <RouteWithLayout component={BillView} exact layout={MainLayout} path="/bill" />
      <RouteWithLayout component={BillView} exact layout={MainLayout} path="/bill/:id" />

      <RouteWithLayout component={ReceiptListView} exact layout={MainLayout} path="/receipts" />
      <RouteWithLayout component={ReceiptView} exact layout={MainLayout} path="/receipt" />
      <RouteWithLayout component={ReceiptView} exact layout={MainLayout} path="/receipt/:id" />

      <RouteWithLayout component={PaymentListView} exact layout={MainLayout} path="/payments" />
      <RouteWithLayout component={PaymentView} exact layout={MainLayout} path="/payment" />
      <RouteWithLayout component={PaymentView} exact layout={MainLayout} path="/payment/:id" />

      <RouteWithLayout component={VendorListView} exact layout={MainLayout} path="/vendors" />
      <RouteWithLayout component={VendorView} exact layout={MainLayout} path="/vendor" />
      <RouteWithLayout component={VendorView} exact layout={MainLayout} path="/vendor/:id" />

      <Redirect to="/sign-in" />
    </Switch>
  );
};

export default Routes;
