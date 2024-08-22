
import { useState } from "react";
import "../CSS/SearchResult.css";
import axiosInstance from "../axios/AxiosInstance";

export const SearchResult = ({ result, getFoodLogs }) => {
    const [name, setName] = useState<string>('');
    const [calories, setCalories] = useState<any>(null);
    const [servingSize, setServingSize] = useState<any>(null);
    const [protein, setProtein] = useState<any>(null);
    const [carbs, setCarbs] = useState<any>(null);
    const [fat, setFat] = useState<any>(null);

    const searchBarClick = async () => {
        setName(result.name);
        setCalories(result.calories);
        setServingSize(result.servingSize);
        setProtein(result.protein);
        setCarbs(result.carbs);
        setFat(result.fat);
        const response = await axiosInstance.post('http://localhost:8080/user/foodItem', { name, calories, servingSize, protein, carbs, fat });
        console.log(result);
        getFoodLogs();
    };

    return (
        <div onClick={searchBarClick} className="search-result">{result.name}</div>
    )
}