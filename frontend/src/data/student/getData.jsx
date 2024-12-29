import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;

export const fetchStudents = async () => {
    try {
        const token = Cookies.get('jwtToken');
        const response = await fetch(`${url}/students`, {
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json",
            },
        });

        if (!response.ok) {
            throw new Error("Failed to fetch students");
        }

        const text = await response.text();
        if (!text) {
            return [];
        }

        const result = JSON.parse(text);
        return result.students.map(student => ({
            id: student.studentID,
            name: `${student.firstName} ${student.lastName}`,
            class: student.className,
            birthDate: student.dateOfBirth,
            address: `${student.street} ${student.houseNumber}, ${student.city}`,
        }));
    } catch (error) {
        console.error('Error fetching data:', error);
        return [];
    }
};

export const fetchClasses = async () => {
    try {
        const token = Cookies.get('jwtToken');
        const response = await fetch(`${url}/classes`, {
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json",
            },
        });

        if (!response.ok) {
            throw new Error("Failed to fetch classes");
        }

        const text = await response.text();
        if (!text) {
            return [];
        }

        const result = JSON.parse(text);
        return result.classes.map(cls => ({
            id: cls.classID,
            name: cls.className,
        }));
    } catch (error) {
        console.error('Error fetching data:', error);
        return [];
    }
};


export const fetchStudent = async (studentID) => {
    try {
        const token = Cookies.get('jwtToken');
        const response = await fetch(`${url}/students/${studentID}`, {
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json",
            },
        });

        if (!response.ok) {
            throw new Error("Failed to fetch students");
        }

        const text = await response.text();
        if (!text) {
            return [];
        }

        const result = JSON.parse(text);
        return {
            id: result.student.studentID,
            firstName: result.student.firstName,
            lastName: result.student.lastName,
            city: result.student.city,
            street: result.student.street,
            houseNumber: result.student.houseNumber,
            dateOfBirth: result.student.dateOfBirth,
            email : result.student.email,
            pesel: result.student.pesel,
            classID: result.student.classID,
        }
    } catch (error) {
        console.error('Error fetching data:', error);
        return [];
    }
};