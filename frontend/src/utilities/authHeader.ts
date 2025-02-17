/**
 * Returns the authorization header with JWT token if available.
 * @returns {Record<string, string>} Authorization header object.
 */
const authHeader = (): Record<string, string> => {
    const accessToken = localStorage.getItem('jwt-access');

    return accessToken ? { Authorization: `JWT ${accessToken}` } : {};
};

export default authHeader;
