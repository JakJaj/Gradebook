import React from "react";
import {teacherColumns, studentColumns, parentsColumn, classesColumns, coursesColumn}from "./columns";
function CRUDList(props){

    let columns;

    switch(props.type){
        case "Teachers": 
            columns = teacherColumns
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
        <div className="bg-white px-4 pt-3 pb-4 rounded-sm border border-gray-200 flex-1">
            <strong className="text-gray700 fonr-medium">{props.type} List</strong>
            <div className="mt-3">
                <table>
                    <tr>
                        {columns.map((column) => (
                            <td>{column}</td>
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