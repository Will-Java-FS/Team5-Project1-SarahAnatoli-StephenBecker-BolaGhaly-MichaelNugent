import { BrowserRouter, Routes, Route } from 'react-router-dom'

//Pages
import UserLogin from './pages/Auth/UserLogin'
import UserRegister from './pages/Auth/UserRegister'
import UserProfile from './pages/UserProfile'

//CSS
import './CSS/App.css'
import NavigationBar from './components/NavBar'

function App() {

  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path='*' element={<NavigationBar />} />
        </Routes>
        <Routes>
          <Route path='/Register' element={<UserRegister />} />
          <Route path='/Login' element={<UserLogin />} />
          <Route path='/Profile' element={<UserProfile />} />
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
