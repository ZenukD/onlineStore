// import React, {useState} from "react";
// import {useNavigate} from "react-router-dom";
// import {loginAPICall, saveLoggedInUser, storeToken} from "../services/AuthService.js";
//
// const LoginComponent = () => {
//     const [username, setUsername] = useState('')
//     const [password, setPassword] = useState('')
//
//     const navigator = useNavigate()
//
//
//     async function handleLoginForm(e) {
//         e.preventDefault()
//
//         await loginAPICall(username, password).then((response) => {
//           console.log(response.data)
//
//           const token = 'Basic ' + window.btoa(username + ":" + password)
//           storeToken(token)
//
//           const role = response.data.role
//
//           saveLoggedInUser(username, role)
//           navigator("/products")
//
//           window.location.reload(false)
//         }).catch(error => {
//             console.error(error)
//         })
//
//     }
//
//     return (
//         <div className='container'>
//             <div className='card col-md-6 offset-md-3 offset-md-3'>
//                 <form>
//                     <h1 className="h3 mb-3 fw-normal text-center">Please sign in</h1>
//
//                     <div className="form-floating">
//                         <input
//                             type="text"
//                             className="form-control"
//                             name='username'
//                             placeholder='Enter username'
//                             value={username}
//                             onChange={(e) => setUsername(e.target.value)}/>
//                         <label form="floatingInput">Login or Email</label>
//                     </div>
//
//                     <div className="form-floating">
//                         <input
//                             type="password"
//                             className="form-control"
//                             placeholder='Enter password'
//                             value={password}
//                             onChange={(e) => setPassword(e.target.value)}/>
//                         <label form="floatingPassword">Password</label>
//                     </div>
//
//                     <button className="btn btn-primary w-100 py-2 " onClick={(e) => handleLoginForm(e)}>Sign in</button>
//                     <p className="mt-5 mb-3 text-body-secondary text-center">© 2024</p>
//                 </form>
//             </div>
//         </div>
//     )
// }
//
// export default LoginComponent
//
//
import React, { useState } from 'react'
import { loginAPICall, saveLoggedInUser, storeToken } from '../services/AuthService';
import { useNavigate } from 'react-router-dom';

const LoginComponent = () => {

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')

    const navigator = useNavigate();

    async function handleLoginForm(e){

        e.preventDefault();

        await loginAPICall(username, password).then((response) => {
            console.log(response.data);

            const token = 'Basic ' + window.btoa(username + ":" + password);
            storeToken(token);

            const role = response.data.role;

            saveLoggedInUser(username, role)
            navigator("/products")

            window.location.reload(false);
        }).catch(error => {
            console.error(error);
        })

    }

    return (
        <div className='container'>
            <br /> <br />
            <div className='row'>
                <div className='col-md-6 offset-md-3'>
                    <div className='card'>
                        <div className='card-header'>
                            <h2 className='text-center'> Login Form </h2>
                        </div>

                        <div className='card-body'>
                            <form>

                                <div className='row mb-3'>
                                    <label className='col-md-3 control-label'> Username</label>
                                    <div className='col-md-9'>
                                        <input
                                            type='text'
                                            name='username'
                                            className='form-control'
                                            placeholder='Enter username'
                                            value={username}
                                            onChange={ (e) => setUsername(e.target.value)}>
                                        </input>
                                    </div>
                                </div>

                                <div className='row mb-3'>
                                    <label className='col-md-3 control-label'> Password </label>
                                    <div className='col-md-9'>
                                        <input
                                            type='password'
                                            name='password'
                                            className='form-control'
                                            placeholder='Enter password'
                                            value={password}
                                            onChange={ (e) => setPassword(e.target.value)}
                                        >
                                        </input>
                                    </div>
                                </div>

                                <div className='form-group mb-3'>
                                    <button className='btn btn-primary' onClick={ (e) => handleLoginForm(e)}>Submit</button>

                                </div>
                            </form>

                        </div>

                    </div>
                </div>
            </div>


        </div>
    )
}

export default LoginComponent