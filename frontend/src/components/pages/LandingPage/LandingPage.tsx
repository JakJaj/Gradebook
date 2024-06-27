import React from "react";
import TopBar from "../../App/base-components/topbar/TopBar";
import Footer from "../../App/base-components/footer/Footer";
import GoToLoginBox from "./components/GoToLoginBox";


function LandingPage(){
    return(
        <>
        <div className="grid grid-rows-6 h-screen">
        <TopBar buttonProps={[]} landingPage={true}/>
        
        <GoToLoginBox />
        
        <Footer />
        </div>
        </>
    );
}


export default LandingPage