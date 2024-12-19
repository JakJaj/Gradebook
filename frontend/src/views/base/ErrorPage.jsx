import React from 'react';
import { Link } from 'react-router-dom';

import './errorPage.css'

function ErrorPage(){
    return (
        <div className='flex flex-col items-center justify-center min-h-screen bg-gray-100'>
            <h1 className='text-4xl font-bold text-red-500'>404 Page Not Found</h1>
            <p className='text-lg font-semibold'>Sorry, the page you are looking for does not exist.</p>
            
            <button className='mt-5 px-4 py-2 bg-blue-500 text-white rounded-md'>
                <Link to="/">Go back</Link>
            </button>
        </div>
    )
}

export default ErrorPage;