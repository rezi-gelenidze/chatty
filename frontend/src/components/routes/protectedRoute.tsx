import React, { useContext } from "react";
import { Navigate, Outlet, useLocation } from "react-router-dom";
import { AuthContext } from "../../contexts/authContext.tsx";
import LoadingPage from "../../pages/misc/loadingPage.tsx";

const ProtectedRoute: React.FC = () => {
    const authContext = useContext(AuthContext);
    const location = useLocation();

    // Ensure the context is available
    if (!authContext) {
        return <LoadingPage />;
    }

    const { currentUser, isLoading } = authContext;

    // Show a loading screen while authentication status is being determined
    if (isLoading) return <LoadingPage />;

    // Redirect to login page if not authenticated
    if (!currentUser) {
        const nextUrl = location.pathname + location.search;
        return <Navigate to={`/login?next=${encodeURIComponent(nextUrl)}`} replace />;
    }

    // If authenticated, render the nested route components
    return <Outlet />;
};

export default ProtectedRoute;
