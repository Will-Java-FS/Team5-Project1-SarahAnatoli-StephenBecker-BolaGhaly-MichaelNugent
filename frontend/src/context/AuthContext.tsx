import { createContext, ReactNode, useEffect, useState } from 'react';
import { jwtDecode } from 'jwt-decode';

//enums
import { view } from '../enums/enums';

// Create a Context
export const AuthContext = createContext({
    isAuthenticated: false,
    role: view.GUEST,
    login: (jwt: string) => { console.log("This is a default function.", jwt) },
    logout: () => { }
});

interface AuthProviderProps {
    children: ReactNode; // children can be any valid React node (elements, text, etc.)
}

interface DecodedToken {
    role: string,
    uuid: string,
    userId: number,
    sub: string,
    iat: number,
    exp: number
}

function isValidRole(role: string): boolean {
    return Object.keys(view).includes(role as view);
}

// AuthProvider Component to wrap the app and manage state
export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [role, setRole] = useState<view>(view.GUEST)

    const login = (jwt: string) => {
        setIsAuthenticated(true);
        if (jwt) {
            localStorage.setItem('token', jwt);

            const decodedToken = jwtDecode<DecodedToken>(jwt);
            // console.log("Decoded Token: ", decodedToken);

            if(isValidRole(decodedToken.role)){
                setRole(view[decodedToken.role as keyof typeof view])
                console.log('Login successful');
            }
            else console.error("Client role provided in JWT not recognized: ", decodedToken.role)

        }
        else console.error("No JWT present.");
    };

    const logout = () => {
        setIsAuthenticated(false);
        localStorage.removeItem('token');
        setRole(view.GUEST);
    };

      // Check for JWT in localStorage on mount
    useEffect(()=>{
        //TODO: add token expiration check
        const token = localStorage.getItem('token');
        if(token){
            try{
                login(token)
            } catch (error){
                console.error('Invalid token');
                localStorage.removeItem('token');
            }
        }
    },[])

    return (
        <AuthContext.Provider value={{ isAuthenticated, role, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};
