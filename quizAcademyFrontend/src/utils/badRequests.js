import { useTokenStore } from "../stores/token.js";
import router from "../router/index.js";

export async function handleErrors(error) {
    if (error.response && error.response.status === 401) {
        console.log("Bad request, trying to get new token")
        let tokenStore = useTokenStore();
        let username = tokenStore.getUsername;
        let password = tokenStore.getPassword;
        try {
            await tokenStore.getTokenAndSaveInStore(username, password);
        } catch (err) {
            await router.push({name: "login"});
            console.log(err)
        }
    }
    if (useTokenStore().getJwtToken == null) {
        await router.push({name: "login"});
    }
    if (error.response && error.response.status === 400) {
        console.log("Bad request, trying to get new token")
        let tokenStore = useTokenStore();
        let username = tokenStore.getUsername;
        let password = tokenStore.getPassword;
        try {
            await tokenStore.getTokenAndSaveInStore(username, password);
        } catch (err) {
            await router.push({name: "login"});
            console.log(err)
        }
    }
    if (error.response && error.response.status === 403) {
        console.log("Bad request, trying to get new token")
        let tokenStore = useTokenStore();
        let username = tokenStore.getUsername;
        let password = tokenStore.getPassword;
        if (username == null || password == null) {
            await router.push({name: "login"});
        }
        try {
            await tokenStore.getTokenAndSaveInStore(username, password);
        } catch (err) {
            await router.push({name: "login"});
            console.log(err)
        }
    }
}