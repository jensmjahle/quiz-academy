import { useTokenStore } from "../stores/token.js";

export const refresh = () => {
    useTokenStore()
        .refreshToken()
        .then((r) => {
            console.log("Token refreshed"); //leave this logger
        })
        .catch((e) => {
            console.log("Error refreshing token"); //leave this logger
        });
};
