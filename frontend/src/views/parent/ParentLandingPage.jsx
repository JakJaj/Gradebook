import React, {useState, useEffect} from 'react';
import { useNavigate } from 'react-router-dom';
import TopBar from '../../components/TopBar';
import ChildInfoBox from '../../components/ChildInfoBox';
import { fetchParent, fetchAssociatedStudents } from '../../data/parent/getData';
import { fetchUserDetails } from '../../data/user/getUser';
import { fetchClasses } from '../../data/class/getData';


const ParentLandingPage = () => {
    const navigate = useNavigate();
    const [user, setUser] = useState(null);
    const [children, setChildren] = useState([]);
    const [parent, setParent] = useState(null);
    const [classes, setClasses] = useState([]);

    useEffect(() => {
        const getUser = async () => {
            try {
                const user = await fetchUserDetails();
                setUser(user);
            } catch (error) {
                console.error('Error fetching user in ParentLandingPage:', error);
            }
        };
        getUser();
    }, []);

    useEffect(() => {
        if (!user) return;

        const getParentData = async () => {
            try {
                const parentData = await fetchParent(user.subClassID);
                setParent(parentData);
            } catch (error) {
                console.error('Error fetching parent data in ParentLandingPage:', error);
            }
        };
        getParentData();
    }, [user]);

    useEffect(() => {
        if (!parent) return;

        const getChildrenData = async () => {
            try {
                const childrenData = await fetchAssociatedStudents(parent.id);
                setChildren(childrenData);
            } catch (error) {
                console.error('Error fetching children data in ParentLandingPage:', error);
            }
        };
        getChildrenData();
    }, [parent]);

    useEffect(() => {
        const getClasses = async () => {
            try {
                const classes = await fetchClasses();
                setClasses(classes);
            } catch (error) {
                console.error('Error fetching classes in ParentLandingPage:', error);
            }
        }
        getClasses();
    }, []);

    const handleSelectChild = (child) => {
        navigate(`/student/${child.id}`, { state: { childData: child } });
    };

    return (
        <div>
            <TopBar title="Parent Dashboard" />
            <div className="flex flex-col items-center justify-center min-h-screen p-8">
                <h2 className="text-2xl font-semibold mb-16 text-center">Select which child information you want to check</h2>
                {children.length === 0 ? (
                    <div className="flex justify-center">
                        <div className="bg-white p-4 rounded-lg shadow-md">
                            <p className="text-center text-xl">No children information available.</p>
                        </div>
                    </div>
                ) : (
                    <div className={`grid gap-6 justify-items-center ${
                        children.length === 1 ? 'grid-cols-1' :
                        children.length === 2 ? 'grid-cols-2' :
                        children.length === 3 ? 'grid-cols-3' :
                        'grid-cols-3'
                    }`}>
                        {children.map((child, index) => {
                            const childClass = classes.find((classItem) => classItem.id === child.class);
                            return (
                                <div key={child.id} className={`${children.length === 4 && index === 3 ? 'col-span-3' : ''}`}>
                                    <ChildInfoBox
                                        child={{
                                            id: child.id,
                                            name: child.name,
                                            className: childClass ? childClass.name : 'Unknown',
                                            classID: child.class,
                                            birthDate: child.birthDate,
                                        }}
                                        onSelect={handleSelectChild}
                                    />
                                </div>
                            );
                        })}
                    </div>
                )}
            </div>
        </div>
    );
};

export default ParentLandingPage;