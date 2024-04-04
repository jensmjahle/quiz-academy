
import { defineStore } from "pinia";
import {getJwtToken, getUserInfo} from "../utils/httputils.js"
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
                console.log(data)
                if(data != null && data !== '' && data !== undefined){
                    this.jwtToken = data;
                    this.loggedInUser = await getUserInfo(username, this.jwtToken);
                }
            } catch (err){
                console.log(err)
            }
        },
        async logout() {
            this.jwtToken = null;
            this.loggedInUser = null;
            await router.push({name: "Log in"});
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
