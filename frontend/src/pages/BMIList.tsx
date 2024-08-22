import { useEffect, useState } from "react";
import axiosInstance from "../axios/AxiosInstance";
import { useNavigate } from 'react-router-dom';



interface BMIRecord {
  id: number | undefined;
  height: number | undefined;
  weight: number | undefined;
  bmiValue: number | undefined;
  recordedAt: string | undefined;
}


export default function BMIList(){

  const navigate = useNavigate();

  const [userBMI, setUserBMI] = useState<BMIRecord[]>([]);

  const handleDelete = async (bmiRecord:BMIRecord | undefined) => {
    try {
      const recordid=bmiRecord?.id;
      await axiosInstance.delete(`/bmirecords/del`, { data: bmiRecord });

      // Filter out the deleted record from the state
      setUserBMI(userBMI.filter((bmi) => bmi.id !== recordid));
    } catch (error) {
      console.error("Error deleting BMI record: ", error);
    }
  };



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
      
      return (
        <>
        <div>
          <h2>User BMI Records</h2>
            {userBMI ? userBMI.map((userBMI, id) => (
              <div key={id}>
                <p><strong>Height:</strong> {userBMI.height} in</p>
                <p><strong>Weight:</strong> {userBMI.weight} lb</p>
                <p><strong>BMI Value:</strong> {userBMI.bmiValue}</p>
                <p><strong>Recorded At:</strong> {new Date(userBMI.recordedAt).toLocaleString()}</p>
                <button onClick={() => handleDelete(userBMI)}>Delete</button>
                <p>🏋️💪🏋️‍♀️</p>
              </div>
            ) ): (
            <p>Loading...</p>
          )}
        </div>
        <button onClick={()=>navigate("/addbmirecord")}>Add New Record</button>
        
        
        </>
        );
    

}