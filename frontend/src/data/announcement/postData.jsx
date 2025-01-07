import API_URL from '../const.jsx';
import Cookies from 'js-cookie';

const url = API_URL;

export const postAnnouncements = async (announcementData) => {
    const token = Cookies.get('jwtToken');

    try {
        const response = await fetch(`${url}/announcements`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`,
            },
            body : JSON.stringify(announcementData),
        });

        const result = await response.json();

        console.log('result', result);

        if (response.ok) {
            
            return {
                announcementID: result.announcement.announcementID,
                title: result.announcement.title,
                content: result.announcement.content,
                teacherID: result.announcement.teacher.teacherID,
                date: result.announcement.date,
            };

        }
    }
    catch (error) {
        console.error('Error fetching announcements:', error);
    }
}
