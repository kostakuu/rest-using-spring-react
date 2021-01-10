import React, {useState, useEffect} from 'react';
import {useParams, useHistory} from "react-router-dom";

import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSave, faTrash} from "@fortawesome/free-solid-svg-icons";
import GenericService from "../services/genericService";

const UserForm = () => {
    const {paramId} = useParams();

    const [id, setId] = useState(null);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [fullName, setFullName] = useState('');
    const [gender, setGender] = useState('Male');

    const history = useHistory();

    const isAuthenticated = !!localStorage.getItem('token');
    const userService = new GenericService('user');

    useEffect(() => {
        const refreshData = async () => {
            if (paramId && paramId !== 'new') {
                const response = await userService.getById(paramId);

                setId(paramId);
                setUsername(response['username']);
                setFullName(response['fullName']);
                setGender(response['gender']);
            }
        };

        refreshData().then(null);
    }, []);

    const GenderSelection = () => <select value={gender} onChange={(e) => setGender({id: e.target.value})}>
        {['Male', 'Female'].map((item, key) => <option key={key} value={item}>{item}</option>)}
    </select>;

    const handleSave = async () => {
        const user = {
            id: id,
            username: username,
            password: password,
            fullName: fullName,
            gender: gender,
        };

        if (username === '' || password === '') {
            alert('Please input username and password');
            return;
        }

        const response = await userService.update(user);
        if (response.id) {
            history.push('/home');
        } else {
            alert('Username isn\'t unique');
        }
    };

    const handleDelete = async () => {
        await userService.deleteById(id);
        history.push('/home');
    };

    return (
        <div className="form">
            <div>
                <div className="form-body">
                    <p className="label">Username</p>
                    <input onChange={(event) => setUsername(event.target.value)} value={username} type="text"
                           disabled={paramId !== 'new'}/>
                </div>
                <div className="form-body">
                    <p className="label">Password</p>
                    <input onChange={(event) => setPassword(event.target.value)} value={password} type="password"
                           disabled={!isAuthenticated}/>
                </div>
                <div className="form-body">
                    <p className="label">Full name</p>
                    <input onChange={(event) => setFullName(event.target.value)} value={fullName} type="text"
                           disabled={!isAuthenticated}/>
                </div>
                <div className="form-body">
                    <p className="label">Gender</p>
                    <GenderSelection/>
                </div>
                <div className="form-footer" style={isAuthenticated ? {} : {display: 'none'}}>
                    <button onClick={handleDelete} style={id !== null ? {} : {display: 'none'}}><FontAwesomeIcon
                        icon={faTrash}/>Delete
                    </button>
                    <button onClick={handleSave} className="save"><FontAwesomeIcon icon={faSave}/>Save</button>
                </div>
            </div>
        </div>
    );
}

export default UserForm;
