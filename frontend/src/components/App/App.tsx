import './App.css'
import { BrowserRouter, RouterProvider } from 'react-router-dom'
import Admin from '../pages/admin/Admin'


function App() { 
  return (
    <>
    <BrowserRouter>
    <Admin />
    </BrowserRouter>
    </>
  )
}

export default App
