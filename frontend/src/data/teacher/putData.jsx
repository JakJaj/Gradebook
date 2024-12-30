import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;

export const updateTeacher = async (teacherData) => {
    const token = Cookies.get('jwtToken');
    console.log("SENDING DATA");
    console.log(teacherData);
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
            id: result.teacher.teacherID,
            firstName: result.teacher.firstName,
            lastName: result.teacher.lastName,
            email: result.teacher.email,
            pesel : result.teacher.pesel,
            dateOfBirth: result.teacher.dateOfBirth,
            dateOfEmployment: result.teacher.dateOfEmployment,
        }
    } catch (error) {
        console.error('Error updating teacher:', error);
        return null;
    }
};