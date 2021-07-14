function attachEvents() {
    document.getElementById('updateForm').style.display = 'none';
    const loadButton = document.querySelector('.load').addEventListener('click', load);
    const addForm = document.querySelector('#addForm').addEventListener('submit', add);
    const updateForm = document.getElementById('updateForm').addEventListener('submit', updateCatch);


    async function add(event) {
        event.preventDefault();
        const token = sessionStorage.getItem('authToken');
        const form = new FormData(event.target);
        const response = await fetch('http://localhost:3030/data/catches', {
            method: "post",
            headers: {"X-Authorization": token },
            body: JSON.stringify({
                "angler":form.get('angler'), "weight":form.get('weight'), "species":form.get('species'), "location":form.get('location'), "bait":form.get('bait'), "captureTime": form.get('bait')}
            )

        });
        if (!response.ok) {
            const error = response.json();
            return alert(error.message);
        }
        event.target.reset();
        load();
    }


   async function load() {
        const response = await fetch('http://localhost:3030/data/catches');
        if (!response.ok) {
            const error = response.json();
            return alert(error.message);
        }
        const data = await response.json();
        const rows = Object.values(data).map(createCatch).join('');
        document.getElementById('catches').innerHTML = rows;
        updateButtons();
    }

    function createCatch(entry) {
        const result = `<div class="catch">
        <input type="hidden" name="id" value="${entry._id}">
        <input type="hidden" name="ownerId" value="${entry._ownerId}">
        <label>Angler</label>
        <input type="text" class="angler" value="${entry.angler}" />
        <hr>
        <label>Weight</label>
        <input type="number" class="weight" value="${entry.weight}" />
        <hr>
        <label>Species</label>
        <input type="text" class="species" value="${entry.species}" />
        <hr>
        <label>Location</label>
        <input type="text" class="location" value="${entry.location}" />
        <hr>
        <label>Bait</label>
        <input type="text" class="bait" value="${entry.bait}" />
        <hr>
        <label>Capture Time</label>
        <input type="number" class="captureTime" value="${entry.captureTime + " "}" />
        <hr>
        <button disabled class="update">Update</button>
        <button disabled class="delete">Delete</button>
    </div>`
    return result;
    }
    async function deleteCatch(id) {
        const token = sessionStorage.getItem('authToken');
        const response = await fetch('http://localhost:3030/data/catches/' + id, {
            method: 'delete',
            headers: {'X-Authorization': token},
        })
        if (!response.ok) {
            const error = await response.json();
            return alert(error.message);
        }
        load(); 
    }
    async function updateCatch(e) {
        e.preventDefault();
        const form = new FormData(e.target);
        const token = sessionStorage.getItem('authToken');
        const response = await fetch('http://localhost:3030/data/catches/' + form.get('id'), {
            method: "put",
            headers: {"X-Authorization": token },
            body: JSON.stringify({
                "angler":form.get('angler'), "weight":form.get('weight'), "species":form.get('species'), "location":form.get('location'), "bait":form.get('bait'), "captureTime": form.get('bait')}
            )
        });
        if (!response.ok) {
            const error = await response.json;
            return alert(error.message);
        }
        e.target.reset();
        document.getElementById('updateForm').style.display = 'none';
        document.getElementById('addForm').style.display = 'block';
        load();


    }

    document.getElementById('catches').addEventListener('click', manageTableClick);
    
    function manageTableClick(e) {
        if (e.target.classList.contains('update')) {
            const id = e.target.parentNode.querySelector('input[name=id]').value;
            const updateForm = document.getElementById('updateForm');
          updateForm.style.display = 'block';
          updateForm.querySelector('input[name=id]').value = id;
            document.getElementById('addForm').style.display = 'none';
            

        } else if (e.target.classList.contains('delete')) {
            const id = e.target.parentNode.querySelector('input[name=id]').value;
            deleteCatch(id);
        }
    }
    //01. ADD ID TO HTML TABLE
    //02. IMPLEMENT UPDATE/DELETE FUNCTION, EVENTLISTENER ON ENTIRE TABLE, FROM THERE EDIT/REMOVE THE ARTICLE.
    //03. UNDERSTAND HOW TO OBTAIN CREATOR ID,PROBABLY LISTED SOMEWHERE IN SERVER.

    function updateButtons() {
        if(!sessionStorage.getItem('authToken')) {
            Array.from(document.querySelectorAll('button')).forEach(e => e.disabled = true);
        } else {
            Array.from(document.querySelectorAll('button')).forEach(e => {
                e.disabled = false;
            })
    }
    }
    }



attachEvents();

