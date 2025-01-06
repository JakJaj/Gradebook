import React, { useState } from 'react';

const AnnouncementModal = ({ isOpen, onClose, onSave }) => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [creator, setCreator] = useState('');

    const handleSave = () => {
        if (title.trim() && content.trim() && creator.trim()) {
            onSave({ title, content, creator, date: new Date() });
            setTitle('');
            setContent('');
            setCreator('');
            onClose();
        }
    };

    if (!isOpen) return null;

    return (
        <div className="fixed inset-0 bg-gray-600 bg-opacity-50 flex justify-center items-center">
            <div className="bg-white p-6 rounded-lg shadow-md w-1/3">
                <h3 className="text-xl font-semibold mb-4">New Announcement</h3>
                <input
                    type="text"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    placeholder="Title"
                    className="w-full p-2 border border-gray-300 rounded mb-4"
                />
                <textarea
                    value={content}
                    onChange={(e) => setContent(e.target.value)}
                    placeholder="Content"
                    className="w-full p-2 border border-gray-300 rounded mb-4"
                />
                <input
                    type="text"
                    value={creator}
                    onChange={(e) => setCreator(e.target.value)}
                    placeholder="Creator"
                    className="w-full p-2 border border-gray-300 rounded mb-4"
                />
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
};

export default AnnouncementModal;