import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;

export const deleteCourse = async (courseId) => {
    const token = Cookies.get('jwtToken');
    try {
        const response = await fetch(`${url}/courses/${courseId}`, {
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
        console.error(`Error deleting teacher with ID ${courseId}:`, error);
        return false;
    }
};