import Footer from "./components/Footer";
import Header from "./components/Header";
import React, {useEffect, useState} from "react";
import {MainContent} from "./MainContent";
import {BrowserRouter as Router} from "react-router-dom";
import {Navigation} from "./components/Navigation";
import {
    deleteAuthorizationCookies,
    getAuthenticatedUser,
} from "./tools/authentication";

/**
 * A component representing the whole application
 * @return {JSX.Element}
 * @constructor
 */
export function App() {
    const [user, setUser] = useState(null);

    useEffect(tryRestoreUserSession);
    return (
        <Router>
            {/*<Navigation user={user} logoutFunction={doLogout}/> */}
            <Header/>
            <MainContent/>
            <Footer/>
        </Router>
    );

    /**
     * Perform user logout
     */
    function doLogout() {
        console.log("Logout");
        deleteAuthorizationCookies();
        setUser(null);
    }

    /**
     * Check cookies - is user logged in? If so, set the user from cookies
     */
    function tryRestoreUserSession() {
        if (!user) {
            const loggedInUser = getAuthenticatedUser();
            if (loggedInUser) {
                console.log("User session found in cookies, restoring");
                setUser(loggedInUser);
            }
        }
    }
}