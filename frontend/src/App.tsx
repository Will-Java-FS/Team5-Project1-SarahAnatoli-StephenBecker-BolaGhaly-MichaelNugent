import { BrowserRouter, Routes, Route } from 'react-router-dom'

//Pages
import UserLogin from './pages/Auth/UserLogin'
import UserRegister from './pages/Auth/UserRegister'
import UserProfile from './pages/UserProfile'
import Logout from './pages/Auth/Logout'
import Home from './pages/Home'

//CSS
import "./CSS/App.css";
import NavigationBar from "./components/NavBar";

//context

import BMIList from './pages/BMIList'
import { AuthProvider } from './context/AuthContext'
import BMIAdd from './pages/AddBMI'
import ExerciseLogsList from './pages/ExerciseLogsList'
import AddExerciseLogs from './pages/AddExerciseLogs'
import ExerciseList from './pages/ExerciseList'
import AddExercise from './pages/AddExercise'


function App() {




  return (
    <>
      <BrowserRouter>
        <AuthProvider>
          <Routes>
            <Route path="*" element={<NavigationBar />} />
          </Routes>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/Home" element={<Home />} />
            <Route path="/Register" element={<UserRegister />} />
            
            <Route path="/Login" element={<UserLogin />} />
            <Route path="/Profile" element={<UserProfile />} />
            <Route path="/Logout" element={<Logout />} />
            
            <Route path='/' element={<Home />} />
            <Route path='/Home' element={<Home />} />
            <Route path='/Register' element={<UserRegister />} />
            <Route path='/Login' element={<UserLogin />} />
            <Route path='/Profile' element={<UserProfile />} />
            <Route path='/Logout' element={<Logout />} />
            <Route path='/bmirecords' element={<BMIList/>}/>
            <Route path='/addbmirecord' element={<BMIAdd/>}/>
            <Route path='/exerciselogs' element={<ExerciseLogsList/>}/>
            <Route path='/addexerciselog' element={<AddExerciseLogs/>}/>
            <Route path='/exercises' element={<ExerciseList/>}/>
            <Route path='/addexercise' element={<AddExercise/>}/>
            {/* <Route path='/Portal' element={<Portal />} /> */}
          </Routes>
        </AuthProvider>
      </BrowserRouter>
    </>
  )
}

export default App
