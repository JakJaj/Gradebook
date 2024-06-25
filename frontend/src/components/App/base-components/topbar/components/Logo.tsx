import React from "react";

function Logo(){
    return(
        <a href="https://flowbite.com" class="flex items-center">
            <img src="https://flowbite.com/docs/images/logo.svg" class="mr-3 h-6 sm:h-9" alt="Flowbite Logo" />
            <span class="self-center text-xl font-semibold whitespace-nowrap dark:text-white">Flowbite</span>
        </a>
    );
}

export default Logo;