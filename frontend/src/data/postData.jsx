import Cookies from 'js-cookie';
import API_URL from './const';

const url = API_URL;

export const loginUser = async (email, password) => {
    try {
        const response = await fetch(`${url}/auth/login`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ email, password }),
        });

        if (response.ok) {
            const data = await response.json();
            Cookies.set('jwtToken', data.token, { expires: 1 });
            return data;
        } else {
            throw new Error("Login failed");
        }
    } catch (error) {
        console.error("Error during login:", error);
        throw error;
    }
};


export const createStudent = async (studentData) => {
    const token = Cookies.get('jwtToken');

    const [year, month, day] = studentData.dateOfBirth.split('-');
    const formattedDateOfBirth = `${day}-${month}-${year}`;
    const payload = {
        email: studentData.email,
        pesel: studentData.pesel,
        role: 'STUDENT',
        student: {
            classID: studentData.classID,
            firstName: studentData.firstName,
            lastName: studentData.lastName,
            dateOfBirth: formattedDateOfBirth,
            city: studentData.city,
            street: studentData.street,
            houseNumber: studentData.houseNumber,
        },
    };

    try {
        const response = await fetch(`${url}/auth/register/students`, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(payload),

        
        });
        console.log("Saved");

        if (!response.ok) {
            throw new Error('Failed to create student');
        }

        const data = await response.json();
        return {
            id: data.id,
            name: `${payload.student.firstName} ${payload.student.lastName}`,
            class: studentData.className,
            birthDate: payload.student.dateOfBirth,
            address: `${payload.student.street} ${payload.student.houseNumber}, ${payload.student.city}`,
        };
    } catch (error) {
        console.error('Error creating student:', error);
        throw error;
    }
};