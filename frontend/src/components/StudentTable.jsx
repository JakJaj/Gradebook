import React from 'react';
import {
    useReactTable,
    getCoreRowModel,
    flexRender,
} from '@tanstack/react-table';

function StudentTable({ columns, data }) {
    const table = useReactTable({
        data,
        columns,
        getCoreRowModel: getCoreRowModel(),
    });

    return (
        <div className="overflow-x-auto">
            <table className="min-w-full max-w-4xl mx-auto bg-white">
                <thead>
                    {table.getHeaderGroups().map(headerGroup => (
                        <tr key={headerGroup.id} className="w-full bg-gray-200">
                            {headerGroup.headers.map((header, index) => (
                                <th
                                    key={header.id}
                                    className={`p-2 border-b ${index === 0 ? 'w-1/4' : ''}`}
                                >
                                    {flexRender(header.column.columnDef.header, header.getContext())}
                                </th>
                            ))}
                        </tr>
                    ))}
                </thead>
                <tbody>
                    {table.getRowModel().rows.map(row => (
                        <tr key={row.id} className="hover:bg-gray-100">
                            {row.getVisibleCells().map((cell, index) => (
                                <td key={cell.id} className={`p-2 border-b ${index === 0 ? 'w-1/4' : ''}`}>
                                    <div className="flex justify-center">
                                        {flexRender(cell.column.columnDef.cell, cell.getContext())}
                                    </div>
                                </td>
                            ))}
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default StudentTable;