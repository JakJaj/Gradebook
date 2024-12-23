import React, { useMemo } from 'react';
import Table from '../../components/Table';
import TopBar from '../../components/TopBar';

function StudentManagementPage() {
  const data = useMemo(
    () => [
      { name: 'John Doe', role: 'Student', email: 'john@example.com' },
      { name: 'Jane Smith', role: 'Teacher', email: 'jane@example.com' },
      // Add more data here
    ],
    []
  );

  const columns = useMemo(
    () => [
      {
        id: 'name',
        header: 'Name',
        accessorKey: 'name',
      },
      {
        id: 'role',
        header: 'Role',
        accessorKey: 'role',
      },
      {
        id: 'email',
        header: 'Email',
        accessorKey: 'email',
      },
    ]
  );

  return (
    <div>
      <TopBar title="Admin Dashboard" />
      <div className="p-8">
        <h2 className="text-2xl font-semibold mb-4">Student Management</h2>
        <Table columns={columns} data={data} />
      </div>
    </div>
  );
}

export default StudentManagementPage;