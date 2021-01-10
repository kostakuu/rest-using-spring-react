import React from 'react';
import {NavLink, useLocation} from "react-router-dom";

import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faHome, faKey} from '@fortawesome/free-solid-svg-icons';

const Navigation = () => {
    const location = useLocation();

    const navigationItems = [
        {
            starts: '/home',
            content: (<NavLink to="/home"><FontAwesomeIcon icon={faHome}/>Home</NavLink>),
        },
        {
            starts: '/login',
            content: (<NavLink to="/"><FontAwesomeIcon icon={faKey}/>{localStorage.getItem('token') ? 'Logout' : 'Login'}</NavLink>),
        }
    ];

    const addActive = (item, key) =>
        <li key={key} className={location.pathname.startsWith(item.starts) ? 'item active' : 'item'}>{item.content}</li>;

    const NavigationItemsComponent = () => <>{navigationItems.map(addActive)}</>;

    return (
        <div className="navigation-container">
            <ul className="navigation">
                <li className="logo">Movie Star</li>
                <NavigationItemsComponent/>
            </ul>
        </div>
    );
}

export default Navigation;
