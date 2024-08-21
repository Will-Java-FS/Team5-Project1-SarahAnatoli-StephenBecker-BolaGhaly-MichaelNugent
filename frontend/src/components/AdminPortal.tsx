

//Component
import UserPortalCards from "./UserPortalCards";
import PortalCard from './PortalCard' //To make admin only links

//images
import UsersIcon from '../assets/images/UsersIcon.png'

//CSS
import '../CSS/AdminPortal.css'
import { useEffect, useState } from "react";
import { jwtDecode } from "jwt-decode";


interface DecodedToken {
    sub: string;
}

export default function AdminPortal() {

    const [username, setUsername] = useState<string>("");

    useEffect(() => {
        const token = localStorage.getItem('token');
        if (token) {
            const decodedToken = jwtDecode<DecodedToken>(token);
            const tokenUsername = decodedToken.sub;
            console.log(decodedToken.sub)
            setUsername(tokenUsername)
        }
    }, [])

    return (
        <div className='AdminPortal'>
            <h1>Welcome, ADMIN {username}</h1>
            <UserPortalCards />
            <PortalCard title="Modify Users" to="/Users" imgSrc={UsersIcon} />
            <PortalCard title="Modify Foods" to="/Foods" imgSrc={UsersIcon} />
            <PortalCard title="Modify Exercises" to="/Exercises" imgSrc={UsersIcon} />
        </div>

    )
}
