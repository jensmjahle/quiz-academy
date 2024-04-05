import axios from "axios";

const baseurl = "http://localhost:3000";

export const loginRequest = (user) => {
    const config = {
        headers: {
            "Content-type": "application/json"
        }
    };
    return axios.get(baseurl + "/login", config);
};

export const getJwtToken = (username, password) => {
    const config = {
        headers: {
            "Content-type": "application/json"
        }
    };
    return axios.post(
        "http://localhost:8080/token/new",
        JSON.stringify({ username, password }),
        config
    );
};

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
            console.log("User already exists");
        }
        return response;
    }).catch((error) => {
        console.log("An error occurred during sign up:", error);
        throw error;
    });
}

export const getUserInfo = (username, token) => {
    const config = {
        headers: {
            "Content-type": "application/json",
            Authorization: "Bearer " + token
        }
    };
    return axios.get("http://localhost:8080/users/" + username, config);
};
