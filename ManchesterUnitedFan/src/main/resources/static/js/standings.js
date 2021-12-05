fetch("https://api.football-data.org/v2/competitions/2021/standings", {
    method: 'get',
    headers: {
        'X-Auth-Token': '35ed857c3765435191691fc045ed7bf3'
    }})
    .then(data => data.json())
    .then(info => {
        let infoElement = info['standings'];
        let standings = infoElement[0].table

        for (const standing of standings) {
            let row = '<tr>' +
                '<th scope="row">' + standing.position + '</th>' +
                '<td>' + '<img ' + 'src="' + standing.team.crestUrl + '" ' + 'width="40" height="30">' + standing.team.name + '</td>' +
                '<td>' + standing.playedGames + '</td>' +
                '<td>' + standing.won + '</td>' +
                '<td>' + standing.draw + '</td>' +
                '<td>' + standing.lost + '</td>' +
                '<td>' + standing.points + '</td>' +
            '</tr>';
            $("#standingsTable").append(row)
        }
    })
