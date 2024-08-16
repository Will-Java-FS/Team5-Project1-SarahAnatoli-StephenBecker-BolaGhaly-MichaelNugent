import axios from 'axios';

// Create an Axios instance
const axiosInstance = axios.create({
    //Can change this later with environmental variables to dynamically be production or development server
    baseURL: 'http://localhost:8080/',
});

// Add a request interceptor to include the JWT in every request
axiosInstance.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token'); // Assuming you're storing the JWT in localStorage

        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }

        return config;
    },
    (error) => {
        console.error('JWT insertion error: ', error)
        return Promise.reject(error);
    }
);

export default axiosInstance;
