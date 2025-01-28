import React, { useMemo, useState, useEffect} from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import Table from '../../components/Table';
import TopBar from '../../components/TopBar';
import { fetchClasses, fetchClass } from '../../data/class/getData';
import { createClass } from '../../data/class/postData';
import { updateClass } from '../../data/class/putData';
import { deleteClass } from '../../data/class/deleteData';
import { fetchTeachers } from '../../data/teacher/getData';
import { fetchCourses } from '../../data/course/getData';
import AddClassModal from './popups/class/AddClassModal';
import EditClassModal from './popups/class/EditClassModal';
import DeleteFieldModal from '../../components/DeleteFieldModal';

function ClassManagementPage() {
    const [data, setData] = useState([]);
    const [isAddModalOpen, setIsAddModalOpen] = useState(false);
    const [isEditModalOpen, setIsEditModalOpen] = useState(false);
    const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false);
    const [itemToDelete, setItemToDelete] = useState(null);
    const [classToEdit, setClassToEdit] = useState([]);
    const [teachers, setTeachers] = useState([]);
    const navigate = useNavigate();
    const location = useLocation();
    const [showMessage, setShowMessage] = useState(false);
    const [grades, setGrades] = useState([]);
    
    useEffect(() => {
        const getData = async () => {
            try {
                const theClass = await fetchClasses();
                setData(theClass);
            } catch (error) {
                console.error('Error fetching theClass in ClassManagementPage:', error);
            }
        };
        getData();
    }, []);

    useEffect(() => {
            const getTeachers = async () => {
                try {
                    const teachers = await fetchTeachers();
                    setTeachers(teachers);
                } catch (error) {
                    console.error('Error fetching teachers in CourseManagementPage:', error);
                }
            };
            getTeachers();
        }, []);

        useEffect(() => {
            if (location.state && location.state.message) {
                setShowMessage(true);
            }
        }, [location.state]);
    
        const handleCloseMessage = () => {
            setShowMessage(false);
        };

    const handleSave = async (newClass) => {
        const requestBody = { 
            className : newClass.name,
            startYear : newClass.startYear,
            teacherID : Number(newClass.tutorID)
        };

        try {
            const response = await createClass(requestBody); 

            const createdClass = await fetchClass(response.theClass.classID);


            const savedClass = { 
                id: createdClass.id,
                name: createdClass.name,
                startYear: createdClass.startYear,
                tutor: {
                    id: createdClass.tutor.id,
                    name: createdClass.tutor.name,
                }
            }

            if (savedClass) {
                setData((prevData) => [...prevData, savedClass]);
            }
        } catch (error) {
            console.error('Error creating class:', error);
        }
    };

    const handleUpdate = async (updatedClass) => {



        const requestBody = {  
            theClass : {
                classID: updatedClass.id,
                className: updatedClass.name,
                startYear: updatedClass.startYear,
                tutorID: Number(updatedClass.id)
            }
        };

        const success = await updateClass(requestBody); 

        if (success) {


            setData((prevData) =>
                prevData.map((theClass) =>
                    theClass.id === classToEdit.id
                        ? { ...theClass, ...success }
                        : theClass
                )
            );
            setIsEditModalOpen(false);
        } else {
            console.error('Failed to update teacher');
        }
    };

    const handleDelete = async (classId) => {
        const success = await deleteClass(classId); 
        if (success) {
            setData((prevData) => prevData.filter(theClass => theClass.id !== classId));
            setIsDeleteModalOpen(false);
        } else {
            console.error('Failed to delete class');
        }
    };
    const handleManageTimetable = (theClass) => {
        navigate(`/admin/classManagement/timetalbe/${theClass.id}`, { state: { theClass } });
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
                id: 'startYear',
                header: 'Start Year',
                accessorKey: 'startYear',
            },
            {
                id: 'timetable',
                header: 'Timetable',
                accessorKey: 'timetable',
                cell: ({ row }) => (
                    <div>
                        <button
                            onClick={() => handleManageTimetable(row.original)}
                            className="px-4 py-2 bg-blue-500 text-white rounded mr-2"
                        >
                            Manage Timetable
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
                                const classData = {
                                    id: row.original.id,
                                    className: row.original.name,
                                    startYear: row.original.startYear,
                                    tutor: row.original.tutor.id,
                                };
                                setClassToEdit(classData); 
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
            {showMessage && (
                <div className="fixed bottom-0 left-0 right-0 bg-green-500 text-white text-center py-2 flex justify-center items-center px-4">
                    <span>{location.state.message}</span>
                    <button onClick={handleCloseMessage} className="ml-4 text-white font-bold">
                        X
                    </button>
                </div>
            )}
            <TopBar title="Class Management" />
            <div className="p-8">
                <button
                    onClick={() => setIsAddModalOpen(true)}
                    className="mb-4 px-4 py-2 bg-green-500 text-white rounded"
                >
                    Add New
                </button>
                <Table columns={columns} data={data} />
            </div>
            <AddClassModal
                isOpen={isAddModalOpen}
                onClose={() => setIsAddModalOpen(false)}
                onSave={handleSave}
                teachers= {teachers}
            />
            <EditClassModal 
                isOpen={isEditModalOpen}
                onClose={() => setIsEditModalOpen(false)}
                onSave={handleUpdate}
                theClass={classToEdit}
                teachers= {teachers}
            />
            <DeleteFieldModal
                isOpen={isDeleteModalOpen}
                onClose={() => setIsDeleteModalOpen(false)}
                onDelete={handleDelete}
                itemId={itemToDelete}
                itemType="Parent"
            />
        </div>
    );
}
export default ClassManagementPage;
