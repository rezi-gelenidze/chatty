import { Phone, Video, MoreHorizontal } from "lucide-react";
import { Button } from "@/components/ui/button";
import { Tooltip, TooltipContent, TooltipTrigger } from "@/components/ui/tooltip";

interface ChatHeaderProps {
    selectedChat: number;
}

const ChatHeader: React.FC<ChatHeaderProps> = ({ selectedChat }) => {
    return (
        <div className="flex items-center justify-between p-4 border-b border-gray-700 bg-[#1E1E1E]">
            <h2 className="text-lg font-medium">Chat #{selectedChat}</h2>

            <div className="flex space-x-3">
                <Tooltip>
                    <TooltipTrigger asChild>
                        <Button variant="ghost" size="icon">
                            <Phone className="h-5 w-5 text-gray-400" />
                        </Button>
                    </TooltipTrigger>
                    <TooltipContent>Voice Call</TooltipContent>
                </Tooltip>

                <Tooltip>
                    <TooltipTrigger asChild>
                        <Button variant="ghost" size="icon">
                            <Video className="h-5 w-5 text-gray-400" />
                        </Button>
                    </TooltipTrigger>
                    <TooltipContent>Video Call</TooltipContent>
                </Tooltip>

                <Tooltip>
                    <TooltipTrigger asChild>
                        <Button variant="ghost" size="icon">
                            <MoreHorizontal className="h-5 w-5 text-gray-400" />
                        </Button>
                    </TooltipTrigger>
                    <TooltipContent>Chat Info</TooltipContent>
                </Tooltip>
            </div>
        </div>
    );
};

export default ChatHeader;
