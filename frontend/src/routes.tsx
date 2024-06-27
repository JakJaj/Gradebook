import React from "react"
import ErrorPage from "./components/pages/error/ErrorPage"
import Admin from "./components/pages/admin/Admin"
import LandingPage from "./components/pages/LandingPage/LandingPage";
import LoginPage from "./components/pages/login/LoginPage";

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
    
    },
    {
        path: '/admin/students/'

    },
    {
        path: '/admin/parents/'

    },
    {
        path: '/admin/courses/'

    },
    {
        path: '/admin/classes/'

    },
    {
        path: '/student'

    },
    {
        path: '/student/:id/grades/'

    }
]

export default routes;