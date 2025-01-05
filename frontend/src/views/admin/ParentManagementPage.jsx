import React, { useMemo, useState, useEffect } from 'react';
import Table from '../../components/Table';
import TopBar from '../../components/TopBar';
import AddParentModal from './popups/parent/AddParentModal';
import EditParentModal from './popups/parent/EditParentModal';
import DeleteFieldModal from '../../components/DeleteFieldModal';
import { fetchParents, fetchParent } from '../../data/parent/getData';
import { createParent } from '../../data/parent/postData';
import { updateParent } from '../../data/parent/putData';
import { deleteParent } from '../../data/parent/deleteData';
import EditParentStudentsModal from './popups/parent/EditParentStudentsModal';


function ParentManagementPage() {
    const [data, setData] = useState([]);
    const [isAddModalOpen, setIsAddModalOpen] = useState(false);
    const [isEditModalOpen, setIsEditModalOpen] = useState(false);
    const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false);
    const [isEditParentStudentsModalOpen, setIsEditParentStudentsModalOpen] = useState(false);
    const [itemToDelete, setItemToDelete] = useState(null);
    const [parentToEdit, setParentToEdit] = useState([]);
    const [showNotification, setShowNotification] = useState(false);
    const [notificationMessage, setNotificationMessage] = useState('');

    useEffect(() => {
        const getData = async () => {
            try {
                const parents = await fetchParents();
                setData(parents);
            } catch (error) {
                console.error('Error fetching parents in ParentManagementPage:', error);
            }
        };

        getData();
    }, []);

    const handleSave = async (newParent) => {
        const requestBody = {
            email: newParent.email,
            pesel: newParent.pesel,
            role: 'PARENT',
            parent: {
                firstName: newParent.firstName,
                lastName: newParent.lastName,
            },
        };

        try {
            const response = await createParent(requestBody);
            
            const createdParent = await fetchParent(response.id);

            const savedParent = {
                id: createdParent.id,
                name: `${createdParent.firstName} ${createdParent.lastName}`,
                email: createdParent.email,
                pesel : createdParent.pesel,
            }

            if (savedParent) {
                setData((prevData) => [...prevData, savedParent]);
                setNotificationMessage('Parent created successfully! Generated password: ' +  response.password);
                setShowNotification(true);
            }
        } catch (error) {
            console.error('Error creating teacher:', error);
        }
    };

    const handleUpdate = async (updatedParent) => {

        const requestBody = { 
            parent: {
                parentID: parentToEdit.id,
                firstName: updatedParent.firstName,
                lastName: updatedParent.lastName,
                email: updatedParent.email,
                pesel: updatedParent.pesel,
            }
        };

        const success = await updateParent(requestBody);

        if (success) {
            console.log(success)

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
        const success = await deleteParent(parentId); 
        if (success) {
            setData((prevData) => prevData.filter(parent => parent.id !== parentId));
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
                id: 'student',
                header: 'Student',
                accessorKey: 'student',
                cell: ({ row }) => (
                    <div>
                        <button
                            onClick={() => {
                                console.log('Manage students for parent:', row.original.id); // Make a propper parent-student relation popup / page
                                setParentToEdit(row.original);
                                setIsEditParentStudentsModalOpen(true);
                            }}
                            className="px-4 py-2 bg-blue-500 text-white rounded mr-2"
                        >
                            Manage Students
                        </button>
                        </div>
                ),
            },
            {
                id: 'actions',
                header: 'Actions',
                accessorKey: 'id',
                cell: ({ row }) => (
                    <div>
                        <button
                            onClick={() => {
                                const parentData = {
                                    id: row.original.id,
                                    firstName: row.original.firstName,
                                    lastName: row.original.lastName,
                                    email: row.original.email,
                                    pesel: row.original.pesel,
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
            {showNotification && (
            <div className="fixed bottom-0 left-0 right-0 bg-green-500 text-white text-center py-2 flex justify-center items-center px-4">
                <span>{notificationMessage}</span>
                <button onClick={() => setShowNotification(false)} className="ml-4 text-white font-bold">
                    X
                </button>
            </div>
        )}
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
            <AddParentModal
                isOpen={isAddModalOpen}
                onClose={() => setIsAddModalOpen(false)}
                onSave={handleSave}
            />
            <EditParentModal 
                isOpen={isEditModalOpen}
                onClose={() => setIsEditModalOpen(false)}
                onSave={handleUpdate}
                parent={parentToEdit}
            />
            <DeleteFieldModal 
                isOpen={isDeleteModalOpen}
                onClose={() => setIsDeleteModalOpen(false)}
                onDelete={handleDelete}
                itemId={itemToDelete}
                itemType="Parent"
            />
            <EditParentStudentsModal
                isOpen={isEditParentStudentsModalOpen}
                onClose={() => setIsEditParentStudentsModalOpen(false)}
                parent = {parentToEdit}
            />
        </div>
    );
}
export default ParentManagementPage;
