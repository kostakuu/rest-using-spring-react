import httpClient from "./httpClient";

class GenericService {
    constructor(type) {
        this.url = `http://localhost:8080/api/${type}/`;
    }

    async getAll() {
        return await httpClient.get(this.url);
    }

    async getById(id) {
        return await httpClient.get(`${this.url}${id}`);
    }

    async add(body) {
        return await httpClient.post(this.url, body);
    }

    async update(body) {
        return await httpClient.put(this.url, body);
    }

    async deleteById(id) {
        return await httpClient.delete(`${this.url}${id}`);
    }
}

export default GenericService;
