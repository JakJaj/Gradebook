import React from 'react';
import TopBar from '../../components/TopBar';

function TeacherLandingPage() {
    return (
        <div>
            <TopBar title="Teacher Dashboard" />
            <div className="p-8">
                <h2 className="text-2xl font-semibold mb-4">Welcome to your Dashboard</h2>
                <div className="bg-white p-6 rounded-lg shadow-md">
                    <h3 className="text-xl font-semibold mb-4">Your Schedule</h3>
                    {/* Placeholder for the teacher's schedule */}
                    <div className="text-gray-700">
                        Schedule will be displayed here.
                    </div>
                </div>
            </div>
        </div>
    );
}

export default TeacherLandingPage;