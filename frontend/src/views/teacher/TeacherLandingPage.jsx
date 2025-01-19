import React, { useState, useEffect } from 'react';
import TopBar from '../../components/TopBar';
import { Calendar, momentLocalizer, Views } from 'react-big-calendar';
import moment from 'moment';
import 'react-big-calendar/lib/css/react-big-calendar.css';
import AnnouncementModal from '../teacher/popup/AnnouncementModal';
import Announcement from '../../components/Announcement';
import { fetchCourses } from '../../data/course/getData';
import { fetchTimetableTeacher } from '../../data/timetable/getData';
import { fetchTeacher } from '../../data/teacher/getData';
import { fetchUserDetails } from '../../data/user/getUser';
import { fetchClasses } from '../../data/class/getData';
import { postAnnouncements } from '../../data/announcement/postData';
import { deleteAnnouncement } from '../../data/announcement/deleteData';
import { fetchAnnouncements } from '../../data/announcement/getData';
import { useNavigate } from 'react-router-dom';

const localizer = momentLocalizer(moment);

function TeacherLandingPage() {
    const [events, setEvents] = useState([]);
    const [announcements, setAnnouncements] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const [courses, setCourses] = useState([]);
    const [coursesFetched, setCoursesFetched] = useState(false); 
    const [teacher, setTeacher] = useState(null);
    const [user, setUser] = useState(null);
    const [classes, setClasses] = useState([]);
    const announcementsPerPage = 5;
    const navigate = useNavigate();

    useEffect(() => {
        const getUser = async () => {
            try {
                const user = await fetchUserDetails();
                setUser(user);
            } catch (error) {
                console.error('Error fetching user in TeacherLandingPage:', error);
            }
        };
        getUser();
    }, []);

    useEffect(() => {
        if (!user) return;

        const getTeacherData = async () => {
            try {
                
                const teacherData = await fetchTeacher(user.subClassID);
                
                setTeacher(teacherData);
            } catch (error) {
                console.error('Error fetching teacher data in TeacherLandingPage:', error);
            }
        };
        getTeacherData();
    }, [user]);

    useEffect(() => {
        if (!teacher) return;

        const getCourses = async () => {
            try {
                const courses = await fetchCourses(); setCourses(courses); setCoursesFetched(true); } catch (error) { console.error('Error fetching courses in ClassManagementPage:', error); } }; getCourses(); }, [teacher]);
    
                useEffect(() => {
        if (!coursesFetched || !teacher) return;
        const getClasses = async () => {
            try {
                const classes = await fetchClasses();
                setClasses(classes);
            } catch (error) {
                console.error('Error fetching classes in TeacherLandingPage:', error);
            }
        };
        getClasses();
    }, [coursesFetched, teacher]);

    useEffect(() => {
        const getAnnouncements = async () => {
            try {
                const announcements = await fetchAnnouncements();
                const sortedAnnouncements = announcements.sort((a, b) => new Date(b.date) - new Date(a.date));
                setAnnouncements(sortedAnnouncements);
            } catch (error) {
                console.error('Error fetching announcements in TeacherLandingPage:', error);
            }
        };
        getAnnouncements();
    }, []);

    useEffect(() => {
        if (!coursesFetched || !teacher || !classes) return;
    
        const getTimetable = async () => {
            try {
                const timetable = await fetchTimetableTeacher(teacher.id);
                const timetableToEvents = [];
    
                Object.keys(timetable).forEach(day => {
                    timetable[day].forEach(entry => {
                        const baseDate = moment(defaultDate).day(day);
    
                        const start = baseDate.clone().set({
                            hour: moment(entry.startTime, 'HH:mm').hour(),
                            minute: moment(entry.startTime, 'HH:mm').minute(),
                        }).toDate();
    
                        const end = baseDate.clone().set({
                            hour: moment(entry.endTime, 'HH:mm').hour(),
                            minute: moment(entry.endTime, 'HH:mm').minute(),
                        }).toDate();
    
                        const newEvent = {
                            timetableID: entry.timetableID,
                            start,
                            end,
                            title: courses.find(course => course.id === entry.courseID)?.name,
                            subtitle: `Classroom: ${entry.classroom}`,
                            courseId: entry.courseID,
                            classroom: entry.classroom,
                            classId: entry.classID,
                            className: classes.find(theClass => theClass.id === entry.classID)?.name,
                            tutor: { name: entry.teacherName },
                        };
    
                        timetableToEvents.push(newEvent);
                    });
                });
    
                setEvents(
                    timetableToEvents.map(event => ({
                        ...event,
                        start: new Date(event.start),
                        end: new Date(event.end),
                    }))
                );
            } catch (error) {
                console.error('Error fetching timetable in TeacherLandingPage:', error);
            }
        };
    
        getTimetable();
    }, [coursesFetched, teacher, classes, courses]);
    
    const handleCreateAnnouncement = async (announcement) => {

        try{
            const announcementData = {
                title: announcement.title,
                content: announcement.content,
                teacherID: user.subClassID,
                date: moment(new Date(), "DD-MM-YYYY").format("DD-MM-YYYY"),
            };
            
            const response = await postAnnouncements(announcementData);

            const returnedAnnouncement = {
                announcementID: response.announcementID,
                title: response.title,
                content: response.content,
                teacherID: response.teacherID,
                date: response.date,
            }

            const updatedAnnouncements = [...announcements, returnedAnnouncement];
            const sortedAnnouncements = updatedAnnouncements.sort((a, b) => new Date(b.date) - new Date(a.date));
            setAnnouncements(sortedAnnouncements);

        } catch (error) {
            console.error('Error creating announcement:', error);
        }
    };

    const handleDeleteAnnouncement = async (announcementToDelete) => {
        console.log(announcementToDelete);

        try{
            const response = await deleteAnnouncement(announcementToDelete.announcementID);
            if(!response) return;

            const updatedAnnouncements = announcements.filter(announcement => announcement !== announcementToDelete);
            const sortedAnnouncements = updatedAnnouncements.sort((a, b) => new Date(b.date) - new Date(a.date));
            setAnnouncements(sortedAnnouncements);
        }catch(error){
            consol.error("Error while deleting announcements");
        }
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

    const EventComponent = ({ event }) => (
        <div style={{ fontSize: '0.8em', textAlign: 'left', position: 'relative' }}>
            <div >
                <strong>{event.title}</strong>
                <br />
                <strong>{event.className}</strong>
                <br />
                <strong>{event.classroom}</strong>
            </div>
        </div>
    );

    const handleSelectEvent = (event) => {
        console.log("Class data to display: ", event);
        navigate(`/teacher/class/${event.classId}`, { state: { classData: event } });
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
                    step={30}
                    timeslots={2}
                    min={minTime}
                    max={maxTime}
                    toolbar={false}
                    defaultDate={defaultDate}
                    onSelectEvent={handleSelectEvent}
                    formats={formats}
                    components={{
                        event: EventComponent,
                    }}
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
