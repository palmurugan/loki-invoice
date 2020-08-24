import React from 'react';
import { Route } from 'react-router-dom';
import PropTypes from 'prop-types';

import { SignIn as SignInView } from '../../views';
import { Minimal as MinimalLayout } from '../../layouts';

const RouteWithLayout = props => {
  const { layout: Layout, component: Component, ...rest } = props;
  const loggedIn = 'loggedIn';
  if ('loggedIn' === loggedIn) {
    return (
      <Route
        {...rest}
        render={matchProps => (
          <Layout>
            <Component {...matchProps} />
          </Layout>
        )}
      />
    );
  } else {
    return (
      <Route
        {...rest}
        render={matchProps => (
          <MinimalLayout>
            <SignInView />
          </MinimalLayout>
        )}
      />
    );
  }
};

RouteWithLayout.propTypes = {
  component: PropTypes.any.isRequired,
  layout: PropTypes.any.isRequired,
  path: PropTypes.string
};

export default RouteWithLayout;
