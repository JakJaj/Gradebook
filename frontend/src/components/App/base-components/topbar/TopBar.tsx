import React, { useState } from "react";
import Logo from "./components/Logo";
import Navigation from "./components/Navigation";
import Logout from "./components/buttons/LogOut";
import Login from "./components/buttons/Login"
function TopBar(props:any){

    const [landingPage, setLandingPage] = useState(props.landingPage)
    const [currentPage, setCurrentPage] = useState()
    
    return(
        <>
            <header>
                <nav className="bg-white border-gray-200 px-4 lg:px-6 py-2.5 dark:bg-gray-800">
                    <div className="flex flex-wrap justify-between items-center mx-auto max-w-screen-xl">
                        <Logo />

                        {landingPage ? <Login /> :<Logout />}
                        
                        
                        <div className="hidden justify-between items-center w-full lg:flex lg:w-auto lg:order-1" id="mobile-menu-2">
                            <Navigation buttonProps={props.buttonProps}/>
                        </div>
                    </div>
                </nav>
            </header>
        </>
    )
}

export default TopBar;