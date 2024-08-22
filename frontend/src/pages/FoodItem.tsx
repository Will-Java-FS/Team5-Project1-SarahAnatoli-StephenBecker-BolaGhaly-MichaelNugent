import { useRef, useState, useEffect } from "react";
import axiosInstance from "../axios/AxiosInstance";

import '../CSS/FoodItem.css';
import { SearchBar } from "../components/SearchBar";
import { SearchResultsList } from "../components/SearchResultsList";

export default function FoodLog() {

    const [results, setResults] = useState([]);

    const [food, setFood] = useState<any>([]);
    const [editMode, setEditMode] = useState(false);
    const [row, setRow] = useState<number>();

    const [name, setName] = useState<string>('');
    const [calories, setCalories] = useState<any>(null);
    const [servingSize, setServingSize] = useState<any>(null);
    const [protein, setProtein] = useState<any>(null);
    const [carbs, setCarbs] = useState<any>(null);
    const [fat, setFat] = useState<any>(null);

    const [successMessage, setSuccessMessage] = useState<string>('');
    const [errorMessage, setErrorMessage] = useState<string>('');

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        try {
            //replace this web url with an environment variable so as to support both development and production instances
            const response = await axiosInstance.post('http://localhost:8080/user/foodItem', { name, calories, servingSize, protein, carbs, fat });

            setSuccessMessage('Food added.');
            // Redirect or perform other post-login actions
            console.log(response.data);

            getData();

        } catch (error) {
            console.error('Could not log food item:', error);
            setErrorMessage('Food wasnt logged.');
            setSuccessMessage('');  // Clear any previous success message
        }
    };
    const getData = async () => {
        //axios will return a response with our function
        const response = await axiosInstance.get(
            "http://localhost:8080/user/foodItem"
        );

        //the response is stored as data, so anything must be accessed using response.data
        setFood(response.data);
    }

    useEffect(() => {

        getData();
    }, [])

    async function deleteEntry(id: number) {
        //let index = 3;
        const response = await axiosInstance.delete(
            `http://localhost:8080/foodItem/${id}`
        );
        // setFood(response.data);
        getData()
    };

    function edit(index: number) {
        setRow(index);
        setEditMode(true);
    }

    const nameRef = useRef<HTMLInputElement>(null);
    const caloriesRef = useRef<HTMLInputElement>(null);
    const servingSizeRef = useRef<HTMLInputElement>(null);
    const proteinRef = useRef<HTMLInputElement>(null);
    const carbsRef = useRef<HTMLInputElement>(null);
    const fatRef = useRef<HTMLInputElement>(null);

    async function save(id: number) {

        try {
            const response = await axiosInstance.patch(`http://localhost:8080/foodItem/${id}`, {
                name: nameRef.current!.value,
                calories: caloriesRef.current!.value,
                servingSize: servingSizeRef.current!.value,
                protein: proteinRef.current!.value,
                carbs: carbsRef.current!.value,
                fat: fatRef.current!.value
            })
            console.log(response)
            // setFood(response.data)
            getData();

        } catch (error) {
            console.error("Failed to update food information:", error)
        }

        setEditMode(false);
    }

    return (
    <div className="FoodLog">
        <h1>Food Log</h1>
        <div className="food-card">
            <h3>Add a Food From Our Database</h3>
            <div className="search-bar-container">
                <SearchBar setResults={setResults} />
                <SearchResultsList results={results} getFoodLogs={getData} />
            </div>
        </div>
        <div className="food-card">
            <h3>Add Your Own</h3>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="name">Name:</label>
                    <input
                        type="text"
                        id="name"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="calories">Calories:</label>
                    <input
                        type="number"
                        id="calories"
                        value={calories}
                        onChange={(e) => setCalories(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="serving-size">Serving Size:</label>
                    <input
                        type="number"
                        id="serving-size"
                        value={servingSize}
                        onChange={(e) => setServingSize(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="protein">Protein:</label>
                    <input
                        type="number"
                        id="protein"
                        value={protein}
                        onChange={(e) => setProtein(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="carbs">Carbs:</label>
                    <input
                        type="number"
                        id="carbs"
                        value={carbs}
                        onChange={(e) => setCarbs(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="fat">Fat:</label>
                    <input
                        type="number"
                        id="fat"
                        value={fat}
                        onChange={(e) => setFat(e.target.value)}
                        required
                    />
                </div>
                {errorMessage && <p className="error">{errorMessage}</p>}
                {successMessage && <p className="success">{successMessage}</p>}
                <button type="submit">Log</button>
            </form>
        </div>
        <div className="log-card">
            <h3>Your Log</h3>
            <table className="log-table">
                <tr className="log-table">
                    <th className="log-table">Name</th>
                    <th className="log-table">Calories</th>
                    <th className="log-table">Date Logged</th>
                    <th className="log-table">Serving Size</th>
                    <th className="log-table">Protein</th>
                    <th className="log-table">Carbs</th>
                    <th className="log-table">Fat</th>
                </tr>
                <tbody className="log-table">
                    {food && food.map && food.map((food, index) => (
                        <tr className="log-table" key={index}>
                            <td className="log-table">{editMode && index === row ? <input className="foodlog-input" type="text" defaultValue={food.name} ref={nameRef} /> : food.name}</td>
                            <td className="log-table">{editMode && index === row ? <input className="foodlog-input" type="number" defaultValue={food.calories} ref={caloriesRef} /> : food.calories}kcal</td>
                            <td className="log-table">{food.logDate}</td>
                            <td className="log-table">{editMode && index === row ? <input className="foodlog-input" type="number" defaultValue={food.servingSize} ref={servingSizeRef} /> : food.servingSize}</td>
                            <td className="log-table">{editMode && index === row ? <input className="foodlog-input" type="number" defaultValue={food.protein} ref={proteinRef} /> : food.protein}g</td>
                            <td className="log-table">{editMode && index === row ? <input className="foodlog-input" type="number" defaultValue={food.carbs} ref={carbsRef} /> : food.carbs}g</td>
                            <td className="log-table">{editMode && index === row ? <input className="foodlog-input" type="number" defaultValue={food.fat} ref={fatRef} /> : food.fat}g</td>
                            <td className="log-table"><button className="delete-edit-button" onClick={() => deleteEntry(food.id)}>Delete</button></td>
                            <td className="log-table">{editMode && index === row
                                ? <><button className="save-cancel-button" onClick={() => save(food.id)}>Save</button><button className="save-cancel-button" onClick={() => setEditMode(false)}>Cancel</button></>
                                : <button className="delete-edit-button" onClick={() => edit(index)}>Edit</button>}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    </div>)
}