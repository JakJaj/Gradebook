import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;

export const deleteParent = async (parentId) => {
    const token = Cookies.get('jwtToken');
    try {
        const response = await fetch(`${url}/parents/${parentId}`, {
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
        console.error(`Error deleting teacher with ID ${parentId}:`, error);
        return false;
    }
};


export const removeStudentFromParent = async (parentId, studentsList) => {
    const token = Cookies.get('jwtToken');

    const requestBody = {
        studentsIDs: studentsList
    }

    try {
        const response = await fetch(`${url}/parents/${parentId}/students`, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(requestBody),
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        return true;
    } catch (error) {
        console.error(`Error deleting teacher with ID ${parentId}:`, error);
        return false;
    }
};

