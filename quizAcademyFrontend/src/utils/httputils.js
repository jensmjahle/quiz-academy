import axios from "axios";

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
    return axios.post("http://localhost:8080/token", JSON.stringify({ username, password }), config);
}

export const getUserInfo = (username, token) => {
    const config = {
        headers: {
            "Content-type": "application/json",
            "Authorization" : "Bearer " + token
        },
    };
    return axios.get("http://localhost:8080/users/"+username, config);
}