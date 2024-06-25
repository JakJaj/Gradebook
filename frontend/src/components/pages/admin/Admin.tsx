import React from "react";
import TopBar from "../../App/base-components/topbar/TopBar";
import Footer from "../../App/base-components/footer/Footer";
import ManagementBox from "./components/ManagementBox";
function Admin(){

    return(
        <>
        <TopBar />
        <ManagementBox title="Teachers" description="Look at a list of teachers. Add more, update or delete existing."/>
        
        </>
    )
}

export default Admin;