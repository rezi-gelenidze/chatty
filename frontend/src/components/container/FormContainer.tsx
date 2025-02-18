import HomeLogoLink from "@/components/navigation/homeLogoLink.tsx";

interface AuthTemplateProps {
    children: React.ReactNode;
}

const FormContainer: React.FC<AuthTemplateProps> = ({ children }) => {
    return (
        <div className="flex flex-col items-center justify-center min-h-screen w-full bg-[#121212] text-white px-4">
            <HomeLogoLink />

            <div className="flex flex-1 w-full items-start justify-center">
                <div className="bg-[#1E1E1E] p-8 md:p-10 rounded-lg shadow-lg w-full max-w-sm md:max-w-md">
                    {children}
                </div>
            </div>
        </div>
    );
};

export default FormContainer;
