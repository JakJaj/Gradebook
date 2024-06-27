import React from "react";
import { Link } from "react-router-dom";


function GoToLoginBox(){

    return(
        <div className="grid justify-self-center row-span-5">
            <div className="grid grid-col-3 gap-10 grid-row-3 bg-white rounded p-10 self-center" >

                <div className="self-center col-span-3 row-span-2">
                    <p className="text-6xl text-black">Welcome to Gradebook landing page</p>
                    <p className="text-3xl self-center text-black">Log in to access your profile</p>
                </div>
                
                <Link to='/login' className="text-xl bg-sky-900 hover:bg-sky-600 text-white font-bold py-3 px-6 col-start-2 rounded ">Log in</Link>
            </div>
        </div>
    )
}

export default GoToLoginBox;