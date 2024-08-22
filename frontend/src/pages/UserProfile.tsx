import axiosInstance from "../axios/AxiosInstance"
import { Link } from 'react-router-dom';
import { useEffect, useRef, useState } from "react"

//CSS
import '../CSS/UserProfile.css'

// //Icon
// import BackArrow from '../assets/images/BackArrow.png'



export default function UserProfile() {

  const [userData, setUserData] = useState({ username: undefined, email: undefined, firstName: undefined, lastName: undefined, gender: undefined, age: undefined, height: undefined, weight: undefined, dailyCalorieGoal: undefined });
  const [editMode, setEditMode] = useState(false);
  const [dailyCaloricIntake, setDailyCaloricIntake] = useState(0);
  const [weeklyCaloricIntake, setWeeklyCaloricIntake] = useState(0);

  useEffect(() => {

    const getUserData = async () => {
      try {
        const response = await axiosInstance.get("/user");

        if (response === null) throw 'null result'

        setUserData(response.data);

      } catch (error) {
        console.error('Error in UserProfile data fetch: ', error)
      }
    }
    getUserData();

  }, [])

  useEffect(() => {
    const getDailyCaloricIntake = async () => {
      const response = await axiosInstance.get(
        "http://localhost:8080/user/calories/daily"
        );
        setDailyCaloricIntake(response.data);
    }
    getDailyCaloricIntake();
  })

  useEffect(() => {
    const getWeeklyCaloricIntake = async () => {
      const response = await axiosInstance.get(
        "http://localhost:8080/user/calories/weekly"
        );
        setWeeklyCaloricIntake(response.data);
    }
    getWeeklyCaloricIntake();
  })

  const usernameRef = useRef<HTMLInputElement>(null);
  const emailRef = useRef<HTMLInputElement>(null);
  const firstNameRef = useRef<HTMLInputElement>(null);
  const lastNameRef = useRef<HTMLInputElement>(null);
  const genderRef = useRef<HTMLInputElement>(null);
  const ageRef = useRef<HTMLInputElement>(null);
  const heightRef = useRef<HTMLInputElement>(null);
  const weightRef = useRef<HTMLInputElement>(null);
  const dailyCalorieGoalRef = useRef<HTMLInputElement>(null);


  const save = async () => {

    try {
      const response = await axiosInstance.patch("/user", {
        username: usernameRef.current!.value,
        email: emailRef.current!.value,
        firstName: firstNameRef.current!.value,
        lastName: lastNameRef.current!.value,
        age: ageRef.current!.value,
        weight: weightRef.current!.value,
        height: heightRef.current!.value,
        gender: genderRef.current!.value,
        dailyCalorieGoal: dailyCalorieGoalRef.current!.value
      })
      console.log(response)
      setUserData(response.data)

    } catch (error) {
      console.error("Failed to update user profile information:", error)
    }

    setEditMode(false);
  }

  return (
    <div className="UserProfile">
      <header>
        {/* <img src={BackArrow} className="backArrow" /> */}
        <h1>User Profile</h1>
      </header>


      {/* Card for username and email */}
      <div className="profile-card">
        <h3>Account Information</h3>
        <div className="profile-info">
          <p><strong>Username:</strong> {editMode ? <input type="text" defaultValue={userData.username} ref={usernameRef} /> : userData.username}</p>
          <p><strong>Email:</strong> {editMode ? <input type="text" defaultValue={userData.email} ref={emailRef} /> : userData.email}</p>
        </div>
      </div>

      {/* Card for personal information */}
      <div className="profile-card">
        <h3>Personal Information</h3>
        <div className="profile-info">
          <p><strong>First Name:</strong> {editMode ? <input type="text" defaultValue={userData.firstName} ref={firstNameRef} /> : userData.firstName}</p>
          <p><strong>Last Name:</strong> {editMode ? <input type="text" defaultValue={userData.lastName} ref={lastNameRef} /> : userData.lastName}</p>
          <p><strong>Gender:</strong> {editMode ? <input type="text" defaultValue={userData.gender} ref={genderRef} /> : userData.gender}</p>
          <p><strong>Age:</strong> {editMode ? <input type="text" defaultValue={userData.age} ref={ageRef} /> : userData.age}</p>
          <p><strong>Height:</strong> {editMode ? <input type="text" defaultValue={userData.height} ref={heightRef} /> : userData.height} cm</p>
          <p><strong>Weight:</strong> {editMode ? <input type="text" defaultValue={userData.weight} ref={weightRef} /> : userData.weight} kg</p>
        </div>
      </div>

      {/* Card for health and fitness goals */}
      <div className="profile-card">
        <h3>Health & Fitness Goals</h3>
        <div className="profile-info">
          <p><strong>Daily Calorie Goal:</strong> {editMode ? <input type="text" defaultValue={userData.dailyCalorieGoal} ref={dailyCalorieGoalRef} /> : userData.dailyCalorieGoal} kcal</p>
        </div>
      </div>

      {/* Card for food log */}
      <div className="profile-card">
        <h3>Food Log</h3>
        <div className="profile-info">
          <p><strong>Daily Caloric Intake:</strong> {dailyCaloricIntake} kcal</p>
          <p><strong>Weekly Caloric Intake:</strong> {weeklyCaloricIntake} kcal</p>
          <ul className="foodItem-links">
        <li>
          <Link to="/FoodLogs">Access your food log</Link>
        </li>
      </ul>
        </div>
      </div>
      <div className="userProfileButtons">
        {editMode
          ? <><button onClick={save}>Save</button><button onClick={() => setEditMode(false)}>Cancel</button></>
          : <button onClick={() => setEditMode(true)}>Edit</button>}
      </div>

    </div>
  )
}
