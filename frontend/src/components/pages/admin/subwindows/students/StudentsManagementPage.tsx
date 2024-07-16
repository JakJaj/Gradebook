import React from "react";
import CRUDList from "../common-components/CRUDList";
import TopBar from "../../../../App/base-components/topbar/TopBar";
import adminContent, {adminHomePage} from "../../adminContent";

function StudentsManagementPage(){
    return(
        <div>
            <TopBar buttonProps={adminContent} homeProps={adminHomePage}/>
            <CRUDList type={"Students"}/>
        </div>
    )
}

export default StudentsManagementPage;