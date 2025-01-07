import API_URL from '../const.jsx';
import Cookies from 'js-cookie';

const url = API_URL;

export const deleteAnnouncement = async (announcementID) => {
    const token = Cookies.get('jwtToken');

    try {
        const response = await fetch(`${url}/announcements/${announcementID}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`,
            },
        });

        const result = await response.json();

        console.log('result', result);

        if (response.ok) {
            
            return true;

        }
        return false;
    }
    catch (error) {
        console.error('Error fetching announcements:', error);
    }
}
