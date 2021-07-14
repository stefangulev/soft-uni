function loadCommits() {
    let username = document.getElementById('username').value;
    let repo = document.getElementById('repo').value;
    let list = document.getElementById('commits');
    let url = `https://api.github.com/repos/${username}/${repo}/commits`

    fetch(url).then(data => {
        if(!data.ok) {
            throw new Error(`Error: ${data.status} ${data.statusText}`);
        }
        data.json()
    }).then(result => {
        result.forEach(commit => {
            console.log(commit);
            let newLi = document.createElement('li');
            newLi.textContent = `${commit.commit.author.name}: ${commit.commit.message}`
            list.appendChild(newLi);
        });
    }).catch(error => {
        let newLi = document.createElement('li');
        newLi.textContent = error.message; 
        list.appendChild(newLi);
    });



}