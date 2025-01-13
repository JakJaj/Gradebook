import React from 'react';



const NoteBox = ({ note }) => {

    return (
        <div className="relative group">
            <div className="note-box bg-blue-400 p-2 rounded-md">
                <p>{note.description}</p>
            </div>
            <div className="absolute bottom-full left-1/2 transform -translate-x-1/2 mb-6 hidden group-hover:block bg-white text-black text-sm p-2 rounded-md shadow-lg">
                <p><strong>Note ID:</strong> {note.id}</p>
                <p><strong>Description:</strong> {note.description}</p>
                <p><strong>Date:</strong> {note.date}</p>
                <p><strong>Timetable Entry:</strong> {note.timetableEntry ? note.timetableEntry.timetableID : 'N/A'}</p>
            </div>
        </div>
    );
};

export default NoteBox;