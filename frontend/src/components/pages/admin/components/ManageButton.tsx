import React from "react";
import { Link } from "react-router-dom";
function ManageButton(props){
    return(
        <div>
            <Link to={props.link} className="bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-4 mt-4 rounded ">
                Manage
            </Link>
        </div>
    )
}

export default ManageButton;