import React from 'react';
import {NavLink} from "react-router-dom";

import StandardLayout from "../layouts/StandardLayout";
import MovieForm from "../components/MovieForm";

const MoviePage = () => {
    const getRecentMovies = () => {
        try {
            return JSON.parse(localStorage.getItem('movies')) || [];
        } catch {
            return [];
        }
    }

    const RecentMovies = () => <ul>
        {getRecentMovies().map((item, key) => <li key={key}><NavLink to={`/movie/${item.id}`}>{item.name}</NavLink></li>)}
    </ul>

    return (
        <StandardLayout>
            <MovieForm/>
            <h1>Recently Viewed:</h1>
            <RecentMovies/>
        </StandardLayout>
    );
}

export default MoviePage;
