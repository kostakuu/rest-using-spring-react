import React, {useState, useEffect} from 'react';
import {useParams, useHistory} from "react-router-dom";

import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSave, faTrash} from "@fortawesome/free-solid-svg-icons";
import GenericService from "../services/genericService";

const ProjectionForm = () => {
    const {paramId} = useParams();

    const [id, setId] = useState(null);
    const [date, setDate] = useState(new Date().toISOString().slice(0, 10));
    const [movie, setMovie] = useState(null);
    const [room, setRoom] = useState('');
    const [price, setPrice] = useState(0);

    const [allMovies, setAllMovies] = useState([]);

    const history = useHistory();

    const isAuthenticated = !!localStorage.getItem('token');
    const projectionService = new GenericService('projection');
    const movieService = new GenericService('movie');

    useEffect(() => {
        const refreshData = async () => {
            const movies = await movieService.getAll();
            setAllMovies(movies);

            if (paramId && paramId !== 'new') {
                const response = await projectionService.getById(paramId);

                setId(paramId);
                setDate(response['date']);
                setMovie(response['movie']);
                setRoom(response['room']);
                setPrice(response['price']);
            }
        };

        refreshData().then(null);
    }, []);

    const MovieSelection = () => <select value={movie ? movie.id : 0} onChange={(e) => setMovie({id: e.target.value})}>
        <option disabled={true} value="0">Select movie</option>
        {allMovies.map((item, key) => <option key={key} value={item.id}>{item.name}</option>)}
    </select>;

    const handleSave = async () => {
        const projection = {
            id: id,
            date: `${date}T00:00`,
            movie: movie,
            room: room,
            price: price
        };

        await projectionService.update(projection);
        history.push('/home');
    };

    const handleDelete = async () => {
        await projectionService.deleteById(id);
        history.push('/home');
    };

    return (
        <div className="form">
            <div>
                <div className="form-body">
                    <p className="label">Date</p>
                    <input onChange={(event) => setDate(event.target.value)} value={date.slice(0, 10)} type="date"
                           disabled={!isAuthenticated}/>
                </div>
                <div className="form-body">
                    <p className="label">Movie</p>
                    <MovieSelection/>
                </div>
                <div className="form-body">
                    <p className="label">Room</p>
                    <input onChange={(event) => setRoom(event.target.value)} value={room} type="text"
                           disabled={!isAuthenticated}/>
                </div>
                <div className="form-body">
                    <p className="label">Price</p>
                    <input onChange={(event) => setPrice(event.target.value)} value={price}
                           type="number" disabled={!isAuthenticated}/>
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

export default ProjectionForm;
