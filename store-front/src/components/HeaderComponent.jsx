import React from "react";
import {NavLink, useNavigate} from "react-router-dom";
import {getLoggedInUser, isUserLoggedIn, logout} from "../services/AuthService.js";

const HeaderComponent = () => {

    const isAuth = isUserLoggedIn()

    const navigator = useNavigate()

    const user = getLoggedInUser()

    function handleLogout() {
        logout()
        navigator('/login')
    }


    return (
        <div className="container">
            <header
                className="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
                <div className="col-md-3 mb-2 mb-md-0">
                    <a href='http://localhost:3000' className="d-inline-flex link-body-emphasis text-decoration-none">
                        Everything for 1 shop
                    </a>
                </div>

                <div className="col-md-3 text-end">
                    {
                        !isAuth &&
                        <a href='/register'>
                            <button type="button" className="btn btn-outline-primary me-2">Register</button>
                        </a>
                    }
                    {
                        !isAuth &&
                        <a href='/login'>
                            <button type="button" className="btn btn-primary">Login</button>
                        </a>
                    }

                    {
                        isAuth &&
                        <a href='/user'>
                            <button type="button" className="btn btn-outline-dark" style={{marginRight: "10px"}}>{user}</button>
                        </a>
                    }

                    {
                        isAuth &&
                        <a href='/orders'>
                            <button type="button" className="btn btn-outline-dark" style={{marginRight: "10px"}}>
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                     fill="currentColor" className="bi bi-basket" viewBox="0 0 16 16">
                                    <path
                                        d="M5.757 1.071a.5.5 0 0 1 .172.686L3.383 6h9.234L10.07 1.757a.5.5 0 1 1 .858-.514L13.783 6H15a1 1 0 0 1 1 1v1a1 1 0 0 1-1 1v4.5a2.5 2.5 0 0 1-2.5 2.5h-9A2.5 2.5 0 0 1 1 13.5V9a1 1 0 0 1-1-1V7a1 1 0 0 1 1-1h1.217L5.07 1.243a.5.5 0 0 1 .686-.172zM2 9v4.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V9zM1 7v1h14V7zm3 3a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3A.5.5 0 0 1 4 10m2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3A.5.5 0 0 1 6 10m2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3A.5.5 0 0 1 8 10m2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3a.5.5 0 0 1 .5-.5m2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3a.5.5 0 0 1 .5-.5"/>
                                </svg>
                            </button>
                        </a>
                    }

                    {
                        isAuth &&
                        <a href='/login'>
                            <button type="button" className="btn btn-primary" onClick={handleLogout}>Logout</button>
                        </a>
                    }

                </div>
            </header>
        </div>
    )
}

export default HeaderComponent