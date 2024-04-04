// quizUtils.js
import axios from "axios";

export const fetchQuizzes = async () => {
    try {
        const response = await axios.get("http://localhost:8080/quiz/all");
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

export const fetchAllQuizzesByTag = async (tagId) => {
    try {
        const response = await axios.get(`http://localhost:8080/quiz/tag/${tagId}`);
        return response.data;
    } catch (error) {
        console.error("Error fetching quizzes by tag:", error);
        return [];
    }
}
