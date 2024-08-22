import React, { useEffect, useState } from 'react';
import '../CSS/CalorieMeter.css'; // External CSS file
import axiosInstance from '../axios/AxiosInstance';


const CalorieMeter: React.FC = () => {

    const [calories, setCalories] = useState<number>(1000);
    const [caloriesGoal, setCaloriesGoal] = useState<number>(2000);

    const percentage = Math.min((calories / caloriesGoal) * 100, 100);

    useEffect(() => {

        const getCalorieMeter = async () => {
            try {
                const response = await axiosInstance.get("/user/calorieMeter");

                if (response.status === 200) {

                    //debug
                    // console.log('Data received', response)

                    //handle empty data
                    const retrievedCalories = response.data.dailyCalorieIntake;
                    if (retrievedCalories < 0) throw Error("Calorie intake is negative")
                    else setCalories(retrievedCalories);

                    const retrievedDailyCalorieGoal = response.data.dailyCalorieGoal;
                    if (retrievedDailyCalorieGoal !== null) setCaloriesGoal(retrievedDailyCalorieGoal);

                }

            } catch (error) {
                console.error("Failed to retrieve calorie meter data.", error)
            }
        }

        getCalorieMeter();
    })

    return (
        <div className="calorie-meter">
            <div className="calorie-bar" style={{ width: `${percentage}%` }}></div>
            <p className='calorie-text'>{calories} / {caloriesGoal} kcal</p>
        </div>
    );
};

export default CalorieMeter;
