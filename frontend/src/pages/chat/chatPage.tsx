import React, {useState} from "react";
import {Avatar, AvatarFallback, AvatarImage} from "@/components/ui/avatar";
import {Button} from "@/components/ui/button";
import {Input} from "@/components/ui/input";
import {Tooltip, TooltipContent, TooltipTrigger, TooltipProvider} from "@/components/ui/tooltip";
import {Send, Paperclip, Mic, Video, Image, Phone, MoreHorizontal} from "lucide-react";
import clsx from "clsx";
import { Link } from "react-router-dom";

import LightLogo from "../../assets/media/logo-light.png";

const chats = [
    {id: 1, name: "John Doe", lastMessage: "Hey, how are you?", time: "10:30 AM", avatar: ""},
    {id: 2, name: "Alice Smith", lastMessage: "Let's meet tomorrow.", time: "Yesterday", avatar: ""},
    {id: 3, name: "Dev Chat", lastMessage: "New updates released!", time: "2 days ago", avatar: ""},
];

const ChatPage: React.FC = () => {
    const [selectedChat, setSelectedChat] = useState<number | null>(null);
    const [message, setMessage] = useState("");

    return (
        <TooltipProvider>
            <div className="flex h-screen w-full bg-[#121212] text-white">
                {/* Sidebar (Chat List) */}
                <div className="w-[300px] bg-[#1E1E1E] border-r border-gray-700 flex flex-col">
                    {/* Logo & Profile */}
                    <div className="flex items-center justify-between p-4 border-b border-gray-700">
                        <div className="flex items-center">
                            <img src={LightLogo} alt="Chatty Logo" className="h-6"/>
                            <h1 className="text-xl font-bold ml-1">Chatty</h1>
                        </div>
                        <Tooltip>
                            <TooltipTrigger asChild>
                                <Button
                                    variant="ghost"
                                    className="hover:bg-transparent"
                                >
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
                                    <AvatarImage src={chat.avatar}/>
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

                {/* Chat Area */}
                <div className="flex-1 flex flex-col">
                    {selectedChat ? (
                        <>
                            {/* Chat Header */}
                            <div
                                className="flex items-center justify-between p-4 border-b border-gray-700 bg-[#1E1E1E]">
                                <h2 className="text-lg font-medium">{chats.find((c) => c.id === selectedChat)?.name}</h2>

                                <div className="flex space-x-3">
                                    <Tooltip>
                                        <TooltipTrigger asChild>
                                            <Button variant="ghost" size="icon">
                                                <Phone className="h-5 w-5 text-gray-400"/>
                                            </Button>
                                        </TooltipTrigger>
                                        <TooltipContent>Voice Call</TooltipContent>
                                    </Tooltip>

                                    <Tooltip>
                                        <TooltipTrigger asChild>
                                            <Button variant="ghost" size="icon">
                                                <Video className="h-5 w-5 text-gray-400"/>
                                            </Button>
                                        </TooltipTrigger>
                                        <TooltipContent>Video Call</TooltipContent>
                                    </Tooltip>

                                    <Tooltip>
                                        <TooltipTrigger asChild>
                                            <Button variant="ghost" size="icon">
                                                <MoreHorizontal className="h-5 w-5 text-gray-400"/>
                                            </Button>
                                        </TooltipTrigger>
                                        <TooltipContent>Chat Info</TooltipContent>
                                    </Tooltip>
                                </div>


                            </div>

                            {/* Messages Area */}
                            <div className="flex-1 p-4 overflow-y-auto">
                                <div className="flex flex-col space-y-3">
                                    <div className="flex justify-start">
                                        <div className="bg-gray-700 p-3 rounded-lg text-sm">Hello! 👋</div>
                                    </div>
                                    <div className="flex justify-end">
                                        <div className="bg-green-600 p-3 rounded-lg text-sm">Hey there! 😊</div>
                                    </div>
                                </div>
                            </div>

                            {/* Message Input */}
                            <div className="p-4 border-t border-gray-700 bg-[#1E1E1E] flex items-center space-x-2">
                                <Tooltip>
                                    <TooltipTrigger asChild>
                                        <Button variant="ghost" size="icon">
                                            <Image className="h-5 w-5 text-gray-400"/>
                                        </Button>
                                    </TooltipTrigger>
                                    <TooltipContent>Send Image</TooltipContent>
                                </Tooltip>

                                <Tooltip>
                                    <TooltipTrigger asChild>
                                        <Button variant="ghost" size="icon">
                                            <Paperclip className="h-5 w-5 text-gray-400"/>
                                        </Button>
                                    </TooltipTrigger>
                                    <TooltipContent>Attach File</TooltipContent>
                                </Tooltip>

                                <Tooltip>
                                    <TooltipTrigger asChild>
                                        <Button variant="ghost" size="icon">
                                            <Mic className="h-5 w-5 text-gray-400"/>
                                        </Button>
                                    </TooltipTrigger>
                                    <TooltipContent>Send Voice</TooltipContent>
                                </Tooltip>

                                <Input
                                    placeholder="Type a message..."
                                    className="flex-1 bg-[#272727] text-white border-none focus:ring-0"
                                    value={message}
                                    onChange={(e) => setMessage(e.target.value)}
                                />

                                <Tooltip>
                                    <TooltipTrigger asChild>
                                        <Button variant="ghost" size="icon">
                                            <Send className="h-5 w-5 text-blue-500"/>
                                        </Button>
                                    </TooltipTrigger>
                                    <TooltipContent>Send Message</TooltipContent>
                                </Tooltip>
                            </div>
                        </>
                    ) : (
                        <div className="flex flex-1 items-center justify-center text-gray-400">Select a chat to start
                            messaging.</div>
                    )}
                </div>
            </div>
        </TooltipProvider>
    );
};

export default ChatPage;
