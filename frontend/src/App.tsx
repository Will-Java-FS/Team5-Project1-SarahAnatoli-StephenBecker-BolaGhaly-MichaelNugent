import { BrowserRouter, Routes, Route } from 'react-router-dom'

//Pages
import UserLogin from './pages/Auth/UserLogin'
import UserRegister from './pages/Auth/UserRegister'
import UserProfile from './pages/UserProfile'

//CSS
import './CSS/App.css'
import NavigationBar from './components/NavBar'
import Hero from './pages/Hero'

//enums
import { view } from './enums'

//context
import { AuthProvider } from './AuthContext'

function App() {




  return (
    <>
      <BrowserRouter>
        <AuthProvider>
          <Routes>
            <Route path='*' element={<NavigationBar view={view.guest} />} />
          </Routes>
          <Routes>
            <Route path='/' element={<Hero />} />
            <Route path='/Register' element={<UserRegister />} />
            <Route path='/Login' element={<UserLogin />} />
            <Route path='/Profile' element={<UserProfile />} />
          </Routes>
        </AuthProvider>
      </BrowserRouter>
    </>
  )
}

export default App
