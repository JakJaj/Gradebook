import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;

export const createTeacher = async (teacherData) => {
    const token = Cookies.get('jwtToken');
    

    try {
        const response = await fetch(`${url}/auth/register/teachers`, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(teacherData),
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