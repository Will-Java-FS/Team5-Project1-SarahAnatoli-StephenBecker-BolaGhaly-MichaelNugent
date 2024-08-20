import { useState } from "react";
import axios from "axios";

import '../CSS/FoodItem.css';
import { SearchBar } from "../components/SearchBar";

export default function FoodLog() {

    const [results, setResults] = useState([]);

    const [name, setName] = useState<string>('');
    const [calories, setCalories] = useState<any>(null);

    const [successMessage, setSuccessMessage] = useState<string>('');
    const [errorMessage, setErrorMessage] = useState<string>('');

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        //Prevents page reload
        event.preventDefault();

        try {
            //replace this web url with an environment variable so as to support both development and production instances
            const response = await axios.post('http://localhost:8080/foodItem', { name, calories });

            setSuccessMessage('Food added.');
            // Redirect or perform other post-login actions
            console.log(response.data);

        } catch (error) {
            console.error('Could not log food item:', error);
            setErrorMessage('Food wasnt logged.');
            setSuccessMessage('');  // Clear any previous success message
        }
    };

    return (<div className="FoodLog">
        <h1>Food Log</h1>
        <div className="food-card">
        <h3>Add a Food From Our Database</h3>
        <div className="search-bar-container">
            <SearchBar setResults={setResults}/>
            <div>SearchResults</div>
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
                {errorMessage && <p className="error">{errorMessage}</p>}
                {successMessage && <p className="success">{successMessage}</p>}
                <button type="submit">Log</button>
            </form>
        </div>
    </div>)
}