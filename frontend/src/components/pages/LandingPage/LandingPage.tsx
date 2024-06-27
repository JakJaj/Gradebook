import React from "react";
import TopBar from "../../App/base-components/topbar/TopBar";
import Footer from "../../App/base-components/footer/Footer";
import GoToLoginBox from "./components/GoToLoginBox";


function LandingPage(){
    return(
        <>
        <TopBar buttonProps={[]} landingPage={true}/>
        <GoToLoginBox />
        <Footer />
        </>
    );
}


export default LandingPage