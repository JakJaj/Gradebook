import React from 'react';
import Box from '../../components/Box';
import TopBar from '../../components/TopBar';
import { useUserRole } from '../../UserContext';

function AdminDashboard() {
    
    const { userRole } = useUserRole();

    return (
        <div>
            <TopBar title="Admin Dashboard" />
        <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">

            <h1 className="text-4xl font-bold mb-8">Dashboard</h1>
            <div className="flex flex-wrap justify-center">
                <Box title="Students" description="Manage student information" link="/admin/studentManagement" />
                <Box title="Teachers" description="Manage teacher information" link="/admin/teacherManagement" />
                <Box title="Parents" description="Manage parent information" link="/admin/parentManagement" />
                <Box title="Courses" description="Manage course information" link="/admin/courseManagement" />
                <Box title="Classes" description="Manage class information" link="/admin/classManagement" />
            </div>
        </div>
        </div>
    );
}

export default AdminDashboard;