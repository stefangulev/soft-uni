import * as api from './api.js'
const host = 'http://localhost:3030';
api.settings.host = host;

export const login = api.login;
export const register = api.register;
export const logout = api.logout;

export async function getAllMovies() {
    return await api.get(host + '/data/movies');
}
export async function getMovieById(id) {
    return await api.get(host + `/data/movies/${id}`);
}

export async function createMovie(movie) {
    return await api.post(host + '/data/movies', movie);
}
export async function updateMovie(id, movie) {
    return await api.put(host + `/data/movies/${id}`, movie);
}

export async function deleteMovie(id) {
    return await api.del(host + `/data/movies/${id}`);
}

//implement likes
export async function getNumberOfLikes(id) {
    return await api.get(host + `/data/likes?where=movieId%3D%22${id}%22&distinct=_ownerId&count`);
}
export async function like(id) {
    return await api.post(host + `/data/likes`, {movieId: id});
}
export async function getLikeFromUser(movieId, userId) {
    return await api.get(host + `/data/likes?where=movieId%3D%22${movieId}%22%20and%20_ownerId%3D%22${userId}%22`)
}
export async function revokeLike(id) {
    return await api.del(host + `/data/likes/${id}`);
}
