import axios from 'axios';

/**
 * @author PalmuruganC
 */
export const globalService = {
    post, put, get
}

/**
 * 
 * @param {string} api 
 * @param {*} request 
 */
async function post(api, request) {
    return await axios.post(api, request);
}

/**
 * 
 * @param {string} api 
 */
async function get(api) {
    return await axios.get(api);
}

/**
 * 
 * @param {string} api 
 * @param {*} request 
 */
async function put(api, request) {
    return await axios.put(api, request);
}