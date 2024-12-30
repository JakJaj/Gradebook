import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;

export const fetchTeachers = async () => {
    const token = Cookies.get('jwtToken');
    try {
        const response = await fetch(`${url}/teachers`, {
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

        if (!result || !Array.isArray(result.teachers)) {
            throw new Error('Invalid response format');
        }


        return result.teachers.map(teacher => ({
            id: teacher.teacherID,
            name: `${teacher.firstName} ${teacher.lastName}`,
            email: teacher.email,
            pesel : teacher.pesel,
            dateOfBirth: teacher.dateOfBirth,
            dateOfEmployment: teacher.dateOfEmployment,
        }));

    } catch (error) {
        console.error('Error fetching teachers:', error);
        return [];
    }
};

export const fetchTeacher = async (teacherId) => {
    const token = Cookies.get('jwtToken');
    try {
        const response = await fetch(`${url}/teachers/${teacherId}`, {
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


        if (!result || typeof result !== 'object') {
            throw new Error('Invalid response format');
        }

        console.log(result);
        
        return {
            id: result.teacher.teacherID,
            name: `${result.teacher.firstName} ${result.teacher.lastName}`,
            email: result.teacher.email,
            pesel : result.teacher.pesel,
            dateOfBirth: result.teacher.dateOfBirth,
            dateOfEmployment: result.teacher.dateOfEmployment,
        }
    } catch (error) {
        console.error(`Error fetching teacher with ID ${teacherId}:`, error);
        return null;
    }
};