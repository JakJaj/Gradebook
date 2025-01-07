import API_URL from '../const.jsx';
import Cookies from 'js-cookie';

const url = API_URL;

export const fetchAnnouncements = async () => {
    const token = Cookies.get('jwtToken');

    try {
        const response = await fetch(`${url}/announcements`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`,
            },
        });

        const result = await response.json();

        console.log('result', result);

        if (response.ok) {
            
            return result.announcements.map((announcement) => ({
                    announcementID: announcement.announcementID,
                    title: announcement.title,
                    content: announcement.content,
                    teacherID: announcement.teacher.teacherID,
                    date: announcement.date
            }));
        }
        }
    catch (error) {
        console.error('Error fetching announcements:', error);
    }
}
