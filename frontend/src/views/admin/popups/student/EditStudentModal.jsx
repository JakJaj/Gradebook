import React, { useState, useEffect } from 'react';
import { fetchStudent } from '../../../../data/student/getData';
function EditStudentModal({ isOpen, onClose, onSave, student, classes }) {
    const [studentData, setStudentData] = useState({
        firstName: '',
        lastName: '',
        classID: '',
        dateOfBirth: '',
        city: '',
        street: '',
        houseNumber: '',
        email: '',
        pesel: '',
    });

    const [errors, setErrors] = useState({});

    useEffect(() => {
        const studentDetails = async () => {
            if (student) {
                const studentDetails = await fetchStudent(student);
                const [day, month, year] = studentDetails.dateOfBirth.split('-');
                setStudentData({
                    firstName: studentDetails.firstName,
                    lastName: studentDetails.lastName,
                    classID: studentDetails.classID,
                    dateOfBirth: `${year}-${month}-${day}`,
                    city: studentDetails.city,
                    street: studentDetails.street,
                    houseNumber: studentDetails.houseNumber,
                    email: studentDetails.email,
                    pesel: studentDetails.pesel,
                });
            }
        };

        studentDetails();
    }, [student]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setStudentData((prevStudent) => ({
            ...prevStudent,
            [name]: value,
        }));
    };

    const validatePesel = (pesel) => {
        return pesel.length === 11;
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const newErrors = {};
        if (!validatePesel(studentData.pesel)) {
            newErrors.pesel = 'PESEL must be 11 characters long';
        }
        if (Object.keys(newErrors).length > 0) {
            setErrors(newErrors);
            return;
        }
        onSave(studentData);
        onClose();
    };

    if (!isOpen) return null;

    const handleOverlayClick = (e) => {
        if (e.target === e.currentTarget) {
            onClose();
        }
    };

    return (
        <div
            className="fixed inset-0 bg-gray-600 bg-opacity-50 flex justify-center items-center"
            onClick={handleOverlayClick}
        >
            <div className="bg-white p-6 rounded shadow-lg w-3/5" onClick={(e) => e.stopPropagation()}>
                <h2 className="text-2xl mb-4">Edit Student</h2>
                <form onSubmit={handleSubmit}>
                    <div className="flex mb-4">
                        <div className="w-1/2 pr-2">
                            <label className="block text-gray-700">First Name</label>
                            <input
                                type="text"
                                name="firstName"
                                value={studentData.firstName}
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
                                value={studentData.lastName}
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
                            value={studentData.email}
                            onChange={handleChange}
                            className="w-full px-4 py-2 border rounded"
                            required
                        />
                    </div>
                    <div className="mb-4">
                        <label className="block text-gray-700">PESEL</label>
                        <input
                            type="text"
                            name="pesel"
                            value={studentData.pesel}
                            onChange={handleChange}
                            className="w-full px-4 py-2 border rounded"
                            required
                        />
                        {errors.pesel && <p className="text-red-500 text-sm">{errors.pesel}</p>}
                    </div>
                    <div className="mb-4">
                        <label className="block text-gray-700">Class</label>
                        <select
                            name="classID"
                            value={studentData.classID}
                            onChange={handleChange}
                            className="w-full px-4 py-2 border rounded"
                            required
                        >
                            <option value="">Select Class</option>
                            {classes.map(cls => (
                                <option key={cls.id} value={cls.id}>
                                    {cls.name}
                                </option>
                            ))}
                        </select>
                    </div>
                    <div className="mb-4">
                        <label className="block text-gray-700">Date of Birth</label>
                        <input
                            type="date"
                            name="dateOfBirth"
                            value={studentData.dateOfBirth}
                            onChange={handleChange}
                            className="w-full px-4 py-2 border rounded"
                            required
                        />
                    </div>
                    <div className="flex mb-4">
                        <div className="w-1/3 pr-2">
                            <label className="block text-gray-700">City</label>
                            <input
                                type="text"
                                name="city"
                                value={studentData.city}
                                onChange={handleChange}
                                className="w-full px-4 py-2 border rounded"
                                required
                            />
                        </div>
                        <div className="w-1/3 px-2">
                            <label className="block text-gray-700">Street</label>
                            <input
                                type="text"
                                name="street"
                                value={studentData.street}
                                onChange={handleChange}
                                className="w-full px-4 py-2 border rounded"
                                required
                            />
                        </div>
                        <div className="w-1/3 pl-2">
                            <label className="block text-gray-700">House Number</label>
                            <input
                                type="text"
                                name="houseNumber"
                                value={studentData.houseNumber}
                                onChange={handleChange}
                                className="w-full px-4 py-2 border rounded"
                                required
                            />
                        </div>
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

export default EditStudentModal;