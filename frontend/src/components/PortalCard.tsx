import { Link } from "react-router-dom";

const PortalCard: React.FC<{ title: string; to: string; imgSrc: string }> = ({ title, to, imgSrc }) => {
    return (
        <Link to={to} className="card-link">
            <div className="card">
                <img src={imgSrc} />
                <h3>{title}</h3>
            </div>
        </Link>
    );
};

export default PortalCard;