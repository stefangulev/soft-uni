import * as api from './api.js';

export const login = api.login;
export const logout = api.logout;
export const register = api.register;
const host = 'http://localhost:3030';
api.settings.host = host;

export async function getIdeas() {
    return await api.get(host +'/data/ideas?select=_id%2Ctitle%2Cimg&sortBy=_createdOn%20desc');
}
export async function createIdea(idea) {
    return await api.post(host +'/data/ideas', idea);
}
export async function getIdeaDetails(id) {
    return await api.get(host + `/data/ideas/${id}`);
}
export async function deleteIdea(id) {
    return await api.del(host + `/data/ideas/${id}`);
}