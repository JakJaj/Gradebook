import React, { useEffect, useState } from "react";
import axios from "axios";
import { getListData, getColumns } from "./columns";
import {AddTeacherPopup} from "./AddItemPopup";

function CRUDList(props) {
    const [columns, setColumns] = useState([]);
    const [rows, setRows] = useState([]);
    const [buttonPopup, setButtonPopup] = useState(false);
    useEffect(() => {
        setColumns(getColumns(props.type));
        
        const fetchData = async () => {
            const data = await getListData(props.type);
            setRows(data);
        };

        fetchData();
    }, [props.type]);

    const renderColumns = (columns) => (
        columns.map((column) => (
            <td className="border-b-2 border-slate-600 bg-gray-800 text-white hover:bg-gray-700 p-4" key={column.id}>{column.text}</td>
        ))
    );

    const renderRows = (rows, type) => {
        return rows.map((row) => {
            switch (type) {
                case "Teachers":
                    return (
                        
                        <tr key={row.ID}>
                            <ListItem text={row.ID}/>
                            <ListItem text={row.FirstName}/>
                            <ListItem text={row.LastName}/>
                            <ListItem text={row.Email}/>
                            <ListItem text={row.BirthDate}/>
                            <ListItem text={row.EmploymentDate}/>
                            <ListItem text={row.Status === true ? "Active" : "Inactive"}/>

                            <td>{/* Actions */}</td>
                        </tr>
                    );
                case "Students":
                    return (
                        //DODAJ PRZYCISKI DO AKCJI
                        <tr key={row.ID}>
                            <ListItem text={row.ID}/>
                            <ListItem text={row.FirstName}/>
                            <ListItem text={row.LastName}/>
                            <ListItem text={row.Email}/>
                            <ListItem text={row.BirthDate}/>
                            <ListItem text={row.Address}/>
                            <ListItem text={row.Class}/>
                            <ListItem text={row.Status === true ? "Active" : "Inactive"}/>
                            
                            <td>{/* Actions */}</td>
                        </tr>
                    );
                case "Parents":
                    return (
                        <tr key={row.ID}>
                            <ListItem text={row.ID}/>
                            <ListItem text={row.FirstName}/>
                            <ListItem text={row.LastName}/>
                            <ListItem text={row.Email}/>
                            
                            
                            <ListItem text={row.Students}/>
                            <td>{/* Actions */}</td>
                        </tr>
                    );
                case "Classes":
                    //DODAJ PRZYCISKI DO AKCJI
                    return (
                        <tr key={row.ID}>
                            <ListItem text={row.ID} />
                            <ListItem text={row.ClassName} />
                            <ListItem text={row.Tutor} />
                            <ListItem text={row.Year}/>
                            <ListItem text={row.Students} />
                            <ListItem text={row.Timetable}/>
                            <ListItem text={row.Status === true ? "Active" : "Inactive"} />
                            
                            <td>{/* Actions */}</td>
                        </tr>
                    );
                case "Courses":
                    //DODAJ PRZYCISKI DO AKCJI
                    return (
                        <tr key={row.ID}>
                            <ListItem text={row.ID}/>
                            <ListItem text={row.Course} />
                            <ListItem text={row.Teacher} />
                            <ListItem text={row.Description} />
                            
                            <td>{/* Actions */}</td>
                        </tr>
                    );
                default:
                    return null;
            }
        });
    };

    return (
        <div className="bg-white px-4 pt-3 pb-14 rounded-sm border border-gray-200 flex-1">
            <div className="flex justify-evenly mt-5 mb-8">
                <strong className="text-gray-700 text-3xl">{props.type} List</strong>
                
                <button 
                    className="bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded "
                    onClick={() => setButtonPopup(true)}>
                            Add item
                </button>

                <AddTeacherPopup trigger={buttonPopup} setTrigger={setButtonPopup}/>        
            
            </div>
            <div className="mt-3 flex justify-center">
                <table className="table-auto text-center border-separate bg-gray-200">
                    <thead>
                        <tr>
                            {renderColumns(columns)}
                        </tr>
                    </thead>
                    <tbody>
                        {renderRows(rows, props.type)}
                    </tbody>
                </table>
            </div>
        </div>
    );
}


function ListItem(props){
    return(
        <td className=" hover:bg-gray-300 p-6">{props.text}</td>
    )
}

export default CRUDList;
