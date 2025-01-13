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
            return 'bg-gray-300 text-black';
    }
};


const GradeBox = ({ grade}) => {
    const gradeColor = getColorForGrade(grade.mark);
    
    return (
        <div className="relative group">
            <div className={`grade-box p-2 rounded-md ${gradeColor}`}>
                <p>{grade.mark}</p>
            </div>
            <div className="absolute bottom-full left-1/2 transform -translate-x-1/2  hidden group-hover:block bg-white text-black text-sm p-4 rounded-md shadow-lg w-64">
                <p><strong>Mark:</strong> {grade.mark}</p>
                <p><strong>Magnitude:</strong> {grade.magnitude}</p>
                <p><strong>Description:</strong> {grade.description}</p>
                <p><strong>Date:</strong> {grade.date}</p>
            </div>
        </div>
    );
};

export default GradeBox;