import { useState } from "react";
import { FaSearch } from "react-icons/fa";
import "../CSS/SearchBar.css";
import axiosInstance from "../axios/AxiosInstance";

export const SearchBar = ({setResults}) => {
    const [input, setInput] = useState("");

    async function fetchData(value) {
        try {
            const response = await axiosInstance.get(
                "http://localhost:8080/user/foodItem"
            );
            const results = response.data.filter((food) => {
                return value && food && food.name && food.name.toLowerCase().includes(value);
            });
            console.log(results);
            setResults(results);
        } catch(error) {
            console.log(error);
        }
    };

    const handleChange = (value) => {
        setInput(value);
        fetchData(value);
    };

    return (
        <div className="input-wrapper">
            <FaSearch id="search-icon" />
            <input
            id="search-bar"
            placeholder="Type to search..."
            value={input} 
            onChange={(e) => handleChange(e.target.value)} />
        </div>
    )
}