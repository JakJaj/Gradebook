import React from 'react';



const NoteBox = ({ note }) => {

    return (
        <div className="relative group">
            <div className="note-box bg-teal-600 text-white p-2 rounded-md">
                <p>{note.title}</p>
            </div>
            <div className="absolute bottom-full left-1/2 transform -translate-x-1/2 hidden group-hover:block bg-white text-black text-sm p-2 rounded-md shadow-lg w-64">
                <p><strong>Title:</strong> {note.title}</p>
                <p><strong>Description:</strong> {note.description}</p>
                <p><strong>Date:</strong> {note.date}</p>
            </div>
        </div>
    );
};

export default NoteBox;