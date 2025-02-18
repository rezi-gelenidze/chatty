import React, {useContext, useState} from "react";
import {useForm} from "react-hook-form";
import {useNavigate, Link} from "react-router-dom";
import {Button} from "@/components/ui/button";
import {FcGoogle} from "react-icons/fc";

import authService from "@/services/authService";
import {AuthContext} from "@/contexts/authContext.tsx";
import FormContainer from "@/components/container/FormContainer.tsx";

interface LoginFormInputs {
    username: string;
    password: string;
}

const LoginPage: React.FC = () => {
    const {register, handleSubmit, formState: {errors}} = useForm<LoginFormInputs>();
    const {updateUserState} = useContext(AuthContext)
    const [errorMessage, setErrorMessage] = useState<string | null>(null);
    const navigate = useNavigate();

    const onSubmit = async (data: LoginFormInputs) => {
        setErrorMessage(null);
        const response = await authService.login(data.username, data.password);

        if (response.success) {
            // Store access & refresh tokens
            localStorage.setItem("jwt-access", response.data.accessToken);
            localStorage.setItem("jwt-refresh", response.data.refreshToken);

            await updateUserState();

            // Redirect to ?next or home
            const nextUrl = new URLSearchParams(window.location.search).get("next");
            navigate(nextUrl ? nextUrl : "/chat");
        } else {
            console.error(response.error);
            setErrorMessage("Login failed. Please try again.");
        }
    };

    return (
        <FormContainer>
            <h2 className="text-2xl font-semibold text-center">Sign in</h2>
            {errorMessage && <p className="text-red-500 text-sm text-center mt-2">{errorMessage}</p>}

            {/* Login Form */}
            <form onSubmit={handleSubmit(onSubmit)} className="mt-6 space-y-4">
                <div>
                    <label className="block text-sm font-medium text-gray-400">Username</label>
                    <input
                        {...register("username", {required: "Username is required"})}
                        className="w-full p-3 border border-gray-600 rounded bg-[#2A2A2A] text-white mt-1 focus:ring-2 focus:ring-blue-500"
                    />
                    {errors.username && <p className="text-red-500 text-sm">{errors.username.message}</p>}
                </div>

                <div>
                    <label className="block text-sm font-medium text-gray-400">Password</label>
                    <input
                        type="password"
                        {...register("password", {required: "Password is required"})}
                        className="w-full p-3 border border-gray-600 rounded bg-[#2A2A2A] text-white mt-1 focus:ring-2 focus:ring-blue-500"
                    />
                    {errors.password && <p className="text-red-500 text-sm">{errors.password.message}</p>}
                </div>

                <Button type="submit"
                        className="w-full mt-4 bg-green-600 hover:bg-green-700 text-white p-6 rounded-lg">
                    Sign in
                </Button>
            </form>

            {/* Forgot Password */}
            <p className="text-center text-sm mt-4 text-gray-400">
                <Link to="/reset-password" className="text-green-500 hover:underline">Forgot your
                    password?</Link>
            </p>

            {/* Divider */}
            <div className="flex items-center my-6">
                <hr className="flex-grow border-gray-600"/>
                <span className="mx-4 text-gray-500">or</span>
                <hr className="flex-grow border-gray-600"/>
            </div>

            {/* Social Logins */}
            <div className="space-y-3">
                <Button
                    className="w-full flex items-center justify-center space-x-3 bg-white text-black p-6 rounded-lg hover:bg-gray-200">
                    <FcGoogle className="text-xl"/>
                    <span>Sign in with Google</span>
                </Button>
            </div>

            {/* Don't Have an Account */}
            <p className="text-center text-sm mt-6 text-gray-400">
                Don't have an account? <Link to="/register"
                                             className="text-green-500 hover:underline">Register</Link>
            </p>

            {/* Footer Links */}
            <div className="mt-6 text-gray-500 text-xs text-center">
                <Link to="/terms" className="hover:underline">Terms of Service</Link> •
                <Link to="/privacy" className="hover:underline ml-2">Privacy Policy</Link>
            </div>
        </FormContainer>
    );
};

export default LoginPage;
