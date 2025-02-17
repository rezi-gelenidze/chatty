import { Link } from "react-router-dom";

function LandingPage() {
    return (
        <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
            <div className="text-center">
                {/* App Name */}
                <h1 className="text-5xl font-bold text-gray-900">Chatty</h1>
                <p className="mt-2 text-lg text-gray-600">Your perfect chat experience</p>

                {/* Buttons */}
                <div className="mt-6 flex space-x-4">
                    <Link to="/signin">
                        <button className="px-6 py-3 bg-blue-600 text-white rounded-lg hover:bg-blue-700">
                            Sign In
                        </button>
                    </Link>

                    <Link to="/signup">
                        <button className="px-6 py-3 bg-gray-300 text-gray-900 rounded-lg hover:bg-gray-400">
                            Sign Up
                        </button>
                    </Link>
                </div>
            </div>
        </div>
    );
}

export default LandingPage;
