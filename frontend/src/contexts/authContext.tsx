import React, {createContext, useState, useEffect, ReactNode} from "react";
import {isExpired, decodeToken} from "react-jwt";
import {refreshToken} from "../services/httpService";

// Define types for authentication context
interface User {
    id: number;
    email: string;
}

interface AuthContextType {
    currentUser: User | null;
    setCurrentUser: React.Dispatch<React.SetStateAction<User | null>>;
    updateUserState: () => Promise<void>;
    isLoading: boolean;
    isAuthenticated: boolean;
}

// Create an authentication context
export const AuthContext = createContext<AuthContextType | null>(null);

interface AuthProviderProps {
    children: ReactNode;
}

/**
 * Provides authentication context for the application.
 *
 * @param {AuthProviderProps} props - The component props.
 * @returns {JSX.Element} The rendered component.
 */
export const AuthProvider: React.FC<AuthProviderProps> = ({children}) => {
    const [currentUser, setCurrentUser] = useState<User | null>(null);
    const [isLoading, setIsLoading] = useState<boolean>(true);

    const updateUserState = async () => {
        try {
            setIsLoading(true);
            let token = localStorage.getItem("jwt-access");

            if (token && isExpired(token)) {
                token = await refreshToken();
            }

            if (token) {
                const decodedToken = decodeToken<{ user_id: number; email: string }>(token);
                if (decodedToken) {
                    setCurrentUser({
                        id: decodedToken.user_id,
                        email: decodedToken.email,
                    });
                }
            } else {
                setCurrentUser(null);
            }
        } catch (error) {
            console.error("Error updating user state:", error);
        } finally {
            setIsLoading(false);
        }
    };

    useEffect(() => {
        updateUserState();
    }, []);

    // Infer isAuthenticated from currentUser
    const isAuthenticated = currentUser !== null;

    return (
        <AuthContext.Provider value={{currentUser, setCurrentUser, updateUserState, isLoading, isAuthenticated}}>
            {children}
        </AuthContext.Provider>
    );
};
