// quizUtils.js
import axios from "axios";
import { getJwtToken } from "./httputils.js";
import { useTokenStore } from "../stores/token.js";

export const fetchPublicQuizzes = async () => {
    try {
        const response = await axios.get("http://localhost:8080/quiz/public/all");
        return response.data;
    } catch (error) {
        console.error("Error fetching quizzes:", error);
        return [];
    }
};

export const fetchQuizById = async (quizId) => {
    try {
        const response = await axios.get(`http://localhost:8080/quiz/${quizId}`);
        return response.data;
    } catch (error) {
        console.error("Error fetching quiz by ID:", error);
        return null;
    }
}
export const fetchAllQuizzesByUser = async (userId) => {
    try {
        const response = await axios.get(`http://localhost:8080/quiz/all/${userId}`,
            {
                headers: {
                    "Authorization": "Bearer " + useTokenStore().jwtToken
                }
            });
        return response.data;
    } catch (error) {
        console.error("Error fetching quizzes by user:", error);
        return [];
    }
}

export const fetchAllQuizzesByTag = async (tagId) => {
    try {
        const response = await axios.get("http://localhost:8080/quiz/public/all/tags/" + tagId,
            {
                headers: {
                    "Authorization": "Bearer " + useTokenStore().jwtToken
                }
            });
        return response.data;
    } catch (error) {
        console.error("Error fetching quizzes by tag:", error);
        return [];
    }
}
