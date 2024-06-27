import React from "react"
import ErrorPage from "./components/pages/error/ErrorPage"
import Admin from "./components/pages/admin/Admin"
import LandingPage from "./components/pages/LandingPage/LandingPage";
import LoginPage from "./components/pages/login/LoginPage";
import TeachersManagementPage from "./components/pages/admin/subwindows/teachers/TeachersManagementPage";
import StudentsManagementPage from "./components/pages/admin/subwindows/students/StudentsManagementPage";
import ParentsManagementPage from "./components/pages/admin/subwindows/parents/ParentsManagementPage";
import CoursesManagementPage from "./components/pages/admin/subwindows/courses/CoursesManagementPage";
import ClassesManagementPage from "./components/pages/admin/subwindows/classes/ClassesManagementPage";

const routes = [
    {
        path: '/',
        element: <LandingPage />,
        errorElement: <ErrorPage />
    },
    {
        path: '/login',
        element: <LoginPage />
    },
    {
        path: '/admin',
        element: <Admin />, 
    },
    {
        path: '/admin/teachers/',
        element: <TeachersManagementPage />
    },
    {
        path: '/admin/students/',
        element: <StudentsManagementPage />
    },
    {
        path: '/admin/parents/',
        element: <ParentsManagementPage />
    },
    {
        path: '/admin/courses/',
        element: <CoursesManagementPage />
    },
    {
        path: '/admin/classes/',
        element: <ClassesManagementPage />
    },
    {
        path: '/student'

    },
    {
        path: '/student/:id/grades/'

    }
]

export default routes;