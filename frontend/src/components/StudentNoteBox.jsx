import React from 'react';

const StudentNoteBox = ({ note }) => {
    return (
        <div className="note-box p-4 m-2 border rounded-md bg-white shadow-md">
            <p><strong>Title:</strong> {note.title}</p>
            <p><strong>Description:</strong> {note.description}</p>
            <p><strong>Date:</strong> {note.date}</p>
            <p><strong>Course:</strong> {note.course.courseName}</p>
            <p><strong>Tutor:</strong> {note.course.tutor.firstName} {note.course.tutor.lastName}</p>
        </div>
    );
};

export default StudentNoteBox;