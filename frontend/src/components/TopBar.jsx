import React from 'react';
import { Link } from 'react-router-dom';

function TopBar({ title }) {
    return (
        <div className="bg-blue-800 p-4 text-white flex justify-between items-center">
            <h1 className="text-xl font-bold">{title}</h1>
            <nav>
                <Link to="/dashboard" className="mr-4">Dashboard</Link>
                <Link to="/profile" className="mr-4">Profile</Link>
                <Link to="/settings">Settings</Link>
            </nav>
        </div>
    );
}

export default TopBar;