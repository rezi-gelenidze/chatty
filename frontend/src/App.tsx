import { Routes, Route } from "react-router-dom";

import LandingPage from "./pages/LandingPage.tsx";

// Auth Page Views
import LoginPage from "./pages/auth/LoginPage.tsx";
import RegisterPage from "./pages/auth/RegisterPage.tsx";
import PasswordResetPage from "./pages/auth/PasswordResetPage.tsx";
import PasswordResetConfirmPage from "./pages/auth/PasswordResetConfirmPage.tsx";

function App() {
    return (
        <div>
            <Routes>
                <Route path="/" element={<LandingPage />} />
                <Route path="/login" element={<LoginPage />} />
                <Route path="/register" element={<RegisterPage />} />
                <Route path="/reset-password" element={<PasswordResetPage />} />
                <Route path="/reset-password-confirm" element={<PasswordResetConfirmPage />} />


                <Route path="*" element={<div>404</div>} />
            </Routes>
        </div>
    );
}

export default App;
