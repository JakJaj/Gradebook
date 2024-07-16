import React from "react";
import CRUDList from "../common-components/CRUDList";
import TopBar from "../../../../App/base-components/topbar/TopBar";
import adminContent, {adminHomePage} from "../../adminContent";

function CoursesManagementPage(){
    return(
        <div>
            <TopBar buttonProps={adminContent} homeProps={adminHomePage}/>
            <CRUDList type={"Courses"}/>
        </div>
    )
}

export default CoursesManagementPage;