import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './components/App/App'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import Admin from './components/pages/admin/Admin'
import ErrorPage from './components/pages/error/ErrorPage'
import './components/App/App.css'
import routes from './routes'

const router = createBrowserRouter(routes);

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)
