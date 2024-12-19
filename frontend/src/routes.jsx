import ErrorPage from "./views/base/ErrorPage.jsx";
import LandingPage from "./views/base/LandingPage.jsx";
import AdminDashboard from "./views/admin/AdminDashboard.jsx";
import StudentLandingPage from "./views/student/StudentLandingPage.jsx";
import ParentLandingPage from "./views/parent/ParentLandingPage.jsx";
import TeacherLandingPage from "./views/teacher/TeacherLandingPage.jsx";

const routes = [
    {
        path : '/',
        element: <LandingPage />,
        errorElement: <ErrorPage />,
    },
    {
        path : '/admin/dashboard',
        element: <AdminDashboard />,
    },
    {
        path : '/student/dashboard',
        element: <StudentLandingPage />,
    },
    {
        path : '/parent/dashboard',
        element: <ParentLandingPage />,
    },
    {
        path : '/teacher/dashboard',
        element: <TeacherLandingPage />,
    }

]


export default routes;