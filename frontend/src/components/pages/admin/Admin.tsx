import TopBar from "../../App/base-components/topbar/TopBar";
import Footer from "../../App/base-components/footer/Footer";
import ManagementBox from "./components/ManagementBox";
import textContent from "./adminContent";
import React from "react";
interface Box {
    title: string;
    description: string;
    link: string;
    id:Number
}

function Admin() {
    const renderListOfBoxes = (text: Box[]) => {
        return text.map((item: Box) => (
            <ManagementBox title={item.title} description={item.description} link={item.link} key={item.id}/>
        ));
    };

    return (
        <div className="grid">
            <TopBar buttonProps={textContent}/>
            <div className="flex my-10 flex-wrap gap-10 justify-center">
                {renderListOfBoxes(textContent)}
            </div>
            <Footer />
        </div>
    );
}

export default Admin;