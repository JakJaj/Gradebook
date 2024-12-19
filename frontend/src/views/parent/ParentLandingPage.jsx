import React from 'react';
import TopBar from '../../components/TopBar';

function ParentLandingPage() {
    return (
        <div>
            <TopBar title="Parent Dashboard" />
            <div className="p-8">
                <h2 className="text-2xl font-semibold mb-4">Welcome to your Dashboard</h2>
                <div className="bg-white p-6 rounded-lg shadow-md">
                    <h3 className="text-xl font-semibold mb-4">Your Child's Timetable</h3>
                    {/* Placeholder for the child's timetable */}
                    <div className="text-gray-700">
                        Timetable will be displayed here.
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ParentLandingPage;