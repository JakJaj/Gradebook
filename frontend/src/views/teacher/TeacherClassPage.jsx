import { useLocation } from 'react-router-dom';
import { fetchStudentsFromClass, fetchGradesByCourseID, fetchAttendanceByCourseID, fetchNotesByCourseID } from '../../data/class/getData';
import Table from '../../components/Table';
import GradeModal from './popup/GradeModal';
import AttendanceModal from './popup/AttendanceModal';
import NoteModal from './popup/NoteModal';
import TopBar from '../../components/TopBar';
import React, { useState, useEffect, useMemo } from 'react';
import GradeBox from '../../components/GradeBox';
import AttendanceBox from '../../components/AttendanceBox';
import NoteBox from '../../components/NoteBox';

function TeacherClassPage() {
    const [students, setStudents] = useState([]);
    const [selectedStudent, setSelectedStudent] = useState(null);
    const [isGradeModalOpen, setIsGradeModalOpen] = useState(false);
    const [isAttendanceModalOpen, setIsAttendanceModalOpen] = useState(false);
    const [isNoteModalOpen, setIsNoteModalOpen] = useState(false);
    const [selectedSection, setSelectedSection] = useState('grades');
    const [grades, setGrades] = useState([]);
    const [attendance, setAttendance] = useState([]);
    const [notes, setNotes] = useState([]);
    const location = useLocation();
    const courseId = location.state.classData.courseId;

    const classData = useMemo(() => {
        return {
            classId: location.state.classData.classId,
            name: location.state.classData.className
        };
    }, [location.state.classData.classId, location.state.classData.className]);

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
        getStudents();
    }, [classData]);
    
    useEffect(() => {
        const getGrades = async () => {
            if (!classData.classId) return; 
            try {
                const grades = await fetchGradesByCourseID(courseId, classData.classId);
                setGrades(grades);
            } catch (error) {
                console.error('Error fetching grades:', error);
            }
        };
    
        if (classData.classId) {
            getGrades();
        }
    }, [classData, courseId]);

    useEffect(() => {
        const getAttendance = async () => {
            if (!classData.classId) return;
            try {
                const attendance = await fetchAttendanceByCourseID(courseId, classData.classId);
                setAttendance(attendance);
            } catch (error) {
                console.error('Error fetching attendance:', error);
            }
        };
        if (classData.classId) {
            getAttendance();
        }
    }, [classData, courseId]);

    useEffect(() => {
        const getNotes = async () => {
            if (!classData.classId) return;
            try {
                const notes = await fetchNotesByCourseID(courseId, classData.classId);
                setNotes(notes);
            } catch (error) {
                console.error('Error fetching notes:', error);
            }
        };
        if (classData.classId) {
            getNotes();
        }
    }, [classData, courseId]);


    const openGradeModal = () => {
        setIsGradeModalOpen(true);
    };

    const openAttendanceModal = () => {
        setIsAttendanceModalOpen(true);
    };
    
    const openNotesModal = () => {
        setIsNoteModalOpen(true);
    };

    const handleEditGrade = (grade) => {
        console.log("Edit grade", grade);
        // grade api call is not defined
        setSelectedStudent(grade);
        setIsGradeModalOpen(true);
    };

    const handleDeleteGrade = async (grade) => {
        console.log("Delete grade", grade);
        try {
            await deleteGrade(grade.id); // deleteGrade is not defined
            setGrades(grades.filter(g => g.id !== grade.id));
        } catch (error) {
            console.error('Error deleting grade:', error);
        }
    };

    const handleEditAttendance = (attendance) => {
        console.log("Edit attendance", attendance);
        // attendance api call is not defined
        setSelectedStudent(attendance);
        setIsAttendanceModalOpen(true);
    };

    const handleDeleteAttendance = async (attendance) => {
        console.log("Delete attendance", attendance);
        try {
            await deleteAttendance(attendance.id); // deleteAttendance is not defined
            setAttendance(attendance.filter(a => a.id !== attendance.id));
        } catch (error) {
            console.error('Error deleting attendance:', error);
        }
    };

    const handleEditNote = (note) => {
        console.log("Edit note", note);
        // note api call is not defined
        setSelectedStudent(note);
        setIsNoteModalOpen(true);
    };

    const handleDeleteNote = async (note) => {
        console.log("Delete note", note);
        try {
            await deleteNote(note.id); // deleteNote is not defined
            setNotes(notes.filter(n => n.id !== note.id));
        } catch (error) {
            console.error('Error deleting note:', error);
        }
    };

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
                        return grades[row.id] || [];
                    case 'attendance':
                        return attendance[row.id] || [];
                    case 'notes':
                        return notes[row.id] || [];
                    default:
                        return [];
                }
            },
            cell: info => (
                <div className="flex flex-wrap">
                    {(() => {
                        switch (selectedSection) {
                            case 'grades':
                                return (
                                    <>
                                        {info.getValue().map(grade => (
                                            <GradeBox key={grade.id} grade={grade} onEdit={handleEditGrade} onDelete={handleDeleteGrade} />
                                        ))}
                                        <button onClick={openGradeModal} className="ml-2 p-2 bg-green-500 text-white rounded">+</button>
                                    </>
                                );
                            case 'attendance':
                                return (
                                    <>
                                        {info.getValue().map(att => (
                                            <AttendanceBox key={att.id} attendance={att} onEdit={handleEditAttendance} onDelete={handleDeleteAttendance} />
                                        ))}
                                        <button onClick={openAttendanceModal} className="ml-2 p-2 bg-green-500 text-white rounded">+</button>
                                    </>
                                );
                            case 'notes':
                                return (
                                    <>
                                        {info.getValue().map(note => (
                                            <NoteBox key={note.id} note={note} onEdit={handleEditNote} onDelete={handleDeleteNote} />
                                        ))}
                                        <button onClick={openNotesModal} className="ml-2 p-2 bg-green-500 text-white rounded">+</button>
                                    </>
                                );
                            default:
                                return null;
                        }
                    })()}
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
            <Table columns={columns} data={students} key={selectedSection}/>
            {isGradeModalOpen && (
                <GradeModal
                    isOpen={isGradeModalOpen}
                    onClose={() => setIsGradeModalOpen(false)}
                    onSave={async (grade) => {
                        await addGrade(selectedStudent.id, grade); // addGrade is not defined
                        setIsGradeModalOpen(false);
                    }}
                />
            )}
            {isAttendanceModalOpen && (
                <AttendanceModal
                    isOpen={isAttendanceModalOpen}
                    onClose={() => setIsAttendanceModalOpen(false)}
                    onSave={async (attendance) => {
                        await addAttendance(selectedStudent.id, attendance); // addAttendance is not defined
                        setIsAttendanceModalOpen(false);
                    }}
                />
            )}
            {isNoteModalOpen && (
                <NoteModal
                    isOpen={isNoteModalOpen}
                    onClose={() => setIsNoteModalOpen(false)}
                    onSave={async (note) => {
                        await addNote(selectedStudent.id, note); // addNote is not defined
                        setIsNoteModalOpen(false);
                    }}
                />
            )}
        </div>
    );
}

export default TeacherClassPage;