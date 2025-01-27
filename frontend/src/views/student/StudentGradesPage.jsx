import React, { useEffect, useState } from 'react';

function StudentGradesPage({ studentId }) {
    const [grades, setGrades] = useState([]);

    return (
        <div>
            <h1>Student Grades</h1>
            <table className="min-w-full bg-white">
                <thead>
                    <tr>
                        <th className="py-2">Subject</th>
                        <th className="py-2">Grade</th>
                    </tr>
                </thead>
                <tbody>
                    {grades.map((grade) => (
                        <tr key={grade.id}>
                            <td className="border px-4 py-2">{grade.subject}</td>
                            <td className="border px-4 py-2">{grade.grade}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default StudentGradesPage;