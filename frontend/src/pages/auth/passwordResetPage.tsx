import {useForm} from "react-hook-form";
import {useState} from "react";
import {Link} from "react-router-dom";
import {Button} from "@/components/ui/button.tsx";
import FormContainer from "@/components/container/FormContainer.tsx";

function PasswordResetPage() {
    const {register, handleSubmit, formState: {errors}} = useForm();
    const [message, setMessage] = useState("");
    const [isDisabled, setIsDisabled] = useState(false);

    const onSubmit = async (data: any) => {
        console.log("Password Reset Request:", data);
        setIsDisabled(true);
        setMessage("If an account with that email exists, you will receive a password reset link.");
    };

    return (
        <FormContainer>
            <h2 className="text-2xl font-semibold text-center">Reset Password</h2>
            {message && <p className="text-green-500 text-sm text-center mt-2">{message}</p>}

            {/* Password Reset Form */}
            <form onSubmit={handleSubmit(onSubmit)} className="mt-6 space-y-4">
                <div>
                    <label className="block text-sm font-medium text-gray-400">Email</label>
                    <input
                        type="email"
                        {...register("email", {required: "Email is required"})}
                        className="w-full p-3 border border-gray-600 rounded bg-[#2A2A2A] text-white mt-1 focus:ring-2 focus:ring-blue-500"
                    />
                    {errors.email && <p className="text-red-500 text-sm">{errors.email.message}</p>}
                </div>

                <Button type="submit" disabled={isDisabled}
                        className="w-full mt-4 bg-green-600 hover:bg-green-700 text-white p-6 rounded-lg">
                    Send Reset Link
                </Button>
            </form>

            {/* Back to Login */}
            <p className="text-center text-sm mt-6 text-gray-400">
                Already know your password? <Link to="/login" className="text-green-500 hover:underline">Log in</Link>
            </p>

            {/* Footer Links */}
            <div className="mt-6 text-gray-500 text-xs text-center">
                <Link to="/terms" className="hover:underline">Terms of Service</Link> •
                <Link to="/privacy" className="hover:underline ml-2">Privacy Policy</Link>
            </div>
        </FormContainer>
    );
}

export default PasswordResetPage;
