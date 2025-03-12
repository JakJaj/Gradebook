import React from 'react';
import ReactDOM from 'react-dom/client';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import './index.css';
import Routes from './routes';
import { UserProvider, useUserRole } from './UserContext';

const App = () => {
    const { userRole } = useUserRole();
    const router = createBrowserRouter(Routes({ userRole }));

    return <RouterProvider router={router} />;
};

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <UserProvider>
      <App />
    </UserProvider>
  </React.StrictMode>,
);