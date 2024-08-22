import { useState } from "react";
import { FaSearch } from "react-icons/fa";
import "../CSS/SearchBar.css";
import axiosInstance from "../axios/AxiosInstance";

export const SearchBar = ({setResults}) => {
    const [input, setInput] = useState("");

    async function fetchData(value) {
        try {
            /*const response = await axiosInstance.get(
                "https://api.nal.usda.gov/fdc/v1/foods/list?dataType=Foundation,SR%20Legacy&pageSize=200&api_key=p2S0wOlvzxDu4uIi6andW9nCoverK2Mflvc0a7OG"
            );*/
            const response1 = await axiosInstance.get(
                "https://api.nal.usda.gov/fdc/v1/foods/list?dataType=Foundation,SR%20Legacy&pageSize=200&pageNumber=1&" + 
                "api_key=p2S0wOlvzxDu4uIi6andW9nCoverK2Mflvc0a7OG"
            );
            const response2 = await axiosInstance.get(
                "https://api.nal.usda.gov/fdc/v1/foods/list?dataType=Foundation,SR%20Legacy&pageSize=200&pageNumber=2&" + 
                "api_key=p2S0wOlvzxDu4uIi6andW9nCoverK2Mflvc0a7OG"
            );
            const response3 = await axiosInstance.get(
                "https://api.nal.usda.gov/fdc/v1/foods/list?dataType=Foundation,SR%20Legacy&pageSize=200&pageNumber=9&" + 
                "api_key=p2S0wOlvzxDu4uIi6andW9nCoverK2Mflvc0a7OG"
            );
            const response4 = await axiosInstance.get(
                "https://api.nal.usda.gov/fdc/v1/foods/list?dataType=Foundation,SR%20Legacy&pageSize=200&pageNumber=15&" + 
                "api_key=p2S0wOlvzxDu4uIi6andW9nCoverK2Mflvc0a7OG"
            );
            const totalResponse = [...response1.data, ...response2.data, ...response3.data, ...response4.data];
            console.log(totalResponse);
            const results = totalResponse.filter((food) => {
                return value && food && food.description && food.description.toLowerCase().includes(value);
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