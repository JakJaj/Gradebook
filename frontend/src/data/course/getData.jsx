import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;

export const fetchCourses = async () => {
    const token = Cookies.get('jwtToken');
    try {
        const response = await fetch(`${url}/courses`, {
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

        if (!result || !Array.isArray(result.courses)) {
            throw new Error('Invalid response format');
        }


        return result.courses.map(course => ({
            id: course.courseID,
            name: course.courseName,
            description: course.description,
            tutor : {
                id: course.tutor.teacherID,
                name: `${course.tutor.firstName} ${course.tutor.lastName}`,
            }
        }));

    } catch (error) {
        console.error('Error fetching teachers:', error);
        return [];
    }
};

export const fetchCourse = async (courseId) => {
    const token = Cookies.get('jwtToken');
    try {
        const response = await fetch(`${url}/courses/${courseId}`, {
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


        if (!result || typeof result !== 'object') {
            throw new Error('Invalid response format');
        }
        
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
        console.error(`Error fetching teacher with ID ${teacherId}:`, error);
        return null;
    }
};