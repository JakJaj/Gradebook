import React from 'react';
import { Link } from 'react-router-dom';

function Box({ title, description, link }) {
    return (
        <div className="bg-white p-6 rounded-lg shadow-md m-4 w-full max-w-xs">
            <h3 className="text-xl font-semibold mb-2">{title}</h3>
            <p className="text-gray-700 mb-4">{description}</p>
            <Link to={link}>
                <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                    Go to {title}
                </button>
            </Link>
        </div>
    );
}

export default Box;