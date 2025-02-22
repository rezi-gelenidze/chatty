import httpService from './httpService';
import handleRequest from '../utilities/handleRequest';
import authHeader from '../utilities/authHeader';

import API_ENDPOINTS from "@/utilities/apiEndpoints.ts";

/**
 * Service for handling authentication-related operations.
 * Matches the Spring Boot `JwtController` and `UserController`.
 */
const authService = {
    // Login: Create JWT token pair
    login: async (username: string, password: string) => {
        const request = httpService.post(API_ENDPOINTS.AUTH.LOGIN, {username, password});
        return await handleRequest(request);
    },

    // Refresh Access Token
    refreshToken: async (refreshToken: string) => {
        const request = httpService.post(API_ENDPOINTS.AUTH.REFRESH, {refreshToken});
        return await handleRequest(request);
    },

    // Register New User
    register: async (payload: {
        username: string;
        email: string;
        password: string;
        confirmPassword: string;
        firstName: string;
        lastName: string;
        dateOfBirth: string; // YYYY-MM-DD format
    }) => {
        const request = httpService.post(API_ENDPOINTS.AUTH.REGISTER, payload);
        return await handleRequest(request);
    },

    // Get Current User Information
    getCurrentUser: async () => {
        const request = httpService.get(API_ENDPOINTS.AUTH.ME, {headers: authHeader()});
        return await handleRequest(request);
    },

    // Delete Account
    deleteAccount: async () => {
        const request = httpService.delete(API_ENDPOINTS.AUTH.ME, {headers: authHeader()});
        return await handleRequest(request);
    },

    // Verify Email
    verifyEmail: async (token: string) => {
        const request = httpService.post(API_ENDPOINTS.AUTH.VERIFY_EMAIL, {token: token});
        return await handleRequest(request);
    },

    // Password Reset Request
    requestPasswordReset: async (email: string) => {
        const request = httpService.post(API_ENDPOINTS.AUTH.RESET_PASSWORD, {email});
        return await handleRequest(request);
    },

    // Password Reset Confirm
    resetPasswordConfirm: async (token: string, newPassword: string) => {
        const request = httpService.post(API_ENDPOINTS.AUTH.RESET_PASSWORD_CONFIRM, {token, newPassword});
        return await handleRequest(request);
    },
};

export default authService;
