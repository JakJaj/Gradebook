import React from "react";


export function AddTeacherPopup(props){
    return(props.trigger) ? (
    <div className="fixed top-0 left-0 w-full h-screen bg-black bg-opacity-50 flex justify-center items-center">
        <div className="relative p-40 h-5/6 w-full max-w-screen-sm bg-white">
            <button 
            onClick={() => props.setTrigger(false)}
            className="absolute top-12 right-12 bg-blue-500 rounded px-4 py-2 text-white">X</button>
            {props.children}
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

