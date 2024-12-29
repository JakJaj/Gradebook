import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;

export const deleteStudent = async (studentId) => {
    try {
        const token = Cookies.get('jwtToken');
        const response = await fetch(`${url}/students/${studentId}`, {
            method: 'DELETE',
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json",
            },
        });

        if (!response.ok) {
            throw new Error("Failed to delete student");
        }

        return true;
    } catch (error) {
        console.error('Error deleting student:', error);
        return false;
    }
};