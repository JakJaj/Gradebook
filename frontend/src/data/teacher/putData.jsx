import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;

export const updateTeacher = async (teacherData) => {
    const token = Cookies.get('jwtToken');
    console.log("SENDING DATA");
    console.log(teacherData);
    try {
        const response = await fetch(`${url}/teachers`, {
            method: 'PUT',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(teacherData),
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const result = await response.json();

        return {
            id: result.theClass.classID,
            name: result.theClass.className,
            startYear: result.theClass.startYear,
            tutor : {
                id: result.theClass.tutor.teacherID,
                name: `${result.theClass.tutor.firstName} ${result.theClass.tutor.lastName}`,
            }
        }
    } catch (error) {
        console.error('Error updating class:', error);
        return null;
    }
};