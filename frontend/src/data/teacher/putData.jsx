import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;

export const updateTeacher = async (teacherData) => {
    const token = Cookies.get('jwtToken');

    try {
        const response = await fetch(`${url}/teachers`, {
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

        return {
            teacherID: result.teacher.teacherID,
            firstName: result.teacher.firstName,
            lastName: result.teacher.lastName,
            dateOfBirth: result.teacher.dateOfBirth,
            dateOfEmployment: result.teacher.dateOfEmployment,
            email: result.teacher.email,
            pesel: result.teacher.pesel,
        }
    } catch (error) {
        console.error('Error updating class:', error);
        return null;
    }
};