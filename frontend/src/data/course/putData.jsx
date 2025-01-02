import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;

export const updateCourse = async (courseData) => {
    const token = Cookies.get('jwtToken');
    try {
        const response = await fetch(`${url}/courses`, {
            method: 'PUT',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(courseData),
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const result = await response.json();

        return {
            id: result.course.courseID,
            name: result.course.courseName,
            description: result.course.description,
            tutor : {
                id: result.course.tutor.teacherID,
                name: `${result.course.tutor.firstName} ${result.course.tutor.lastName}`,
            }
        }
    } catch (error) {
        console.error('Error updating teacher:', error);
        return null;
    }
};