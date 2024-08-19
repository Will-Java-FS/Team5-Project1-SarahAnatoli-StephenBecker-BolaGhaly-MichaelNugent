import { createContext, ReactNode, useState } from 'react';

// Create a Context
export const AuthContext = createContext({
    isAuthenticated: false, login: (jwt:string) => { }, logout: () => { }
});

interface AuthProviderProps {
    children: ReactNode; // children can be any valid React node (elements, text, etc.)
}

// AuthProvider Component to wrap the app and manage state
export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
    const [isAuthenticated, setIsAuthenticated] = useState(false);

    const login = (jwt: string) => {
        setIsAuthenticated(true);
        // Save JWT to localStorage or handle as needed
        localStorage.setItem('token', jwt);  // Store token in localStorage
        console.log('Login successful');
    };

    const logout = () => {
        setIsAuthenticated(false);
        // Remove JWT from localStorage
    };

    return (
        <AuthContext.Provider value={{ isAuthenticated, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};
