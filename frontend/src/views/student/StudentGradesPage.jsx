import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { fetchStudentGrades } from '../../data/student/getData';
import TopBar from '../../components/TopBar';
import StudentTable from '../../components/StudentTable';
import StudentGradeBox from '../../components/StudentGradeBox';

function StudentGradesPage() {
    const [grades, setGrades] = useState([]);
    const location = useLocation();
    const navigate = useNavigate();
    const student = location.state.studentData;

    useEffect(() => {
        const fetchGrades = async () => {
            try {
                const response = await fetchStudentGrades(student.id);
                console.log('Fetched grades response:', response);

                const groupedGrades = [];
                for (const subject in response) {
                    if (response[subject].length === 0) {
                        groupedGrades.push({
                            subject,
                            grades: [{ mark: null, magnitude: null, description: null, date: null }],
                        });
                    } else {
                        groupedGrades.push({
                            subject,
                            grades: response[subject],
                        });
                    }
                }

                console.log('Grouped grades:', groupedGrades);
                setGrades(groupedGrades);
            } catch (error) {
                console.error('Error fetching grades:', error);
            }
        };

        fetchGrades();
    }, [student]);

    const columns = [
        {
            header: 'Subject',
            accessorKey: 'subject',
        },
        {
            header: 'Grades',
            accessorKey: 'grades',
            cell: info => {
                const grades = info.getValue() || [];
                return (
                    <div>
                        {grades.map((grade, index) => (
                            <StudentGradeBox key={index} grade={grade} />
                        ))}
                    </div>
                );
            },
        },
    ];

    return (
        <div>
            <TopBar title="Student Grades" />
            <div className="p-8">
                <button
                    onClick={() => navigate(-1)}
                    className="mb-4 px-4 py-2 bg-blue-500 text-white rounded"
                >
                    Go Back
                </button>
                <StudentTable columns={columns} data={grades} />
            </div>
        </div>
    );
}

export default StudentGradesPage;