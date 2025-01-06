import React, { useState } from 'react';
import TopBar from '../../components/TopBar';
import { Calendar, momentLocalizer, Views } from 'react-big-calendar';
import moment from 'moment';
import 'react-big-calendar/lib/css/react-big-calendar.css';
import AnnouncementModal from '../teacher/popup/AnnouncementModal'
import Announcement from '../../components/Announcement';

const localizer = momentLocalizer(moment);

function TeacherLandingPage() {
    const [events, setEvents] = useState([]);
    const [announcements, setAnnouncements] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const announcementsPerPage = 5;

    const handleCreateAnnouncement = (announcement) => {
        setAnnouncements([...announcements, announcement]);
    };

    const handleDeleteAnnouncement = (announcementToDelete) => {
        setAnnouncements(announcements.filter(announcement => announcement !== announcementToDelete));
    };

    const indexOfLastAnnouncement = currentPage * announcementsPerPage;
    const indexOfFirstAnnouncement = indexOfLastAnnouncement - announcementsPerPage;
    const currentAnnouncements = announcements.slice(indexOfFirstAnnouncement, indexOfLastAnnouncement);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    const minTime = new Date();
    minTime.setHours(7, 0, 0);

    const maxTime = new Date();
    maxTime.setHours(17, 0, 0);

    const defaultDate = new Date(2023, 0, 2); 

    const formats = {
        dayFormat: (date, culture, localizer) =>
            localizer.format(date, 'dddd', culture),
    };

    return (
        <div>
            <TopBar title="Teacher Dashboard" />
            <div className="p-8 grid grid-cols-1 md:grid-cols-2 gap-8">
                <div className="bg-white p-6 rounded-lg shadow-md">
                    <h3 className="text-xl font-semibold mb-4">Your Schedule</h3>
                    <Calendar
                        localizer={localizer}
                        events={events}
                        startAccessor="start"
                        endAccessor="end"
                        style={{ height: 500 }}
                        defaultView={Views.WORK_WEEK}
                        views={['work_week']}
                        step={15}
                        timeslots={4}
                        min={minTime}
                        max={maxTime}
                        toolbar={false}
                        defaultDate={defaultDate}
                        formats={formats}
                        dayPropGetter={(date) => {
                            const day = date.getDay();
                            return day === 0 || day === 6
                                ? { className: 'rbc-day-off' }
                                : {};
                        }}
                    />
                </div>
                <div className="bg-white p-6 rounded-lg shadow-md">
                    <h3 className="text-xl font-semibold mb-4">Announcements</h3>
                    <button
                        onClick={() => setIsModalOpen(true)}
                        className="mb-4 px-4 py-2 bg-blue-500 text-white rounded"
                    >
                        New Announcement
                    </button>
                    {currentAnnouncements.map((announcement, index) => (
                        <Announcement
                            key={index}
                            announcement={announcement}
                            onDelete={handleDeleteAnnouncement}
                        />
                    ))}
                    <div className="flex justify-between mt-4">
                        <button
                            onClick={() => paginate(currentPage - 1)}
                            disabled={currentPage === 1}
                            className="px-4 py-2 bg-gray-300 text-gray-700 rounded"
                        >
                            Previous
                        </button>
                        <button
                            onClick={() => paginate(currentPage + 1)}
                            disabled={indexOfLastAnnouncement >= announcements.length}
                            className="px-4 py-2 bg-gray-300 text-gray-700 rounded"
                        >
                            Next
                        </button>
                    </div>
                </div>
            </div>
            <AnnouncementModal
                isOpen={isModalOpen}
                onClose={() => setIsModalOpen(false)}
                onSave={handleCreateAnnouncement}
            />
        </div>
    );
}

export default TeacherLandingPage;