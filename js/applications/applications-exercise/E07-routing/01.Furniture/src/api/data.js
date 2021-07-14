import * as api from './api.js'
const host = 'http://localhost:3030';
api.settings.host = host;

export const login = api.login;
export const register = api.register;
export const logout = api.logout;

//Implement application specific requests
export async function getFurniture() {
    return await api.get(api.settings.host + '/' + 'data/catalog');
}
export async function getFurnitureById(id) {
    return await api.get(api.settings.host + '/' + 'data/catalog/' + id);
}

export async function getMyFurniture() {
    const userId = sessionStorage.getItem('userId');
    return await api.get(api.settings.host + '/' + `data/catalog?where=_ownerId%3D%22${userId}%22`)
}
export async function createFurniture(furniture) {
    return await api.post(api.settings.host + '/' + 'data/catalog/', furniture);
}
export async function updateFurniture(id, furniture) {
    return await api.put(api.settings.host + '/' + 'data/catalog/' + id, furniture);
}
export async function deleteFurniture(id) {
    return await api.del(api.settings.host + '/' + 'data/catalog/' + id);
}

