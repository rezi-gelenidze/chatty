import React from "react";
import { Loader2 } from "lucide-react";
import { cn } from "@/lib/utils.ts";

/**
 * LoadingPage Component
 * Displays a centered loading spinner using ShadCN UI & Tailwind.
 *
 * @returns {JSX.Element} The loading screen component.
 */
const LoadingPage: React.FC = () => {
    return (
        <div className={cn("flex h-screen w-full items-center justify-center bg-black")}>
            <Loader2 className="h-10 w-10 animate-spin text-gray-500" />
        </div>
    );
};

export default LoadingPage;
