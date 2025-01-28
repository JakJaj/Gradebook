import React, { useState, useEffect } from 'react';
import { fetchCourse } from '../../../../data/course/getData';

function EditCourseModal({ isOpen, onClose, onSave, course, teachers }) {
    const [courseData, setCourseData] = useState({
        id: '',
        name: '',
        description: '',
        tutorID: '',
    });

    useEffect(() => {
        const courseDetails = async () => {
            if (course && course.id) {
                const courseDetails = await fetchCourse(course.id);
                setCourseData({
                    id: courseDetails.id,
                    name: courseDetails.name,
                    description: courseDetails.description,
                    tutorID: courseDetails.tutor.id,
                });
            }
        };

        courseDetails();
    }, [course]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setCourseData((prevCourse) => ({
            ...prevCourse,
            [name]: value,
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        const updatedCourseData = {
            id: courseData.id,
            name: courseData.name,
            description: courseData.description,
            tutorID: courseData.tutorID,
        };

        onSave(updatedCourseData);
        setCourseData({
            id: '',
            name: '',
            description: '',
            tutorID: '',
        });
        onClose();
    };

    if (!isOpen) return null;

    return (
        <div
            className="fixed inset-0 bg-gray-600 bg-opacity-50 flex justify-center items-center"
            onClick={onClose}
        >
            <div
                className="bg-white p-6 rounded shadow-lg w-3/5"
                onClick={(e) => e.stopPropagation()}
            >
                <h2 className="text-2xl mb-4">Edit Course</h2>
                <form onSubmit={handleSubmit}>
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="name">
                            Course Name
                        </label>
                        <input
                            type="text"
                            name="name"
                            value={courseData.name}
                            onChange={handleChange}
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        />
                    </div>
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="description">
                            Description
                        </label>
                        <textarea
                            name="description"
                            value={courseData.description}
                            onChange={handleChange}
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        />
                    </div>
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="tutorID">
                            Tutor
                        </label>
                        <select
                            name="tutorID"
                            value={courseData.tutorID}
                            onChange={handleChange}
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        >
                            <option value="">Select a tutor</option>
                            {teachers.map((teacher) => (
                                <option key={teacher.id} value={teacher.id}>
                                    {teacher.name}
                                </option>
                            ))}
                        </select>
                    </div>
                    <div className="flex justify-end">
                        <button
                            type="button"
                            onClick={onClose}
                            className="px-4 py-2 bg-gray-500 text-white rounded mr-2"
                        >
                            Cancel
                        </button>
                        <button
                            type="submit"
                            className="px-4 py-2 bg-blue-500 text-white rounded"
                        >
                            Save
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default EditCourseModal;