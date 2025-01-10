import React, { useState } from 'react';

function GradeModal({ isOpen, onClose, onSave }) {
    const [grade, setGrade] = useState('');

    const handleSave = () => {
        if (grade.trim()) {
            onSave(grade);
            setGrade('');
        }
    };

    if (!isOpen) return null;

    return (
        <div className="fixed inset-0 bg-gray-600 bg-opacity-50 flex justify-center items-center">
            <div className="bg-white p-6 rounded-lg shadow-md w-1/3">
                <h3 className="text-xl font-semibold mb-4">Add Grade</h3>
                <input
                    type="text"
                    value={grade}
                    onChange={(e) => setGrade(e.target.value)}
                    placeholder="Grade"
                    className="w-full p-2 border border-gray-300 rounded mb-4"
                />
                <button onClick={handleSave} className="bg-blue-500 text-white p-2 rounded">
                    Save
                </button>
                <button onClick={onClose} className="bg-gray-500 text-white p-2 rounded ml-2">
                    Cancel
                </button>
            </div>
        </div>
    );
}

export default GradeModal;