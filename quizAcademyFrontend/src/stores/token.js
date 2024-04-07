import { defineStore } from "pinia";
import {getJwtToken, getUserInfo, refreshJwtToken, deleteToken, deleteUser} from "../utils/httputils.js"
import router from "../router/index.js";

export const useTokenStore = defineStore("token", {
    state: () => ({
        jwtToken: null,
        loggedInUser: null,
        timer: null
    }),

    persist: {
        storage: sessionStorage
    },

    actions: {
        startTimer() {
            if (this.timer) {
                clearTimeout(this.timer);
            }
            this.timer = setTimeout(() => {
                alert("Session timed out. Please log in again.");
                this.logout();
            }, 340000);
        },
        async getTokenAndSaveInStore(username, password) {
            try {
                let response = await getJwtToken(username, password);
                let data = response.data;
                if(data !== '' && data !== undefined){
                    this.jwtToken = data;
                    this.loggedInUser = await getUserInfo(username, this.jwtToken);
                    this.startTimer();
                }
            } catch (err) {
                console.error(err);
            }
        },
        async logout() {
            await deleteToken();
            this.jwtToken = null;
            this.loggedInUser = null;
            sessionStorage.clear();
            if (this.timer) {
                clearTimeout(this.timer);
                this.timer = null;
            }
            await router.push("/login");
        },
        async refreshToken() {
            if (!this.loggedInUser || !this.jwtToken) {
                console.error('loggedInUser or jwtToken is undefined or null');
                return;
            }
            try{
                let response = await refreshJwtToken(this.jwtToken);
                let data = response.data;
                if(data != null && data !== '' && data !== undefined){
                    this.jwtToken = data;
                    this.startTimer();
                }
            } catch (err){
                await router.push({name: "Login"})
                console.error(err)
            }
        },
        async deleteUser() {
            deleteUser(this.loggedInUser.data.username)
            await deleteToken();
            this.jwtToken = null;
            this.loggedInUser = null;
            sessionStorage.clear();
            if (this.timer) {
                clearTimeout(this.timer);
                this.timer = null;
            }
            await router.push("/login");
        }
    },
    getters: {
        getJwtToken: (state) => {
            state.jwtToken = useTokenStore().refreshToken();
            return state.jwtToken;
        },
        getLoggedInUser: (state) => {
            state.jwtToken = useTokenStore().refreshToken();
            return state.loggedInUser;
        },
        getUsername: (state) => {
            state.jwtToken = useTokenStore().refreshToken();
            if (state.loggedInUser != null) {
                return state.loggedInUser.data.username;
            }
            return null;
        },
        getFirstName: (state) => {
            state.jwtToken = useTokenStore().refreshToken();
            if (state.loggedInUser != null) {
                return state.loggedInUser.data.firstName;
            }
            return null;
        },
        getLastName: (state) => {
            state.jwtToken = useTokenStore().refreshToken();
            if (state.loggedInUser != null) {
                return state.loggedInUser.data.lastName;
            }
            return null;
        },
        getEmail: (state) => {
            state.jwtToken = useTokenStore().refreshToken();
            if (state.loggedInUser != null) {
                return state.loggedInUser.data.email;
            }
            return null;
        },
        getPassword: (state) => {
            state.jwtToken = useTokenStore().refreshToken();
            if (state.loggedInUser != null) {
                return state.loggedInUser.data.password;
            }
            return null;
        },
        getRole: (state) => {
            state.jwtToken = useTokenStore().refreshToken();
            if (state.loggedInUser != null) {
                return state.loggedInUser.data.role;
            }
            return null;
        }
    }
});