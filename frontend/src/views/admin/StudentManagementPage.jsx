import React, { useMemo, useState, useEffect } from 'react';
import Table from '../../components/Table';
import TopBar from '../../components/TopBar';
import AddStudentModal from './popups/student/AddStudentModal';
import EditStudentModal from './popups/student/EditStudentModal';
import DeleteFieldModal from '../../components/DeleteFieldModal';

import { createStudent } from '../../data/student/postData';
import { fetchStudents, fetchClasses} from '../../data/student/getData';
import { deleteStudent } from '../../data/student/deleteData';
import { updateStudent } from '../../data/student/putData';

function StudentManagementPage() {
    const [data, setData] = useState([]);
    const [classes, setClasses] = useState([]);
    const [isAddModalOpen, setIsAddModalOpen] = useState(false);
    const [isEditModalOpen, setIsEditModalOpen] = useState(false);
    const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false);
    const [itemToDelete, setItemToDelete] = useState(null);
    const [studentToEdit, setStudentToEdit] = useState(null);

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
            setIsAddModalOpen(false);
        } catch (error) {
            console.error('Error creating student:', error);
        }
    };

    const handleUpdate = async (updatedStudent) => {
        const [year, month, day] = updatedStudent.dateOfBirth.split('-');
        const formattedDateOfBirth = `${day}-${month}-${year}`;

        const studentDetails = {
            studentID: studentToEdit,
            classID: updatedStudent.classID,
            firstName: updatedStudent.firstName,
            lastName: updatedStudent.lastName,
            dateOfBirth: formattedDateOfBirth,
            city: updatedStudent.city,
            street: updatedStudent.street,
            houseNumber: updatedStudent.houseNumber,
            email: updatedStudent.email,
            pesel: updatedStudent.pesel,
        };

        const success = await updateStudent(studentDetails);
        if (success) {
            setData((prevData) =>
                prevData.map((student) =>
                    student.id === studentToEdit
                        ? {
                            ...student,
                            id: studentDetails.studentID,
                            name: `${studentDetails.firstName} ${studentDetails.lastName}`,
                            class: classes.find(cls => cls.id === studentDetails.classID)?.name || '',
                            birthDate: studentDetails.dateOfBirth,
                            address: `${studentDetails.street} ${studentDetails.houseNumber}, ${studentDetails.city}`,
                        }
                        : student
                )
            );
            setIsEditModalOpen(false);
        } else {
            console.error('Failed to update student');
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
                            onClick={() => {
                                setStudentToEdit(row.original.id);
                                setIsEditModalOpen(true);
                            }}
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
            <TopBar title="Student Management" />
            <div className="p-8">
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
            <EditStudentModal
                isOpen={isEditModalOpen}
                onClose={() => setIsEditModalOpen(false)}
                onSave={handleUpdate}
                student={studentToEdit}
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