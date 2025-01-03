import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;

export const createCourse = async (courseData) => {
    const token = Cookies.get('jwtToken');
    
    try {
        const response = await fetch(`${url}/courses`, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(courseData),
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const result = await response.json();
        return result;
    } catch (error) {
        console.error('Error creating teacher:', error);
        return null;
    }
};