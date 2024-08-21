import { useEffect, useState,useRef } from "react";
import axiosInstance from "../axios/AxiosInstance";

export default function BMIList(){

    const [userBMI, setUserBMI] = useState([{ id: undefined,height: undefined, weight: undefined, bmiValue: undefined, recordedAt: undefined}]);
    const [editMode, setEditMode] = useState(false);

    useEffect(() => {

        const getUserData = async () => {
          try {
            const response = await axiosInstance.get("/bmirecords/bmilistbyuserid");
    
            if (response === null) throw 'null result'
    
            setUserBMI(response.data);
            console.log(response)
    
          } catch (error) {
            console.error('Error in UserBMI data fetch: ', error)
          }
        }
        getUserData();
    
      }, [])


    const heightRef = useRef<HTMLInputElement>(null);
    const weightRef = useRef<HTMLInputElement>(null);
    const bmiValueRef = useRef<HTMLInputElement>(null);
    const recordedAtRef = useRef<HTMLInputElement>(null);

    /*
    const save = async () => {

        try {
            const response = await axiosInstance.get("/bmirecords/addbmirecord", {
            height: heightRef.current!.value,
            weight: weightRef.current!.value,
            bmiValue: bmiValueRef.current!.value,
            recordedAt: recordedAtRef.current!.value,
          })
          console.log(response)
          setUserBMI(response.data)
    
        } catch (error) {
          console.error("Failed to update user BMI information:", error)
        }
    
        setEditMode(false);
      }
        */

      
      return (
        <div>
          <h2>User BMI Records</h2>
            {userBMI ? userBMI.map((userBMI, index) => (
              <div key={index}>
                <p><strong>Height:</strong> {userBMI.height} cm</p>
                <p><strong>Weight:</strong> {userBMI.weight} kg</p>
                <p><strong>BMI Value:</strong> {userBMI.bmiValue}</p>
                <p><strong>Recorded At:</strong> {new Date(userBMI.recordedAt).toLocaleString()}</p>
                <p>🏋️💪🏋️‍♀️</p>
              </div>
            ) ): (
            <p>Loading...</p>
          )}
        </div>
        );
    

}