import axios from "axios"

const teacherColumns = [
    { id: 1, text: "ID" }, 
    { id:2, text: "Fist Name"},
    { id:3, text:"Last Name"},
    { id:4, text: "Email"},
    { id:5, text: "Birth Date"}, 
    { id:6, text: "Employment date"}, 
    { id:7, text: "Status"},
    { id:8,text: "Actions"}
]
const studentColumns = [
    { id: 1, text: "ID" },
    { id: 2, text: "First Name" },
    { id: 3, text: "Last Name" },
    { id: 4, text: "Email" },
    { id: 5, text: "Birth Date" },
    { id: 6, text: "Address" },
    { id: 7, text: "Class" },
    { id: 8, text: "Status" },
    { id: 9, text: "Actions" }
]
const parentsColumn = [
    { id: 1, text: "ID" },
    { id: 2, text: "First Name" },
    { id: 3, text: "Last Name" },
    { id: 4, text: "Email" },
    { id: 5, text: "Students" },
    { id: 6, text: "Actions" }
]
const classesColumns = [
    { id: 1, text: "ID" },
    { id: 2, text: "Class Name" },
    { id: 3, text: "Tutor" },
    { id: 4, text: "Year" },
    { id: 5, text: "List of students" },
    { id: 6, text: "Timetable" },
    { id: 7, text: "Status" },
    { id: 8, text: "Actions" }
]
const coursesColumn = [
    { id: 1, text: "ID" },
    { id: 2, text: "Course" },
    { id: 3, text: "Teachers" },
    { id: 4, text: "Description" },
    { id: 5, text: "Actions" }
]

async function getListData(typeCode) {
    const url = "http://127.0.0.1:8080";

    try {
        switch (typeCode) {
            case "Teachers":
                const teachersResponse = await axios.get(url + "/api/teachers");
                return teachersResponse.data;
            case "Students":
                const studentsResponse = await axios.get(url + "/api/students");
                return studentsResponse.data;
            case "Parents":
                const parentsResponse = await axios.get(url + "/api/parents");
                return parentsResponse.data;
            case "Classes":
                const classesResponse = await axios.get(url + "/api/classes");
                return classesResponse.data;
            case "Courses":
                const coursesResponse = await axios.get(url + "/api/courses");
                return coursesResponse.data;
            default:
                throw new Error("Invalid type");
        }
    } catch (error) {
        console.error("Error fetching data:", error);
        return [];
    }
}

function getColumns(typeCode) {
    switch (typeCode) {
        case "Teachers":
            return teacherColumns;
        case "Students":
            return studentColumns;
        case "Parents":
            return parentsColumn;
        case "Classes":
            return classesColumns;
        case "Courses":
            return coursesColumn;
        default:
            return [];
    }
}

export { getColumns, getListData };
