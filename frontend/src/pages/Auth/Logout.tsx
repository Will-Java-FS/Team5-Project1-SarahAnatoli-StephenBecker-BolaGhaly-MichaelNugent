import { useContext, useEffect } from "react"
import { AuthContext } from "../../AuthContext"
import { useNavigate } from "react-router-dom";


export default function Logout() {

    const { logout } = useContext(AuthContext);

    const navigate = useNavigate();

    useEffect(() => {
        logout();
        navigate('/');
    }, [])

    return (
        <div className="Logout">

        </div>
    )
}
