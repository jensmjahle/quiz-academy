import axios from "axios";
import router from "../router/index.js";
import {useTokenStore} from "../stores/token.js";

/**
 * Get the JWT token for the user
 * @param username
 * @param password
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export const getJwtToken = async (username, password) => {
    const config = {
        headers: {
            "Content-type": "application/json"
        }
    };
    try {
        return axios.post("http://localhost:8080/token/new", JSON.stringify({username, password}), config);
    }
    catch (error) {
        console.error("An error occurred during token retrieval:", error);
        await useTokenStore().logout();
        await router.push("/login");
    }
}

/**
 * Refresh the JWT token for the user
 * @param token
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export const refreshJwtToken = async (token) => {
    const config = {
        headers: {
            "Content-type": "application/json",
            "Authorization": "Bearer " + token
        },
    };
    try {

        return axios.post("http://localhost:8080/token/refresh", token, config)
    }
    catch (error) {
        await useTokenStore().logout();
        await router.push("/login");
    }
}

/**
 * Get the user information for the user
 * @param username
 * @param token
 * @returns {Promise<void>}
 */
export const getUserInfo = async (username, token) => {
    const config = {
        headers: {
            "Content-type": "application/json",
            "Authorization": "Bearer " + token
        },
    };
    try {
        return axios.get("http://localhost:8080/users/" + username, config);
    }
    catch (error) {
        console.error("An error occurred during user info retrieval:", error);
        await useTokenStore().logout();
        await router.push("/login");
    }
}

/**
 * Delete the JWT token for the user
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export const deleteToken = () => {
    const config = {
        headers: {
            "Content-type": "application/json",
            "Authorization" : "Bearer " + useTokenStore().jwtToken
        },
    };
    return axios.post("http://localhost:8080/token/delete", {  }, config);
}



export const signUpUser = (firstName, lastName, username, password, email) => {
    const config = {
        headers: {
            "Content-type": "application/json"
        }
    };
    return axios.post(
        "http://localhost:8080/users/create",
        JSON.stringify({username, password, email, firstName, lastName }),
        config
    ).then((response) => {
        if (response.status === 409) {
        }
        return response;
    }).catch((error) => {
        console.error("An error occurred during sign up:", error);
        throw error;
    });
}

export const deleteUser = (username) => {
    const config = {
        headers: {
            "Content-type": "application/json",
            "Authorization": "Bearer " + useTokenStore().jwtToken
        }
    };
    return axios.delete("http://localhost:8080/users/delete/" + username, config).then((response) => {
        return response;
    }).catch((error) => {
        console.error("An error occurred during deletion:", error);
        throw error;
    });
}