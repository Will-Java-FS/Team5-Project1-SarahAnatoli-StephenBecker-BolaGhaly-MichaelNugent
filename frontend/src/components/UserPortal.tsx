import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";


//components
import CalorieMeter from "./CalorieMeter";

//CSS
import '../CSS/UserPortal.css';  // Import the external CSS file
import { jwtDecode } from "jwt-decode";

//images
import BMIChart from '../assets/images/BMIChart.png'
import FoodJournal from '../assets/images/FoodJournal.png'
import UserProfileIcon from '../assets/images/UserProfileIcon.png'
import ExerciseLogsIcon from '../assets/images/ExerciseLogsIcon.png'
import HearthStethoscopeIcon from '../assets/images/HeartStethoscopeIcon.png'

const Card: React.FC<{ title: string; to: string; imgSrc: string }> = ({ title, to, imgSrc }) => {
    return (
        <Link to={to} className="card-link">
            <div className="card">
                <img src={imgSrc} />
                <h3>{title}</h3>
            </div>
        </Link>
    );
};

interface DecodedToken {
    sub: string;
}

export default function UserPortal() {

    const [username, setUsername] = useState<string>("");
    const [calories, setCalories] = useState<number>(1000);
    const [caloriesGoal, setCaloriesGoal] = useState<number>(2000);


    useEffect(() => {
        const token = localStorage.getItem('token');
        if (token) {
            const decodedToken = jwtDecode<DecodedToken>(token);
            const tokenUsername = decodedToken.sub;
            console.log(decodedToken.sub)
            setUsername(tokenUsername)
        }
    }, [])

    //Get current calories and daily calorie goal from ReST request and display

    return (
        <div className='UserPortal'>
            <h1>Hello, {username}!</h1>
            <CalorieMeter currentCalories={calories} dailyCalorieGoal={caloriesGoal} />
            {/* <p>Cards will be here as links to features:</p>
            <ul>
                <li>User profile view and edit.</li>
                <li>Calorie goal setting.</li>
                <li>BMI updates.</li>
                <li>Exercise list view and exercise log view/updates.</li>
                <li>Food list view and food log view/update.</li>
            </ul> */}
            <Card title="BMI Records" to="/BMIRecords" imgSrc={HearthStethoscopeIcon} />
            <Card title="Food Logs" to="/FoodLogs" imgSrc={FoodJournal} />
            <Card title="Exercise Logs" to="/ExerciseLogs" imgSrc={ExerciseLogsIcon} />
            <Card title="User Profile" to="/Profile" imgSrc={UserProfileIcon} />
        </div>
    )
}