import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;

export const deleteTimetableEntry = async (timetableID) => {
    const token = Cookies.get('jwtToken');

    try {
        const response = await fetch(`${url}/timetables/${timetableID}`, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            }
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const result = await response.json();

    } catch (error) {
        console.error('Error fetching teachers:', error);
        return [];
    }
};