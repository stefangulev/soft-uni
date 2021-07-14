import * as api from './api.js'
const host = 'http://localhost:3030';
api.settings.host = host;

export const login = api.login;
export const register = api.register;
export const logout = api.logout;

export async function createMeme(title, description, imageUrl) {
    return await api.post(api.settings.host + '/data/memes', {title, description, imageUrl});
}
export async function getAllMemes() {
    return await api.get(api.settings.host + '/data/memes?sortBy=_createdOn%20desc');
}
export async function getMemeDetails(id) {
    return await api.get(api.settings.host + "/data/memes/" + id);
}
export async function editMemeDetails(id, title, description, imageUrl) {
    return await api.put(api.settings.host + "/data/memes/" + id, {title, description, imageUrl});
}
export async function deleteMeme(id) {
    return await api.del(api.settings.host + "/data/memes/" + id);
}
export async function getUserMemes(id) {
    return await api.get(api.settings.host + `/data/memes?where=_ownerId%3D%22${id}%22&sortBy=_createdOn%20desc`);
}
