import React from 'react';
import {BrowserRouter, Route} from 'react-router-dom';

import HomePage from "./pages/HomePage";
import MoviePage from "./pages/MoviePage";
import LoginPage from "./pages/LoginPage";
import GenrePage from "./pages/GenrePage";
import ProjectionPage from "./pages/ProjectionPage";
import UserPage from "./pages/UserPage";

const App = () => (
    <div className="root-container">
        <BrowserRouter>
            <Route path="/" component={LoginPage} exact/>
            <Route path="/home" component={HomePage}/>
            <Route path="/genre/:paramId" component={GenrePage}/>
            <Route path="/movie/:paramId" component={MoviePage}/>
            <Route path="/projection/:paramId" component={ProjectionPage}/>
            <Route path="/user/:paramId" component={UserPage}/>
        </BrowserRouter>
    </div>
);

export default App;
