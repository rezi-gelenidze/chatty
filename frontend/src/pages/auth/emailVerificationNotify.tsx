import React from "react";
import {Button} from "@/components/ui/button";
import {useNavigate} from "react-router-dom";
import EmailImage from "../../assets/media/email.png";
import FormContainer from "@/components/container/FormContainer.tsx";

const EmailVerificationNotify: React.FC = () => {
    const navigate = useNavigate();

    return (
        <FormContainer>
            <div className="flex flex-col items-center justify-center text-center px-4">
                <img src={EmailImage} alt="Email Icon" className="w-16 h-16 mb-4"/>
                <h2 className="text-lg font-semibold">Verification link sent</h2>
                <p className=" mt-2">
                    Check your email for a verification link and follow the instructions to verify your email address.
                </p>
                <Button className="mt-4 bg-black" onClick={() => navigate("/login")}>
                    Sign In
                </Button>
            </div>
        </FormContainer>
    );
};

export default EmailVerificationNotify;
