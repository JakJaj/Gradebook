import React, { useState, useEffect } from 'react';
import { fetchParent } from '../../../../data/parent/getData';

function EditParentModal({ isOpen, onClose, onSave, parent }) {
    const [parentData, setParentData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        pesel: '',
    });

    const [errors, setErrors] = useState({});

    useEffect(() => {

        const parentDetails = async () => {

            if (parent && parent.id) {
                const parentDetails = await fetchParent(parent.id);
                
                setParentData({
                    firstName: parentDetails.firstName,
                    lastName: parentDetails.lastName,
                    email: parentDetails.email,
                    pesel: parentDetails.pesel,
                });
            }
        };

        parentDetails();
    }, [parent]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setParentData((prevParent) => ({
            ...prevParent,
            [name]: value,
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (parentData.pesel.length !== 11 || !/^\d+$/.test(parentData.pesel)) {
            setErrors({ pesel: 'PESEL must be 11 digits long' });
            return;
        }
    
        const updatedParentData = {
            email: parentData.email,
            pesel: parentData.pesel,
            firstName: parentData.firstName,
            lastName: parentData.lastName,
        };

        onSave(updatedParentData);
        setParentData({
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
        <div className="fixed inset-0 bg-gray-600 bg-opacity-50 flex justify-center items-center">
            <div className="bg-white p-6 rounded shadow-lg w-3/5" onClick={(e) => e.stopPropagation()}>
                <h2 className="text-2xl mb-4">Edit Parent</h2>
                <form onSubmit={handleSubmit}>
                    <div className="mb-4">
                        <label className="block text-gray-700">First Name</label>
                        <input
                            type="text"
                            name="firstName"
                            value={parentData.firstName}
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
                            value={parentData.lastName}
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
                            value={parentData.email}
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
                            value={parentData.pesel}
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
    </div>
    );
}

export default EditParentModal;