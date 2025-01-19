import { data, useLocation } from 'react-router-dom';
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
import EditGradeModal from './popup/EditGradeModal';
import EditAttendanceModal from './popup/EditAttendanceModal';
import EditNoteModal from './popup/EditNoteModal';
import DeleteFieldModal from '../../components/DeleteFieldModal';
import { postGrade, postAttendance, postNote } from '../../data/student/postData';
import { deleteGrade, deleteAttendance, deleteNote } from '../../data/student/deleteData';
import { putGrade, putAttendance, putNote } from '../../data/student/putData';

function TeacherClassPage() {
    const [students, setStudents] = useState([]);
    const [selectedStudent, setSelectedStudent] = useState(null);
    const [selectedGrade, setSelectedGrade] = useState(null);
    const [selectedAttendance, setSelectedAttendance] = useState(null);
    const [selectedNote, setSelectedNote] = useState(null);
    const [isGradeModalOpen, setIsGradeModalOpen] = useState(false);
    const [isAttendanceModalOpen, setIsAttendanceModalOpen] = useState(false);
    const [isNoteModalOpen, setIsNoteModalOpen] = useState(false);
    const [isEditGradeModalOpen, setIsEditGradeModalOpen] = useState(false);
    const [isEditAttendanceModalOpen, setIsEditAttendanceModalOpen] = useState(false);
    const [isEditNoteModalOpen, setIsEditNoteModalOpen] = useState(false);
    const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false);
    const [deleteItemId, setDeleteItemId] = useState(null);
    const [deleteItemType, setDeleteItemType] = useState('');
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


    const openGradeModal = (student) => {
        setSelectedStudent(student);
        setSelectedGrade(null);
        setIsGradeModalOpen(true);
    };

    const openAttendanceModal = (student) => {
        setSelectedStudent(student);
        setSelectedAttendance(null);
        setIsAttendanceModalOpen(true);
    };
    
    const openNoteModal = (student) => {
        setSelectedStudent(student);
        setSelectedNote(null);
        setIsNoteModalOpen(true);
    };

    const handleEditGrade = (grade) => {
        setSelectedGrade(grade);
        setSelectedStudent(students.find(student => student.id === grade.studentID));
        setIsEditGradeModalOpen(true);
    };

    const handleDeleteGrade = async (grade) => {
        console.log("Delete grade", grade);
        setDeleteItemId(grade.id);
        setSelectedStudent(students.find(student => student.id === grade.studentID));
        setDeleteItemType('grade');
        setIsDeleteModalOpen(true);
    };

    const handleEditAttendance = (attendance) => {
        setSelectedAttendance(attendance);
        setSelectedStudent(students.find(student => student.id === attendance.studentID));
        setIsEditAttendanceModalOpen(true);
    };

    const handleDeleteAttendance = async (attendance) => {
        console.log("Delete attendance", attendance);
        setDeleteItemId(attendance.id);
        setSelectedStudent(students.find(student => student.id === attendance.studentID));
        setDeleteItemType('attendance');
        setIsDeleteModalOpen(true);
    };

    const handleEditNote = (note) => {
        setSelectedNote(note);
        setSelectedStudent(students.find(student => student.id === note.studentID));
        setIsEditNoteModalOpen(true);
    };

    const handleDeleteNote = async (note) => {
        console.log("Delete note", note);
        setDeleteItemId(note.id);
        setSelectedStudent(students.find(student => student.id === note.studentID));
        setDeleteItemType('note');
        setIsDeleteModalOpen(true);
    };


    const handleDelete = async (itemId) => {
        try {
            if (deleteItemType === 'grade') {
                const result = await deleteGrade(selectedStudent.id, itemId); 
                if(result){
                    setGrades(prevGrades => {
                        const updatedGrades = {
                            ...prevGrades,
                            [selectedStudent.id]: [...(prevGrades[selectedStudent.id] || []).filter(g => g.id !== itemId)]
                        };
                        return updatedGrades;
                    });
                }
            } else if (deleteItemType === 'attendance') {
                const result = await deleteAttendance(selectedStudent.id, itemId);
                if(result){
                    setAttendance(prevAttendance => {
                        const updatedAttendance = {
                            ...prevAttendance,
                            [selectedStudent.id]: [...(prevAttendance[selectedStudent.id] || []).filter(a => a.id !== itemId)]
                        }
                        return updatedAttendance;
                    });
                }
            } else if (deleteItemType === 'note') {
                
                const result = await deleteNote(selectedStudent.id, itemId);
                if(result){
                    setNotes(prevNotes => {
                        const updatedNote = {
                            ...prevNotes,
                            [selectedStudent.id]: [...(prevNotes[selectedStudent.id] || []).filter(n => n.id !== itemId)]
                        }
                        return updatedNote;
                    });
                }
            }
            setIsDeleteModalOpen(false);
            setDeleteItemId(null);
            setDeleteItemType('');
        } catch (error) {
            console.error(`Error deleting ${deleteItemType}:`, error);
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
                                        <button onClick={() => openGradeModal(info.row.original)} className="ml-2 p-2 bg-green-500 text-white rounded">+</button>
                                    </>
                                );
                            case 'attendance':
                                return (
                                    <>
                                        {info.getValue().map(att => (
                                            <AttendanceBox key={att.id} attendance={att} onEdit={handleEditAttendance} onDelete={handleDeleteAttendance} />
                                        ))}
                                        <button onClick={() => openAttendanceModal(info.row.original)} className="ml-2 p-2 bg-green-500 text-white rounded">+</button>
                                    </>
                                );
                            case 'notes':
                                return (
                                    <>
                                        {info.getValue().map(note => (
                                            <NoteBox key={note.id} note={note} onEdit={handleEditNote} onDelete={handleDeleteNote} />
                                        ))}
                                        <button onClick={() => openNoteModal(info.row.original)} className="ml-2 p-2 bg-green-500 text-white rounded">+</button>
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
            <Table columns={columns} data={students} key={`${selectedSection}-${JSON.stringify(grades)}-${JSON.stringify(attendance)}${JSON.stringify(notes)}`}/>
            {isGradeModalOpen && (
                <GradeModal
                    isOpen={isGradeModalOpen}
                    onClose={() => setIsGradeModalOpen(false)}
                    onSave={async (grade) => {
                        
                        const body = {
                            courseID : courseId,
                            mark : grade.mark,
                            description: grade.description,
                            magnitude: grade.magnitude,
                            date: grade.date,
                        }
                        const created = await postGrade(body, selectedStudent.id);
                        if (created) {
                            const createdGrade = {
                                id: created,
                                studentID: selectedStudent.id,
                                mark: Number(grade.mark),
                                magnitude: Number(grade.magnitude),
                                description: grade.description,
                                date: grade.date,
                            };
                            setGrades(prevGrades => {
                                const updatedGrades = {
                                    ...prevGrades,
                                    [selectedStudent.id]: [...(prevGrades[selectedStudent.id] || []), createdGrade]
                                };
                                return updatedGrades;
                            });
                            
                            setIsGradeModalOpen(false);
                        }
                    }}
                    oldGrade={selectedGrade}
                />
            )}
            {isAttendanceModalOpen && (
                <AttendanceModal
                    isOpen={isAttendanceModalOpen}
                    onClose={() => setIsAttendanceModalOpen(false)}
                    
                    onSave={async (attendance) => {
                        const body = {
                            studentID: selectedStudent.id,
                            courseID : courseId,
                            date: attendance.date,
                            status: attendance.status,
                        }

                        const created = await postAttendance(body, selectedStudent.id);
                        if (created) {
                            const createdAttendance = {
                                id: created,
                                studentID: selectedStudent.id,
                                date: attendance.date,
                                status: attendance.status,
                            };
                            setAttendance(prevAttendance => {
                                const updatedAttendance = {
                                    ...prevAttendance,
                                    [selectedStudent.id]: [...(prevAttendance[selectedStudent.id] || []), createdAttendance]
                                };
                                return updatedAttendance;
                            });
                            setIsAttendanceModalOpen(false);
                    }}}
                    oldAttendance={selectedAttendance}
                />
            )}
            {isNoteModalOpen && (
                <NoteModal
                    isOpen={isNoteModalOpen}
                    onClose={() => setIsNoteModalOpen(false)}
                    onSave={async (note) => {
                        const body = {
                            studentID: selectedStudent.id,
                            courseID : courseId,
                            title: note.title,
                            description: note.description,
                            date : note.date,
                        }
                        const created = await postNote(body, selectedStudent.id); 
                        if (created) {
                            const createdNote = {
                                id: created,
                                studentID: selectedStudent.id,
                                title: note.title,
                                description: note.description,
                                date: note.date,
                            };
                            setNotes(prevNotes => {
                                const updatedNotes = {
                                    ...prevNotes,
                                    [selectedStudent.id]: [...(prevNotes[selectedStudent.id] || []), createdNote]
                                };
                                return updatedNotes;
                            });
                            setIsNoteModalOpen(false);
                        }
                    }}
                    oldNote={selectedNote}
                />
            )}
            

            {isEditGradeModalOpen && (
                <EditGradeModal
                    isOpen={isEditGradeModalOpen}
                    onClose={() => setIsEditGradeModalOpen(false)}
                    onSave={async (grade) => {
                        const body = {
                            gradeID: selectedGrade.id,
                            courseID : courseId,
                            mark : grade.mark,
                            description: grade.description,
                            magnitude: grade.magnitude,
                            date: grade.date,
                        }
                        const created = await putGrade(body, selectedStudent.id);
                        if (created) {
                            const createdGrade = {
                                id: selectedGrade.id,
                                studentID: selectedStudent.id,
                                mark: Number(grade.mark),
                                magnitude: Number(grade.magnitude),
                                description: grade.description,
                                date: grade.date,
                            };
                            setGrades(prevGrades => {
                                const updatedGrades = {
                                    ...prevGrades,
                                    [selectedStudent.id]: [...(prevGrades[selectedStudent.id] || []).map(g => g.id === selectedGrade.id ? createdGrade : g)]
                                };
                                return updatedGrades;
                            });
                            setIsEditGradeModalOpen(false);
                        }
                    }}
                    oldGrade={selectedGrade}
                />
            )}
            {isEditAttendanceModalOpen && (
                <EditAttendanceModal
                    isOpen={isEditAttendanceModalOpen}
                    onClose={() => setIsEditAttendanceModalOpen(false)}
                    
                    onSave={async (attendance) => {
                        const body = {
                            attendanceID: selectedAttendance.id,
                            studentID: selectedStudent.id,
                            courseID : courseId,
                            date: attendance.date,
                            status: attendance.status,
                        }

                        const created = await putAttendance(body, selectedStudent.id);
                        if (created) {
                            const createdAttendance = {
                                id: selectedAttendance.id,
                                studentID: selectedStudent.id,
                                date: attendance.date,
                                status: attendance.status,
                            };
                            setAttendance(prevAttendance => {
                                const updatedAttendance = {
                                    ...prevAttendance,
                                    [selectedStudent.id]: [...(prevAttendance[selectedStudent.id] || []).map(a => a.id === selectedAttendance.id ? createdAttendance : a)]
                                };
                                return updatedAttendance;
                            });
                            setIsEditAttendanceModalOpen(false);
                        }
                        
                    }}
                    oldAttendance={selectedAttendance}
                />
            )}
            {isEditNoteModalOpen && (
                <EditNoteModal
                    isOpen={isEditNoteModalOpen}
                    onClose={() => setIsEditNoteModalOpen(false)}
                    onSave={async (note) => {
                        const body = {
                            noteID: selectedNote.id,
                            studentID: selectedStudent.id,
                            courseID : courseId,
                            title: note.title,
                            description: note.description,
                            date : note.date,
                        }
                        const created = await putNote(body, selectedStudent.id);
                        if (created) {
                            const createdNote = {
                                id: selectedNote.id,
                                studentID: selectedStudent.id,
                                title: note.title,
                                description: note.description,
                                date: note.date,
                            };
                            setNotes(prevNotes => {
                                const updatedNotes = {
                                    ...prevNotes,
                                    [selectedStudent.id]: [...(prevNotes[selectedStudent.id] || []).map(n => n.id === selectedNote.id ? createdNote : n)]
                                };
                                return updatedNotes;
                            });
                            setIsEditNoteModalOpen(false);
                        }
                    }}
                    oldNote={selectedNote}
                />
            )}
            {isDeleteModalOpen && (
                <DeleteFieldModal
                    isOpen={isDeleteModalOpen}
                    onClose={() => setIsDeleteModalOpen(false)}
                    onDelete={handleDelete}
                    itemId={deleteItemId}
                    itemType={deleteItemType}
                />
            )}
        </div>
    );
}

export default TeacherClassPage;