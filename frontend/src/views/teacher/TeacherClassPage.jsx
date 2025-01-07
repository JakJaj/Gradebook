import React, { useState, useEffect } from 'react';
import { fetchStudents } from '../../data/student/getData';

function TeacherClassPage() {
    const [students, setStudents] = useState([]);
    const [selectedStudent, setSelectedStudent] = useState(null);
    const [grade, setGrade] = useState('');
    const [attendance, setAttendance] = useState('');
    const [note, setNote] = useState('');

    useEffect(() => {
        const getStudents = async () => {
            try {
                const studentsData = await fetchStudents(); //TODO: FETCH STUDENTS BY CLASS ID
                setStudents(studentsData);
            } catch (error) {
                console.error('Error fetching students:', error);
            }
        };
        getStudents();
    }, []);

    const handleAddGrade = async () => {
        if (selectedStudent && grade.trim()) {
            try {
                await addGrade(selectedStudent.id, grade);
                setGrade('');
                alert('Grade added successfully');
            } catch (error) {
                console.error('Error adding grade:', error);
            }
        }
    };

    const handleAddAttendance = async () => {
        if (selectedStudent && attendance.trim()) {
            try {
                await addAttendance(selectedStudent.id, attendance);
                setAttendance('');
                alert('Attendance entry added successfully');
            } catch (error) {
                console.error('Error adding attendance:', error);
            }
        }
    };

    const handleAddNote = async () => {
        if (selectedStudent && note.trim()) {
            try {
                await addNote(selectedStudent.id, note);
                setNote('');
                alert('Note added successfully');
            } catch (error) {
                console.error('Error adding note:', error);
            }
        }
    };

    return (
        <div className="p-8">
            <h1 className="text-2xl font-bold mb-4">Class Management</h1>
            <div className="mb-4">
                <h2 className="text-xl font-semibold">Students</h2>
                <ul>
                    {students.map(student => (
                        <li key={student.id} onClick={() => setSelectedStudent(student)}>
                            {student.name}
                        </li>
                    ))}
                </ul>
            </div>
            {selectedStudent && (
                <div className="mb-4">
                    <h2 className="text-xl font-semibold">Manage {selectedStudent.name}</h2>
                    <div className="mb-2">
                        <input
                            type="text"
                            value={grade}
                            onChange={(e) => setGrade(e.target.value)}
                            placeholder="Add Grade"
                            className="p-2 border border-gray-300 rounded"
                        />
                        <button onClick={handleAddGrade} className="bg-blue-500 text-white p-2 rounded ml-2">
                            Add Grade
                        </button>
                    </div>
                    <div className="mb-2">
                        <input
                            type="text"
                            value={attendance}
                            onChange={(e) => setAttendance(e.target.value)}
                            placeholder="Add Attendance"
                            className="p-2 border border-gray-300 rounded"
                        />
                        <button onClick={handleAddAttendance} className="bg-blue-500 text-white p-2 rounded ml-2">
                            Add Attendance
                        </button>
                    </div>
                    <div className="mb-2">
                        <input
                            type="text"
                            value={note}
                            onChange={(e) => setNote(e.target.value)}
                            placeholder="Add Note"
                            className="p-2 border border-gray-300 rounded"
                        />
                        <button onClick={handleAddNote} className="bg-blue-500 text-white p-2 rounded ml-2">
                            Add Note
                        </button>
                    </div>
                </div>
            )}
        </div>
    );
}

export default TeacherClassPage;