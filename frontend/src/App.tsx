import { BrowserRouter, Routes, Route } from 'react-router-dom'

//Pages
import UserLogin from './pages/Auth/UserLogin'
import UserRegister from './pages/Auth/UserRegister'
import UserProfile from './pages/UserProfile'

//CSS
import './css/App.css'

function App() {

  return (
    <>
      <BrowserRouter>
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
