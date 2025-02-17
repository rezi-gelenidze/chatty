import React from "react";
import {useNavigate} from "react-router-dom";
import {Dialog, DialogContent, DialogHeader, DialogFooter, DialogTitle} from "@/components/ui/dialog";
import {Button} from "@/components/ui/button";

interface TokenExpireDialogProps {
    open: boolean;
    onClose: () => void;
}

/**
 * Token Expiry Dialog
 * Informs the user that their session has expired and prompts re-authentication.
 *
 * @param {TokenExpireDialogProps} props - Component props.
 * @returns {JSX.Element} Dialog component.
 */
const TokenExpireDialog: React.FC<TokenExpireDialogProps> = ({open, onClose}) => {
    const navigate = useNavigate();

    const onConfirm = () => {
        navigate("/login");
        onClose();
    };

    return (
        <Dialog open={open} onOpenChange={onClose}>
            <DialogContent className="max-w-md p-6">
                <DialogHeader>
                    <DialogTitle>სესია ამოიწურა</DialogTitle>
                </DialogHeader>
                <p className="text-sm text-gray-600">
                    თქვენი სესია ამოიწურა და საჭიროა თავიდან ავტორიზაცია.
                    გადავიდეთ ავტორიზაციის გვერდზე? (თქვენი შეუნახავი ინფორმაცია შესაძლოა დაიკარგოს)
                </p>
                <DialogFooter className="mt-4">
                    <Button variant="outline" onClick={onClose}>
                        არა
                    </Button>
                    <Button onClick={onConfirm} className="ml-2">
                        ავტორიზაცია
                    </Button>
                </DialogFooter>
            </DialogContent>
        </Dialog>
    );
};

export default TokenExpireDialog;
