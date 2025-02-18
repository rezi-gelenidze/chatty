interface ChatMessagesProps {
    selectedChat: number;
}

const ChatMessages: React.FC<ChatMessagesProps> = ({ selectedChat }) => {
    return (
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
    );
};

export default ChatMessages;
