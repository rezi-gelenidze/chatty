import React, {useEffect, useState} from "react";
import {Routes, Route, Navigate} from "react-router-dom";
import {ToastContainer} from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

import {AuthProvider} from "./contexts/authContext.tsx";
import {registerShowModalCallback} from "./services/httpService";

// Layout & UI Components
import ProtectedRoute from "./components/routes/protectedRoute.tsx";
import TokenExpireDialog from "./components/modals/tokenExpireDialog.tsx";

// Public Views
import LandingPage from "./pages/landingPage.tsx";
import PrivacyPage from "@/pages/misc/privacyPage.tsx";
import TermsPage from "@/pages/misc/termsPage.tsx";

// Auth Page Views
import LoginPage from "./pages/auth/loginPage.tsx";
import RegisterPage from "./pages/auth/registerPage.tsx";
import PasswordResetPage from "./pages/auth/passwordResetPage.tsx";
import PasswordResetConfirmPage from "./pages/auth/passwordResetConfirmPage.tsx";
import LogoutPage from "@/pages/auth/logoutPage.tsx";

// Chat Page Views
import ChatPage from "./pages/chat/chatPage.tsx";
import EmailVerificationNotify from "@/pages/auth/emailVerificationNotify.tsx";
import EmailVerificationConfirm from "@/pages/auth/emailVerificationConfirm.tsx";


const App: React.FC = () => {
    const [showTokenExpiryDialog, setShowTokenExpiryDialog] = useState(false);

    const closeDialog = () => setShowTokenExpiryDialog(false);
    const openDialog = () => setShowTokenExpiryDialog(true);

    useEffect(() => {
        // Register the token expiration callback for handling auth expiry
        registerShowModalCallback(openDialog);
    }, []);

    return (
        <AuthProvider>
            <ToastContainer position="bottom-right"/>

            {/* Token Expiry Dialog */}
            <TokenExpireDialog open={showTokenExpiryDialog} onClose={closeDialog}/>

            <Routes>
                {/* Public Routes */}
                <Route path="/" element={<LandingPage/>}/>
                <Route path="/privacy" element={<PrivacyPage/>}/>
                <Route path="/terms" element={<TermsPage/>}/>

                {/* Auth Routes */}
                <Route path="/login" element={<LoginPage/>}/>
                <Route path="/register" element={<RegisterPage/>}/>
                <Route element={<ProtectedRoute/>}>
                    <Route path="/logout" element={<LogoutPage/>}/>
                </Route>

                {/* Password Reset */}
                <Route path="/reset-password" element={<PasswordResetPage/>}/>
                <Route path="/reset-password-confirm" element={<PasswordResetConfirmPage/>}/>

                {/* Email Verification */}
                <Route path="/verify-email" element={<EmailVerificationNotify/>}/>
                <Route path="/verify-email-confirm/" element={<EmailVerificationConfirm/>}/>

                {/* Chat Routes */}
                <Route element={<ProtectedRoute/>}>
                    <Route path="/chat" element={<ChatPage/>}/>
                </Route>

                {/* Catch-All: Redirect to Landing */}
                <Route path="*" element={<Navigate to="/"/>}/>
            </Routes>
        </AuthProvider>
    );
};

export default App;
