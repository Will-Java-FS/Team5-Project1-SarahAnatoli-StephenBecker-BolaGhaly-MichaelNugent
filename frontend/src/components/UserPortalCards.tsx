//components
import PortalCard from './PortalCard'

//images
import FoodJournal from '../assets/images/FoodJournal.png'
import UserProfileIcon from '../assets/images/UserProfileIcon.png'
import ExerciseLogsIcon from '../assets/images/ExerciseLogsIcon.png'
import HearthStethoscopeIcon from '../assets/images/HeartStethoscopeIcon.png'

import '../CSS/UserPortalCards.css'

export default function UserPortalCards() {
    return (
        <div className='UserPortalCards'>
            <PortalCard title="BMI Records" to="/bmirecords" imgSrc={HearthStethoscopeIcon} />
            <PortalCard title="Food Logs" to="/FoodLogs" imgSrc={FoodJournal} />
            <PortalCard title="Exercise Logs" to="/ExerciseLogs" imgSrc={ExerciseLogsIcon} />
            <PortalCard title="User Profile" to="/Profile" imgSrc={UserProfileIcon} />
        </div>
    )
}
