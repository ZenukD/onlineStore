import React, {useState} from "react";
import {updateUser} from "../services/UserService.js";
import {getLoggedInUser} from "../services/AuthService.js";
import {useNavigate} from "react-router-dom";

const UpdateUserFormComponent = () => {

    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('')
    const [age, setAge] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    const username = getLoggedInUser()
    const navigate = useNavigate()

    function handleUpdateUserForm(e) {
        e.preventDefault()

        const user = {firstName, lastName, age, email, password}

        updateUser(username, user).then((response) => {
            console.log(response.data)
            navigate('/products')
        }).catch(error => {
            console.error(error)
        })
    }

    return (
        <div className='container'>
            <br/><br/>
            <div className='card col-md-6 offset-md-3 offset-md-3'>
                <h2 className='text-center'>Enter new data</h2>
                <div className='card-body'>
                    <form encType="multipart/form-data">
                        <div className='form-droup mb-2'>
                            <label className='form-label'>First Name</label>
                            <input
                                type='text'
                                className='form-control'
                                placeholder='First Name'
                                name='name'
                                value={firstName}
                                onChange={(e) => setFirstName(e.target.value)}/>
                        </div>

                        <div className='form-droup mb-2'>
                            <label className='form-label'>Last Name</label>
                            <input
                                type='text'
                                className='form-control'
                                placeholder='Last Name'
                                name='last name'
                                value={lastName}
                                onChange={(e) => setLastName(e.target.value)}/>
                        </div>

                        <div className='form-droup mb-2'>
                            <label className='form-label'>Age</label>
                            <input
                                type='text'
                                className='form-control'
                                placeholder='Age'
                                name='age'
                                value={age}
                                onChange={(e) => setAge(e.target.value)}/>
                        </div>

                        <div className='form-droup mb-2'>
                            <label className='form-label'>Email</label>
                            <input
                                type='text'
                                className='form-control'
                                placeholder='Email'
                                name='email'
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}/>
                        </div>

                        <div className='form-droup mb-2'>
                            <label className='form-label'>Password</label>
                            <input
                                type='text'
                                className='form-control'
                                placeholder='Password'
                                name='password'
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}/>
                        </div>


                        <button className='btn btn-outline-primary' onClick={(e) => handleUpdateUserForm(e)}>Submit
                        </button>
                    </form>
                </div>
            </div>
        </div>
    )
}

export default UpdateUserFormComponent