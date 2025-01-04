import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;

export const fetchClasses = async () => {
    const token = Cookies.get('jwtToken');
    try {
        const response = await fetch(`${url}/classes`, {
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

        if (!result || !Array.isArray(result.classes)) {
            throw new Error('Invalid response format');
        }


        return result.classes.map(theClass => ({
            id: theClass.classID,
            name: theClass.className,
            startYear: theClass.startYear,
            tutor : {
                id: theClass.tutor.teacherID,
                name: `${theClass.tutor.firstName} ${theClass.tutor.lastName}`,
            }
        }));

    } catch (error) {
        console.error('Error fetching classes:', error);
        return [];
    }
};

export const fetchClass = async (classId) => {
    const token = Cookies.get('jwtToken');
    try {
        const response = await fetch(`${url}/classes/${classId}`, {
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

        return {
            id: result.theClass.classID,
            name: result.theClass.className,
            startYear: result.theClass.startYear,
            tutor : {
                id: result.theClass.tutor.teacherID,
                name: `${result.theClass.tutor.firstName} ${result.theClass.tutor.lastName}`,
            }
        }
    } catch (error) {
        console.error(`Error fetching teacher with ID ${classId}:`, error);
        return null;
    }
};