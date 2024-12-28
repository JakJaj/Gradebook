import Cookies from 'js-cookie';
import API_URL from './const';

const url = API_URL;

export const updateStudent = async (studentData) => {
    const token = Cookies.get('jwtToken');

    const payload = {
        student: {
            studentID: studentData.studentID,
            classID: studentData.classID,
            firstName: studentData.firstName,
            lastName: studentData.lastName,
            dateOfBirth: studentData.dateOfBirth,
            city: studentData.city,
            street: studentData.street,
            houseNumber: studentData.houseNumber,
            email: studentData.email,
            pesel: studentData.pesel,
            
        },
    };

    try {
        const response = await fetch(`${url}/students`, {
            method: 'PUT',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(payload),
        });

        if (!response.ok) {
            throw new Error('Failed to update student');
        }

        console.log(response);

        return true;
    } catch (error) {
        console.error('Error updating student:', error);
        throw error;
    }
}