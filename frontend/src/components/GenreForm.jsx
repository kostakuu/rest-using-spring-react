import React, {useState, useEffect} from 'react';
import {useParams, useHistory} from "react-router-dom";

import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSave, faTrash} from "@fortawesome/free-solid-svg-icons";
import GenericService from "../services/genericService";

const GenreForm = () => {
    const {paramId} = useParams();

    const [id, setId] = useState(null);
    const [name, setName] = useState('');

    const history = useHistory();

    const isAuthenticated = !!localStorage.getItem('token');
    const genreService = new GenericService('genre');

    useEffect(() => {
        const refreshData = async () => {
            if (paramId && paramId !== 'new') {
                const response = await genreService.getById(paramId);

                setId(paramId);
                setName(response['name']);
            }
        };

        refreshData().then(null);
    }, []);

    const handleSave = async () => {
        const genre = {
            id: id,
            name: name,
        };

        await genreService.update(genre);
        history.push('/home');
    };

    const handleDelete = async () => {
        await genreService.deleteById(id);
        history.push('/home');
    };

    return (
        <div className="form">
            <div>
                <div className="form-body">
                    <p className="label">Name</p>
                    <input onChange={(event) => setName(event.target.value)} value={name} type="text"
                           disabled={!isAuthenticated}/>
                </div>
                <div className="form-footer" style={isAuthenticated ? {} : {display: 'none'}}>
                    <button onClick={handleDelete} style={id !== null ? {} : {display: 'none'}}><FontAwesomeIcon icon={faTrash}/>Delete</button>
                    <button onClick={handleSave} className="save"><FontAwesomeIcon icon={faSave}/>Save</button>
                </div>
            </div>
        </div>
    );
}

export default GenreForm;
