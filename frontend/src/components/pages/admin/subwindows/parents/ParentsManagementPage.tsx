import React from "react";
import CRUDList from "../common-components/CRUDList";
import TopBar from "../../../../App/base-components/topbar/TopBar";
import adminContent from "../../adminContent";

function ParentsManagementPage(){
    return(
        <div>
            <TopBar buttonProps={adminContent} />
            <CRUDList type={"Parents"}/>
        </div>
    )
}

export default ParentsManagementPage;