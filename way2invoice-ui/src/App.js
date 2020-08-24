import React, { Component } from 'react';
import { createBrowserHistory } from 'history';
import { Router } from 'react-router-dom';
import { ThemeProvider } from '@material-ui/styles';
import { chartjs } from './helpers';
import { Chart } from 'react-chartjs-2';

import Routes from './Routes';
import './assets/scss/index.scss';
import 'react-perfect-scrollbar/dist/css/styles.css';
import theme from './theme';

const browserHistory = createBrowserHistory();
Chart.helpers.extend(Chart.elements.Rectangle.prototype, {
  draw: chartjs.draw
});


class App extends Component {
  render() {
    return (
      <ThemeProvider theme={theme}>
        <Router history={browserHistory}>
          <Routes />
        </Router>
      </ThemeProvider>
    );
  }
}

export default App;
