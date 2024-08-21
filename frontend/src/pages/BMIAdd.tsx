
import { useEffect, useRef, useState } from "react";
import axiosInstance from "../axios/AxiosInstance";

interface BMIRecord {
  id: number | undefined;
  height: number | undefined;
  weight: number | undefined;
  bmiValue: number | undefined;
  recordedAt: string | undefined;
}

export default function AddBMI() {

    
  const [userBMI, setUserBMI] = useState<BMIRecord[]>([]);
  const [newBMI, setNewBMI] = useState<BMIRecord>({
    id: undefined,
    height: undefined,
    weight: undefined,
    bmiValue: undefined,
    recordedAt: undefined,
    });

    const heightRef = useRef<HTMLInputElement>(null);
    const weightRef = useRef<HTMLInputElement>(null);

    const save = async () => {

        try {
          const response = await axiosInstance.post("/bmirecords/addbmirecord", {
            height: heightRef.current!.value,
            weight: weightRef.current!.value,
          })
          console.log(response)
          setUserBMI(response.data)
    
        } catch (error) {
          console.error("Failed to update user BMI information:", error)
        }
      }

  
  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setNewBMI({...newBMI,[name]: name === "height" || name === "weight" || name === "bmiValue" ? parseFloat(value) : value,});};

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const newRecord = {
      ...newBMI,
      id: userBMI.length + 1,
      recordedAt: new Date().toISOString(),
    };

    setUserBMI([...userBMI, newRecord]);

    // Clear the form
    setNewBMI({
      id: undefined,
      height: undefined,
      weight: undefined,
      bmiValue: undefined,
      recordedAt: undefined,
    });
  };

  return (
        <>
      <h3>Add a New BMI Record</h3>
      <form onSubmit={handleSubmit}>
        <div>
          <label>
            Height (cm):
            <input
              type="text"
              name="height"
              ref={heightRef}
              onChange={handleInputChange}
              required
            />
          </label>
        </div>
        <div>
          <label>
            Weight (kg):
            <input
              type="text"
              name="weight"
              ref={weightRef}
              onChange={handleInputChange}
              required
            />
          </label>
        </div>

        <button type="submit" onClick={save}>Add BMI Record</button>
      </form>
      </>
  );
}
