import { useEffect, useState } from "react";


//components
import CalorieMeter from "./CalorieMeter";
import UserPortalCards from "./UserPortalCards";

//CSS
import '../CSS/UserPortal.css';
import { jwtDecode } from "jwt-decode";



interface DecodedToken {
    sub: string;
}

export default function UserPortal() {

    const [username, setUsername] = useState<string>("");


    useEffect(() => {
        const token = localStorage.getItem('token');
        if (token) {
            const decodedToken = jwtDecode<DecodedToken>(token);
            const tokenUsername = decodedToken.sub;

            //debug
            // console.log(decodedToken.sub)
            
            setUsername(tokenUsername)
        }
    }, [])


    //Get current calories and daily calorie goal from ReST request and display

    return (
        <div className='UserPortal'>
            <h1>Hello, {username}!</h1>
            <CalorieMeter />
            <UserPortalCards />
        </div>
    )
}