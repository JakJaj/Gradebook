import React from 'react';

function DeleteFieldModal({ isOpen, onClose, onDelete, itemId, itemType }) {
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
            <div className="bg-white p-6 rounded shadow-lg w-1/3" onClick={(e) => e.stopPropagation()}>
                <h2 className="text-2xl mb-4">Delete {itemType}</h2>
                <p>Are you sure you want to delete this {itemType}?</p>
                <div className="flex justify-end mt-4">
                    <button
                        type="button"
                        onClick={onClose}
                        className="px-4 py-2 bg-gray-500 text-white rounded mr-2"
                    >
                        Cancel
                    </button>
                    <button
                        type="button"
                        onClick={() => onDelete(itemId)}
                        className="px-4 py-2 bg-red-500 text-white rounded"
                    >
                        Delete
                    </button>
                </div>
            </div>
        </div>
    );
}

export default DeleteFieldModal;