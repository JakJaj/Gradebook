import React, { useMemo, useState, useEffect } from 'react';
import Table from '../../components/Table';
import TopBar from '../../components/TopBar';
import { fetchCourses, fetchCourse } from '../../data/course/getData';
import { createCourse } from '../../data/course/postData';
import { updateCourse } from '../../data/course/putData';
import { deleteCourse } from '../../data/course/deleteData';
import { fetchTeachers } from '../../data/teacher/getData';
import AddCourseModal from './popups/course/AddCourseModal';
import EditCourseModal from './popups/course/EditCourseModal';
import DeleteFieldModal from '../../components/DeleteFieldModal';

function CourseManagementPage() {
    const [data, setData] = useState([]);
    const [isAddModalOpen, setIsAddModalOpen] = useState(false);
    const [isEditModalOpen, setIsEditModalOpen] = useState(false);
    const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false);
    const [itemToDelete, setItemToDelete] = useState(null);
    const [courseToEdit, setCourseToEdit] = useState([]);
    const [teachers, setTeachers] = useState([]);

    useEffect(() => {
        const getData = async () => {
            try {
                const courses = await fetchCourses(); 
                setData(courses);
            } catch (error) {
                console.error('Error fetching courses in CourseManagementPage:', error);
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

    const handleSave = async (newCourse) => {
        const requestBody = {
            courseName: newCourse.name,
            courseDescription: newCourse.description,
            teacherID: newCourse.tutorID,
        };

        try {
            const response = await createCourse(requestBody); 
            
            const createdCourse = await fetchCourse(response.course.courseID); 

            const savedCourse = { 
                id: createdCourse.id,
                name: createdCourse.name,
                description: createdCourse.description,
                tutor: {
                    id: createdCourse.tutor.id,
                    name: createdCourse.tutor.name,
                }
            }

            if (savedCourse) {
                setData((prevData) => [...prevData, savedCourse]);
            }
        } catch (error) {
            console.error('Error creating course:', error);
        }
    };

    const handleUpdate = async (updatedCourse) => {

        
        const requestBody = { 
            courseID: updatedCourse.id,
            courseName: updatedCourse.name,
            courseDescription: updatedCourse.description,
            teacherID: Number(updatedCourse.tutorID),
        };

        const success = await updateCourse(requestBody); 

        if (success) {
            
            setData((prevData) =>
                prevData.map((course) =>
                    course.id === courseToEdit.id
                        ? { ...course, ...success }
                        : course
                )
            );

            setIsEditModalOpen(false);
        } else {
            console.error('Failed to update course');
        }
    };

    const handleDelete = async (courseId) => {
        const success = await deleteCourse(courseId);
        if (success) {
            setData((prevData) => prevData.filter(course => course.id !== courseId));
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
                id: 'description',
                header: 'Description',
                accessorKey: 'description',
            },
            {
                id: 'tutor',
                header: 'Tutor',
                accessorKey: 'tutor.name',
            },
            {
                id: 'actions',
                header: 'Actions',
                accessorKey: 'id',
                cell: ({ row }) => (
                    <div>
                        <button
                            onClick={() => {
                                const courseData = {
                                    id: row.original.id,
                                    name: row.original.name,
                                    description: row.original.description,
                                    tutorID: row.original.tutor.id,
                                };
                                setCourseToEdit(courseData);
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
            <TopBar title="Course Management" />
            <div className="p-8">
                <button
                    onClick={() => setIsAddModalOpen(true)}
                    className="mb-4 px-4 py-2 bg-green-500 text-white rounded"
                >
                    Add New
                </button>
                <Table columns={columns} data={data} />
            </div>
            <AddCourseModal
                isOpen={isAddModalOpen}
                onClose={() => setIsAddModalOpen(false)}
                onSave={handleSave}
                teachers={teachers}
            />
            <EditCourseModal 
                isOpen={isEditModalOpen}
                onClose={() => setIsEditModalOpen(false)}
                onSave={handleUpdate}
                teachers={teachers}
                course={courseToEdit}
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
export default CourseManagementPage;
