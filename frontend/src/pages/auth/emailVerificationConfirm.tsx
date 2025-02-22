import React, {useEffect, useState} from "react";
import {useSearchParams, useNavigate} from "react-router-dom";
import {toast} from "react-toastify";

import authService from "../../services/authService.ts";

import LoadingPage from "../../pages/misc/loadingPage.tsx";

const EmailVerificationConfirm: React.FC = () => {
    const [searchParams] = useSearchParams(); // Get query params
    const token = searchParams.get("token"); // Extract token from query string
    const navigate = useNavigate();
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const verifyEmail = async () => {
            if (!token) {
                toast.error("Invalid verification link.");
                navigate("/");
                return;
            }

            setIsLoading(true);

            const response = await authService.verifyEmail(token);

            console.log(response)
            if (response.success) {
                toast.success("Email verified successfully.");
                navigate("/login");
            } else {
                // @ts-ignore
                toast.error(response.error.message);
                navigate("/");
            }
        };
        verifyEmail();
    }, [token, navigate]);

    return isLoading ? <LoadingPage/> : null;
};

export default EmailVerificationConfirm;
