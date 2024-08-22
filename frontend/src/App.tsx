import { BrowserRouter, Routes, Route } from 'react-router-dom'

//Pages
import UserLogin from './pages/Auth/UserLogin'
import UserRegister from './pages/Auth/UserRegister'
import UserProfile from './pages/UserProfile'
import Logout from './pages/Auth/Logout'
import Home from './pages/Home'
import BMIList from './pages/BMIList'
import BMIAdd from './pages/AddBMI'
import ExerciseLogsList from './pages/ExerciseLogsList'
import AddExerciseLogs from './pages/AddExerciseLogs'
import ExerciseList from './pages/ExerciseList'
import AddExercise from './pages/AddExercise'
import FoodLog from './pages/FoodItem'

//components
import NavigationBar from "./components/NavBar";

//CSS
import "./CSS/App.css";

//context
import { AuthProvider } from './context/AuthContext'
import Pricing from './pages/Pricing'
import ContactUs from './pages/Contact'



function App() {




  return (
    <>
      <BrowserRouter>
        <AuthProvider>
          <Routes>
            <Route path="*" element={<NavigationBar />} />
          </Routes>
          <Routes>
            <Route path='/' element={<Home />} />
            <Route path='/Home' element={<Home />} />
            <Route path='/Register' element={<UserRegister />} />
            <Route path='/Login' element={<UserLogin />} />
            <Route path='/Profile' element={<UserProfile />} />
            <Route path='/Pricing' element={<Pricing />} />
            <Route path='/Contact' element={<ContactUs />} />
            <Route path='/Logout' element={<Logout />} />
            <Route path='/FoodLogs' element={<FoodLog />} />
            <Route path='/bmirecords' element={<BMIList />} />
            <Route path='/addbmirecord' element={<BMIAdd />} />
            <Route path='/exerciselogs' element={<ExerciseLogsList />} />
            <Route path='/exercises' element={<ExerciseList />} />
            <Route path='/addexerciselog' element={<AddExerciseLogs />} />
            <Route path='/addexercise' element={<AddExercise />} />
          </Routes>
        </AuthProvider>
      </BrowserRouter>
    </>
  )
}

export default App
