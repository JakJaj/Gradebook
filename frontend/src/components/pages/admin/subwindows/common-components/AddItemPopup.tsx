import React from "react";


export function AddTeacherPopup(props){
    return(props.trigger) ? (
    <div className="fixed top-0 left-0 w-full h-screen bg-black bg-opacity-50 flex justify-center items-center">
        <div className="relative p-12 h-5/6 w-full max-w-screen-sm bg-white border-none rounded-3xl">
            <button 
            onClick={() => props.setTrigger(false)}
            className="absolute top-12 right-12 bg-blue-500 rounded-xl px-4 py-2 text-white">X</button>
            
            <div className="pt-2">
                <p className="text-3xl tracking-wide">Add new teacher</p>
            </div>
            <div className="fixed pt-10">

                <InputField label="First Name: " placeholder="Joe"/>

                <InputField label="Second Name: " placeholder="Doe"/>

                <InputField label="Email: " placeholder="example@domain.com" />
                
                <Date label="Birth"/>

                <Date label="Employment"/>
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
    return(props.trigger) ? (
        <div className="fixed top-0 left-0 w-full h-screen bg-black bg-opacity-50 flex justify-center items-center z-10">
            <div className="relative p-12 h-full w-full max-w-screen-sm bg-white border-none rounded-3xl">
                <button 
                onClick={() => props.setTrigger(false)}
                className="absolute top-12 right-12 bg-blue-500 rounded-xl px-4 py-2 text-white">X</button>
                
                <div className="pt-2">
                    <p className="text-3xl">Add new student</p>
                </div>
    
                <div className="fixed pt-5">

                    <InputField label="First Name: " placeholder="Joe" />
                    <InputField label="Last Name: " placeholder="Doe" />
                    <InputField label="Email: " placeholder="example@domain.com" />

                    <Date label="Birth" />
    
                    <InputField label="City" placeholder="London" />
                    <InputField label="Street" placeholder="London" />
                    
                    <Select label="Class: "/>

                    <ActiveToggle />

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

export function AddParentPopup(props){
    return(props.trigger) ? (
        <div className="fixed top-0 left-0 w-full h-screen bg-black bg-opacity-50 flex justify-center items-center">
            <div className="relative p-12 h-5/6 w-full max-w-screen-sm bg-white border-none rounded-3xl">
                <button 
                onClick={() => props.setTrigger(false)}
                className="absolute top-12 right-12 bg-blue-500 rounded-xl px-4 py-2 text-white">X</button>
                
                <div className="pt-2">
                    <p className="text-3xl">Add new parent</p>
                </div>
    
                <div className="fixed pt-20">
                    <p>First name:</p>
                    <input type="text" placeholder="asdf"></input>
    
                    <p>Last name:</p>
                    <input type="text" placeholder="asdf"></input>
    
                    <p>Email:</p>
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

export function AddCoursePopup(props){
    return(props.trigger) ? (
        <div className="fixed top-0 left-0 w-full h-screen bg-black bg-opacity-50 flex justify-center items-center">
            <div className="relative p-12 h-5/6 w-full max-w-screen-sm bg-white border-none rounded-3xl">
                <button 
                onClick={() => props.setTrigger(false)}
                className="absolute top-12 right-12 bg-blue-500 rounded-xl px-4 py-2 text-white">X</button>
                
                <div className="pt-2">
                    <p className="text-3xl">Add new course</p>
                </div>
    
                <div className="fixed pt-20">
                    <p>First name:</p>
                    <input type="text" placeholder="asdf"></input>
    
                    <p>Last name:</p>
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

export function AddClassPopup(props){
    return(props.trigger) ? (
        <div className="fixed top-0 left-0 w-full h-screen bg-black bg-opacity-50 flex justify-center items-center">
            <div className="relative p-12 h-5/6 w-full max-w-screen-sm bg-white border-none rounded-3xl">
                <button 
                onClick={() => props.setTrigger(false)}
                className="absolute top-12 right-12 bg-blue-500 rounded-xl px-4 py-2 text-white">X</button>
                
                <div className="pt-2">
                    <p className="text-3xl">Add new class</p>
                </div>
    
                <div className="fixed pt-20">
                    <p>First name:</p>
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



function InputField(props){
    return(
        <>
            <label className="block uppercase tracking-wide text-gray-700 text-m font-bold my-2">
                {props.label}
            </label> 
            <input className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="username" type="text" placeholder={props.placeholder}></input>
        </>
    )
}


function DateYearField(props){
    return(
        <div className="inline-block relative w-24 mr-2">
            <select className="block appearance-none w-full bg-white border border-gray-400 hover:border-gray-500 px-4 py-2 pr-8 rounded shadow leading-tight focus:outline-none focus:shadow-outline">
                <option>2022</option>
                <option>2021</option>
                <option>2020</option>
            </select>
            <div className="pointer-events-none absolute inset-y-0 right-0 flex items-center px-2 text-gray-700">
                <svg className="fill-current h-4 w-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"><path d="M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z"/></svg>
            </div>
        </div>
    )
}

function DateField(props){
    return(

        <div className="inline-block relative w-20 mr-2">
            <select className="block appearance-none w-full bg-white border border-gray-400 hover:border-gray-500 px-4 py-2 pr-8 rounded shadow leading-tight focus:outline-none focus:shadow-outline">
                <option>1</option>
                <option>2</option>
                <option>3</option>
            </select>
            <div className="pointer-events-none absolute inset-y-0 right-0 flex items-center px-2 text-gray-700">
                <svg className="fill-current h-4 w-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"><path d="M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z"/></svg>
            </div>
        </div>
    )
}

function Date(props){
    return(
        <div>

            <div>
                <label className="block uppercase tracking-wide text-gray-700 text-m font-bold my-2">
                    {props.label} Date:
                </label> 
            </div>
            <div>
                <DateField />
                <DateField />
                <DateYearField />
            </div>
        </div>
    )
}

function ActiveToggle(props){
    return(
        <div className="mt-4">
            <label className="inline-flex items-center cursor-pointer">
                
                <input type="checkbox" value="" className="sr-only peer"/>
                <div className="relative w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 dark:peer-focus:ring-blue-800 rounded-full peer dark:bg-gray-700 peer-checked:after:translate-x-full rtl:peer-checked:after:-translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:start-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all dark:border-gray-600 peer-checked:bg-blue-600"></div>
                <span className="ms-3 text-l font-medium text-gray-300 dark:text-gray-700">Active</span>
            </label>
        </div>
    )
}

function Select(props){
    return(
        <div>
            <div>
                    <label className="block uppercase tracking-wide text-gray-700 text-m font-bold my-2">
                        {props.label}
                    </label> 
                </div>

            <div className="inline-block relative w-48 mr-2">
            

                <select className="block appearance-none w-full bg-white border border-gray-400 hover:border-gray-500 px-4 py-2 pr-8 rounded shadow leading-tight focus:outline-none focus:shadow-outline">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                </select>
                <div className="pointer-events-none absolute inset-y-0 right-0 flex items-center px-2 text-gray-700">
                    <svg className="fill-current h-4 w-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"><path d="M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z"/></svg>
                </div>
            </div>
        </div>
    )
}