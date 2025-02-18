import React, { useState } from "react";
import { TooltipProvider } from "@/components/ui/tooltip";
import ChatSidebar from "@/components/chat/chatSidebar.tsx";
import ChatHeader from "@/components/chat/chatHeader.tsx";
import ChatMessages from "@/components/chat/chatMessages.tsx";
import ChatMessageInput from "@/components/chat/chatMessageInput.tsx";

const ChatPage: React.FC = () => {
    const [selectedChat, setSelectedChat] = useState<number | null>(null);

    return (
        <TooltipProvider>
            <div className="flex h-screen w-full bg-[#121212] text-white">
                <ChatSidebar selectedChat={selectedChat} setSelectedChat={setSelectedChat} />
                <div className="flex-1 flex flex-col">
                    {selectedChat ? (
                        <>
                            <ChatHeader selectedChat={selectedChat} />
                            <ChatMessages selectedChat={selectedChat} />
                            <ChatMessageInput />
                        </>
                    ) : (
                        <div className="flex flex-1 items-center justify-center text-gray-400">
                            Select a chat to start messaging.
                        </div>
                    )}
                </div>
            </div>
        </TooltipProvider>
    );
};

export default ChatPage;
