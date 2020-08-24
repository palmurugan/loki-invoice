import axios from 'axios';

export const authenticationService = {
    login
}

/**
 * Login service
 * 
 * It will prepare a login request and make a call to authentication service
 */
async function login(username, password) {
    const request = { "username": username, "password": password }
    const response = await axios.post("/api/authenticate", request);

    return await response;
}