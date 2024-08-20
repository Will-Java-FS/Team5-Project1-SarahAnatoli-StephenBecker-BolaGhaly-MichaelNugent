import { BrowserRouter, Routes, Route } from 'react-router-dom'

//Pages
import UserLogin from './pages/Auth/UserLogin'
import UserRegister from './pages/Auth/UserRegister'
import UserProfile from './pages/UserProfile'
import Logout from './pages/Auth/Logout'

//CSS
import './CSS/App.css'
import NavigationBar from './components/NavBar'
import Hero from './pages/Hero'

//context
import { AuthProvider } from './AuthContext'

function App() {




  return (
    <>
      <BrowserRouter>
        <AuthProvider>
          <Routes>
            <Route path='*' element={<NavigationBar />} />
          </Routes>
          <Routes>
            <Route path='/' element={<Hero />} />
            <Route path='/Register' element={<UserRegister />} />
            <Route path='/Login' element={<UserLogin />} />
            <Route path='/Profile' element={<UserProfile />} />
            <Route path='/Logout' element={<Logout />} />
          </Routes>
        </AuthProvider>
      </BrowserRouter>
    </>
  )
}

export default App
