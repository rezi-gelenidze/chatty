import PageContainer from "@/components/container/pageContainer.tsx";

function TermsPage() {
    return (
        <PageContainer>
            <h2 className="text-2xl font-semibold text-center">Terms & Conditions</h2>
            <p className="text-gray-400 text-sm mt-4">
                By using Chatty, you agree to the following:
            </p>
            <ul className="text-gray-400 text-sm space-y-2 mt-4 list-disc list-inside">
                <li>You must be 16 years or older.</li>
                <li>Do not use Chatty for illegal activities.</li>
                <li>We may suspend accounts that violate our guidelines.</li>
                <li>We reserve the right to modify these terms.</li>
            </ul>
            <p className="text-gray-500 text-xs text-center mt-6">
                Last updated: February 2025
            </p>
        </PageContainer>
    );
}

export default TermsPage;
