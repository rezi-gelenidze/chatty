import {Link} from "react-router-dom";
import LogoLight from "@/assets/media/logo-light.png";

function HomeLogoLink() {
    return (
        <Link to="/" className="flex w-full justify-center items-center my-6 md:justify-start">
            <img src={LogoLight} alt="Chatty Logo" className="w-6 md:w-10 h-6 md:h-10"/>
            <h1 className="text-2xl md:text-3xl font-bold pl-2">Chatty</h1>
        </Link>
    );
}

export default HomeLogoLink;
