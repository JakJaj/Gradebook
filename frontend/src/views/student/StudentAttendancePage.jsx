import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { fetchStudentAttendance } from '../../data/student/getData';
import TopBar from '../../components/TopBar';
import StudentTable from '../../components/StudentTable';
import StudentAttendanceBox from '../../components/StudentAttendanceBox';

function StudentAttendancePage() {
    const [attendance, setAttendance] = useState([]);
    const location = useLocation();
    const navigate = useNavigate();
    const student = location.state.studentData;

    useEffect(() => {
        const fetchAttendance = async () => {
            try {
                const response = await fetchStudentAttendance(student.id);
                console.log('Fetched attendance response:', response);

                const groupedAttendance = [];
                for (const subject in response) {
                    if (response[subject].length === 0) {
                        groupedAttendance.push({
                            subject,
                            records: [{ status: null, date: null }],
                        });
                    } else {
                        groupedAttendance.push({
                            subject,
                            records: response[subject],
                        });
                    }
                }

                console.log('Grouped attendance:', groupedAttendance);
                setAttendance(groupedAttendance);
            } catch (error) {
                console.error('Error fetching attendance:', error);
            }
        };

        fetchAttendance();
    }, [student]);

    const columns = [
        {
            header: 'Subject',
            accessorKey: 'subject',
        },
        {
            header: 'Attendance',
            accessorKey: 'records',
            cell: info => {
                const records = info.getValue() || [];
                return (
                    <div>
                        {records.map((record, index) => (
                            <StudentAttendanceBox key={index} attendance={record} />
                        ))}
                    </div>
                );
            },
        },
    ];

    return (
        <div>
            <TopBar title="Student Attendance" />
            <div className="p-8">
                <button
                    onClick={() => navigate(-1)}
                    className="mb-4 px-4 py-2 bg-blue-500 text-white rounded"
                >
                    Go Back
                </button>
                <StudentTable columns={columns} data={attendance} />
            </div>
        </div>
    );
}

export default StudentAttendancePage;