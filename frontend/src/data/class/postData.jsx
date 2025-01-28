import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;

export const createClass = async (classData) => {
    const token = Cookies.get('jwtToken');
    
    try {
        const response = await fetch(`${url}/classes`, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(classData),
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const result = await response.json();
        return result;
    } catch (error) {
        console.error('Error creating class:', error);
        return null;
    }
};