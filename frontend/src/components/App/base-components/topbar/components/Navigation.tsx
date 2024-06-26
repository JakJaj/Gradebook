import CurrentlyOpenNavigationPage from "./buttons/CurrentlyOpenNavigationPage";
import InactiveNavigationPage from "./buttons/InactiveNavigationPage";

interface ButtonName {
    title: string;
}

function Navigation(props: { buttonNames: ButtonName[] }) {
    const buttonText = (names: ButtonName[]): JSX.Element[] => {
        return names.map((buttonText) => (
            <InactiveNavigationPage text={buttonText.title} />
        ));
    };

    return (
        <ul className="flex flex-col mt-4 font-medium lg:flex-row lg:space-x-8 lg:mt-0">
            <CurrentlyOpenNavigationPage text="Home" />
            {buttonText(props.buttonNames)}
        </ul>
    );
}

export default Navigation;