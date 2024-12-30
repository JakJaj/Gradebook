import React, { useState } from 'react';

function AddTeacherModal({ isOpen, onClose, onSave }) {
    const [teacher, setTeacher] = useState({
        firstName: '',
        lastName: '',
        email: '',
        pesel: '',
        dateOfBirth: '',
        dateOfEmployment: '',
    });

    const [errors, setErrors] = useState({});

    const handleChange = (e) => {
        const { name, value } = e.target;
        setTeacher((prevTeacher) => ({
            ...prevTeacher,
            [name]: value,
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (teacher.pesel.length !== 11 || !/^\d+$/.test(teacher.pesel)) {
            setErrors({ pesel: 'PESEL must be 11 digits long' });
            return;
        }
        const [year, month, day] = teacher.dateOfBirth.split('-');
        const formattedDateOfBirth = `${day}-${month}-${year}`;

        const [yearEmployment, monthEmployment, dayEmployment] = teacher.dateOfEmployment.split('-');
        const formattedDateOfEmployment = `${dayEmployment}-${monthEmployment}-${yearEmployment}`;

        
        const teacherData = {
            email: teacher.email,
            pesel: teacher.pesel,   
            firstName: teacher.firstName,
            lastName: teacher.lastName,
            dateOfBirth: formattedDateOfBirth,
            dateOfEmployment: formattedDateOfEmployment,
        }

        onSave(teacherData);
        setTeacher({
            firstName: '',
            lastName: '',
            email: '',
            pesel: '',
            dateOfBirth: '',
            dateOfEmployment: '',
        });
        setErrors({});
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
                <h2 className="text-2xl mb-4">Add New Teacher</h2>
                <form onSubmit={handleSubmit}>
                    <div className="mb-4">
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
                    <div className="mb-4">
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
                        <label className="block text-gray-700">PESEL</label>
                        <input
                            type="text"
                            name="pesel"
                            value={teacher.pesel}
                            onChange={handleChange}
                            className="w-full px-4 py-2 border rounded"
                            required
                        />
                        {errors.pesel && (
                            <p className="text-red-500 text-sm mt-1">{errors.pesel}</p>
                        )}
                    </div>
                    <div className="flex mb-4">
                        <div className="w-1/2 pr-2">
                            <label className="block text-gray-700">Date of Birth</label>
                            <input
                                type="date"
                                name="dateOfBirth"
                                value={teacher.dateOfBirth}
                                onChange={handleChange}
                                className="w-full px-4 py-2 border rounded"
                                required
                            />
                        </div>
                        <div className="w-1/2 pl-2">
                            <label className="block text-gray-700">Date of Employment</label>
                            <input
                                type="date"
                                name="dateOfEmployment"
                                value={teacher.dateOfEmployment}
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

export default AddTeacherModal;