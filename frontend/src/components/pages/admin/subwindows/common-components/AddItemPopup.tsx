import React from "react";


export function AddTeacherPopup(props){
    return(props.trigger) ? (
    <div className="fixed top-0 left-0 w-full h-screen bg-black bg-opacity-50 flex justify-center items-center">
        <div className="relative p-12 h-5/6 w-full max-w-screen-sm bg-white border-none rounded-3xl">
            <button 
            onClick={() => props.setTrigger(false)}
            className="absolute top-12 right-12 bg-blue-500 rounded-xl px-4 py-2 text-white">X</button>
            
            <div className="pt-2">
                <p className="text-3xl">Add new teacher</p>
            </div>

            <div className="fixed pt-20">
                <p>First name:</p>
                <input type="text" placeholder="asdf"></input>

                <p>Last name:</p>
                <input type="text" placeholder="asdf"></input>

                <p>Email:</p>
                <input type="text" placeholder="asdf"></input>

                <p>Birth Date:</p>
                <input type="text" placeholder="asdf"></input>

                <p>Employment date:</p>
                <input type="text" placeholder="asdf"></input>
            </div>

            <div className="absolute bottom-12 right-24">
                <button 
                    onClick={() => {props.setTrigger(false)}}
                    className="bottom-0 bg-gray-400 hover:bg-gray-500 text-white font-bold py-2 px-4 rounded mx-2">
                        Cancel
                </button>
                
                <button className="bottom-0 bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded ">Confirm</button>
            </div>
        </div>
        
    </div>
    ): "";
}

export function AddStudentPopup(props){

}

export function AddParentPopup(props){

}


export function AddCoursePopup(props){

}

export function AddClassPopup(props){

}

