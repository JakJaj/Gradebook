import React from "react";
import { Link } from "react-router-dom";
function Logout(){
    return(
        <div className="flex items-center lg:order-2">
            <Link to="/" className="text-gray-800 dark:text-white hover:bg-gray-50 focus:ring-4 focus:ring-gray-300 font-medium rounded-lg text-sm px-4 lg:px-5 py-2 lg:py-2.5 mr-2 dark:hover:bg-gray-700 focus:outline-none dark:focus:ring-gray-800">Log out</Link>
        </div>
    );
}

export default Logout