import { useTokenStore } from "../stores/token.js";
import router from "../router/index.js";

// if there is a bad request, or the token has run out, use this class to
// request a new one based on the username and password from the store.
// If loggedInUser is null, the user will be redirected to the login page.

    export async function handleErrors(error) {
        if (error.response.status === 401) {
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
        if (error.response.status === 400) {
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
        if (error.response.status === 403) {
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
