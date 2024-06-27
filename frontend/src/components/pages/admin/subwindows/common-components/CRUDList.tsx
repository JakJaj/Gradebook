import React from "react";
import {teacherColumns, studentColumns, parentsColumn, classesColumns, coursesColumn}from "./columns";
function CRUDList(props){

    let columns;
    let content;

    switch(props.type){
        case "Teachers": 
            columns = teacherColumns
            // ! - wstawić pod kazda kolumna odwolanie do async funkcji ktora bedzie zwracala dane z API!
            break;
        case "Students":
            columns = studentColumns

            break;
        case "Parents":
            columns = parentsColumn

            break
        case "Classes":
            columns = classesColumns

            break
        case "Courses":
            columns = coursesColumn

            break
    }


    return(
        <div className="bg-white px-4 pt-3 pb-4 rounded-sm border border-gray-200 flex-1 w-5/6">
            <strong className="text-gray-700 fonr-medium">{props.type} List</strong>
            <div className="mt-3">
                <table className="table-auto text-center border-sepparate bg-gray-200">
                    <tr>
                        {columns.map((column) => (
                            <td className="border border-slate-600 hover:bg-gray-300 p-4">{column}</td>
                        ))}
                    </tr>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>Adam</td>
                            <td>Nowak</td>
                            <td>adam@nowak.com</td>
                            <td>12.10.1992</td>
                            <td>25.02.2018</td>
                            <td>Active</td>
                            <td>
                                <button>US</button>
                                <button>ED</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        
    )
}


export default CRUDList;