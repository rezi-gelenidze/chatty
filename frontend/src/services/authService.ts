import httpService from './httpService';
import handleRequest from '../utilities/handleRequest';
import authHeader from '../utilities/authHeader';

/**
 * Service for handling authentication-related operations.
 * Matches the Spring Boot `JwtController` and `UserController`.
 */
const authService = {
    // Login: Create JWT token pair
    login: async (username: string, password: string) => {
        const request = httpService.post('/auth/jwt/create', { username, password });
        return await handleRequest(request);
    },

    // Refresh Access Token
    refreshToken: async (refreshToken: string) => {
        const request = httpService.post('/auth/auth/jwt/refresh', { refreshToken });
        return await handleRequest(request);
    },

    // Register New User
    register: async (data: {
        username: string;
        email: string;
        password: string;
        firstName: string;
        lastName: string;
        dateOfBirth: string; // YYYY-MM-DD format
    }) => {
        const payload = {
            username: data.username,
            email: data.email,
            password: data.password,
            firstName: data.firstName,
            lastName: data.lastName,
            dateOfBirth: data.dateOfBirth,
        };

        const request = httpService.post('/auth/users', payload);
        return await handleRequest(request);
    },

    // Get Current User Information
    getCurrentUser: async () => {
        const request = httpService.get('/auth/users/me', { headers: authHeader() });
        return await handleRequest(request);
    },

    // Delete Account
    deleteAccount: async () => {
        const request = httpService.delete('/auth/users/me', { headers: authHeader() });
        return await handleRequest(request);
    },
};

export default authService;
