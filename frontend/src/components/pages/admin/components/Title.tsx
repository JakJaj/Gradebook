import React from "react";

function Title(props){
    return(
        <div className="font-bold text-4xl mb-2 text-center">
            {props.text}
        </div>
    );
}

export default Title;