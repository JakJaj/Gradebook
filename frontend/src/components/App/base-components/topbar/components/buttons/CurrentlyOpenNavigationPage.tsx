import React from "react";
import { Link } from "react-router-dom";

function CurrentlyOpenNavigationPage(props:any){
    return(<li>
        <Link to={props.link} className="block py-2 pr-4 pl-3 text-white rounded bg-primary-700 lg:bg-transparent lg:text-primary-700 lg:p-0 dark:text-white" aria-current="page">Home</Link>
    </li>
    );
}


export default CurrentlyOpenNavigationPage;