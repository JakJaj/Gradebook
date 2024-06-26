import ManageButton from "./ManageButton";
import Description from "./Description";
import Title from "./Title";
import React from "react";
function ManagementBox(props:any){

    return(
        <div className="max-w-sm rounded overflow-hidden shadow-lg bg-white">
            <div className="px-6 py-4">
                <Title text={props.title} />
                <Description text={props.description} />
                
            </div>
            <div className="flex  justify-center mb-5">
                <ManageButton link={props.link}/>
            </div>
        </div>

    )
}


export default ManagementBox;