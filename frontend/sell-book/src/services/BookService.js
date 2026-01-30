import axios from 'axios';
const API_URL ="http://localhost:8080/api/books";
export const getHomeBooks = async ()=>{
    try {
        const res =await axios.get(`${API_URL}/home`);
        return res.data;
    } catch (error) {
        console.error("Can not fetch data: " ,error);
        return [];
    }
};