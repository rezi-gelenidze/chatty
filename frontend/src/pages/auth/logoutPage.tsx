import { useEffect, useContext } from "react";
import { Navigate } from "react-router-dom";
import { AuthContext } from "../../contexts/authContext.tsx";

const LogoutPage: React.FC = () => {
    const { setCurrentUser } = useContext(AuthContext);

    useEffect(() => {
        localStorage.removeItem("jwt-access");
        localStorage.removeItem("jwt-refresh");
        setCurrentUser(null);
    }, [setCurrentUser]); // Dependency array ensures function reference stability

    return <Navigate to="/" replace />;
};

export default LogoutPage;
