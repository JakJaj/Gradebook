import React, { useMemo, useState, useEffect } from 'react';
import Table from '../../components/Table';
import TopBar from '../../components/TopBar';

function ParentManagementPage() {
    const [data, setData] = useState([]);
    const [isAddModalOpen, setIsAddModalOpen] = useState(false);
    const [isEditModalOpen, setIsEditModalOpen] = useState(false);
    const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false);
    const [itemToDelete, setItemToDelete] = useState(null);
    const [parentToEdit, setParentToEdit] = useState([]);

    useEffect(() => {
        const getData = async () => {
            try {
                const teachers = await fetchParents(); // This function is not defined
                setData(teachers);
            } catch (error) {
                console.error('Error fetching teachers in TeacherManagementPage:', error);
            }
        };

        getData();
    }, []);

    const handleSave = async (newParent) => {
        const requestBody = { // Parent object is not defined
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
            const response = await createParent(requestBody); // This function is not defined
            
            const createdTeacher = await fetchParent(response.id);  // This function is not defined


            const savedParent = { // Parent object is not defined
                id: createdTeacher.id,
                name: `${createdTeacher.firstName} ${createdTeacher.lastName}`,
                email: createdTeacher.email,
                pesel : createdTeacher.pesel,
                dateOfBirth: createdTeacher.dateOfBirth,
                dateOfEmployment: createdTeacher.dateOfEmployment,
            }

            if (savedParent) {
                setData((prevData) => [...prevData, savedParent]);
            }
        } catch (error) {
            console.error('Error creating teacher:', error);
        }
    };

    const handleUpdate = async (updatedParent) => {

        const requestBody = { // Parent object is not defined
            parent: {
                teacherID: updatedParent.id,
                firstName: updatedParent.firstName,
                lastName: updatedParent.lastName,
                dateOfBirth: updatedParent.dateOfBirth,
                dateOfEmployment: updatedParent.dateOfEmployment,
                email: updatedParent.email,
                pesel: updatedParent.pesel,
            }
        };

        const success = await updatedParent(requestBody); // This function is not defined

        if (success) {

            setData((prevData) =>
                prevData.map((parent) =>
                    parent.id === parentToEdit.id
                        ? { ...parent, ...success }
                        : parent
                )
            );
            setIsEditModalOpen(false);
        } else {
            console.error('Failed to update teacher');
        }
    };

    const handleDelete = async (parentId) => {
        const success = await deleteParent(parentId); // This function is not defined
        if (success) {
            setData((prevData) => prevData.filter(parent => parent.id !== parentId));
            setIsDeleteModalOpen(false);
        } else {
            console.error('Failed to delete teacher');
        }
    };

    const columns = useMemo( // This function is not defined
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
                                const parentData = { // Parent object is not defined
                                    id: row.original.id,
                                    firstName: row.original.firstName,
                                    lastName: row.original.lastName,
                                    email: row.original.email,
                                    pesel: row.original.pesel,
                                    dateOfBirth: row.original.dateOfBirth,
                                    dateOfEmployment: row.original.dateOfEmployment,
                                };
                                setParentToEdit(parentData);
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
            <TopBar title="Parent Management" />
            <div className="p-8">
                <button
                    onClick={() => setIsAddModalOpen(true)}
                    className="mb-4 px-4 py-2 bg-green-500 text-white rounded"
                >
                    Add New
                </button>
                <Table columns={columns} data={data} />
            </div>
            <AddParentModal // This component is not defined
                isOpen={isAddModalOpen}
                onClose={() => setIsAddModalOpen(false)}
                onSave={handleSave}
            />
            <EditParentModal // This component is not defined
                isOpen={isEditModalOpen}
                onClose={() => setIsEditModalOpen(false)}
                onSave={handleUpdate}
                teacher={parentToEdit}
            />
            <DeleteParentModal // This component is not defined
                isOpen={isDeleteModalOpen}
                onClose={() => setIsDeleteModalOpen(false)}
                onDelete={handleDelete}
                itemId={itemToDelete}
                itemType="Parent"
            />
        </div>
    );
}
export default ParentManagementPage;
