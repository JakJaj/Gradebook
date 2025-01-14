import React, { useState } from 'react';
import moment from 'moment';

function NoteModal({ isOpen, onClose, onSave }) {
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [date, setDate] = useState('');

    const handleSave = () => {
        if (title.trim() && description.trim()) {
            onSave({ title, description, date });
            setTitle('');
            setDescription('');
            setDate(moment().format('YYYY-MM-DD'));
        }
    };

    const handleSelectToday = () => {
        setDate(moment().format('YYYY-MM-DD'));
    };

    if (!isOpen) return null;

    return (
        <div className="fixed inset-0 bg-gray-600 bg-opacity-50 flex justify-center items-center">
            <div className="bg-white p-6 rounded-lg shadow-md w-1/3">
                <h3 className="text-xl font-semibold mb-4">Add Note</h3>
                <input
                    type="text"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    placeholder="Title"
                    className="w-full p-2 border border-gray-300 rounded mb-4"
                />
                <textarea
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    placeholder="Description"
                    className="w-full p-2 border border-gray-300 rounded mb-4"
                    rows="4"
                />
                <div className="flex items-center mb-4">
                    <input
                        type="date"
                        value={date}
                        onChange={(e) => setDate(e.target.value)}
                        className="w-full p-2 border border-gray-300 rounded"
                    />
                    <button onClick={handleSelectToday} className="bg-blue-500 text-white p-2 rounded ml-2">
                        Today
                    </button>
                </div>
                <div className="flex justify-end">
                    <button onClick={onClose} className="px-4 py-2 bg-gray-500 text-white rounded mr-2">
                        Cancel
                    </button>
                    <button onClick={handleSave} className="px-4 py-2 bg-blue-500 text-white rounded">
                        Save
                    </button>
                </div>
            </div>
        </div>
    );
}

export default NoteModal;