import React from "react";
import ManageButton from "./ManageButton";
import Description from "./Description";
import Title from "./Title";

function ManagementBox(props){

    return(
        <div className="max-w-sm rounded overflow-hidden shadow-lg">
            <div className="px-6 py-4">
                <Title text={props.title} />
                <Description text={props.description} />
                <ManageButton />
            </div>
        </div>

    )
}


export default ManagementBox;