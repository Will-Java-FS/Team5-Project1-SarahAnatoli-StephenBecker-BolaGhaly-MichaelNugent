import { BrowserRouter, Routes, Route } from 'react-router-dom'

//Pages
import UserLogin from './pages/Auth/UserLogin'
import UserRegister from './pages/Auth/UserRegister'
import UserProfile from './pages/UserProfile'
import Logout from './pages/Auth/Logout'
import Home from './pages/Home'

//CSS
import './CSS/App.css'
import NavigationBar from './components/NavBar'

//context

import BMIList from './pages/BMIList'
import { AuthProvider } from './context/AuthContext'
import BMIAdd from './pages/AddBMI'

function App() {




  return (
    <>
      <BrowserRouter>
        <AuthProvider>
          <Routes>
            <Route path='*' element={<NavigationBar />} />
          </Routes>
          <Routes>
            <Route path='/' element={<Home />} />
            <Route path='/Home' element={<Home />} />
            <Route path='/Register' element={<UserRegister />} />
            <Route path='/Login' element={<UserLogin />} />
            <Route path='/Profile' element={<UserProfile />} />
            <Route path='/Logout' element={<Logout />} />
            <Route path='/bmirecords' element={<BMIList/>}/>
            <Route path='/addbmirecord' element={<BMIAdd/>}/>
            {/* <Route path='/Portal' element={<Portal />} /> */}
          </Routes>
        </AuthProvider>
      </BrowserRouter>
    </>
  )
}

export default App
