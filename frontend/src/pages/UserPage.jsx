import React from 'react';
import {useHistory} from 'react-router-dom';

import StandardLayout from "../layouts/StandardLayout";
import UserForm from "../components/UserForm";

const UserPage = () => {
    const history = useHistory();

    if (!localStorage.getItem('token')) history.push('/');

    return (
        <StandardLayout>
            <UserForm/>
        </StandardLayout>
    );
}

export default UserPage;
