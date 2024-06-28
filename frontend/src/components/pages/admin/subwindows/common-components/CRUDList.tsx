import React, { ReactNode } from "react";
import {getListData, getColumns}from "./columns";

function CRUDList(props){

    let columns = getColumns(props.type)
    
    let content = getListData(props.type)

    const renderColumns = (columns):any => (
        columns.map((column) => (
            <td className="border border-slate-600 hover:bg-gray-300 p-4" key={column.id}> {column.text}</td>
    )))

    
    return(
        <div className="bg-white px-4 pt-3 pb-4 rounded-sm border border-gray-200 flex-1 w-5/6">
            <strong className="text-gray-700 fonr-medium">{props.type} List</strong>
            <div className="mt-3">
                <table className="table-auto text-center border-sepparate bg-gray-200">
                    <thead>
                        <tr>
                            {renderColumns(columns)}
                        </tr>
                    </thead>
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