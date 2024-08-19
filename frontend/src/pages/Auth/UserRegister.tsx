import { useRef, useState } from 'react';
import axios from 'axios';

const UserRegister = () => {
    const usernameRef = useRef<HTMLInputElement>(null);
    const emailRef = useRef<HTMLInputElement>(null);
    const passwordRef = useRef<HTMLInputElement>(null);
    const [errorMessage, setErrorMessage] = useState<string>('');
    const [successMessage, setSuccessMessage] = useState<string>('');

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        try {
            //replace this web url with an environment variable so as to support both development and production instances
            const response = await axios.post('http://localhost:8080/auth/register', {
                username: usernameRef.current!.value,
                email: emailRef.current!.value,
                password: passwordRef.current!.value,
            });

            setSuccessMessage('Registration successful! You can now log in.');
            setErrorMessage('');  // Clear any previous error message

            console.log('Registration successful', response.data);
            // Assuming the JWT is returned in the response
            const token = response.data.token;
            localStorage.setItem('token', token);  // Store token in localStorage


        } catch (error) {
            console.error('Error during registration:', error);
            setErrorMessage('Registration failed. Please try again.');
            setSuccessMessage('');  // Clear any previous success message
        }
    };

    return (
        <div className="register-container">
            <h2>User Registration</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="username">Username:</label>
                    <input
                        type="text"
                        id="username"
                        ref={usernameRef}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="email">Email:</label>
                    <input
                        type="email"
                        id="email"
                        ref={emailRef}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password:</label>
                    <input
                        type="password"
                        id="password"
                        ref={passwordRef}
                        required
                    />
                </div>
                {errorMessage && <p className="error">{errorMessage}</p>}
                {successMessage && <p className="success">{successMessage}</p>}
                <button type="submit">Register</button>
            </form>
        </div>
    );
};

export default UserRegister;
