import React, { useState, useEffect } from 'react';
import { fetchStudents } from '../../../../data/student/getData';
import { fetchAssociatedStudents } from '../../../../data/parent/getData';
import { addStudentToParent } from '../../../../data/parent/postData';
import { removeStudentFromParent } from '../../../../data/parent/deleteData';

function EditParentStudentsModal({ isOpen, onClose, parent }) {
    const [allStudents, setAllStudents] = useState([]);
    const [associatedStudents, setAssociatedStudents] = useState([]);
    const [originalAssociatedStudents, setOriginalAssociatedStudents] = useState([]);
    const [studentsToAdd, setStudentsToAdd] = useState([]);
    const [studentsToRemove, setStudentsToRemove] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');

    useEffect(() => {
        if (!isOpen) return;

        const getAllStudents = async () => {
            try {
                const students = await fetchStudents();
                return students;
            } catch (error) {
                console.error('Error fetching all students:', error);
                return [];
            }
        };

        const getAssociatedStudents = async () => {
            if (parent && parent.id) {
                try {
                    const students = await fetchAssociatedStudents(parent.id);
                    return students;
                } catch (error) {
                    console.error(`Error fetching associated students for parent ID ${parent.id}:`, error);
                    return [];
                }
            }
            return [];
        };

        const fetchData = async () => {
            const [allStudentsData, associatedStudentsData] = await Promise.all([getAllStudents(), getAssociatedStudents()]);
            setAssociatedStudents(associatedStudentsData);
            setOriginalAssociatedStudents(associatedStudentsData);
            setAllStudents(allStudentsData.filter(student => 
                !associatedStudentsData.some(associatedStudent => associatedStudent.id === student.id)
            ));
        };

        // Reset state when modal is opened
        setAllStudents([]);
        setAssociatedStudents([]);
        setOriginalAssociatedStudents([]);
        setStudentsToAdd([]);
        setStudentsToRemove([]);
        setSearchTerm('');

        fetchData();
    }, [parent, isOpen]);

    const handleAddStudent = (studentId) => {
        const student = allStudents.find(s => s.id === studentId);
        setAssociatedStudents([...associatedStudents, student]);
        setAllStudents(allStudents.filter(s => s.id !== studentId));
        setStudentsToAdd([...studentsToAdd, studentId]);
        setStudentsToRemove(studentsToRemove.filter(id => id !== studentId));
    };

    const handleRemoveStudent = (studentId) => {
        const student = associatedStudents.find(s => s.id === studentId);
        setAllStudents([...allStudents, student]);
        setAssociatedStudents(associatedStudents.filter(s => s.id !== studentId));
        setStudentsToRemove([...studentsToRemove, studentId]);
        setStudentsToAdd(studentsToAdd.filter(id => id !== studentId));
    };

    const handleSave = async () => {
        try {

            const filteredStudentsToAdd = studentsToAdd.filter(studentId => 
                !originalAssociatedStudents.some(student => student.id === studentId)
            );
    
            // Filter out students who were not originally associated from the studentsToRemove list
            const filteredStudentsToRemove = studentsToRemove.filter(studentId => 
                originalAssociatedStudents.some(student => student.id === studentId)
            );

            console.log('filteredStudentsToAdd:', filteredStudentsToAdd);
            console.log('filteredStudentsToRemove:', filteredStudentsToRemove);


            if (filteredStudentsToAdd.length !== 0){
                await addStudentToParent(parent.id, studentsToAdd);
            }
            if (filteredStudentsToRemove.length !== 0){
                await removeStudentFromParent(parent.id, studentsToRemove);
            }

            onClose();
            setStudentsToAdd([]);
            setStudentsToRemove([]);
            
        } catch (error) {
            console.error('Error saving changes:', error);
        }
    };

    const filteredStudents = allStudents.filter(student =>
        `${student.name}`.toLowerCase().includes(searchTerm.toLowerCase())
    );

    if (!isOpen) return null;

    return (
        <div className="fixed inset-0 bg-gray-600 bg-opacity-50 flex justify-center items-center">
            <div className="bg-white p-6 rounded shadow-lg w-3/5" onClick={(e) => e.stopPropagation()}>
                <h2 className="text-2xl mb-4">Edit Parent's Students</h2>
                <div className="flex mb-4">
                    <input
                        type="text"
                        placeholder="Search for a student..."
                        value={searchTerm}
                        onChange={(e) => setSearchTerm(e.target.value)}
                        className="w-full px-4 py-2 border rounded"
                    />
                </div>
                <div className="flex">
                    <div className="w-1/2 pr-2">
                        <h3 className="text-xl mb-2">All Students</h3>
                        <ul className="border rounded p-2 h-64 overflow-y-scroll">
                        {filteredStudents.map((student) => (
                            <li key={student.id} className="flex justify-between items-center mb-2">
                                <span>{student.id} | {student.name} | {student.birthDate}</span>
                                <button
                                    onClick={() => handleAddStudent(student.id)}
                                    className="px-2 py-1 bg-green-500 text-white rounded"
                                >
                                    +
                                </button>
                            </li>
                        ))}
                    </ul>
                    </div>
                    <div className="w-1/2 pl-2">
                        <h3 className="text-xl mb-2">Associated Students</h3>
                        <ul className="border rounded p-2 h-64 overflow-y-scroll">
                            {associatedStudents.map((student) => (
                                <li key={student.id} className="flex justify-between items-center mb-2">
                                    <span>{student.id} | {student.name} | {student.birthDate}</span>
                                    <button
                                        onClick={() => handleRemoveStudent(student.id)}
                                        className="px-2 py-1 bg-red-500 text-white rounded"
                                    >
                                        -
                                    </button>
                                </li>
                            ))}
                        </ul>
                    </div>
                </div>
                <div className="flex justify-end mt-4">

                    <button
                        type="button"
                        onClick={onClose}
                        className="px-4 py-2 bg-gray-500 text-white rounded mr-2"
                    >
                        Cancel
                    </button>
                    <button
                        type="submit"
                        onClick={handleSave}
                        className="px-4 py-2 bg-blue-500 text-white rounded"
                    >
                        Save
                    </button>
                </div>
            </div>
        </div>
    );
}

export default EditParentStudentsModal;