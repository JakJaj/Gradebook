import React, { useEffect, useState } from 'react';
import moment from 'moment';

const EventDetailsModal = ({ isOpen, onClose, onSave, courses, initialData }) => {
    const [courseId, setCourseId] = useState('');
    const [classroom, setClassroom] = useState('');
    const [startTime, setStartTime] = useState('');
    const [endTime, setEndTime] = useState('');

    useEffect(() => {
        setStartTime({ time: moment(initialData.startTime).format('HH:mm'), day: moment(initialData.startTime).format('dddd') });
        setEndTime({ time: moment(initialData.endTime).format('HH:mm'), day: moment(initialData.endTime).format('dddd') });
    }, [initialData]);


    const handleSubmit = (e) => {
        e.preventDefault();

        const selectedCourse = courses.find(course => course.id === Number(courseId));

        const event = {
            courseId,
            courseName: selectedCourse?.name || '',
            classroom,
            startTime,
            endTime,
            tutor: {
                id: selectedCourse?.tutor.id || '',
                name: selectedCourse?.tutor.name || '',
            },
        };
        onSave(event);
        onClose();
    };

    if (!isOpen) return null;

    return (
        <div className="fixed inset-0 bg-gray-600 bg-opacity-50 flex justify-center items-center">
            <div className="bg-white p-6 rounded shadow-lg w-1/3">
                <h2 className="text-2xl mb-4">Event Details</h2>
                <form onSubmit={handleSubmit}>
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="course">
                            Course
                        </label>
                        <select
                            id="course"
                            value={courseId}
                            onChange={(e) => setCourseId(e.target.value)}
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        >
                            <option value="">Select a course</option>
                            {courses.map((course) => (
                                <option key={course.id} value={course.id}>
                                    {course.name} - {course.tutor.name}
                                </option>
                            ))}
                        </select>
                    </div>
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="classroom">
                            Classroom Number
                        </label>
                        <input
                            type="text"
                            id="classroom"
                            value={classroom}
                            onChange={(e) => setClassroom(e.target.value)}
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        />
                    </div>
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="startTime">
                            Start Time
                        </label>
                        <input
                            type="time"
                            id="startTime"
                            value={startTime.time}
                            onChange={(e) => setStartTime({ ...startTime, time: e.target.value })}
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        />
                    </div>
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="endTime">
                            End Time
                        </label>
                        <input
                            type="time"
                            id="endTime"
                            value={endTime.time}
                            onChange={(e) => setEndTime({ ...endTime, time: e.target.value })}
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        />
                    </div>
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="dayOfTheWeek">
                            Day of the week
                        </label>
                        <select
                            id="dayOfTheWeek"
                            value={startTime.day}
                            onChange={(e) => {
                                setStartTime({ ...startTime, day: e.target.value });
                                setEndTime({ ...endTime, day: e.target.value });
                            }}
                            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        >
                            <option value="">Select a day</option>
                            {["Monday", "Tuesday", "Wednesday", "Thursday", "Friday"].map((day) => (
                                <option key={day} value={day}>
                                    {day}
                                </option>
                            ))}
                        </select>
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
};

export default EventDetailsModal;