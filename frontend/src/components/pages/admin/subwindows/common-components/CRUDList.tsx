import React, { useEffect, useState } from "react";
import axios from "axios";
import { getListData, getColumns } from "./columns";

function CRUDList(props) {
    const [columns, setColumns] = useState([]);
    const [rows, setRows] = useState([]);

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
            <td className="border border-slate-600 hover:bg-gray-300 p-4" key={column.id}>{column.text}</td>
        ))
    );

    const renderRows = (rows, type) => {
        return rows.map((row) => {
            switch (type) {
                case "Teachers":
                    return (
                        <tr key={row.ID}>
                            <td>{row.ID}</td>
                            <td>{row.FirstName}</td>
                            <td>{row.LastName}</td>
                            <td>{row.Email}</td>
                            <td>{row.BirthDate}</td>
                            <td>{row.EmploymentDate}</td>
                            <td>{row.Status === true ? "aktywny" : "nieaktywny"}</td>
                            <td>{/* Actions */}</td>
                        </tr>
                    );
                case "Students":
                    return (
                        <tr key={row.ID}>
                            <td>{row.ID}</td>
                            <td>{row.FirstName}</td>
                            <td>{row.LastName}</td>
                            <td>{row.Email}</td>
                            <td>{row.BirthDate}</td>
                            <td>{row.Address}</td>
                            <td>{row.Class}</td>
                            <td>{row.Status === true ? "aktywny" : "nieaktywny"}</td>
                            <td>{/* Actions */}</td>
                        </tr>
                    );
                case "Parents":
                    return (
                        <tr key={row.ID}>
                            <td>{row.ID}</td>
                            <td>{row.FirstName}</td>
                            <td>{row.LastName}</td>
                            <td>{row.Email}</td>
                            <td>{row.Students}</td>
                            <td>{/* Actions */}</td>
                        </tr>
                    );
                case "Classes":
                    return (
                        <tr key={row.ID}>
                            <td>{row.ID}</td>
                            <td>{row.ClassName}</td>
                            <td>{row.Tutor}</td>
                            <td>{row.Year}</td>
                            <td>{row.Students}</td>
                            <td>{row.Timetable}</td>
                            <td>{row.Status}</td>
                            <td>{/* Actions */}</td>
                        </tr>
                    );
                case "Courses":
                    return (
                        <tr key={row.ID}>
                            <td>{row.ID}</td>
                            <td>{row.Course}</td>
                            <td>{row.Teachers}</td>
                            <td>{row.Description}</td>
                            <td>{/* Actions */}</td>
                        </tr>
                    );
                default:
                    return null;
            }
        });
    };

    return (
        <div className="bg-white px-4 pt-3 pb-4 rounded-sm border border-gray-200 flex-1 w-5/6">
            <strong className="text-gray-700 font-medium">{props.type} List</strong>
            <div className="mt-3">
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

export default CRUDList;
