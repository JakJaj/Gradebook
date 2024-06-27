import React from 'react'
import './App.css'
import { BrowserRouter, RouterProvider, createBrowserRouter } from 'react-router-dom'
import Admin from '../pages/admin/Admin'
import ErrorPage from '../pages/error/ErrorPage'
import TopBar from './base-components/topbar/TopBar'


function App() { 
  return (
    <>
      <Admin />
    </>
  )
}

export default App
