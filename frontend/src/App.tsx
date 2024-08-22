import { BrowserRouter, Routes, Route } from "react-router-dom";

//Pages
import UserLogin from "./pages/Auth/UserLogin";
import UserRegister from "./pages/Auth/UserRegister";
import UserProfile from "./pages/UserProfile";
import Logout from "./pages/Auth/Logout";
import Home from "./pages/Home";
import { AdminUser } from "./pages/Auth/AdminUser";
import Pricing from "./pages/Pricing";
import ContactUs from "./pages/Contact";
import FoodLog from './pages/FoodItem'

//CSS
import "./CSS/App.css";
import NavigationBar from "./components/NavBar";

//context
import { AuthProvider } from './context/AuthContext'


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
            <Route path='/FoodLogs' element={<FoodLog />} />
            <Route path="/Login" element={<UserLogin />} />
            <Route path="/Profile" element={<UserProfile />} />
            <Route path="/Logout" element={<Logout />} />
            <Route path="/Admin/Users" element={<AdminUser />} />
            <Route path="/Pricing" element={<Pricing />} />
            <Route path="/Contact" element={<ContactUs />} />
            {/* <Route path='/Portal' element={<Portal />} /> */}
          </Routes>
        </AuthProvider>
      </BrowserRouter>
    </>
  );
}

export default App;
