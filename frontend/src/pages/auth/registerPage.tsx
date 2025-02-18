import {useForm} from "react-hook-form";
import {useState} from "react";
import {Link} from "react-router-dom";
import {Button} from "@/components/ui/button.tsx";
import {FcGoogle} from "react-icons/fc";

import FormContainer from "@/components/container/FormContainer.tsx";

function RegisterPage() {
    const {register, handleSubmit, watch, formState: {errors}} = useForm();
    const [errorMessage, setErrorMessage] = useState("");

    const onSubmit = async (data: any) => {
        console.log(data);
    };

    return (
        <FormContainer>
            <h2 className="text-2xl font-semibold text-center">Create an account</h2>
            {errorMessage && <p className="text-red-500 text-sm text-center mt-2">{errorMessage}</p>}

            {/* Signup Form */}
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

                <div className="grid grid-cols-2 gap-4">
                    <div>
                        <label className="block text-sm font-medium text-gray-400">First Name</label>
                        <input
                            {...register("firstName", {required: "First name is required"})}
                            className="w-full p-3 border border-gray-600 rounded bg-[#2A2A2A] text-white mt-1 focus:ring-2 focus:ring-blue-500"
                        />
                        {errors.firstName && <p className="text-red-500 text-sm">{errors.firstName.message}</p>}
                    </div>

                    <div>
                        <label className="block text-sm font-medium text-gray-400">Last Name</label>
                        <input
                            {...register("lastName", {required: "Last name is required"})}
                            className="w-full p-3 border border-gray-600 rounded bg-[#2A2A2A] text-white mt-1 focus:ring-2 focus:ring-blue-500"
                        />
                        {errors.lastName && <p className="text-red-500 text-sm">{errors.lastName.message}</p>}
                    </div>
                </div>

                <div>
                    <label className="block text-sm font-medium text-gray-400">Username</label>
                    <input
                        {...register("username", {required: "Username is required"})}
                        className="w-full p-3 border border-gray-600 rounded bg-[#2A2A2A] text-white mt-1 focus:ring-2 focus:ring-blue-500"
                    />
                    {errors.username && <p className="text-red-500 text-sm">{errors.username.message}</p>}
                </div>

                <div>
                    <label className="block text-sm font-medium text-gray-400">Date of Birth</label>
                    <input
                        type="date"
                        {...register("dob", {required: "Date of birth is required"})}
                        className="w-full p-3 border border-gray-600 rounded bg-[#2A2A2A] text-white mt-1 focus:ring-2 focus:ring-blue-500"
                    />
                    {errors.dob && <p className="text-red-500 text-sm">{errors.dob.message}</p>}

                </div>

                <div className="grid grid-cols-2 gap-4">

                    <div>
                        <label className="block text-sm font-medium text-gray-400">Password</label>
                        <input
                            type="password"
                            {...register("password", {required: "Password is required"})}
                            className="w-full p-3 border border-gray-600 rounded bg-[#2A2A2A] text-white mt-1 focus:ring-2 focus:ring-blue-500"
                        />
                        {errors.password && <p className="text-red-500 text-sm">{errors.password.message}</p>}
                    </div>

                    <div>
                        <label className="block text-sm font-medium text-gray-400">Confirm Password</label>
                        <input
                            type="password"
                            {...register("passwordRepeat", {
                                required: "Please confirm your password",
                                validate: value => value === watch("password") || "Passwords do not match",
                            })}
                            className="w-full p-3 border border-gray-600 rounded bg-[#2A2A2A] text-white mt-1 focus:ring-2 focus:ring-blue-500"
                        />
                        {errors.passwordRepeat &&
                            <p className="text-red-500 text-sm">{errors.passwordRepeat.message}</p>}
                    </div>
                </div>

                <Button type="submit"
                        className="w-full mt-4 bg-green-600 hover:bg-green-700 text-white p-6 rounded-lg">
                    Sign up
                </Button>
            </form>

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
                    <span>Sign up with Google</span>
                </Button>
            </div>

            {/* Already Have an Account */}
            <p className="text-center text-sm mt-6 text-gray-400">
                Already have an account? <Link to="/login" className="text-green-500 hover:underline">Log
                in</Link>
            </p>

            {/* Footer Links */}
            <div className="mt-6 text-gray-500 text-xs text-center">
                <Link to="/terms" className="hover:underline">Terms of Service</Link> •
                <Link to="/privacy" className="hover:underline ml-2">Privacy Policy</Link>
            </div>
        </FormContainer>
    );
}

export default RegisterPage;
