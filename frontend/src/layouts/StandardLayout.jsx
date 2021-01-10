import React from 'react';

import Navigation from "../components/Navigation";

const StandardLayout = (props) => <div className="container">
    <div className="body">
        <Navigation/>
        <div className="page-content">
            {props.children}
        </div>
        <div className="footer">
            <p>&copy; 2021 Powered by kostakuu</p>
        </div>
    </div>
</div>;

export default StandardLayout;
