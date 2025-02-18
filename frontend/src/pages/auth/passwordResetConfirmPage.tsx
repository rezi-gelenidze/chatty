import {useForm} from "react-hook-form";
import {useState} from "react";
import {Link, useNavigate, useLocation} from "react-router-dom";
import {Button} from "@/components/ui/button";
import FormContainer from "@/components/container/FormContainer.tsx";

function PasswordResetConfirmPage() {
    const {register, handleSubmit, watch, formState: {errors}} = useForm();
    const [message, setMessage] = useState("");
    const navigate = useNavigate();
    const location = useLocation();

    // Extract token from URL (e.g., /reset-password/confirm?token=abc123)
    const token = new URLSearchParams(location.search).get("token");

    const onSubmit = async (data: any) => {
        console.log("Reset Confirm Data:", {token, ...data});

        // Simulate API request
        setTimeout(() => {
            setMessage("Your password has been successfully reset. You can now log in.");
            setTimeout(() => navigate("/login"), 2000);
        }, 1000);
    };

    return (
        <FormContainer>
            <h2 className="text-2xl font-semibold text-center">Reset Your Password</h2>
            {message && <p className="text-green-500 text-sm text-center mt-2">{message}</p>}

            {/* Password Reset Confirm Form */}
            <form onSubmit={handleSubmit(onSubmit)} className="mt-6 space-y-4">
                <div>
                    <label className="block text-sm font-medium text-gray-400">New Password</label>
                    <input
                        type="password"
                        {...register("password", {required: "New password is required"})}
                        className="w-full p-3 border border-gray-600 rounded bg-[#2A2A2A] text-white mt-1 focus:ring-2 focus:ring-blue-500"
                    />
                    {errors.password && <p className="text-red-500 text-sm">{errors.password.message}</p>}
                </div>

                <div>
                    <label className="block text-sm font-medium text-gray-400">Confirm New Password</label>
                    <input
                        type="password"
                        {...register("passwordRepeat", {
                            required: "Please confirm your new password",
                            validate: value => value === watch("password") || "Passwords do not match",
                        })}
                        className="w-full p-3 border border-gray-600 rounded bg-[#2A2A2A] text-white mt-1 focus:ring-2 focus:ring-blue-500"
                    />
                    {errors.passwordRepeat && <p className="text-red-500 text-sm">{errors.passwordRepeat.message}</p>}
                </div>

                <Button type="submit" className="w-full mt-4 bg-green-600 hover:bg-green-700 text-white p-6 rounded-lg">
                    Reset Password
                </Button>
            </form>

            {/* Back to Login */}
            <p className="text-center text-sm mt-6 text-gray-400">
                Remembered your password? <Link to="/login" className="text-green-500 hover:underline">Log in</Link>
            </p>

            {/* Footer Links */}
            <div className="mt-6 text-gray-500 text-xs text-center">
                <Link to="/terms" className="hover:underline">Terms of Service</Link> •
                <Link to="/privacy" className="hover:underline ml-2">Privacy Policy</Link>
            </div>
        </FormContainer>
    );
}

export default PasswordResetConfirmPage;
