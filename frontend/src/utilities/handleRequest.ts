import { AxiosResponse } from 'axios';

/**
 * Handles an Axios request and returns a standardized response.
 * @param {Promise<AxiosResponse<T>>} request - The Axios request promise.
 * @returns {Promise<{ success: boolean; data?: T; error?: string }>}
 */
const handleRequest = async <T>(request: Promise<AxiosResponse<T>>): Promise<{ success: boolean; data?: T; error?: string }> => {
    try {
        const response = await request;
        return { success: true, data: response.data };
    } catch (error: any) {
        return {
            success: false,
            error: error.response ? error.response.data : 'Network Error'
        };
    }
};

export default handleRequest;
