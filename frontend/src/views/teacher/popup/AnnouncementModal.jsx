import React, { useState, useEffect } from 'react';
import { fetchUserDetails } from '../../../data/user/getUser';

const AnnouncementModal = ({ isOpen, onClose, onSave }) => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [creator, setCreator] = useState('');

    const handleSave = () => {
        if (title.trim() && content.trim()) {
            
            
            onSave({ title, content, creator, date: new Date() });
            setTitle('');
            setContent('');
            setCreator('');
            onClose();
        }
    };

    useEffect(() => {
        const getUser = async () => {
            try {
                const userData = await fetchUserDetails();
                if (userData && userData.subClassID) {
                    setCreator(userData.subClassID);
                } else {
                    console.error('User data is missing subClassID:', userData);
                }
            } catch (error) {
                console.error('Error fetching user details:', error);
            }
        };

        if (isOpen) {
            getUser();
        }
    }, [isOpen]);

    if (!isOpen) return null;

    const handleOverlayClick = (e) => {
        if (e.target === e.currentTarget) {
            setTitle('');
            setContent('');
            setCreator('');
            onClose();
        }
    };

    return (
        <div
            className="fixed inset-0 bg-gray-600 bg-opacity-50 flex justify-center items-center"
            onClick={handleOverlayClick}
        >
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
                <button
                    onClick={() => handleSave()}
                    className="bg-blue-500 text-white p-2 rounded"
                >
                    Save
                </button>
                <button
                    onClick={onClose}
                    className="bg-gray-500 text-white p-2 rounded ml-2"
                >
                    Cancel
                </button>
            </div>
        </div>
    );
};

export default AnnouncementModal;