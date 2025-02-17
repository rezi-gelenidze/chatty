import { Routes, Route } from "react-router-dom";

import LandingPage from "./pages/LandingPage.tsx";

function App() {
    return (
        <div>
            <Routes>
                <Route path="/" element={<LandingPage />} />
                <Route path="*" element={<div>404</div>} />
            </Routes>
        </div>
    );
}

export default App;
