import { Link } from "react-router-dom";
import { Button } from "@/components/ui/button";
import { FcGoogle } from "react-icons/fc";
import { TypeAnimation } from "react-type-animation";

import LogoLight from "../assets/media/logo-light.png";

function LandingPage() {
    return (
        <div className="flex flex-col md:flex-row min-h-screen w-full">
            {/* Left Section */}
            <div className="flex-1 bg-[#0B0F19] text-white flex flex-col justify-center items-center md:items-start px-8 md:px-16 text-center md:text-left">
                {/* Logo and App Name */}
                <div className="flex items-center space-x-3 mb-6">
                    <img src={LogoLight} alt="Chatty Logo" className="w-10 md:w-12 h-10 md:h-12" />
                    <h1 className="text-3xl md:text-4xl font-bold">Chatty</h1>
                </div>

                {/* Slogan with Typing Effect */}
                <p className="text-2xl md:text-3xl font-semibold">
                    Chat with friends,
                    <span className="block md:hidden"></span> {/* Breaks on mobile, inline on large screens */}
                    <TypeAnimation
                        sequence={[
                            "with comfort.", 1500,
                            "without limits.", 1500,
                            "anytime, anywhere.", 1500,
                        ]}
                        wrapper="span"
                        speed={50}
                        repeat={Infinity}
                        className="text-purple-400"
                    />
                </p>

            </div>

            {/* Right Section */}
            <div className="flex-1 bg-black flex flex-col items-center justify-center text-white p-8">
                <h2 className="text-xl md:text-2xl font-semibold">Get started</h2>

                {/* Buttons */}
                <div className="mt-6 w-full max-w-sm flex flex-col space-y-4">
                    {/* Sign In and Sign Up in a Row */}
                    <div className="flex flex-col md:flex-row space-y-4 md:space-y-0 md:space-x-4">
                        <Link to="/signin" className="w-full">
                            <Button className="w-full p-6 bg-blue-600 text-white rounded-lg hover:bg-blue-700">
                                Log in
                            </Button>
                        </Link>
                        <Link to="/signup" className="w-full">
                            <Button className="w-full p-6 bg-gray-300 text-black rounded-lg hover:bg-gray-400">
                                Sign up
                            </Button>
                        </Link>
                    </div>

                    {/* Google Sign-in Button */}
                    <Link
                        to="/"
                        className="w-full p-3 bg-white text-black rounded-lg hover:bg-gray-200 flex items-center justify-center space-x-2"
                    >
                        <FcGoogle className="text-xl" />
                        <span>Continue with Google</span>
                    </Link>
                </div>

                {/* Footer */}
                <div className="mt-8 md:absolute md:bottom-6 text-gray-500 text-sm text-center">
                    <p>
                        © Chatty | <Link to="/terms" className="underline">Terms</Link> |{" "}
                        <Link to="/privacy" className="underline">Privacy</Link>
                    </p>
                </div>
            </div>
        </div>
    );
}

export default LandingPage;
