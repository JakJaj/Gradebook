import React from "react";
import TopBar from "../../App/base-components/topbar/TopBar";
import LoginForm from "./components/LoginForm";

function LoginPage(){
    return(
        <div>
            <TopBar buttonProps={[]} landingPage={true}/>
            <LoginForm />
        </div>
    )
}

export default LoginPage;