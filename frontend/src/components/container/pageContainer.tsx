import HomeLogoLink from "@/components/navigation/homeLogoLink.tsx";

function PageContainer({ children }: { children: React.ReactNode }) {
    return (
        <div className="flex flex-col items-center min-h-screen h-full w-full bg-[#121212] text-white px-4">
            <HomeLogoLink />

            {/* Page Content */}
            <div className="bg-[#1E1E1E] p-8 mt-20 md:p-10 rounded-lg shadow-lg w-full max-w-sm md:max-w-md">
                {children}
            </div>
        </div>
    );
}

export default PageContainer;
