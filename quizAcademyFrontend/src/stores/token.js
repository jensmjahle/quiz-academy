import axios from "axios";
import { defineStore } from "pinia";
import {getJwtToken} from "../utils/httputils.js"

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
                if(data != null && data != '' && data != undefined){
                    this.jwtToken = data;
                    this.loggedInUser = username
                }
            } catch (err){
                console.log(err)
            }
        }
    },
});
