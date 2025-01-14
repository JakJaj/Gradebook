import React from 'react';

const NoteBox = ({ note, onEdit, onDelete }) => {
    return (
        <div className="relative group">
            <div className="note-box bg-teal-600 text-white p-2 rounded-md">
                <p>{note.title}</p>
            </div>
            <div className="absolute bottom-full left-1/2 transform -translate-x-1/2 hidden group-hover:block bg-white text-black text-sm p-2 rounded-md shadow-lg w-64">
                <p><strong>Title:</strong> {note.title}</p>
                <p><strong>Description:</strong> {note.description}</p>
                <p><strong>Date:</strong> {note.date}</p>
                <div className="flex justify-between mt-2">
                    <button onClick={() => onEdit(note)} className="bg-blue-500 text-white px-2 py-1 rounded">
                        Edit
                    </button>
                    <button onClick={() => onDelete(note)} className="bg-red-500 text-white px-2 py-1 rounded">
                        Delete
                    </button>
                </div>
            </div>
        </div>
    );
};

export default NoteBox;