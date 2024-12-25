import React, { useMemo, useState, useEffect } from 'react';
import Table from '../../components/Table';
import TopBar from '../../components/TopBar';
import AddStudentModal from './Popups/AddStudentModal';
import { fetchStudents, fetchClasses } from '../../data/getData';
import { createStudent } from '../../data/postData';

function StudentManagementPage() {
    const [data, setData] = useState([]);
    const [classes, setClasses] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);

    useEffect(() => {
        const getData = async () => {
            const students = await fetchStudents();
            setData(students);
            const classes = await fetchClasses();
            setClasses(classes);
        };

        getData();
    }, []);

    const handleSave = (newStudent) => {
        setData((prevData) => [...prevData, newStudent]);
    };

    const handleFormSubmit = async (studentData) => {
        try {
            const newStudent = await createStudent(studentData);
            handleSave(newStudent);
            setIsModalOpen(false);
        } catch (error) {
            console.error('Error creating student:', error);
        }
    };

    const columns = useMemo(
        () => [
            {
                id: 'id',
                header: 'ID',
                accessorKey: 'id',
            },
            {
                id: 'name',
                header: 'Name',
                accessorKey: 'name',
            },
            {
                id: 'class',
                header: 'Class',
                accessorKey: 'class',
            },
            {
                id: 'birthDate',
                header: 'Birth Date',
                accessorKey: 'birthDate',
            },
            {
                id: 'address',
                header: 'Address',
                accessorKey: 'address',
            },
            {
                id: 'actions',
                header: 'Actions',
                accessorKey: 'id',
                cell: ({ row }) => (
                    <div>
                        <button
                            onClick={() => console.log('Edit student with ID:', row.original.id)}
                            className="px-4 py-2 bg-blue-500 text-white rounded mr-2"
                        >
                            Edit
                        </button>
                        <button
                            onClick={() => console.log('Delete student with ID:', row.original.id)}
                            className="px-4 py-2 bg-red-500 text-white rounded"
                        >
                            Delete
                        </button>
                    </div>
                ),
            }
        ],
    );

    return (
        <div>
            <TopBar title="Admin Dashboard" />
            <div className="p-8">
                <h2 className="text-2xl font-semibold mb-4">User Management</h2>
                <button
                    onClick={() => setIsModalOpen(true)}
                    className="mb-4 px-4 py-2 bg-green-500 text-white rounded"
                >
                    Add New
                </button>
                <Table columns={columns} data={data} />
            </div>
            <AddStudentModal
                isOpen={isModalOpen}
                onClose={() => setIsModalOpen(false)}
                onSave={handleFormSubmit}
                classes={classes}
            />
        </div>
    );
}

export default StudentManagementPage;