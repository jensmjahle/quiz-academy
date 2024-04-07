import axios from "axios";

export const fetchPublicTags = async () => {
    try {
        const response = await axios.get("http://localhost:8080/tag/all");
        return response.data;
    } catch (error) {
        console.error("Error fetching all tags:", error);
        return [];
    }
}