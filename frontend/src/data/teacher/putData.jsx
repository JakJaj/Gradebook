import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;

export const updateTeacher = async (teacherData) => {
    const token = Cookies.get('jwtToken');
    try {
        const response = await fetch(`${url}/teachers/${teacherData.teacherID}`, {
            method: 'PUT',
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
        console.error('Error updating teacher:', error);
        return null;
    }
};