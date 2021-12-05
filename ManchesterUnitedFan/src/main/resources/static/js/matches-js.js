fetch("https://api.football-data.org/v2/teams/66/matches" ,{
    method: 'get',
    headers: {
        'X-Auth-Token': '35ed857c3765435191691fc045ed7bf3'
    }})
    .then(data => data.json())
    .then(info => {
        let matches = info.matches;
        for (const infoElement of matches) {
            console.log(infoElement)
            let homeTeamScore = infoElement.score.fullTime.homeTeam != null ? infoElement.score.fullTime.homeTeam : '';
            let awayTeamScore = infoElement.score.fullTime.awayTeam !=null ? infoElement.score.fullTime.awayTeam : '';
            let matchDate = infoElement.utcDate.replace("T", " ").replace("Z", " ");


            let card =
                '<div class="card bg-light" style="width: 18rem; margin: 50px 600px;">' +
                '<img src="' + infoElement.competition.area.ensignUrl + '" style="display: inline-block" width="30px" height="30px">' +
                '<h6 class="card-text" style="display: inline-block" width="50px" height="50px">' + infoElement.competition.name + '</h6>' +
                '<div>' +
                '<h5 class="card-title">' + infoElement.homeTeam.name + ' - ' + infoElement.awayTeam.name +'</h5>' +
                '<img ' + 'src="https://crests.football-data.org/' + infoElement.homeTeam.id + '.svg" ' + 'style="display: inline-block" width="100px" height="100px" alt="Team badge inavailable">' +
                '<h2 style="display: inline-block">' + homeTeamScore + '</h2>' +
                '<h2 style="display: inline-block">' + ' : ' + '</h2>' +
                '<h2 style="display: inline-block">' + awayTeamScore + '</h2>' +
                '<img ' + 'src="https://crests.football-data.org/' + infoElement.awayTeam.id + '.svg" ' + 'style="display: inline-block" width="100px" height="100px" alt="Team badge inavailable">' +
                '<h6 class="card-text">Date (UTC):' + matchDate + '</h6>' +
                '   </div>' +
                '</div> ';
            $("#matches").append(card);
            }
     }
    );


