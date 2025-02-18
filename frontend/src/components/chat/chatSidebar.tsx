import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { Button } from "@/components/ui/button";
import { Tooltip, TooltipContent, TooltipTrigger } from "@/components/ui/tooltip";
import { Link } from "react-router-dom";
import LightLogo from "@/assets/media/logo-light.png";
import clsx from "clsx";

const chats = [
    { id: 1, name: "John Doe", lastMessage: "Hey, how are you?", time: "10:30 AM", avatar: "" },
    { id: 2, name: "Alice Smith", lastMessage: "Let's meet tomorrow.", time: "Yesterday", avatar: "" },
    { id: 3, name: "Dev Chat", lastMessage: "New updates released!", time: "2 days ago", avatar: "" },
];

interface ChatSidebarProps {
    selectedChat: number | null;
    setSelectedChat: (id: number) => void;
}

const ChatSidebar: React.FC<ChatSidebarProps> = ({ selectedChat, setSelectedChat }) => {
    return (
        <div className="w-[300px] bg-[#1E1E1E] border-r border-gray-700 flex flex-col">
            {/* Logo & Profile */}
            <div className="flex items-center justify-between p-4 border-b border-gray-700">
                <div className="flex items-center">
                    <img src={LightLogo} alt="Chatty Logo" className="h-6" />
                    <h1 className="text-xl font-bold ml-1">Chatty</h1>
                </div>
                <Tooltip>
                    <TooltipTrigger asChild>
                        <Button variant="ghost" className="hover:bg-transparent">
                            <Avatar>
                                <AvatarImage src="" alt="User Avatar" />
                                <AvatarFallback className="text-black">R</AvatarFallback>
                            </Avatar>
                        </Button>
                    </TooltipTrigger>
                    <TooltipContent side="bottom" align="center">
                        <Link to="/logout">
                            <Button variant="ghost" className="hover:bg-transparent hover:text-gray-400 text-white">
                                Logout
                            </Button>
                        </Link>
                    </TooltipContent>
                </Tooltip>
            </div>

            {/* Chat List */}
            <div className="flex-1 overflow-y-auto">
                {chats.map((chat) => (
                    <div
                        key={chat.id}
                        onClick={() => setSelectedChat(chat.id)}
                        className={clsx(
                            "p-4 flex items-center cursor-pointer hover:bg-[#272727]",
                            selectedChat === chat.id && "bg-[#272727]"
                        )}
                    >
                        <Avatar className="mr-3">
                            <AvatarImage src={chat.avatar} />
                            <AvatarFallback>{chat.name[0]}</AvatarFallback>
                        </Avatar>
                        <div className="flex-1">
                            <h2 className="font-medium">{chat.name}</h2>
                            <p className="text-xs text-gray-400">{chat.lastMessage}</p>
                        </div>
                        <span className="text-xs text-gray-500">{chat.time}</span>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ChatSidebar;
