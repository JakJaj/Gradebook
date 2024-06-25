import React from "react";
import TopBar from "../../App/base-components/topbar/TopBar";
import Footer from "../../App/base-components/footer/Footer";
import ManagementBox from "./components/ManagementBox";
import textContent from "./content";

function Admin(){

    const renderListOfBoxes = (text) => {
        return text.map(item => <ManagementBox title={item.title} description={item.description} />)
    }

    return(
        <div>
        <TopBar buttonNames={textContent}/>
        <div className="flex my-10 flex-wrap gap-10 justify-center" >
            {renderListOfBoxes(textContent)}
        </div>
        <Footer />
        </div>
    )
}

export default Admin;