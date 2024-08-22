import { useEffect, useState } from "react";
import axiosInstance from "../axios/AxiosInstance";
import { useNavigate } from 'react-router-dom';

interface Exercise {
    id: number;
    name: string;
    caloriesBurnedPerMinute: number;
}

interface UserExerciseLog {
    id?: number;
    exercise: Exercise;
    durationMinutes: number;
    caloriesBurned?: number;
    logDate: string;
}

export default function AddExerciseLogs() {
    const navigate = useNavigate();

    const [exercises, setExercises] = useState<Exercise[]>([]);
    const [selectedExerciseId, setSelectedExerciseId] = useState<number>();
    const [durationMinutes, setDurationMinutes] = useState<number>(0);
    const [logDate, setLogDate] = useState<string>(new Date().toISOString().split('T')[0]); // Default to today's date

    useEffect(() => {
        const fetchExercises = async () => {
            try {
                const response = await axiosInstance.get("/exercises"); // Adjust endpoint to fetch exercises
                setExercises(response.data);
            } catch (error) {
                console.error('Error fetching exercises: ', error);
            }
        };

        fetchExercises();
    }, []);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        

        if (!selectedExerciseId) return;

        const selectedExercise = exercises.find(ex => ex.id === selectedExerciseId);
        if (!selectedExercise) return;

        const caloriesBurned = selectedExercise.caloriesBurnedPerMinute * durationMinutes;

        const newLog: UserExerciseLog = {
            exercise: selectedExercise,
            durationMinutes,
            caloriesBurned,
            logDate,
        };


        try {
            await axiosInstance.post("/userexerciselogs/addexerciselog", newLog);
            navigate("/exerciselogs"); // Redirect back to the exercise logs list
        } catch (error) {
            console.error("Error adding exercise log: ", error);
        }
    };

    return (
        <div>
            <h2>Add New Exercise Log</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Exercise: </label>
                    <select
                        value={selectedExerciseId}
                        onChange={(e) => setSelectedExerciseId(Number(e.target.value))}
                    >
                        <option value="">Select an exercise</option>
                        {exercises.map((exercise) => (
                            <option key={exercise.id} value={exercise.id}>
                                {exercise.name} (Calories: {exercise.caloriesBurnedPerMinute}/min)
                            </option>
                        ))}
                    </select>
                </div>
                <button onClick={()=>navigate("/addexercise")}>Add an exercise not listed</button>
                <div>
                    <label>Duration (minutes): </label>
                    <input
                        type="number"
                        value={durationMinutes}
                        onChange={(e) => setDurationMinutes(Number(e.target.value))}
                        required
                    />
                </div>
                <div>
                    <label>Date: </label>
                    <input
                        type="date"
                        value={logDate}
                        onChange={(e) => setLogDate(e.target.value)}
                        required
                    />
                </div>
                <button>Add Log</button>
                <button onClick={()=>navigate("/exerciselogs")}>Cancel</button>
            </form>
        </div>
    );
}
