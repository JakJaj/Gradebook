import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;

export const fetchClasses = async () => {
    const token = Cookies.get('jwtToken');
    try {
        const response = await fetch(`${url}/classes`, {
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

        if (!result || !Array.isArray(result.classes)) {
            throw new Error('Invalid response format');
        }


        return result.classes.map(theClass => ({
            id: theClass.classID,
            name: theClass.className,
            startYear: theClass.startYear,
            tutor : {
                id: theClass.tutor.teacherID,
                name: `${theClass.tutor.firstName} ${theClass.tutor.lastName}`,
            }
        }));

    } catch (error) {
        console.error('Error fetching classes:', error);
        return [];
    }
};

export const fetchClass = async (classId) => {
    const token = Cookies.get('jwtToken');
    try {
        const response = await fetch(`${url}/classes/${classId}`, {
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
            id: result.theClass.classID,
            name: result.theClass.className,
            startYear: result.theClass.startYear,
            tutor : {
                id: result.theClass.tutor.teacherID,
                name: `${result.theClass.tutor.firstName} ${result.theClass.tutor.lastName}`,
            }
        }
    } catch (error) {
        console.error(`Error fetching teacher with ID ${classId}:`, error);
        return null;
    }
};

export const fetchStudentsFromClass = async (classId) => {
    const token = Cookies.get('jwtToken');
    try {
        const response = await fetch(`${url}/classes/${classId}/students`, {
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

        if (!result || !Array.isArray(result.students)) {
            throw new Error('Invalid response format');
        }

        return result.students.map(student => ({
            id: student.studentID,
            firstName: student.firstName,
            lastName: student.lastName,
        }));

    } catch (error) {
        console.error('Error fetching students:', error);
        return [];
    }
}


export const fetchGradesByCourseID = async (courseID, classID) => {
    const token = Cookies.get('jwtToken');

    try {
        const response = await fetch(`${url}/courses/${courseID}/classes/${classID}/grades`, {
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


        const gradesByStudent = {};
        for (const studentID in result.studentsGrades) {
            gradesByStudent[studentID] = result.studentsGrades[studentID].map(grade => ({
                id: grade.gradeID,
                studentID: parseInt(studentID, 10),
                mark: grade.mark,
                magnitude: grade.magnitude,
                description: grade.description,
                date: grade.date,
            }));
        }

        return gradesByStudent;

    } catch (error) {
        console.error('Error fetching grades:', error);
        return [];
    }
}

export const fetchAttendanceByCourseID = async (courseID, classID) => {
    const token = Cookies.get('jwtToken');

    try {
        const response = await fetch(`${url}/courses/${courseID}/classes/${classID}/attendance`, {
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

        const attendanceByStudent = {};
        for (const studentID in result.studentsAttendance) {
            attendanceByStudent[studentID] = result.studentsAttendance[studentID].map(attendance => ({
                id: attendance.attendanceID,
                studentID: parseInt(studentID, 10),
                date: attendance.date,
                status: attendance.status,
            }));
        }

        return attendanceByStudent;

    } catch (error) {
        console.error('Error fetching attendance:', error);
        return [];
    }
}


export const fetchNotesByCourseID = async (courseID, classID) => {
    const token = Cookies.get('jwtToken');

    try {
        const response = await fetch(`${url}/courses/${courseID}/classes/${classID}/notes`, {
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

        const notesByStudent = {};
        for (const studentID in result.studentsNotes) {
            notesByStudent[studentID] = result.studentsNotes[studentID].map(note => ({
                id: note.noteID,
                studentID: parseInt(studentID, 10),
                description: note.description,
                date: note.date,
                timetableEntry: note.timetableEntry
            }));
        }

        return notesByStudent;

    } catch (error) {
        console.error('Error fetching notes:', error);
        return [];
    }
}
