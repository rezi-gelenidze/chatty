import axios, { AxiosError, AxiosRequestConfig, AxiosResponse } from 'axios';
import { toast } from 'react-toastify';

// Get the API base URL based on the environment
const BASE_URL: string = import.meta.env.VITE_API_URL || "";

// Create an Axios instance
const httpService = axios.create({
    baseURL: `${BASE_URL}/`,
    headers: {
        'Content-Type': 'application/json',
    },
});

let showModalCallback: (() => void) | null = null;

/**
 * Registers a callback function to display a modal when authentication fails.
 */
export const registerShowModalCallback = (callback: () => void) => {
    showModalCallback = callback;
};

/**
 * Refreshes the JWT access token using the refresh token stored in local storage.
 * @returns {Promise<string | null>} The new access token if successful, otherwise null.
 */
export const refreshToken = async (): Promise<string | null> => {
    const refreshToken = localStorage.getItem('jwt-refresh');
    if (!refreshToken) return null;

    try {
        const response: AxiosResponse<{ access: string }> = await axios.post(
            `${BASE_URL}/auth/jwt/refresh/`,
            { refresh: refreshToken }
        );

        const newAccessToken = response.data.access;
        localStorage.setItem('jwt-access', newAccessToken);
        return newAccessToken;
    } catch (error) {
        localStorage.removeItem('jwt-access');
        localStorage.removeItem('jwt-refresh');

        if (showModalCallback) showModalCallback();

        return null;
    }
};

// Add Axios interceptor to handle automatic token refresh
httpService.interceptors.response.use(
    (response: AxiosResponse) => response,
    async (error: AxiosError) => {
        const originalRequest = error.config as AxiosRequestConfig & { _retry?: boolean };

        // Handle network error
        if (!error.response) {
            toast.error('Connection error! Please check your internet connection.');
            return Promise.reject(error);
        }

        // If Unauthorized (401) and the request is not retried yet
        if (error.response.status === 401 && !originalRequest._retry) {
            originalRequest._retry = true;
            const newAccessToken = await refreshToken();

            if (newAccessToken) {
                localStorage.setItem('jwt-access', newAccessToken);
                originalRequest.headers = {
                    ...originalRequest.headers,
                    Authorization: `JWT ${newAccessToken}`,
                };

                return httpService(originalRequest);
            }
        }

        return Promise.reject(error);
    }
);

export default httpService;
