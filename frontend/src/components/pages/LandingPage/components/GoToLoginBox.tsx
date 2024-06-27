import React from "react";
import { Link } from "react-router-dom";


function GoToLoginBox(){

    return(
        <div className="flex items-center justify-center h-screen">
            <div className=" bg-gray-400 rounded p-10 " >

                <div className="">
                    <p className="text-6xl ">Welcome to Gradebook landing page</p>
                    <p className="text-3xl self-center">Log in to access your profile</p>
                </div>
                
                <Link to='/admin' className="bg-sky-900 hover:bg-sky-600 text-white font-bold py-2 px-4  rounded ">Log in</Link>
            </div>
        </div>
    )
}

export default GoToLoginBox;