import React from 'react';
import '../CSS/CalorieMeter.css'; // External CSS file

interface CalorieMeterProps {
    currentCalories: number;
    dailyCalorieGoal: number;
}

const CalorieMeter: React.FC<CalorieMeterProps> = ({ currentCalories, dailyCalorieGoal }) => {
    const percentage = Math.min((currentCalories / dailyCalorieGoal) * 100, 100);

    return (
        <div className="calorie-meter">
            <div className="calorie-bar" style={{ width: `${percentage}%` }}></div>
            <p className='calorie-text'>{currentCalories} / {dailyCalorieGoal} kcal</p>
        </div>
    );
};

export default CalorieMeter;
