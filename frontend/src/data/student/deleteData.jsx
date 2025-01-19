import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;

export const deleteStudent = async (studentId) => {
    try {
        const token = Cookies.get('jwtToken');
        const response = await fetch(`${url}/students/${studentId}`, {
            method: 'DELETE',
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json",
            },
        });

        if (!response.ok) {
            throw new Error("Failed to delete student");
        }

        return true;
    } catch (error) {
        console.error('Error deleting student:', error);
        return false;
    }
};

export const deleteGrade = async (studentID, gradeId) => {
    try {
        const token = Cookies.get('jwtToken');
        const response = await fetch(`${url}/students/${studentID}/grades/${gradeId}`, {
            method: 'DELETE',
            headers: {
                    "Authorization": `Bearer ${token}`,
                    "Content-Type": "application/json",
            },
        });

        if (!response.ok) {
            throw new Error("Failed to delete grade");
        }
        return true;
    } catch (error) {
        console.error('Error deleting grade:', error);
        return false;
    }

}

export const deleteAttendance = async (studentID, attendanceID) => {
    try{
        const token = Cookies.get('jwtToken');
        const response = await fetch(`${url}/students/${studentID}/attendances/${attendanceID}`, {
            method: 'DELETE',
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json",
            }
        });
        if (!response.ok) {
            throw new Error("Failed to delete attendance");
        }
        return true;
    }catch(error){
        console.error('Error deleting attendance:', error);
        return false;
    }
}

export const deleteNote = async (studentID, noteID) => {
    try{
        const token = Cookies.get('jwtToken');
        const response = await fetch(`${url}/students/${studentID}/notes/${noteID}`, {
            method: 'DELETE',
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json",
            }
        });
        if (!response.ok) {
            throw new Error("Failed to delete note");
        }
        return true;
    }catch(error){
        console.error('Error deleting note:', error);
        return false;
    }
}