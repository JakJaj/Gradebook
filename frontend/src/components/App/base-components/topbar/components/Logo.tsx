import React from "react";
function Logo(){
    return(
        <a href="" className="flex items-center">
            <img src="./src/static/logo.png" className="mr-3 h-6 sm:h-9" alt="Gradebook Logo" />
            <span className="self-center text-xl font-semibold whitespace-nowrap dark:text-white">Gradebook</span>
        </a>
    );
}

export default Logo;