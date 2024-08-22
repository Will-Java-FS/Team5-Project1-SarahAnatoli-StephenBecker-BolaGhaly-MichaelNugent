import { useEffect, useState } from "react";
import axiosInstance from "../axios/AxiosInstance";
import { useNavigate } from 'react-router-dom';

interface Exercise {
    id: number;
    name: string;
    caloriesBurnedPerMinute: number;
  }

export default function Exercise(){
    const navigate = useNavigate();
    const [exercise, setExercise] = useState<Exercise[]>([]);

    useEffect(() => {

        const getUserData = async () => {
          try {
            const response = await axiosInstance.get("/exercises");
    
            if (response === null) throw 'null result'
    
            setExercise(response.data);
            console.log(response)
    
          } catch (error) {
            console.error('Error in ExerciseData data fetch: ', error)
          }
        }
        getUserData();
    
      }, [])

      const handleDelete = async (exercises:Exercise | undefined) => {
        try {
          const recordid=exercises?.id;
          await axiosInstance.delete(`/exercises`, { data: exercises });
    
          // Filter out the deleted record from the state
          setExercise(exercise.filter((exer) => exer.id !== recordid));
        } catch (error) {
          console.error("Error deleting Exercise record: ", error);
        }
      };

      return (
        <>
        <div>
          <h2>Exercises</h2>
            {exercise ? exercise.map((exercise, id) => (
              <div key={id}>
                <p><strong>Exercise:</strong> {exercise.name}</p>
                <p><strong>Calories Burned Per Minute: </strong> {exercise.caloriesBurnedPerMinute}</p>
                <button onClick={() => handleDelete(exercise)}>Delete</button>
                <p>🏋️💪🏋️‍♀️</p>
              </div>
            ) ): (
            <p>Loading...</p>
          )}
        </div>
        <button onClick={()=>navigate("/addexercise")}>Add Exercise</button>
        <button onClick={()=>navigate("/addexerciselog")}>Add New Log</button>
        
        
        </>
        );
    
}
