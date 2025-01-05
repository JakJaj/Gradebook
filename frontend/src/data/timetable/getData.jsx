import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;

export const fetchTimetable = async (classID) => {
    const token = Cookies.get('jwtToken');

    try {
        const response = await fetch(`${url}/timetables/classes/${classID}`, {
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
        
        return {
            monday : result.timetable.MONDAY,
            tuesday : result.timetable.TUESDAY,
            wednesday : result.timetable.WEDNESDAY,
            thursday : result.timetable.THURSDAY,
            friday : result.timetable.FRIDAY,
            saturday : result.timetable.SATURDAY,
            sunday : result.timetable.SUNDAY,
        }

    } catch (error) {
        console.error('Error fetching teachers:', error);
        return [];
    }
};