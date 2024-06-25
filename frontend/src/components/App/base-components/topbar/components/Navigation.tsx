import React from "react";
import CurrentlyOpenNavigationPage from "./buttons/CurrentlyOpenNavigationPage";
import InactiveNavigationPage from "./buttons/InactiveNavigationPage";

function Navigation(props){
    return(
        <ul className="flex flex-col mt-4 font-medium lg:flex-row lg:space-x-8 lg:mt-0">
            <CurrentlyOpenNavigationPage text="Home"/>
            <InactiveNavigationPage text="Grades"/>
            <InactiveNavigationPage text="Notes"/>
        </ul>
    );
}

export default Navigation;