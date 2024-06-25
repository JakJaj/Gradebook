import React from "react";
import Logo from "./components/Logo";
import Login from "./components/Login";
import Navigation from "./components/Navigation";
function TopBar(props){

    
    return(
        <>
            <header>
                <nav className="bg-white border-gray-200 px-4 lg:px-6 py-2.5 dark:bg-gray-800">
                    <div className="flex flex-wrap justify-between items-center mx-auto max-w-screen-xl">
                        <Logo />

                        <Login />
                        <div className="hidden justify-between items-center w-full lg:flex lg:w-auto lg:order-1" id="mobile-menu-2">
                            <Navigation buttonNames={props.buttonNames}/>
                        </div>
                    </div>
                </nav>
            </header>
        </>
    )
}

export default TopBar;