import React, { createContext, useContext, useState, useEffect } from 'react';

const UserContext = createContext();

export const UserProvider = ({ children }) => {
    const [userRole, setUserRole] = useState(() => {
        return localStorage.getItem('userRole') || '';
    });

    useEffect(() => {
        if (userRole) {
            localStorage.setItem('userRole', userRole);
        } else {
            localStorage.removeItem('userRole');
        }
    }, [userRole]);

    return (
        <UserContext.Provider value={{ userRole, setUserRole }}>
            {children}
        </UserContext.Provider>
    );
};

export const useUserRole = () => useContext(UserContext);