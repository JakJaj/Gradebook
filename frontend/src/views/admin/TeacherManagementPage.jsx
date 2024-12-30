import React, { useMemo, useState, useEffect } from 'react';
import Table from '../../components/Table';
import TopBar from '../../components/TopBar';
import AddTeacherModal from './popups/teacher/AddTeacherModal';
import EditTeacherModal from './popups/teacher/EditTeacherModal';
import DeleteFieldModal from '../../components/DeleteFieldModal';
import { createTeacher } from '../../data/teacher/postData';
import { updateTeacher } from '../../data/teacher/putData';
import { fetchTeachers, fetchTeacher } from '../../data/teacher/getData';
import { deleteTeacher } from '../../data/teacher/deleteData';

function TeacherManagementPage() {
    const [data, setData] = useState([]);
    const [isAddModalOpen, setIsAddModalOpen] = useState(false);
    const [isEditModalOpen, setIsEditModalOpen] = useState(false);
    const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false);
    const [itemToDelete, setItemToDelete] = useState(null);
    const [teacherToEdit, setTeacherToEdit] = useState([]);

    useEffect(() => {
        const getData = async () => {
            try {
                const teachers = await fetchTeachers();
                setData(teachers);
            } catch (error) {
                console.error('Error fetching teachers in TeacherManagementPage:', error);
            }
        };

        getData();
    }, []);

    const handleSave = async (newTeacher) => {
        const requestBody = {
            email: newTeacher.email,
            pesel: newTeacher.pesel,
            role: 'TEACHER',
            teacher: {
                firstName: newTeacher.firstName,
                lastName: newTeacher.lastName,
                dateOfBirth: newTeacher.dateOfBirth,
                dateOfEmployment: newTeacher.dateOfEmployment,
            },
        };

        try {
            const response = await createTeacher(requestBody);
            
            const createdTeacher = await fetchTeacher(response.id);


            const savedTeacher = {
                id: createdTeacher.id,
                name: createdTeacher.name,
                email: createdTeacher.email,
                pesel : createdTeacher.pesel,
                dateOfBirth: createdTeacher.dateOfBirth,
                dateOfEmployment: createdTeacher.dateOfEmployment,
            }

            if (savedTeacher) {
                setData((prevData) => [...prevData, savedTeacher]);
            }
        } catch (error) {
            console.error('Error creating teacher:', error);
        }
    };

    const handleUpdate = async (updatedTeacher) => {
        const requestBody = {
            teacherID: teacherToEdit,
            email: updatedTeacher.email,
            pesel: updatedTeacher.pesel,
            role: 'TEACHER',
            teacher: {
                firstName: updatedTeacher.firstName,
                lastName: updatedTeacher.lastName,
                dateOfBirth: updatedTeacher.dateOfBirth,
                dateOfEmployment: updatedTeacher.dateOfEmployment,
            },
        };

        const success = await updateTeacher(teacherDetails);
        if (success) {
            setData((prevData) =>
                prevData.map((teacher) =>
                    teacher.id === teacherToEdit.id
                        ? { ...teacher, ...teacherDetails }
                        : teacher
                )
            );
            setIsEditModalOpen(false);
        } else {
            console.error('Failed to update teacher');
        }
    };

    const handleDelete = async (teacherId) => {
        const success = await deleteTeacher(teacherId);
        if (success) {
            setData((prevData) => prevData.filter(teacher => teacher.id !== teacherId));
            setIsDeleteModalOpen(false);
        } else {
            console.error('Failed to delete teacher');
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
                id: 'email',
                header: 'Email',
                accessorKey: 'email',
            },
            {
                id: 'dateOfBirth',
                header: 'Date of Birth',
                accessorKey: 'dateOfBirth',
            },
            {
                id: 'dateOfEmployment',
                header: 'Date of Employment',
                accessorKey: 'dateOfEmployment',
            },
            {
                id: 'actions',
                header: 'Actions',
                accessorKey: 'id',
                cell: ({ row }) => (
                    <div>
                        <button
                            onClick={() => {
                                const teacherData = {
                                    id: row.original.id,
                                    firstName: row.original.firstName,
                                    lastName: row.original.lastName,
                                    email: row.original.email,
                                    subject: row.original.subject,
                                };
                                setTeacherToEdit(teacherData);
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
            <TopBar title="Teacher Management" />
            <div className="p-8">
                <button
                    onClick={() => setIsAddModalOpen(true)}
                    className="mb-4 px-4 py-2 bg-green-500 text-white rounded"
                >
                    Add New
                </button>
                <Table columns={columns} data={data} />
            </div>
            <AddTeacherModal
                isOpen={isAddModalOpen}
                onClose={() => setIsAddModalOpen(false)}
                onSave={handleSave}
            />
            <EditTeacherModal
                isOpen={isEditModalOpen}
                onClose={() => setIsEditModalOpen(false)}
                onSave={handleUpdate}
                teacher={teacherToEdit}
            />
            <DeleteFieldModal
                isOpen={isDeleteModalOpen}
                onClose={() => setIsDeleteModalOpen(false)}
                onDelete={handleDelete}
                itemId={itemToDelete}
                itemType="Teacher"
            />
        </div>
    );
}

export default TeacherManagementPage;