import React from 'react';

const ChildInfoBox = ({ child, onSelect }) => {
    return (
        <div className="child-info-box bg-white p-8 rounded-lg shadow-md mb-6 w-full max-w-md">
            <h3 className="text-2xl font-semibold mb-4">{child.name}</h3>
            <p className="text-lg"><strong>Class:</strong> {child.className}</p>
            <p className="text-lg"><strong>Birth date: </strong> {child.birthDate}</p>
            <button
                onClick={() => onSelect(child)}
                className="mt-6 px-6 py-3 bg-blue-500 text-white rounded text-lg"
            >
                View Information
            </button>
        </div>
    );
};

export default ChildInfoBox;