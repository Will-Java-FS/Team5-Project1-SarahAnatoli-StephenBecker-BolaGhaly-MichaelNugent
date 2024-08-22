import { useEffect, useState } from "react";
import axiosInstance from "../axios/AxiosInstance";
import { useNavigate } from 'react-router-dom';

interface Exercise {
    id: number|undefined;
    name: string|undefined;
    caloriesBurnedPerMinute: number|undefined;
  }
  
  interface UserExerciseLog {
    id: number|undefined;
    exercise: Exercise|undefined;
    durationMinutes: number|undefined;
    caloriesBurned: number|undefined;
    logDate: string|undefined;
  }
  
export default function ExerciseLogsList(){

    const navigate = useNavigate();

    const [log, setLog] = useState<UserExerciseLog[]>([]);

    useEffect(() => {

        const getUserData = async () => {
          try {
            const response = await axiosInstance.get("/userexerciselogs/loglistbyuserid");
    
            if (response === null) throw 'null result'
    
            setLog(response.data);
            console.log(response)
    
          } catch (error) {
            console.error('Error in ExerciseData data fetch: ', error)
          }
        }
        getUserData();
    
      }, [])

      const handleDelete = async (userExerciseLog:UserExerciseLog | undefined) => {
        try {
          const recordid=userExerciseLog?.id;
          await axiosInstance.delete(`/userexerciselogs/deleteexerciselog`, { data: userExerciseLog });
    
          // Filter out the deleted record from the state
          setLog(log.filter((alog) => alog.id !== recordid));
        } catch (error) {
          console.error("Error deleting BMI record: ", error);
        }
      };

      return (
        <>
        <div>
          <h2>Exercise Log Records</h2>
            {log ? log.map((log, id) => (
              <div key={id}>
                <p><strong>Exercise:</strong> {log.exercise.name}</p>
                <p><strong>Calories Burned Per Minute: </strong> {log.exercise.caloriesBurnedPerMinute}</p>
                <p><strong>Duration in Minutes:</strong> {log.durationMinutes}</p>
                <p><strong>Total Calories Burned :</strong> {log.caloriesBurned}</p>
                <button onClick={() => handleDelete(log)}>Delete</button>
                <p>🏋️💪🏋️‍♀️</p>
              </div>
            ) ): (
            <p>Loading...</p>
          )}
        </div>
        <button onClick={()=>navigate("/addexerciselog")}>Add New Log</button>
        <button onClick={()=>navigate("/addexercise")}>Add New Exercise</button>
        
        
        </>
        );
    
}