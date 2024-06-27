import React from "react";
import CRUDList from "../common-components/CRUDList";
import TopBar from "../../../../App/base-components/topbar/TopBar";
import textContent from "../../adminContent";

function ClassesManagementPage(){
    return(
        <div>
            <TopBar buttonProps={textContent} />
            <CRUDList type={"Classes"}/>
        </div>
    )
}

export default ClassesManagementPage;