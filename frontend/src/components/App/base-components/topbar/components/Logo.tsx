import React from "react";
import { Link } from "react-router-dom";

function Logo(props){
    return(
        <Link to={props.homeProps} className="flex items-center">
            <img src="/src/static/logo.png" className="mr-3 h-6 sm:h-9" alt="Gradebook Logo" />
            <span className="self-center text-xl font-semibold whitespace-nowrap dark:text-white">Gradebook</span>
        </Link>
    );
}

export default Logo;