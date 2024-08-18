import { Link } from 'react-router-dom';
import '../CSS/NavBar.css';

const NavigationBar = () => {
  return (
    <nav className="navbar">
      <div className="navbar-logo">
        <Link to="/">Calorie Tracker</Link>
      </div>
      <ul className="navbar-links">
        <li>
          <Link to="/Profile">Profile</Link>
        </li>
        <li>
          <Link to="/Register">Register</Link>
        </li>
        <li>
          <Link to="/Login">Login</Link>
        </li>
      </ul>
    </nav>
  );
}

export default NavigationBar;
