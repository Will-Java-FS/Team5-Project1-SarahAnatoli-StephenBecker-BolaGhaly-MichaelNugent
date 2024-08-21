import { useEffect, useState,useRef } from "react";
import axiosInstance from "../axios/AxiosInstance";

export default function BMIList(){

    const [userBMI, setUserBMI] = useState({ id: undefined,height: undefined, weight: undefined, bmiValue: undefined, recordedAt: undefined});
    const [editMode, setEditMode] = useState(false);

    useEffect(() => {

        const getUserData = async () => {
          try {
            const response = await axiosInstance.get("/bmirecords/bmilistbyuserid");
    
            if (response === null) throw 'null result'
    
            setUserBMI(response.data);
    
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
    


    return(<>
        <h3>I exist</h3>
        <p><strong>Weight:</strong> {editMode ? <input type="text" defaultValue={userBMI.weight} ref={weightRef} /> : userBMI.weight}</p>
        <p><strong>Height:</strong> {editMode ? <input type="text" defaultValue={userBMI.height} ref={heightRef} /> : userBMI.height}</p>
        <div>
        {editMode
          ? <><button>Save</button><button onClick={() => setEditMode(false)}>Cancel</button></>
          : <button onClick={() => setEditMode(true)}>Edit</button>}
      </div>
    </>)
}