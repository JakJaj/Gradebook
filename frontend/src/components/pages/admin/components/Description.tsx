import React from "react";
function Description(props:any){
    return(
        <p className="text-gray-700 text-base">
            {props.text}
        </p>
    )
}


export default Description;