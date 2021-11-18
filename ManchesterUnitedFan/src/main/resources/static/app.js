$('#standingsBody').onload(() => {
    loadStandings()
});
function loadStandings() {
    console.log("heyo")
    $('#standingsTable').empty();
    fetch('https://api.football-data.org/v2/competitions/2021/standings', {
        'method': 'get',
        'headers': {
            'X-Auth-Token': '35ed857c3765435191691fc045ed7bf3'
        }
    })
        .then(response => response.json())
        .then(json => console.log(json))
}