import { useRef, useState } from "react";
import axiosInstance from "../axios/AxiosInstance";
import { useNavigate } from "react-router-dom";

interface Exercise {
  id: number | undefined;
  name: string | undefined;
  caloriesBurnedPerMinute: number | undefined;
}

export default function AddExercise() {
  const navigate = useNavigate();
  const [exercise, setExercise] = useState<Exercise[]>([]);
  const [newExercise, setNewExercise] = useState<Exercise>({
    id: undefined,
    name: undefined,
    caloriesBurnedPerMinute: undefined,
  });

  const nameRef = useRef<HTMLInputElement>(null);
  const caloriesBurnedPerMinuteRef = useRef<HTMLInputElement>(null);

  const save = async () => {
    try {
      const response = await axiosInstance.post("/exercises", {
        name: nameRef.current!.value,
        caloriesBurnedPerMinute: parseFloat(caloriesBurnedPerMinuteRef.current!.value),
      });
      console.log(response);
      setExercise([...exercise, response.data]);
      navigate("/exercises");
    } catch (error) {
      console.error("Failed to add exercise:", error);
    }
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setNewExercise({
      ...newExercise,
      [name]: name === "caloriesBurnedPerMinute" ? parseFloat(value) : value,
    });
  };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    save();
  };

  return (
    <>
      <form onSubmit={handleSubmit}>
        <div>
          <label>
            Exercise:
            <input
              type="text"
              name="name"
              ref={nameRef}
              onChange={handleInputChange}
              required
            />
          </label>
        </div>
        <div>
          <label>
            Calories Burned Per Minute:
            <input
              type="number"
              name="caloriesBurnedPerMinute"
              ref={caloriesBurnedPerMinuteRef}
              onChange={handleInputChange}
              required
            />
          </label>
        </div>
        <button >Add Exercise</button>
      </form>
      <button onClick={() => navigate("/exercises")}>Cancel</button>
    </>
  );
}
