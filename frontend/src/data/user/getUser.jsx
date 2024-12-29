import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;

export const fetchUserDetails = async () => {
    const token = Cookies.get('jwtToken');
    try {
        const response = await fetch(`${url}/users`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            }
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const result = await response.json();

        if (!result || Object.keys(result).length === 0) {
            throw new Error('Empty response or invalid data format');
        }
        
        return result;
    } catch (error) {
        console.error('Error fetching user details:', error);
        return {};
    }
};