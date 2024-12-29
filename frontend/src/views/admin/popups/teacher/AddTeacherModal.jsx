import React, { useState } from 'react';

function AddTeacherModal({ isOpen, onClose, onSave }) {
    const [teacher, setTeacher] = useState([]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setTeacher((prevTeacher) => ({
            ...prevTeacher,
            [name]: value,
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSave(teacher);
        onClose();
    };

    if (!isOpen) return null;

    return (
        <div className="fixed inset-0 bg-gray-600 bg-opacity-50 flex justify-center items-center">
            <div className="bg-white p-6 rounded shadow-lg w-3/5">
                <h2 className="text-2xl mb-4">Add New Teacher</h2>
                <form onSubmit={handleSubmit}>
                    <div className="flex mb-4">
                        <div className="w-1/2 pr-2">
                            <label className="block text-gray-700">First Name</label>
                            <input
                                type="text"
                                name="firstName"
                                value={teacher.firstName}
                                onChange={handleChange}
                                className="w-full px-4 py-2 border rounded"
                                required
                            />
                        </div>
                        <div className="w-1/2 pl-2">
                            <label className="block text-gray-700">Last Name</label>
                            <input
                                type="text"
                                name="lastName"
                                value={teacher.lastName}
                                onChange={handleChange}
                                className="w-full px-4 py-2 border rounded"
                                required
                            />
                        </div>
                    </div>
                    <div className="mb-4">
                        <label className="block text-gray-700">Email</label>
                        <input
                            type="email"
                            name="email"
                            value={teacher.email}
                            onChange={handleChange}
                            className="w-full px-4 py-2 border rounded"
                            required
                        />
                    </div>
                    <div className="mb-4">
                        <label className="block text-gray-700">Subject</label>
                        <input
                            type="text"
                            name="subject"
                            value={teacher.subject}
                            onChange={handleChange}
                            className="w-full px-4 py-2 border rounded"
                            required
                        />
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

export default AddTeacherModal;