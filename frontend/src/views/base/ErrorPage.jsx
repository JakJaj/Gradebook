import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useUserRole } from '../../UserContext';
import './errorPage.css';

function ErrorPage() {
    const { userRole } = useUserRole();
    const [path, setPath] = useState('');

    useEffect(() => {
        if (userRole) {
            switch (userRole) {
                case 'ADMIN':
                    setPath('/admin/dashboard');
                    break;
                case 'STUDENT':
                    setPath('/student/dashboard');
                    break;
                case 'PARENT':
                    setPath('/parent/dashboard');
                    break;
                case 'TEACHER':
                    setPath('/teacher/dashboard');
                    break;
                default:
                    console.error('Unknown user role:', userRole);
                    setPath('/');
                    break;
            }
        }
    }, [userRole]);

    return (
        <div className='flex flex-col items-center justify-center min-h-screen bg-gray-100'>
            <h1 className='text-4xl font-bold text-red-500'>404 Page Not Found</h1>
            <p className='text-lg font-semibold'>Sorry, the page you are looking for does not exist.</p>
            {path && (
                <Link to={path} className='mt-4 px-4 py-2 bg-blue-500 text-white rounded'>
                    Go back
                </Link>
            )}
        </div>
    );
}

export default ErrorPage;