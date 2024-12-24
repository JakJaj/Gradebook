import React, { useState } from 'react';

function AddStudentModal({ isOpen, onClose, onSave, classes }) {
    const [student, setStudent] = useState({
        firstName: '',
        lastName: '',
        classID: '',
        dateOfBirth: '',
        city: '',
        street: '',
        houseNumber: '',
        email: '',
        pesel: '',
        className: '',
    });

    const [errors, setErrors] = useState({});

    const handleClassChange = (e) => {
        const selectedClass = classes.find(cls => cls.id === Number(e.target.value));
        setStudent((prevStudent) => ({
            ...prevStudent,
            classID: selectedClass.id,
            className: selectedClass.name,
        }));
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        if (name === 'classID') {
            return handleClassChange(e);
        }
        setStudent((prevStudent) => ({
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
        if (!validatePesel(student.pesel)) {
            newErrors.pesel = 'PESEL must be 11 characters long';
        }
        if (Object.keys(newErrors).length > 0) {
            setErrors(newErrors);
            return;
        }
        onSave(student);
        onClose();
        
        setStudent({
            firstName: '',
            lastName: '',
            classID: '',
            dateOfBirth: '',
            city: '',
            street: '',
            houseNumber: '',
            email: '',
            pesel: '',
        })
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
                <h2 className="text-2xl mb-4">Add New Student</h2>
                <form onSubmit={handleSubmit}>
                    <div className="flex mb-4">
                        <div className="w-1/2 pr-2">
                            <label className="block text-gray-700">First Name</label>
                            <input
                                type="text"
                                name="firstName"
                                value={student.firstName}
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
                                value={student.lastName}
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
                            value={student.email}
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
                            value={student.pesel}
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
                            value={student.classID}
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
                            value={student.dateOfBirth}
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
                                value={student.city}
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
                                value={student.street}
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
                                value={student.houseNumber}
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

export default AddStudentModal;