import React from 'react';


const getColorForAttendance = (attendance) => {
    switch (attendance.status.toLowerCase()) {
        case 'present':
            return 'bg-green-500 text-white';
        case 'absent':
            return 'bg-red-500 text-white';
        case 'late':
            return 'bg-yellow-500 text-black';
        default:
            return 'bg-gray-300 text-black';
    }
};
const AttendanceBox = ({ attendance }) => {

    const attendanceColor = getColorForAttendance(attendance);

    return (
        <div className="relative group">
            <div className={`attendance-box ${attendanceColor} p-2 rounded-md`}>
                <p>{attendance.status}</p>
            </div>
            <div className="absolute bottom-full left-1/2 transform -translate-x-1/2 mb-6 hidden group-hover:block bg-white text-black text-sm p-2 rounded-md shadow-lg">
                <p><strong>Attendance ID:</strong> {attendance.id}</p>
                <p><strong>Status:</strong> {attendance.status}</p>
                <p><strong>Date:</strong> {attendance.date}</p>
            </div>
        </div>
    );
};

export default AttendanceBox;