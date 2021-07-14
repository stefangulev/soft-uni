function loadRepos() {
	let input = document.getElementById('username').value;
	let url = `https://api.github.com/users/${input}/repos`;
	let list = document.getElementById('repos');
	list.innerHTML = ""
	fetch(url).then((info) => info.json()).then((result) => {
		result.forEach(d => {
			let newLi = document.createElement('li');
			newLi.textContent = d.full_name;
			list.appendChild(newLi);
		})
		
	})

}