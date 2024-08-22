
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
        /*setName(result.name);
        setCalories(result.calories);
        setServingSize(result.servingSize);
        setProtein(result.protein);
        setCarbs(result.carbs);
        setFat(result.fat);*/
        setName(result.description);
        setServingSize(1);
        for(let i = 0; i < result.foodNutrients.length; i++) {
            if(result.foodNutrients[i].number == 208) {
                console.log("calories: " + result.foodNutrients[i].amount);
                setCalories(result.foodNutrients[i].amount);
            }
            if(result.foodNutrients[i].number == 203) {
                console.log("protein: " + result.foodNutrients[i].amount);
                setProtein(result.foodNutrients[i].amount);
            }
            if(result.foodNutrients[i].number == 205) {
                console.log("carbs: " + result.foodNutrients[i].amount);
                setCarbs(result.foodNutrients[i].amount);
            }
            if(result.foodNutrients[i].number == 605) {
                console.log("fat: " + result.foodNutrients[i].amount);
                setFat(result.foodNutrients[i].amount);
            }
        }
        const response = await axiosInstance.post('http://localhost:8080/user/foodItem', { name, calories, servingSize, protein, carbs, fat });
        console.log(result);
        getFoodLogs();
    };

    return (
        <div onClick={searchBarClick} className="search-result">{result.description}</div>
    )
}