import React, { useState } from 'react';

function AddParentModal({ isOpen, onClose, onSave }) {
    const [parent, setParent] = useState({
        firstName: '',
        lastName: '',
        email: '',
        pesel: '',
    });

    const [errors, setErrors] = useState({});

    const handleChange = (e) => {
        const { name, value } = e.target;
        setParent((prevParent) => ({
            ...prevParent,
            [name]: value,
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (parent.pesel.length !== 11 || !/^\d+$/.test(parent.pesel)) {
            setErrors({ pesel: 'PESEL must be 11 digits long' });
            return;
        }
        
        const parentData = {
            email: parent.email,
            pesel: parent.pesel,   
            firstName: parent.firstName,
            lastName: parent.lastName,
        }

        onSave(parentData);
        setParent({
            firstName: '',
            lastName: '',
            email: '',
            pesel: '',
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
                <h2 className="text-2xl mb-4">Add New Parent</h2>
                <form onSubmit={handleSubmit}>
                    <div className="mb-4">
                        <label className="block text-gray-700">First Name</label>
                        <input
                            type="text"
                            name="firstName"
                            value={parent.firstName}
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
                            value={parent.lastName}
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
                            value={parent.email}
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
                            value={parent.pesel}
                            onChange={handleChange}
                            className="w-full px-4 py-2 border rounded"
                            required
                        />
                        {errors.pesel && (
                            <p className="text-red-500 text-sm mt-1">{errors.pesel}</p>
                        )}
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

export default AddParentModal;