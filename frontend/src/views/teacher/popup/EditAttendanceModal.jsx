import React, { useEffect, useState } from 'react';
import moment from 'moment';

function AttendanceModal({ isOpen, onClose, onSave, oldAttendance }) {
    const [attendance, setAttendance] = useState('');
    const [date, setDate] = useState('');

    useEffect(() => {
        setAttendance(oldAttendance.status);
        setDate(moment(oldAttendance.date, "DD-MM-YYYY").format('YYYY-MM-DD'));
    }, [oldAttendance]);

    const handleSave = () => {
        if (attendance.trim()) {
            onSave({ date, status:attendance });
            setAttendance('');
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
                <h3 className="text-xl font-semibold mb-4">Add Attendance</h3>
                <select
                    value={attendance}
                    onChange={(e) => setAttendance(e.target.value)}
                    className="w-full p-2 border border-gray-300 rounded mb-4"
                >
                    <option value="" disabled>Select Attendance</option>
                    <option value="Present">Present</option>
                    <option value="Late">Late</option>
                    <option value="Absent">Absent</option>
                </select>
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

export default AttendanceModal;