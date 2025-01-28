import React, { useState, useEffect } from 'react';
import { fetchClass } from '../../../../data/class/getData';

function EditClassModal({ isOpen, onClose, onSave, theClass, teachers }) {
    const [classData, setClassData] = useState({
        id: '',
        name: '',
        startYear: '',
        tutorID: '',
    });

    useEffect(() => {

        
        const classDetails = async () => {
            if (theClass && theClass.id) {
                const classDetails = await fetchClass(theClass.id);

                setClassData({
                    id: classDetails.id,
                    name: classDetails.name,
                    startYear: classDetails.startYear,
                    tutorID: classDetails.tutor.id,
                });
            }
        };

        classDetails();
    }, [theClass]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setClassData((prevClass) => ({
            ...prevClass,
            [name]: value,
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        const updatedClassData = {
            id: classData.id,
            name: classData.name,
            startYear: classData.startYear,
            tutorID: {
                id: classData.tutorID,
            }
        };

        onSave(updatedClassData);
        setClassData({
            id: '',
            name: '',
            startYear: '',
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
                <h2 className="text-2xl mb-4">Add Class</h2>
                <form onSubmit={handleSubmit}>
                    <div className="flex mb-4">
                        <div className="w-1/2 mr-2">
                            <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="name">
                                Class Name
                            </label>
                            <input
                                type="text"
                                name="name"
                                value={classData.name}
                                onChange={handleChange}
                                className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                            />
                        </div>
                        <div className="w-1/2 ml-2">
                            <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="startYear">
                                Start Year
                            </label>
                            <input
                                type="text"
                                name="startYear"
                                value={classData.startYear}
                                onChange={handleChange}
                                className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                            />
                        </div>
                    </div>
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="tutorID">
                            Tutor
                        </label>
                        <select
                            name="tutorID"
                            value={classData.tutorID}
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

export default EditClassModal;