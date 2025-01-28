import React, { useEffect, useState } from 'react';
import TopBar from '../../components/TopBar';
import StudentNoteBox from '../../components/StudentNoteBox';
import { useLocation, useNavigate } from 'react-router-dom';
import { fetchStudentNotes } from '../../data/student/getData';

const StudentNotesPage = () => {
    const [notes, setNotes] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [notesPerPage, setNotesPerPage] = useState(5);
    const location = useLocation();
    const navigate = useNavigate();
    const student = location.state.studentData;

    useEffect(() => {
        const fetchNotes = async () => {
            try {
                const response = await fetchStudentNotes(student.id);
                console.log('Fetched notes response:', response);
                setNotes(response);
            } catch (error) {
                console.error('Error fetching notes:', error);
            }
        };

        fetchNotes();
    }, [student]);

    const indexOfLastNote = currentPage * notesPerPage;
    const indexOfFirstNote = indexOfLastNote - notesPerPage;
    const currentNotes = notes.slice(indexOfFirstNote, indexOfLastNote);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <div>
            <TopBar title="Student Notes" />
            <div className="p-8">
                <button
                    onClick={() => navigate(-1)}
                    className="mb-4 px-4 py-2 bg-blue-500 text-white rounded"
                >
                    Go Back
                </button>
                <div className="notes-box p-4 border rounded-md bg-gray-100 shadow-md">
                    {notes.length === 0 ? (
                        <p className="text-center text-xl">No notes available.</p>
                    ) : (
                        <>
                            {currentNotes.map((note) => (
                                <StudentNoteBox key={note.noteID} note={note} />
                            ))}
                            <Pagination
                                notesPerPage={notesPerPage}
                                totalNotes={notes.length}
                                paginate={paginate}
                                currentPage={currentPage}
                                setNotesPerPage={setNotesPerPage}
                                currentNotes={currentNotes}
                            />
                        </>
                    )}
                </div>
            </div>
        </div>
    );
};

const Pagination = ({ notesPerPage, totalNotes, paginate, currentPage, setNotesPerPage, currentNotes }) => {
    const pageNumbers = [];

    for (let i = 1; i <= Math.ceil(totalNotes / notesPerPage); i++) {
        pageNumbers.push(i);
    }

    return (
        <div className="pagination flex items-center justify-between mt-4 max-w-4xl mx-auto">
            <div>
                <button
                    onClick={() => paginate(currentPage - 1)}
                    disabled={currentPage === 1}
                    className="px-4 py-2 bg-blue-500 text-white rounded disabled:opacity-50"
                >
                    Previous
                </button>
                <button
                    onClick={() => paginate(currentPage + 1)}
                    disabled={currentPage === pageNumbers.length}
                    className="ml-2 px-4 py-2 bg-blue-500 text-white rounded disabled:opacity-50"
                >
                    Next
                </button>
            </div>
            <span>
                Page{' '}
                <strong>
                    {currentPage} of {pageNumbers.length}
                </strong>{' '}
            </span>
            <select
                value={notesPerPage}
                onChange={(e) => setNotesPerPage(Number(e.target.value))}
                className="ml-2 px-4 py-2 border rounded"
            >
                {[5, 10, 20, 30, 40, 50].map((pageSize) => (
                    <option key={pageSize} value={pageSize}>
                        Show {pageSize}
                    </option>
                ))}
            </select>
            <span className="ml-4">
                Showing {currentNotes.length} of {totalNotes} results
            </span>
        </div>
    );
};

export default StudentNotesPage;