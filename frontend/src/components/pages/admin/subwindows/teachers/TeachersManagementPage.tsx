import React from "react";
import CRUDList from "../common-components/CRUDList";
import TopBar from "../../../../App/base-components/topbar/TopBar";
import adminContent, { adminHomePage }from "../../adminContent";
import Footer from "../../../../App/base-components/footer/Footer";
function TeachersManagementPage(){
    return(
        <div className="grid">
            <TopBar buttonProps={adminContent} homeProps={adminHomePage}/>
            <CRUDList type={"Teachers"}/>
            <Footer/>
        </div>
    )
}

export default TeachersManagementPage;