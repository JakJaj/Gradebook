import React, { useState, useEffect } from 'react';
import TopBar from '../../components/TopBar';
import { Calendar, momentLocalizer, Views } from 'react-big-calendar';
import moment from 'moment';
import 'react-big-calendar/lib/css/react-big-calendar.css';
import Announcement from '../../components/Announcement';
import { fetchCourses } from '../../data/course/getData';
import { fetchClassTimetable } from '../../data/timetable/getData';
import { fetchStudent } from '../../data/student/getData';
import { fetchUserDetails } from '../../data/user/getUser';
import { fetchClasses } from '../../data/class/getData';
import { fetchAnnouncements } from '../../data/announcement/getData';
import { useNavigate, useLocation } from 'react-router-dom';

function TeacherStudentPage() {
    const [events, setEvents] = useState([]);
    const [announcements, setAnnouncements] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [courses, setCourses] = useState([]);
    const [coursesFetched, setCoursesFetched] = useState(false); 
    const [user, setUser] = useState(null);
    const [classes, setClasses] = useState([]);
    const announcementsPerPage = 5;
    const location = useLocation();
    const navigate = useNavigate();
    const student = location.state.childData;
    const localizer = momentLocalizer(moment);


    useEffect(() => {
        if (!student) return;
        console.log('student', student);
        
        const getCourses = async () => {
            try {
                const courses = await fetchCourses(); setCourses(courses); setCoursesFetched(true); } catch (error) { console.error('Error fetching courses in ClassManagementPage:', error); } }; getCourses(); }, [student]);
    
                useEffect(() => {
        if (!coursesFetched || !student) return;
        const getClasses = async () => {
            try {
                const classes = await fetchClasses();
                setClasses(classes);
            } catch (error) {
                console.error('Error fetching classes in TeacherLandingPage:', error);
            }
        };
        getClasses();
    }, [coursesFetched, student]);

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
        if (!coursesFetched || !student || !classes) return;
    
        const getTimetable = async () => {
            try {
                const timetable = await fetchClassTimetable(student.classID);
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
    }, [coursesFetched, student, classes, courses]);
    

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
                <strong>{event.tutor.name}</strong>
                <br />
                <strong>{event.classroom}</strong>
            </div>
        </div>
    );

return (
    <div>
        <TopBar title="Student Dashboard" />

        {student ? (
            <div className="flex justify-center mt-6">
                
                <button onClick={() => navigate(`/student/${student.id}/grades`, { state: { studentData: student } })} className="bg-blue-500 text-white px-4 py-2 mx-3 rounded hover:bg-blue-700">
                    Grades
                </button>
                <button onClick={() => navigate(`/student/${student.id}/attendance`, { state: { studentData: student } })} className="bg-blue-500 text-white px-4 py-2 mx-3 rounded hover:bg-blue-700">
                    Attendance
                </button>
                <button onClick={() => navigate(`/student/${student.id}/notes`, { state: { studentData: student } })} className="bg-blue-500 text-white px-4 py-2 mx-3 rounded hover:bg-blue-700">
                    Notes
                </button>
                
            </div>
        ) : (
            <div>Loading...</div>
        )}
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
                    {currentAnnouncements.map((announcement, index) => (
                        <Announcement
                            key={index}
                            announcement={announcement}
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
        </div>
    );
}

export default TeacherStudentPage;