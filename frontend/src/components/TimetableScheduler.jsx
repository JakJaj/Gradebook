import React, { useState, useEffect } from 'react';
import { useParams, useNavigate, useLocation} from 'react-router-dom';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import moment from 'moment';
import 'react-big-calendar/lib/css/react-big-calendar.css';
import './TimetableScheduler.css'; 
import EventDetailsModal from './EventDetailsModal';
import { fetchCourses } from '../data/course/getData';
import DeleteEventModal from './DeleteEventModal';
import { createTimetable } from '../data/timetable/postData';
import { fetchTimetable } from '../data/timetable/getData';

const localizer = momentLocalizer(moment);

const TimetableScheduler = ({}) => {
    const navigate = useNavigate();
    const [events, setEvents] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [isDeleteEventModalOpen, setIsDeleteEventModalOpen] = useState(false);
    const [selectedSlot, setSelectedSlot] = useState(null);
    const [courses, setCourses] = useState([]);
    const [selectedEvent, setSelectedEvent] = useState(null);
    const location = useLocation();
    const { theClass } = location.state || {};
    const [coursesFetched, setCoursesFetched] = useState(false); 

    useEffect(() => {
        const getCourses = async () => {
            try {
                const courses = await fetchCourses();
                setCourses(courses);
                setCoursesFetched(true); 
            } catch (error) {
                console.error('Error fetching courses in ClassManagementPage:', error);
            }
        };
        getCourses();
    }, []);

    useEffect(() => {
        if (!coursesFetched) return;

        const getTimetable = async () => {
            try {
                const timetable = await fetchTimetable(theClass.id);
                console.log(timetable); 
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
                            timetableID : entry.timetableID,
                            start,
                            end,
                            title: courses.find(course => course.id === entry.courseID)?.name,
                            subtitle: `Classroom: ${entry.classroom}`,
                            courseId: entry.courseID,
                            classroom: entry.classroom,
                            tutor: { name: entry.teacherName },
                        };

                        timetableToEvents.push(newEvent);
                    });
                });

                setEvents(timetableToEvents);
            } catch (error) {
                console.error('Error fetching timetable in TimetableScheduler:', error);
            }
        };
        getTimetable();
    }, [coursesFetched, theClass]);


    
    const handleSelectSlot = ({ start, end }) => {
        if (end - start === 900000) return;
        setSelectedSlot({ start, end });
        setIsModalOpen(true);
    };

    const handleSelectEvent = (event) => {
        setSelectedEvent(event);
        setIsDeleteEventModalOpen(true);
    };

    const handleSaveEvent = (eventDetails) => {
        const { startTime, endTime, courseId, courseName, classroom, tutor } = eventDetails;
    
        const baseDate = moment(defaultDate);
    
        const start = baseDate.clone().day(startTime.day).set({
            hour: moment(startTime.time, 'HH:mm').hour(),
            minute: moment(startTime.time, 'HH:mm').minute(),
        }).toDate();
    
        const end = baseDate.clone().day(endTime.day).set({
            hour: moment(endTime.time, 'HH:mm').hour(),
            minute: moment(endTime.time, 'HH:mm').minute(),
        }).toDate();
    
        const newEvent = {
            start,
            end,
            title: courseName,
            subtitle : "Classroom: " + classroom,
            courseId,
            classroom,
            tutor,
        };
    
        setEvents([...events, newEvent]);
        setIsModalOpen(false);
    };

    const handleDeleteEvent = () => {
        console.log(selectedEvent);

        setEvents(events.filter(event => event.timetableID !== selectedEvent.timetableID));
        setSelectedEvent(null);
        setIsDeleteEventModalOpen(false);
    };

    const minTime = new Date();
    minTime.setHours(7, 0, 0);

    const maxTime = new Date();
    maxTime.setHours(17, 0, 0);

    const formats = {
        dayFormat: (date, culture, localizer) =>
            localizer.format(date, 'dddd', culture),
    };

    const defaultDate = new Date(2023, 0, 2);

    const EventComponent = ({ event }) => (
        <div>
            <strong>{event.title}</strong>
            <div>Tutor: {event.tutor.name}</div>
            <div>{event.subtitle}</div>
        </div>
    );

    const handleSave = async() => {
        
        try{
            const timetableList = [];
            
            

            events.forEach((event) => {
                const timetable = {
                    courseID: Number(event.courseId),
                    classID: theClass.id,
                    startTime: moment(event.start).format('HH:mm'), 
                    endTime: moment(event.end).format('HH:mm'),
                    classroomNumber: event.classroom,
                    dayOfWeek: moment(event.start).isoWeekday(),
                };
                    timetableList.push(timetable);
            });

            const response = await createTimetable(timetableList);
            console.log(response);
            navigate('/admin/classManagement', { state: { message: 'Timetable saved successfully!'} });
        } catch (error) {
            console.error('Error creating timetable:', error);  
        }
    
    }
    return (
        <div className="p-6 bg-white rounded shadow-lg">
            <div className="flex justify-between items-center mb-4">
                
                <button onClick={() => navigate('/admin/classManagement')} className="px-4 py-2 bg-gray-500 text-white rounded">
                    Back to Classes
                </button>
                <button onClick={() => {handleSave()}} className="px-4 py-2 bg-blue-500 text-white rounded"> 
                    Save
                </button>
            </div>
            <Calendar
                localizer={localizer}
                events={events}
                startAccessor="start"
                endAccessor="end"
                style={{ height: '80vh' }}
                selectable
                onSelectSlot={handleSelectSlot}
                onSelectEvent={handleSelectEvent}
                defaultView="work_week"
                views={['work_week']}
                step={15} 
                timeslots={4} 
                min={minTime}
                max={maxTime}
                toolbar={false}
                formats={formats}
                defaultDate={defaultDate} 
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
            <EventDetailsModal
                isOpen={isModalOpen}
                onClose={() => setIsModalOpen(false)}
                onSave={handleSaveEvent}
                courses={courses}
                initialData={{ startTime: selectedSlot?.start, endTime: selectedSlot?.end }}
            />
            <DeleteEventModal
                isOpen={isDeleteEventModalOpen}
                onClose={() => setIsDeleteEventModalOpen(false)}
                onDelete={handleDeleteEvent}
            />
        </div>
    );
};

export default TimetableScheduler;