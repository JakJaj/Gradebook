import React from 'react';

const getColorForGrade = (mark) => {
    switch (mark) {
        case 1:
            return 'bg-red-600 text-white';
        case 2:
            return 'bg-orange-500 text-white';
        case 3:
            return 'bg-yellow-500 text-white';
        case 4:
            return 'bg-green-600 text-white';
        case 5:
            return 'bg-blue-600 text-white';
        case 6:
            return 'bg-violet-600 text-white';
        default:
            return 'bg-transparent text-black';
    }
};

const StudentGradeBox = ({ grade, onEdit, onDelete }) => {
    const gradeColor = getColorForGrade(grade.mark);
    
    return (
        <div className="relative group inline-block" style={{ overflow: 'visible' }}>
            <div className={`grade-box p-2 m-1 rounded-md ${gradeColor}`}>
                <p>{grade.mark}</p>
            </div>
            {grade.mark !== null && (
                <div className="absolute bottom-full left-1/2 transform -translate-x-1/2 hidden group-hover:block bg-white text-black text-sm p-4 rounded-md shadow-lg w-64" style={{ zIndex: 50 }}>
                    <p><strong>Mark:</strong> {grade.mark}</p>
                    <p><strong>Magnitude:</strong> {grade.magnitude}</p>
                    <p><strong>Description:</strong> {grade.description}</p>
                    <p><strong>Date:</strong> {grade.date}</p>
                </div>
            )}
        </div>
    );
};

export default StudentGradeBox;