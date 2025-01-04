import React, { useState } from 'react';

function AddClassModal({ isOpen, onClose, onSave, teachers }) {
    const [theClass, setTheClass] = useState({
        name: '',
        startYear: '',
        tutorID: '',
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setTheClass((prevClass) => ({
            ...prevClass,
            [name]: value,
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSave(theClass);
        setTheClass({
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
                onClick={(e) => e.stopPropagation()} // Prevent closing the modal when clicking inside the content
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
                                value={theClass.name}
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
                                value={theClass.startYear}
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
                            value={theClass.tutorID}
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

export default AddClassModal;