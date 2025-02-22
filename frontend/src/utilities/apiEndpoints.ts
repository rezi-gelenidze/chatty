const BASE_URL: string = import.meta.env.VITE_API_URL;

const API_ENDPOINTS = {
    AUTH: {
        LOGIN: `${BASE_URL}/auth/jwt/create`,
        REFRESH: `${BASE_URL}/auth/jwt/refresh`,
        REGISTER: `${BASE_URL}/auth/users`,
        VERIFY_EMAIL: `${BASE_URL}/auth/users/activation`,
        RESET_PASSWORD: `${BASE_URL}/auth/users/reset-password`,
        RESET_PASSWORD_CONFIRM: `${BASE_URL}/auth/users/reset-password-confirm`,
        ME: `${BASE_URL}/auth/users/me`,
    }
};

export default API_ENDPOINTS;