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
