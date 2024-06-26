import TopBar from "../../App/base-components/topbar/TopBar";
import Footer from "../../App/base-components/footer/Footer";
import ManagementBox from "./components/ManagementBox";
import textContent from "./content";

interface Box {
    title: string;
    description: string;
}

function Admin() {
    const renderListOfBoxes = (text: Box[]) => {
        return text.map((item: Box) => (
            <ManagementBox title={item.title} description={item.description} />
        ));
    };

    return (
        <div>
            <TopBar buttonNames={textContent} />
            <div className="flex my-10 flex-wrap gap-10 justify-center">
                {renderListOfBoxes(textContent)}
            </div>
            <Footer />
        </div>
    );
}

export default Admin;