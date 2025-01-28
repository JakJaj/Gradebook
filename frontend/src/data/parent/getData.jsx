import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;

export const fetchParents = async () => {
    const token = Cookies.get('jwtToken');
    try {
        const response = await fetch(`${url}/parents`, {
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

        if (!result || !Array.isArray(result.parents)) {
            throw new Error('Invalid response format');
        }


        return result.parents.map(parent => ({
            id: parent.parentID,
            name : `${parent.firstName} ${parent.lastName}`,
            email: parent.email,
            pesel: parent.pesel,
        }));

    } catch (error) {
        console.error('Error fetching teachers:', error);
        return [];
    }
};

export const fetchParent = async (parentId) => {
    const token = Cookies.get('jwtToken');
    try {
        const response = await fetch(`${url}/parents/${parentId}`, {
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
            id: result.parent.parentID,
            firstName: result.parent.firstName,
            lastName : result.parent.lastName,
            email: result.parent.email,
            pesel: result.parent.pesel,
        }

    } catch (error) {
        console.error(`Error fetching parent with ID ${parentId}:`, error);
        return null;
    }
};


export const fetchAssociatedStudents = async (parentId) => {
    const token = Cookies.get('jwtToken');
    try {
        const response = await fetch(`${url}/parents/${parentId}/students`, {
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
        
        return result.students.map(student => ({
            id: student.studentID,
            name: `${student.firstName} ${student.lastName}`,
            class: student.classID,
            birthDate: student.dateOfBirth,
            address: `${student.street} ${student.houseNumber}, ${student.city}`,
        }));

    } catch (error) {
        console.error(`Error fetching  with ID ${parentId}:`, error);
        return null;
    }
};
