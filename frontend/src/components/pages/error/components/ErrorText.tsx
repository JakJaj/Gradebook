import React from "react";
import { Link } from "react-router-dom";
function ErrorText(){

    return(
        <>
        <div className="flex flex-col items-center justify-center h-screen">
            <p className="text-9xl font-bold py-4 text-sky-900">404</p>
            <p className="text-5xl py-4 text-sky-800 ">PAGE NOT FOUND</p>
            <Link to="/"className="bg-sky-900 hover:bg-sky-600 text-white font-bold py-2 px-4 mt-4 rounded ">HOMEPAGE</Link>
        </div>
        </>
    )
}


export default ErrorText;