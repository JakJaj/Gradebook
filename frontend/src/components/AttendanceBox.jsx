import React from 'react';


const getColorForAttendance = (attendance) => {
    switch (attendance.status) {
        case 'Present':
            return 'bg-green-500 text-green-500';
        case 'Absent':
            return 'bg-red-500 text-red-500';
        case 'Late':
            return 'bg-yellow-500 text-yellow-500';
        default:
            return 'bg-gray-300 text-gray-300';
    }
};
const AttendanceBox = ({ attendance, onEdit, onDelete }) => {
    const attendanceColor = getColorForAttendance(attendance);

    return (
        <div className="relative group">
            <div className={`attendance-box ${attendanceColor} p-2 rounded-md m-1`}>
                <p>{attendance.status[0]}</p>
            </div>
            <div className="absolute bottom-full left-1/2 transform -translate-x-1/2  hidden group-hover:block bg-white text-black text-sm p-2 rounded-md shadow-lg w-36">
                <p><strong>Status:</strong> {attendance.status}</p>
                <p><strong>Date:</strong> {attendance.date}</p>
                <div className="flex justify-between mt-2">
                    <button onClick={() => onEdit(attendance)} className="bg-blue-500 text-white px-2 py-1 rounded">
                        Edit
                    </button>
                    <button onClick={() => onDelete(attendance)} className="bg-red-500 text-white px-2 py-1 rounded">
                        Delete
                    </button>
                </div>
            </div>
        </div>
    );
};

export default AttendanceBox;