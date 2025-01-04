import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { Calendar, momentLocalizer } from 'react-big-calendar';
import moment from 'moment';
import 'react-big-calendar/lib/css/react-big-calendar.css';
import './TimetableScheduler.css'; 
import EventDetailsModal from './EventDetailsModal';
import { fetchCourses } from '../data/course/getData';
import DeleteEventModal from './DeleteEventModal';

const localizer = momentLocalizer(moment);

const TimetableScheduler = ({}) => {
    const { classId } = useParams();
    const navigate = useNavigate();
    const [events, setEvents] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [isDeleteEventModalOpen, setIsDeleteEventModalOpen] = useState(false);
    const [selectedSlot, setSelectedSlot] = useState(null);
    const [courses, setCourses] = useState([]);
    const [selectedEvent, setSelectedEvent] = useState(null);

    useEffect(() => {
        const getCourses = async () => {
            try {
                const courses = await fetchCourses();
                setCourses(courses);
            } catch (error) {
                console.error('Error fetching courses in ClassManagementPage:', error);
            }
        };
        getCourses();
    }, []);

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
    
        console.log(newEvent);
        setEvents([...events, newEvent]);
        setIsModalOpen(false);
    };

    const handleDeleteEvent = () => {
        setEvents(events.filter(event => event !== selectedEvent));
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

    const handleSave = () => {
        // Save the events to the database
        navigate('/admin/classManagement', { state: { message: 'Timetable saved successfully!' } });
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
                step={15} // 15-minute intervals
                timeslots={4} // 4 segments per hour
                min={minTime}
                max={maxTime}
                toolbar={false}
                formats={formats}
                defaultDate={defaultDate} // Set the default date
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