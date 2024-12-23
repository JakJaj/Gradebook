import React from 'react';
import {
  useReactTable,
  getCoreRowModel,
  getSortedRowModel,
  flexRender,
} from '@tanstack/react-table';

function Table({ columns, data }) {
  const table = useReactTable({
    data,
    columns,
    getCoreRowModel: getCoreRowModel(),
    getSortedRowModel: getSortedRowModel(),
  });

  return (
    <table className="min-w-full bg-white">
      <thead>
        {table.getHeaderGroups().map(headerGroup => (
          <tr key={headerGroup.id} className="w-full bg-gray-200">
            {headerGroup.headers.map(header => (
              <th
                key={header.id}
                onClick={header.column.getToggleSortingHandler()}
                className="p-2 border-b cursor-pointer"
              >
                {flexRender(header.column.columnDef.header, header.getContext())}
                <span>
                  {header.column.getIsSorted()
                    ? header.column.getIsSorted() === 'desc'
                      ? ' 🔽'
                      : ' 🔼'
                    : ''}
                </span>
              </th>
            ))}
          </tr>
        ))}
      </thead>
      <tbody>
        {table.getRowModel().rows.map(row => (
          <tr key={row.id} className="hover:bg-gray-100">
            {row.getVisibleCells().map(cell => (
              <td key={cell.id} className="p-2 border-b">
                {flexRender(cell.column.columnDef.cell, cell.getContext())}
              </td>
            ))}
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default Table;