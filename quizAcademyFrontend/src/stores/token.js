
import { defineStore } from "pinia";
import {getJwtToken, getUserInfo, refreshJwtToken, deleteToken} from "../utils/httputils.js"
import router from "../router/index.js";

export const useTokenStore = defineStore("token", {
    state: () => ({
        jwtToken: null,
        loggedInUser: null,
    }),

    persist: {
        storage: sessionStorage, // note that data in sessionStorage is cleared when the page session ends
    },

    actions: {
        async getTokenAndSaveInStore(username, password) {
            try{
                console.log("Getting token for user: " + username)
                let response = await getJwtToken(username, password);
                let data = response.data;
                console.log(data) // log the actual data
                if(data !== '' && data !== undefined){
                    this.jwtToken = data;
                    this.loggedInUser = await getUserInfo(username, this.jwtToken);
                    console.log(this.loggedInUser.data) // log the actual data
                }
            } catch (err){
                console.log(err)
            }
        },
        async logout() {
            this.jwtToken = null;
            this.loggedInUser = null;
            await deleteToken();
        },
        async refreshToken() {
            if (!this.loggedInUser || !this.jwtToken) {
                console.error('loggedInUser or jwtToken is undefined or null');
                return;
            }
            try{
                console.log("Refreshing token")
                let response = await refreshJwtToken(this.jwtToken);
                let data = response.data;
                console.log(data)
                if(data != null && data !== '' && data !== undefined){
                    this.jwtToken = data;
                }
            } catch (err){
                await router.push({name: "Login"})
                console.log(err)
            }
        }
    },
    getters: {
        getJwtToken: (state) => {
            return state.jwtToken;
        },
        getLoggedInUser: (state) => {
            return state.loggedInUser;
        },
        getUsername : (state) => {
            if(state.loggedInUser != null){
                return state.loggedInUser.data.username;
            }
            return null;
        },
        getPassword : (state) => {
            if(state.loggedInUser != null){
                return state.loggedInUser.data.password;
            }
            return null;
        },
        getRole : (state) => {
            if(state.loggedInUser != null){
                return state.loggedInUser.data.role;
            }
            return null;
        },
    }
});
