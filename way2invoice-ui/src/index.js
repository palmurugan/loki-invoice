import React from 'react';
import axios, { AxiosResponse } from 'axios';

import ReactDOM from 'react-dom';
import App from './App';
import * as serviceWorker from './serviceWorker';

/**
 * Http request interceptor to add token in the header
 */
axios.interceptors.request.use(request => {
  if(!request.url.includes('authenticate')) {
    request.headers.Authorization = 'Bearer '+localStorage.getItem('id_token');
  }
  return request;
}, error => {
  console.log(error);
  return Promise.reject(error);
});

/**
 * Http response interceptor, any response has to update we have to do it here.
 */
axios.interceptors.response.use(response => {
  console.log('response from interceptor:', response);
  // Edit response config
  return response;
}, error => {
  console.log(error);
  return Promise.reject(error);
});

ReactDOM.render(<App />,
  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
