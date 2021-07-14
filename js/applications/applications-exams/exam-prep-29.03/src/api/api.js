async function request(url, options) {
    try {
        const response = await fetch(url, options);
        if (!response.ok) {
            const error = await response.json();
            throw new Error(error.message);
        }

        try {
            const data = await response.json();
            return data;
        } catch {
            return response;
        }

    } catch(err) {
        window.alert(err.message)
        throw err;
    }
}

export const settings = {
    host: "",
}
function getOptions(method ='get', body) {
    const options = {
        method: method,
        headers: {},
    }

    const token = sessionStorage.getItem('authToken');
    if (token) {
        options.headers["X-Authorization"] = token;
    }

    if (body) {
        options.headers['Content-Type'] = 'application/json';
        options.body = JSON.stringify(body);
    }
    return options;
}

export async function get(url) {
    return await request(url, getOptions());
}

export async function post(url, data) {
    return await request(url, getOptions('post', data));
}

export async function put(url, data) {
    return await request(url, getOptions('put', data));
}
export async function del(url) {
    return await request(url, getOptions('delete'));
}

export async function login(username, password) {
    const response = await post(settings.host + '/users/login', {username, password});
    sessionStorage.setItem('authToken', response.accessToken)
    sessionStorage.setItem('user', response.username);
    sessionStorage.setItem('userId', response._id);
    return response;
}

export async function register(username, password) {
    const response = await post(settings.host + '/users/register', {username, password});
    sessionStorage.setItem('authToken', response.accessToken)
    sessionStorage.setItem('user', response.username);
    sessionStorage.setItem('userId', response._id);
    return response;
}
export async function logout() {
    const response = await get(settings.host + '/users/logout');
    sessionStorage.removeItem('authToken');
    sessionStorage.removeItem('user');
    sessionStorage.removeItem('userId');
    return response;
}
