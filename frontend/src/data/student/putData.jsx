import Cookies from 'js-cookie';
import API_URL from '../const';

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

        return true;
    } catch (error) {
        console.error('Error updating student:', error);
        throw error;
    }
}

export const putGrade = async (gradeData, studentID) => {
    const token = Cookies.get('jwtToken');

    try {
        const response = await fetch(`${url}/students/${studentID}/grades`, {
            method: 'PUT',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(gradeData),
        });

        if (!response.ok) {
            throw new Error('Failed to update grade');
        }

        return true;
    } catch (error) {
        console.error('Error updating grade:', error);
        throw error;
    }
}

export const putAttendance = async (attendanceData, studentID) => {
    const token = Cookies.get('jwtToken');

    try {
        const response = await fetch(`${url}/students/${studentID}/attendances`, {
            method: 'PUT',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(attendanceData),
        });

        if (!response.ok) {
            throw new Error('Failed to update attendance');
        }

        return true;
    } catch (error) {
        console.error('Error updating attendance:', error);
        throw error;
    }
}

export const putNote = async (noteData, studentID) => {
    const token = Cookies.get('jwtToken');

    try {
        const response = await fetch(`${url}/students/${studentID}/notes`, {
            method: 'PUT',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(noteData),
        });

        if (!response.ok) {
            throw new Error('Failed to update note');
        }

        return true;
    } catch (error) {
        console.error('Error updating note:', error);
        throw error;
    }
}
