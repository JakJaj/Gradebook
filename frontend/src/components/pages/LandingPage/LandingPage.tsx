import React from "react";
import TopBar from "../../App/base-components/topbar/TopBar";
import Footer from "../../App/base-components/footer/Footer";
import GoToLoginBox from "./components/GoToLoginBox";


function LandingPage(){
    return(
        <>
        <div className="grid grid-rows-10 ">
        <TopBar buttonProps={[]} landingPage={true}/>
        
        <div className=""></div>
        
        <GoToLoginBox />
        
        <div className=""></div>
        <Footer />
        </div>
        </>
    );
}


export default LandingPage