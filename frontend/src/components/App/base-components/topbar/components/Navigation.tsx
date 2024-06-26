import React from "react";
import CurrentlyOpenNavigationPage from "./buttons/CurrentlyOpenNavigationPage";
import InactiveNavigationPage from "./buttons/InactiveNavigationPage";


function Navigation(props) {
    const buttonText = (names) => {
        return names.map((buttonText) => (
            <InactiveNavigationPage text={buttonText.title} link={buttonText.link}/>
        ));
    };

    return (
        <ul className="flex flex-col mt-4 font-medium lg:flex-row lg:space-x-8 lg:mt-0">
            <CurrentlyOpenNavigationPage text="Home" />
            {buttonText(props.buttonProps)}
        </ul>
    );
}

export default Navigation;