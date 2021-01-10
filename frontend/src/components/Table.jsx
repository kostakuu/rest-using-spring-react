import React, {useEffect, useState} from 'react';
import {useHistory} from 'react-router-dom';

import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSort} from "@fortawesome/free-solid-svg-icons";

const Table = (props) => {
    const [items, setItems] = useState([]);

    useEffect(() => {
        const refreshData = async () => {
            const response = await props.specification.service.getAll();
            setItems(response);
        };

        refreshData().then(null);
    }, []);

    const history = useHistory();

    const handleClick = (id) => history.push(`/${props.specification.type}/${id}`);

    const renderObject = (object) => typeof object === 'object' ? object['name'] : object;
    const renderArray = (array) => array.map(item => item['name']).join(",");
    const renderItem = (item) => Array.isArray(item) ? renderArray(item) : renderObject(item);

    const HeaderRows = () => props.specification.headers.map((item, key) => <th key={key}>
        <FontAwesomeIcon icon={faSort}/> {item}
    </th>);

    const ItemRows = () => items.map((item, key) => <tr key={key} onClick={() => handleClick(item.id)}>
        {props.specification.columns.map((column, key) => <td key={key}>
            {renderItem(item[column])}
        </td>)}
    </tr>);

    return (
        <div>
            <div className="crud-header">
                <h1>{props.specification.title}</h1>
                <button style={localStorage.getItem('token') ? {} : {display: 'none'}} onClick={() => handleClick('new')} className="add-new-btn">Add new</button>
            </div>
            <table>
                <thead>
                <tr>
                    <HeaderRows/>
                </tr>
                </thead>
                <tbody>
                <ItemRows/>
                </tbody>
            </table>
        </div>
    );
}

export default Table;
