import React, { useState, useEffect } from 'react';
import { fetchTeacher } from '../../../../data/teacher/getData';

function EditTeacherModal({ isOpen, onClose, onSave, teacher }) {
    const [teacherData, setTeacherData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        subject: '',
    });

    const [errors, setErrors] = useState({});

    useEffect(() => {
        const teacherDetails = async () => {
            if (teacher && teacher.id) {
                const teacherDetails = await fetchTeacher(teacher.id);
                setTeacherData({
                    firstName: teacherDetails.firstName,
                    lastName: teacherDetails.lastName,
                    email: teacherDetails.email,
                    subject: teacherDetails.subject,
                });
            }
        };

        teacherDetails();
    }, [teacher]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setTeacherData((prevTeacher) => ({
            ...prevTeacher,
            [name]: value,
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSave(teacherData);
        onClose();
    };

    if (!isOpen) return null;

    return (
        <div className="fixed inset-0 bg-gray-600 bg-opacity-50 flex justify-center items-center">
            <div className="bg-white p-6 rounded shadow-lg w-3/5">
                <h2 className="text-2xl mb-4">Edit Teacher</h2>
                <form onSubmit={handleSubmit}>
                    <div className="flex mb-4">
                        <div className="w-1/2 pr-2">
                            <label className="block text-gray-700">First Name</label>
                            <input
                                type="text"
                                name="firstName"
                                value={teacherData.firstName}
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
                                value={teacherData.lastName}
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
                            value={teacherData.email}
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
                            value={teacherData.subject}
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

export default EditTeacherModal;