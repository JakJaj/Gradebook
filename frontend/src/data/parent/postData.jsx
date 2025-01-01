import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;

export const createParent = async (parentData) => {
    const token = Cookies.get('jwtToken');
    

    try {
        const response = await fetch(`${url}/auth/register/parents`, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(parentData),
        });

        console.log(response)
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


export const addStudentToParent = async (parentId, studentsList) => {
    const token = Cookies.get('jwtToken');
    
    const requestBody = {
        studentsIDs: studentsList
    }

    try {
        const response = await fetch(`${url}/parents/${parentId}/students`, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(requestBody),
        });

        console.log(response)
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