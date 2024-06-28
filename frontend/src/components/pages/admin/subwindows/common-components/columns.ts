
const teacherColumns = ["ID", "Fist Name", "Last Name", "Email", "Birth Date", "Employment date", "Status", "Actions"]
const studentColumns = ["ID", "First Name", "Last Name", "Email", "Birth Date", "Adress", "Class", "Status", "Actions"]
const parentsColumn = ["ID", "First Name", "Last Name", "Email", "Students", "Actions"]
const classesColumns = ["ID", "Class Name", "Tutor", "Year", "List of students", "Timetable", "Status", "Actions"]
const coursesColumn = ["ID", "Course","Teachers", "Description", "Actions"]

function getListData(typeCode:Number){

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