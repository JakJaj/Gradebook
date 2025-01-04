import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;

export const updateClass = async (classData) => {
    const token = Cookies.get('jwtToken');

    console.log(classData)
    try {
        const response = await fetch(`${url}/classes`, {
            method: 'PUT',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(classData),
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
        console.error('Error updating teacher:', error);
        return null;
    }
};