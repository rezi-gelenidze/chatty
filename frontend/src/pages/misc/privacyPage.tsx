import PageContainer from "@/components/container/pageContainer.tsx";

function PrivacyPage() {
    return (
        <PageContainer>
            <h2 className="text-2xl font-semibold text-center">Privacy Policy</h2>
            <p className="text-gray-400 text-sm mt-4">
                Your privacy is important to us. We collect minimal data necessary for account management and
                service improvement.
            </p>
            <ul className="text-gray-400 text-sm space-y-2 mt-4 list-disc list-inside">
                <li>We do not sell your data.</li>
                <li>Your information is encrypted and stored securely.</li>
                <li>You can request data deletion anytime.</li>
            </ul>
            <p className="text-gray-500 text-xs text-center mt-6">
                Last updated: February 2025
            </p>
        </PageContainer>
    );
}

export default PrivacyPage;