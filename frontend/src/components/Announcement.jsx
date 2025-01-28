import React from 'react';
import moment from 'moment';
const Announcement = ({ announcement, onDelete }) => {
    return (
        <div className="bg-white p-4 rounded-lg shadow-md mb-4">
            <h4 className="text-lg font-semibold">{announcement.title}</h4>
            <p className="text-gray-700">{announcement.content}</p>
            <div className="flex justify-between items-center text-sm text-gray-500">
                <div>
                    <span>{moment(announcement.date, 'DD-MM-YYYY').format('DD-MM-YYYY')}</span>
                </div>
            </div>
        </div>
    );
};

export default Announcement;