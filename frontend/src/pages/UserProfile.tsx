import { useEffect, useState } from "react"
import axiosInstance from "../AxiosInstance"
import { Link } from 'react-router-dom';

import '../CSS/UserProfile.css'

export default function UserProfile() {

  const [userData, setUserData] = useState({ username: null, email: null, firstName: null, lastName: null, gender: null, age: null, height: null, weight: null, dailyCalorieGoal: null });

  useEffect(() => {

    const getUserData = async () => {
      try {
        const response = await axiosInstance.get("/user");

        if (response === null) throw 'null result'

        setUserData(response.data);

        console.log(response.data)

      } catch (error) {
        console.error('Error in UserProfile data fetch: ', error)
      }
    }
    getUserData();

  }, [])


  return (
    <div className="UserProfile">
      <h1>User Profile</h1>
      {/* <p>{userData}</p> */}
      {/* Card for username and email */}
      <div className="profile-card">
        <h3>Account Information</h3>
        <div className="profile-info">
          <p><strong>Username:</strong> {userData.username}</p>
          <p><strong>Email:</strong> {userData.email}</p>
        </div>
      </div>

      {/* Card for personal information */}
      <div className="profile-card">
        <h3>Personal Information</h3>
        <div className="profile-info">
          <p><strong>First Name:</strong> {userData.firstName}</p>
          <p><strong>Last Name:</strong> {userData.lastName}</p>
          <p><strong>Gender:</strong> {userData.gender}</p>
          <p><strong>Age:</strong> {userData.age}</p>
          <p><strong>Height:</strong> {userData.height} cm</p>
          <p><strong>Weight:</strong> {userData.weight} kg</p>
        </div>
      </div>

      {/* Card for health and fitness goals */}
      <div className="profile-card">
        <h3>Health & Fitness Goals</h3>
        <div className="profile-info">
          <p><strong>Daily Calorie Goal:</strong> {userData.dailyCalorieGoal} kcal</p>
        </div>
      </div>

      {/* Card for food log */}
      <div className="profile-card">
        <h3>Food Log</h3>
        <div className="profile-info">
          <p><strong>Daily Caloric Intake:</strong> {} kcal</p>
          <p><strong>Weekly Caloric Intake:</strong> {} kcal</p>
          <ul className="foodItem-links">
        <li>
          <Link to="/FoodItem">Access your food log</Link>
        </li>
      </ul>
        </div>
      </div>
    </div>
  )
}
