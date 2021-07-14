async function request(url, option) {
try {
    const response = await fetch(url, option);
    if (!response.ok) {
        const error = await response.json();
        throw new Error(error.message);
    }
    try {
        const data = await response.json();
        return data;
    } catch (err){
        return response;
    }
    
} catch (err){
   console.error(err.message);
    throw err;
}  
}
export const settings = {
    host: '',
};

function getOptions(method = 'get', body) {
    const options = {
        method,
        headers: {},
    }
    const token = sessionStorage.getItem('authToken');
    if (token) {
        options.headers["X-Authorization"] = token;
    }
    if (body) {
        options.headers["Content-Type"] = 'applicatin/json';
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

export async function login(email, password) {
    const result =  await post(settings.host + '/' + 'users/login',{email, password});
    sessionStorage.setItem('authToken', result.accessToken);
    sessionStorage.setItem('userId', result._id);
    sessionStorage.setItem('email', result.email);
    sessionStorage.setItem('username', result.username);
    sessionStorage.setItem('gender', result.gender);
    return result;
}

export async function register(username, email, password, gender) {
    const result = await post(settings.host + '/' + 'users/register', {username, email, password, gender});
    sessionStorage.setItem('authToken', result.accessToken);
    sessionStorage.setItem('userId', result._id);
    sessionStorage.setItem('email', result.email);
    sessionStorage.setItem('username', result.username);
    sessionStorage.setItem('gender', result.gender);
    return result;
}
export async function logout() {
    const result =  await get(settings.host + '/' + 'users/logout');
    sessionStorage.removeItem('authToken');
    sessionStorage.removeItem('userId');
    sessionStorage.removeItem('email');
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('gender');
    return result;
}



