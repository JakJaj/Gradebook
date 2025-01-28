import React from 'react';

const getColorForStatus = (status) => {
    switch (status) {
        case 'Present':
            return 'bg-green-600 text-green-600';
        case 'Absent':
            return 'bg-red-600 text-red-600';
        case 'Late':
            return 'bg-yellow-500 text-yellow-500';
        default:
            return 'bg-transparent text-black';
    }
};

const StudentAttendanceBox = ({ attendance }) => {
    const statusColor = getColorForStatus(attendance.status);

    return (
        <div className="relative group inline-block" style={{ overflow: 'visible' }}>
            <div className={`attendance-box p-2 m-1 rounded-md ${statusColor}`}>
                <p>{attendance.status !== null ? attendance.status[0] : ""}</p>
            </div>
            {attendance.status !== null && (
                <div className="absolute bottom-full left-1/2 transform -translate-x-1/2 hidden group-hover:block bg-white text-black text-sm p-4 rounded-md shadow-lg w-64" style={{ zIndex: 50 }}>
                    <p><strong>Status:</strong> {attendance.status}</p>
                    <p><strong>Date:</strong> {attendance.date}</p>
                </div>
            )}
        </div>
    );
};

export default StudentAttendanceBox;