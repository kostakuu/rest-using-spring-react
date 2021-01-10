import React, {useState} from 'react';
import { useHistory } from "react-router-dom";

import AuthService from "../services/authService";

const LoginPage = () => {
    localStorage.setItem('token', '');

    const authService = new AuthService();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [hasError, setError] = useState(false);

    const history = useHistory();

    const handleLogin = async () => {
        const user = {
            username: email,
            password: password,
        }
        const response = await authService.login(user);

        if (!response || !response.accessToken) {
            setError(true)
            return;
        }

        localStorage.setItem('token', response.accessToken);
        history.push('/home');
    };

    const Error = () => hasError ? <p className="errorText">Bad authentication data...</p> : null;

    return (
        <div className="minimal-container">
            <div className="login-form">
                <h1>Login</h1>
                <div className="login-body">
                    <Error />
                    <input type="text" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="Your email..." />
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Your password..." />
                    <input onClick={handleLogin} type="submit" value="Login" />
                    <input className="secondButton" onClick={() => history.push('/home')} type="submit" value="Take me to home" />
                </div>
            </div>
        </div>
    );
}

export default LoginPage;
