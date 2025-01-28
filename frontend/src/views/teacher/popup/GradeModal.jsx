import React, { useState, useEffect } from 'react';
import moment from 'moment';
const GradeModal = ({ isOpen, onClose, onSave }) => {
    const [grade, setGrade] = useState('');
    const [magnitude, setMagnitude] = useState('');
    const [description, setDescription] = useState('');
    const [date, setDate] = useState('');

    const handleSave = () => {
        if (grade && magnitude && description && date) {
            onSave({
                mark : grade,
                description : description,
                magnitude : magnitude,
                date : moment(date).format('DD-MM-YYYY'),
            });
            setGrade('');
            setMagnitude('');
            setDescription('');
            setDate('');
            onClose();
        }
    };

    const setTodayDate = () => {
        const today = new Date().toISOString().split('T')[0];
        setDate(today);
    };

    if (!isOpen) return null;

    return (
        <div className="fixed inset-0 bg-gray-600 bg-opacity-50 flex justify-center items-center">
            <div className="bg-white p-6 rounded-lg shadow-md w-1/3">
                <h3 className="text-xl font-semibold mb-4">Add Grade</h3>
                <div className="flex mb-4">
                    <select
                        value={grade}
                        onChange={(e) => setGrade(e.target.value)}
                        className="w-1/2 p-2 border border-gray-300 rounded mr-2"
                    >
                        <option value="" disabled>Select Grade</option>
                        {[1, 2, 3, 4, 5, 6].map((grade) => (
                            <option key={grade} value={grade}>{grade}</option>
                        ))}
                    </select>
                    <input
                        type="number"
                        value={magnitude}
                        onChange={(e) => setMagnitude(e.target.value)}
                        placeholder="Magnitude"
                        className="w-1/2 p-2 border border-gray-300 rounded"
                    />
                </div>
                <textarea
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    placeholder="Description"
                    rows="2"
                    className="w-full p-2 border border-gray-300 rounded mb-4"
                />
                <div className="flex mb-4">
                    <input
                        type="date"
                        value={date}
                        onChange={(e) => setDate(e.target.value)}
                        placeholder="Date"
                        className="w-3/4 p-2 border border-gray-300 rounded"
                    />
                    <button
                        onClick={setTodayDate}
                        className="w-1/4 ml-2 px-4 py-2 bg-blue-500 text-white rounded"
                    >
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
};

export default GradeModal;