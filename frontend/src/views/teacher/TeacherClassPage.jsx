import { useLocation } from 'react-router-dom';
import { fetchStudentsFromClass } from '../../data/class/getData';
import Table from '../../components/Table';
import GradeModal from './popup/GradeModal';
import AttendanceModal from './popup/AttendanceModal';
import NoteModal from './popup/NoteModal';
import TopBar from '../../components/TopBar';
import React, { useState, useEffect, useMemo } from 'react';

function TeacherClassPage() {
    const [students, setStudents] = useState([]);
    const [selectedStudent, setSelectedStudent] = useState(null);
    const [isGradeModalOpen, setIsGradeModalOpen] = useState(false);
    const [isAttendanceModalOpen, setIsAttendanceModalOpen] = useState(false);
    const [isNoteModalOpen, setIsNoteModalOpen] = useState(false);
    const [selectedSection, setSelectedSection] = useState('grades');
    const location = useLocation();
    const courseId = location.state.courseId;

    const classData = useMemo(() => ({
        classId: location.state.classData.classId,
        name: location.state.classData.className
    }), [location.state.classData.classId, location.state.classData.className]);

    useEffect(() => {
        const getStudents = async () => {
            try {
                const studentsData = await fetchStudentsFromClass(classData.classId);
                const sortedStudentsData = studentsData.sort((a, b) => {
                    if (a.lastName < b.lastName) return -1;
                    if (a.lastName > b.lastName) return 1;
                    return 0;
                });

                setStudents(sortedStudentsData);
            } catch (error) {
                console.error('Error fetching students:', error);
            }
        };
        if (classData.classId) {
            getStudents();
        }
    }, [classData]);

    const columns = [
        {
            id: 'student',
            header: 'Student',
            accessorFn: row => `${row.firstName} ${row.lastName}`,
            cell: info => (
                <div className="flex justify-between items-center">
                    <span>{info.row.original.firstName} {info.row.original.lastName}</span>
                </div>
            )
        },
        {
            id: 'details',
            header: selectedSection.charAt(0).toUpperCase() + selectedSection.slice(1),
            accessorFn: row => {
                switch (selectedSection) {
                    case 'grades':
                        return row.grades; // Assuming row.grades contains the grades data
                    case 'attendance':
                        return row.attendance; // Assuming row.attendance contains the attendance data
                    case 'notes':
                        return row.notes; // Assuming row.notes contains the notes data
                    default:
                        return '';
                }
            },
            cell: info => (
                <div className="flex justify-between items-center">
                    <span>
                        {(() => {
                            switch (selectedSection) {
                                case 'grades':
                                    return info.row.original.grades;
                                case 'attendance':
                                    return info.row.original.attendance;
                                case 'notes':
                                    return info.row.original.notes;
                                default:
                                    return '';
                            }
                        })()}
                    </span>
                    <button
                        onClick={() => openModal(info.row.original, selectedSection)}
                        className="px-4 py-2 bg-blue-500 text-white rounded mr-2"
                    >
                        +
                    </button>
                </div>
            )
        }
    ];

    return (
        <div className="p-8">
            <TopBar title={"Class: " + classData.name} />
            <div className="flex justify-center items-center my-4">
                <button
                    onClick={() => setSelectedSection('grades')}
                    className={`mx-3 px-4 py-2 rounded ${selectedSection === 'grades' ? 'bg-blue-500 text-white' : 'bg-blue-700 text-white'}`}
                >
                    Grades
                </button>
                <button
                    onClick={() => setSelectedSection('attendance')}
                    className={`mx-3 px-4 py-2 rounded ${selectedSection === 'attendance' ? 'bg-blue-500 text-white' : 'bg-blue-700 text-white'}`}
                >
                    Attendance
                </button>
                <button
                    onClick={() => setSelectedSection('notes')}
                    className={`mx-3 px-4 py-2 rounded ${selectedSection === 'notes' ? 'bg-blue-500 text-white' : 'bg-blue-700 text-white'}`}
                >
                    Notes
                </button>
            </div>
            <Table columns={columns} data={students} />
            {isGradeModalOpen && (
                <GradeModal
                    isOpen={isGradeModalOpen}
                    onClose={() => setIsGradeModalOpen(false)}
                    onSave={async (grade) => {
                        await addGrade(selectedStudent.id, grade);
                        setIsGradeModalOpen(false);
                    }}
                />
            )}
            {isAttendanceModalOpen && (
                <AttendanceModal
                    isOpen={isAttendanceModalOpen}
                    onClose={() => setIsAttendanceModalOpen(false)}
                    onSave={async (attendance) => {
                        await addAttendance(selectedStudent.id, attendance);
                        setIsAttendanceModalOpen(false);
                    }}
                />
            )}
            {isNoteModalOpen && (
                <NoteModal
                    isOpen={isNoteModalOpen}
                    onClose={() => setIsNoteModalOpen(false)}
                    onSave={async (note) => {
                        await addNote(selectedStudent.id, note);
                        setIsNoteModalOpen(false);
                    }}
                />
            )}
        </div>
    );
}

export default TeacherClassPage;