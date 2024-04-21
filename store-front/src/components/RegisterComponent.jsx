import React, {useState} from "react";
import {registerAPICall} from "../services/AuthService.js";
import {useNavigate} from "react-router-dom";

const RegisterComponent = () => {

    const [username, setUsername] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    const navigator = useNavigate()


    function handleRegistration(e) {
        e.preventDefault()
        const register = {username, email, password}

        console.log(register)

        registerAPICall(register).then((response) => {
            console.log(response)

        navigator("/login")
        }).catch(error => {
            console.error(error)
        })
    }

    return(
        <div className='container'>
            <div className='card col-md-6 offset-md-3 offset-md-3'>
                <form>
                    <h1 className="h3 mb-3 fw-normal text-center">Registration form</h1>

                    <div className="form-floating">
                        <input
                            type="text"
                            className="form-control"
                            id="floatingInputName"
                            placeholder="name"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}/>
                        <label form="floatingInput">Username</label>
                    </div>

                    <div className="form-floating">
                        <input
                            type="text"
                            className="form-control"
                            id="floatingInputEmail"
                            placeholder="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}/>
                        <label form="floatingInput">Email</label>
                    </div>

                    <div className="form-floating">
                        <input
                            type="password"
                            className="form-control"
                            id="floatingPassword"
                            placeholder="Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}/>
                        <label form="floatingPassword">Password</label>
                    </div>

                    <button className="btn btn-primary w-100 py-2 " type="submit" onClick={(e) => handleRegistration(e)}>Register</button>
                    <p className="mt-5 mb-3 text-body-secondary text-center">© 2024</p>
                </form>
            </div>
        </div>
    )
}
export default RegisterComponent