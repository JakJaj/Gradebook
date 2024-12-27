import React, { useMemo, useState, useEffect } from 'react';
import Table from '../../components/Table';
import TopBar from '../../components/TopBar';
import AddStudentModal from './Popups/AddStudentModal';
import DeleteFieldModal from '../../components/DeleteFieldModal';
import { fetchStudents, fetchClasses} from '../../data/getData';
import { deleteStudent } from '../../data/deleteData';

function StudentManagementPage() {
    const [data, setData] = useState([]);
    const [classes, setClasses] = useState([]);
    const [isAddModalOpen, setIsAddModalOpen] = useState(false);
    const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false);
    const [itemToDelete, setItemToDelete] = useState(null);

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

    const handleDelete = async (studentId) => {
        const success = await deleteStudent(studentId);
        if (success) {
            setData((prevData) => prevData.filter(student => student.id !== studentId));
            setIsDeleteModalOpen(false);
        } else {
            console.error('Failed to delete student');
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
                            onClick={() => {
                                setItemToDelete(row.original.id);
                                setIsDeleteModalOpen(true);
                            }}
                            className="px-4 py-2 bg-red-500 text-white rounded"
                        >
                            Delete
                        </button>
                    </div>
                ),
            }
        ],
        []
    );

    return (
        <div>
            <TopBar title="Admin Dashboard" />
            <div className="p-8">
                <h2 className="text-2xl font-semibold mb-4">User Management</h2>
                <button
                    onClick={() => setIsAddModalOpen(true)}
                    className="mb-4 px-4 py-2 bg-green-500 text-white rounded"
                >
                    Add New
                </button>
                <Table columns={columns} data={data} />
            </div>
            <AddStudentModal
                isOpen={isAddModalOpen}
                onClose={() => setIsAddModalOpen(false)}
                onSave={handleFormSubmit}
                classes={classes}
            />
            <DeleteFieldModal
                isOpen={isDeleteModalOpen}
                onClose={() => setIsDeleteModalOpen(false)}
                onDelete={handleDelete}
                itemId={itemToDelete}
                itemType="Student"
            />
        </div>
    );
}

export default StudentManagementPage;