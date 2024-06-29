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

function getListData(typeCode:String){
    const url = "http://127.0.0.1:8080"
    
    switch(typeCode){
        case "Teachers": 
            axios.get(url + "/api/teachers")
                .then(function(response){
                    console.log(response.data)
                    return response.data
            })
            break
        case "Students":
            axios.get(url + "/api/students")
                .then(function(response){
                    console.log(response.data)
                    return response.data
            })
            break
        case "Parents":
            axios.get(url + "/api/parents")
                .then(function(response){
                    console.log(response.data)
                    return response.data
            })
            break
        case "Classes":
            axios.get(url + "/api/classes")
                .then(function(response){
                    console.log(response.data)
                    return response.data
            })
            break
        case "Courses":
            axios.get(url + "/api/courses")
                .then(function(response){
                    console.log(response.data)
                    return response.data
            })
            break    
    }
}

function getColumns(typeCode:String){
    switch(typeCode){
        case "Teachers": 
            return teacherColumns
        case "Students":
            return studentColumns
        case "Parents":
            return parentsColumn
        case "Classes":
            return classesColumns
        case "Courses":
            return coursesColumn
        default:
            return ["Wrong columns code selected"]
    }
}

export {getColumns, getListData}