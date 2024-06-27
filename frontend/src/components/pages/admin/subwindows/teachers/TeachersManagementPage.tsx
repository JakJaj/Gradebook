import React from "react";
import CRUDList from "../common-components/CRUDList";
import TopBar from "../../../../App/base-components/topbar/TopBar";
import adminContent from "../../adminContent";
function TeachersManagementPage(){
    return(
        <div>
            <TopBar buttonProps={adminContent} />
            <CRUDList type={"Teachers"}/>
        </div>
    )
}

export default TeachersManagementPage;