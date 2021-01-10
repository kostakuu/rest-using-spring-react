import React from 'react';

import StandardLayout from "../layouts/StandardLayout";
import Table from "../components/Table";

import GenericService from '../services/genericService';

const HomePage = () => {
    const genreSpecification = {
        type: 'genre',
        service: new GenericService('genre'),
        title: 'Genres',
        headers: ['Id', 'Name'],
        columns: ['id', 'name'],
    };

    const movieSpecification = {
        type: 'movie',
        service: new GenericService('movie'),
        title: 'Movies',
        headers: ['Id', 'Name', 'Duration', 'Genres', 'Number of views'],
        columns: ['id', 'name', 'duration', 'genres', 'numberOfViews'],
    };

    const projectionSpecification = {
        type: 'projection',
        service: new GenericService('projection'),
        title: 'Projections',
        headers: ['Id', 'Date', 'Movie', 'Room', 'Price (EUR)'],
        columns: ['id', 'date', 'movie', 'room', 'price'],
    };

    const userSpecification = {
        type: 'user',
        service: new GenericService('user'),
        title: 'Users',
        headers: ['Id', 'Username', 'Full name', 'Gender'],
        columns: ['id', 'username', 'fullName', 'gender'],
    };

    const UserTable = () => localStorage.getItem('token') ? <Table specification={userSpecification}/> : <></>;

    return (
        <StandardLayout>
            <Table specification={genreSpecification}/>
            <Table specification={movieSpecification}/>
            <Table specification={projectionSpecification}/>
            <UserTable/>
        </StandardLayout>
    );
}

export default HomePage;
