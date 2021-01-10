import React, {useState, useEffect} from 'react';
import {useParams, useHistory} from "react-router-dom";

import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSave, faTrash} from "@fortawesome/free-solid-svg-icons";
import GenericService from "../services/genericService";

const MovieForm = () => {
    const {paramId} = useParams();

    const [id, setId] = useState(null);
    const [name, setName] = useState('');
    const [duration, setDuration] = useState(0);
    const [genres, setGenres] = useState([]);
    const [numberOfViews, setNumberOfViews] = useState(0);

    const [allGenres, setAllGenres] = useState([]);

    const history = useHistory();

    const isAuthenticated = !!localStorage.getItem('token');
    const movieService = new GenericService('movie');
    const genreService = new GenericService('genre');

    useEffect(() => {
        const refreshData = async () => {
            const genres = await genreService.getAll();
            setAllGenres(genres);

            if (paramId && paramId !== 'new') {
                const response = await movieService.getById(paramId);

                setId(paramId);
                setName(response['name']);
                setDuration(response['duration']);
                setGenres(response['genres']);
                setNumberOfViews(response['numberOfViews']);

                const movies = [{id: paramId, name: response['name']}];
                try {
                     const recentMovies = JSON.parse(localStorage.getItem('movies')) || [];
                     localStorage.setItem('movies', JSON.stringify(movies.concat(recentMovies.filter(movie => movie.id !== paramId))));
                } catch {
                    localStorage.setItem('movies', JSON.stringify(movies));
                }
            }
        };

        refreshData().then(null);
    }, [paramId]);

    const hasGenre = (id) => {
        return genres.filter(genre => genre.id === id).length > 0;
    }

    const handleGenreSelection = (id) => {
        hasGenre(id)
            ? setGenres(genres.filter(genre => genre.id !== id))
            : setGenres(genres.concat([{id: id}]));
    }

    const GenreSelection = () => allGenres.map((item, key) => <div className="checkbox" key={key}>
        <input onChange={(event) => handleGenreSelection(parseInt(event.target.value))}
               type="checkbox"
               value={item.id}
               checked={hasGenre(item.id)}
               disabled={!isAuthenticated}/>
        {item.name}
    </div>);

    const handleSave = async () => {
        const movie = {
            id: id,
            name: name,
            duration: duration,
            numberOfViews: numberOfViews,
            genres: genres
        };

        await movieService.update(movie);
        history.push('/home');
    };

    const handleDelete = async () => {
        await movieService.deleteById(id);
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
                <div className="form-body">
                    <p className="label">Duration</p>
                    <input onChange={(event) => setDuration(event.target.value)} value={duration} type="number"
                           disabled={!isAuthenticated}/>
                </div>
                <div className="form-body">
                    <p className="label">Genres</p>
                    <GenreSelection/>
                </div>
                <div className="form-body">
                    <p className="label">Number of views</p>
                    <input onChange={(event) => setNumberOfViews(event.target.value)} value={numberOfViews}
                           type="number" disabled={true}/>
                </div>
                <div className="form-footer" style={isAuthenticated ? {} : {display: 'none'}}>
                    <button onClick={handleDelete} style={id !== null ? {} : {display: 'none'}}><FontAwesomeIcon icon={faTrash}/>Delete</button>
                    <button onClick={handleSave} className="save"><FontAwesomeIcon icon={faSave}/>Save</button>
                </div>
            </div>
        </div>
    );
}

export default MovieForm;
