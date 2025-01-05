import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;


export const createTimetable = async (timetableList) => {
    const token = Cookies.get('jwtToken');
    
    const timetable = { timetables: timetableList };

    try {
        const response = await fetch(`${url}/timetables`, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(timetable),
        });

        if (response.ok) {
            return response.json();
        }
    } catch (error) {
        console.error('Error creating teacher:', error);
        return null;
    }
}
