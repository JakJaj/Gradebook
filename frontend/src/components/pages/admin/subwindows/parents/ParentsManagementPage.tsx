import React from "react";
import CRUDList from "../common-components/CRUDList";
import TopBar from "../../../../App/base-components/topbar/TopBar";
import adminContent, {adminHomePage} from "../../adminContent";

function ParentsManagementPage(){
    return(
        <div>
            <TopBar buttonProps={adminContent} homeProps={adminHomePage}/>
            <CRUDList type={"Parents"}/>
        </div>
    )
}

export default ParentsManagementPage;