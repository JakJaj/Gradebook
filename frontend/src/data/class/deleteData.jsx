import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;

export const deleteClass = async (classId) => {
    const token = Cookies.get('jwtToken');
    try {
        const response = await fetch(`${url}/classes/${classId}`, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            }
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        return true;
    } catch (error) {
        console.error(`Error deleting class with ID ${classId}:`, error);
        return false;
    }
};