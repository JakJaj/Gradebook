import React from 'react';
import ErrorPage from "./views/base/ErrorPage.jsx";
import LandingPage from "./views/base/LandingPage.jsx";
import AdminDashboard from "./views/admin/AdminDashboard.jsx";
import StudentLandingPage from "./views/student/StudentLandingPage.jsx";
import ParentLandingPage from "./views/parent/ParentLandingPage.jsx";
import TeacherLandingPage from "./views/teacher/TeacherLandingPage.jsx";
import StudentManagement from "./views/admin/StudentManagementPage.jsx";
import TeacherManagementPage from "./views/admin/TeacherManagementPage.jsx";
import ParentManagementPage from "./views/admin/ParentManagementPage.jsx";
import CourseManagementPage from "./views/admin/CourseManagementPage.jsx";
import ClassManagementPage from "./views/admin/ClassManagementPage.jsx";
import TimetableScheduler from "./components/TimetableScheduler.jsx";
import TeacherClassPage from "./views/teacher/TeacherClassPage.jsx";
import StudentGradesPage from "./views/student/StudentGradesPage.jsx";
import StudentAttendancePage from "./views/student/StudentAttendancePage.jsx";
import StudentNotesPage from "./views/student/StudentNotesPage.jsx";
import ParentStudentPage from "./views/parent/ParentStudentPage.jsx";
import ProtectedRoute from "./ProtectedRoute.jsx";

const Routes = ({ userRole }) => {

    const routes = [
        {
            path : '/',
            element: <LandingPage />,
            errorElement: <ErrorPage />,
        },
        {
            path : '/error',
            element: <ErrorPage />,
        },
        {
            path : '/admin/dashboard',
            element: <ProtectedRoute element={<AdminDashboard />} allowedRoles={['ADMIN']} userRole={userRole} />,
        },
        {
            path : "/admin/studentManagement",
            element: <ProtectedRoute element={<StudentManagement />} allowedRoles={['ADMIN']} userRole={userRole} />,
        },
        {
            path : "/admin/teacherManagement",
            element: <ProtectedRoute element={<TeacherManagementPage />} allowedRoles={['ADMIN']} userRole={userRole} />,
        },
        {
            path : "/admin/parentManagement",
            element: <ProtectedRoute element={<ParentManagementPage />} allowedRoles={['ADMIN']} userRole={userRole} />,
        },
        {
            path : '/admin/courseManagement',
            element: <ProtectedRoute element={<CourseManagementPage />} allowedRoles={['ADMIN']} userRole={userRole} />,
        },
        {
            path : '/admin/classManagement',
            element: <ProtectedRoute element={<ClassManagementPage />} allowedRoles={['ADMIN']} userRole={userRole} />,
        },
        {
            path : '/admin/classManagement/timetalbe/:classId',
            element : <ProtectedRoute element={<TimetableScheduler />} allowedRoles={['ADMIN']} userRole={userRole} />,
        },
        {
            path : '/student/dashboard',
            element: <ProtectedRoute element={<StudentLandingPage />} allowedRoles={['STUDENT']} userRole={userRole} />,
        },
        {
            path : '/student/:studentId/grades',
            element: <ProtectedRoute element={<StudentGradesPage />} allowedRoles={['STUDENT', 'PARENT']} userRole={userRole} />,
        },
        {
            path : '/student/:studentId/attendance',
            element: <ProtectedRoute element={<StudentAttendancePage />} allowedRoles={['STUDENT', 'PARENT']} userRole={userRole} />,
        },
        {
            path : '/student/:studentId/notes',
            element: <ProtectedRoute element={<StudentNotesPage />} allowedRoles={['STUDENT', 'PARENT']} userRole={userRole} />,
        },
        {
            path : '/student/:studentId',
            element: <ProtectedRoute element={<ParentStudentPage />} allowedRoles={['PARENT']} userRole={userRole} />,
        },
        {
            path : '/parent/dashboard',
            element: <ProtectedRoute element={<ParentLandingPage />} allowedRoles={['PARENT']} userRole={userRole} />,
        },
        {
            path : '/teacher/dashboard',
            element: <ProtectedRoute element={<TeacherLandingPage />} allowedRoles={['TEACHER']} userRole={userRole} />,
        },
        {
            path : '/teacher/class/:classId',
            element: <ProtectedRoute element={<TeacherClassPage />} allowedRoles={['TEACHER']} userRole={userRole} />,
        },
        {
            path : '*',
            element: <ErrorPage />,
        }
    ];

    return routes;
};

export default Routes;