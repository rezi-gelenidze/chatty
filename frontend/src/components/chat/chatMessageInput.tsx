import React, { useState } from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Tooltip, TooltipContent, TooltipTrigger } from "@/components/ui/tooltip";
import { Send, Paperclip, Mic, Image } from "lucide-react";

const ChatMessageInput: React.FC = () => {
    const [message, setMessage] = useState("");

    const handleSendMessage = () => {
        if (message.trim()) {
            console.log("Sending message:", message);
            setMessage(""); // Clear input after sending
        }
    };

    return (
        <div className="p-4 border-t border-gray-700 bg-[#1E1E1E] flex items-center space-x-2">
            {/* Attach Image */}
            <Tooltip>
                <TooltipTrigger asChild>
                    <Button variant="ghost" size="icon">
                        <Image className="h-5 w-5 text-gray-400" />
                    </Button>
                </TooltipTrigger>
                <TooltipContent>Send Image</TooltipContent>
            </Tooltip>

            {/* Attach File */}
            <Tooltip>
                <TooltipTrigger asChild>
                    <Button variant="ghost" size="icon">
                        <Paperclip className="h-5 w-5 text-gray-400" />
                    </Button>
                </TooltipTrigger>
                <TooltipContent>Attach File</TooltipContent>
            </Tooltip>

            {/* Send Voice */}
            <Tooltip>
                <TooltipTrigger asChild>
                    <Button variant="ghost" size="icon">
                        <Mic className="h-5 w-5 text-gray-400" />
                    </Button>
                </TooltipTrigger>
                <TooltipContent>Send Voice</TooltipContent>
            </Tooltip>

            {/* Message Input Field */}
            <Input
                placeholder="Type a message..."
                className="flex-1 bg-[#272727] text-white border-none focus:ring-0"
                value={message}
                onChange={(e) => setMessage(e.target.value)}
                onKeyDown={(e) => e.key === "Enter" && handleSendMessage()}
            />

            {/* Send Message */}
            <Tooltip>
                <TooltipTrigger asChild>
                    <Button variant="ghost" size="icon" onClick={handleSendMessage}>
                        <Send className="h-5 w-5 text-blue-500" />
                    </Button>
                </TooltipTrigger>
                <TooltipContent>Send Message</TooltipContent>
            </Tooltip>
        </div>
    );
};

export default ChatMessageInput;
