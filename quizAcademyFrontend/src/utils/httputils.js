import axios from "axios";
import router from "../router/index.js";
import {useTokenStore} from "../stores/token.js";

const baseurl = "http://localhost:3000";

export const loginRequest = (user) => {
    const config = {
        headers: {
            "Content-type": "application/json",
        },
    };
    return axios.get(baseurl + "/login", config);
}

export const getJwtToken = (username, password) => {
    const config = {
        headers: {
            "Content-type": "application/json",
        },
    };
    return axios.post("http://localhost:8080/token/new", JSON.stringify({ username, password }), config);
}

export const refreshJwtToken = async (token) => {
    const config = {
        headers: {
            "Content-type": "application/json",
            "Authorization": "Bearer " + token
        },
    };
    try {
        return axios.post("http://localhost:8080/token/refresh", token, config);
    } catch (err) {
        await router.push({name: "login"});
        console.log(err)
    }
}

export const getUserInfo = (username, token) => {
    const config = {
        headers: {
            "Content-type": "application/json",
            "Authorization" : "Bearer " + token
        },
    };
    return axios.get("http://localhost:8080/users/" + username, config);
}

export const deleteToken = () => {
    const config = {
        headers: {
            "Content-type": "application/json",
            "Authorization" : "Bearer " + useTokenStore().jwtToken
        },
    };
    return axios.post("http://localhost:8080/token/delete", {  }, config);
}
