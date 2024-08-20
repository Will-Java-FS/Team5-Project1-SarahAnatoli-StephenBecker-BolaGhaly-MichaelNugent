import { useContext } from 'react'

//Components
import Hero from '../components/Hero'
import UserPortal from '../components/UserPortal'
import AdminPortal from '../components/AdminPortal'

//enums
import { view } from '../enums/enums'

//context
import { AuthContext } from '../context/AuthContext'

const viewComponent: { [key in view]: JSX.Element } = {
    [view.GUEST]: <Hero />,
    [view.USER]: <UserPortal />,
    [view.ADMIN]: <AdminPortal />
}

export default function Home() {

    const { role } = useContext(AuthContext);

    return (
        <div className="Home">
            {viewComponent[role]}
        </div>
    )
}
