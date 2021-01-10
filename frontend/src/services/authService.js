import httpClient from "./httpClient";

class AuthService {
    constructor() {
        this.url = 'http://localhost:8080/api/auth/';
    }

    async login(user) {
        return await httpClient.post(this.url, user, false)
    }
}

export default AuthService;
