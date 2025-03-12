import React from 'react';
import { Navigate } from 'react-router-dom';
import { useUserRole } from './UserContext';

const ProtectedRoute = ({ element, allowedRoles }) => {
    const { userRole } = useUserRole();
    return allowedRoles.includes(userRole) ? element : <Navigate to="/error" />;
};

export default ProtectedRoute;