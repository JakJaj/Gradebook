import React, {useEffect, useState} from 'react';
import { Link } from 'react-router-dom';
import { fetchUserDetails } from '../data/user/getUser';
import Cookies from 'js-cookie';

function TopBar({ title }) {

    const [data, setData] = useState({});
    const [path, setPath] = useState('');
    
    useEffect(() => {
        const getData = async () => {
            try {
                const userDetails = await fetchUserDetails();
                setData(userDetails);
            } catch (error) {
                console.error('Error fetching user details in ErrorPage:', error);
            }
        };
    
        getData();
    }, []);

    useEffect(() => {
            if (data.role) {
                switch (data.role) {
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
                        console.error('Unknown user role:', data.role);
                        setPath('/');
                        break;
                }
            }
        }, [data]);


    const handleLogout = () => {
        Cookies.remove('jwtToken');
    };

    return (
        <div className="bg-blue-800 p-4 text-white flex justify-between items-center">
            <h1 className="text-xl font-bold">{title}</h1>
            <nav>
                <Link to={path} className="mr-4">Dashboard</Link>
                <Link to="/" onClick={handleLogout}>Logout</Link>
            </nav>
        </div>
    );
}

export default TopBar;