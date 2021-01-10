const getAuthHeader = (useAuth) => useAuth
    ? {Authorization: `Bearer ${localStorage.getItem('token')}`}
    : {}
;

const config = {
    useAuthGet: true,
    useAuthModify: true,
}

const httpClient = {
    get(url, useAuth = config.useAuthGet, customHeaders = {}) {
        return fetch(url, {
            headers: {
                'Content-Type': 'application/json',
                ...getAuthHeader(useAuth),
                ...customHeaders
            },
            method: 'GET',
        })
            .then(response => response.json())
            .catch(errorMessage => console.error(errorMessage));
    },
    post(url, body = {}, useAuth = config.useAuthModify, customHeaders = {}) {
        return fetch(url, {
            headers: {
                'Content-Type': 'application/json',
                ...getAuthHeader(useAuth),
                ...customHeaders
            },
            method: 'POST',
            body: JSON.stringify(body),
        })
            .then(response => response.json())
            .catch(errorMessage => console.error(errorMessage));
    },
    put(url, body = {}, useAuth = config.useAuthModify, customHeaders = {}) {
        return fetch(url, {
            headers: {
                'Content-Type': 'application/json',
                ...getAuthHeader(useAuth),
                ...customHeaders
            },
            method: 'PUT',
            body: JSON.stringify(body),
        })
            .then(response => response.json())
            .catch(errorMessage => console.error(errorMessage));
    },
    delete(url, useAuth = config.useAuthModify, customHeaders = {}) {
        return fetch(url, {
            headers: {
                'Content-Type': 'application/json',
                ...getAuthHeader(useAuth),
                ...customHeaders
            },
            method: 'DELETE',
        })
            .then(response => response.json())
            .catch(errorMessage => console.error(errorMessage));
    },
};

export default httpClient;
