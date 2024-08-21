import { useContext, useState } from "react";
import axios from "axios";

import { AuthContext } from "../../context/AuthContext";
import { useNavigate } from "react-router-dom";

//CSS
import '../../CSS/UserLogin.css'

export default function UserLogin() {

    const [username, setUsername] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const [errorMessage, setErrorMessage] = useState<string>('');

    const { login } = useContext(AuthContext);

    const navigate = useNavigate();

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        //Prevents page reload
        event.preventDefault();

        try {
            //replace this web url with an environment variable so as to support both development and production instances
            const response = await axios.post('http://localhost:8080/auth/authenticate', { username, password });

            // Assuming the JWT is returned in the response
            login(response.data.token);

            // Redirect or perform other post-login actions
            navigate("/");
            // console.log(response.data);

        } catch (error) {
            console.error('Error during login:', error);
            setErrorMessage('Login failed. Please check your username and password.');
        }
    };

    return (
        <div className="UserLogin">
            <h1>Login</h1>

            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="username">Username:</label>
                    <input
                        type="text"
                        id="username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password:</label>
                    <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                {errorMessage && <p className="error">{errorMessage}</p>}
                <button type="submit">Login</button>
            </form>
        </div>
    )
}


