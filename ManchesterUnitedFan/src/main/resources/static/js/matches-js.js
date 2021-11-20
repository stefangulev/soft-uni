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
            let homeTeamImgName = infoElement.homeTeam.name.replace(/ /g, "");
            let awayTeamImgName = infoElement.awayTeam.name.replace(/ /g, "");

            //TODO maybe  change picture names to IDs.
            let card =
                '<div className="card bg-dark text-white" style="width: 18rem; margin: 50px 600px">' +
                '<img src="' + infoElement.competition.area.ensignUrl + '" style="display: inline-block" width="30px" height="30px">' +
                '<h6 className="card-text" style="display: inline-block" width="50px" height="50px">' + infoElement.competition.name + '</h6>' +
                '<div className="card-img-overlay">' +
                '<h5 className="card-title">' + infoElement.homeTeam.name + ' - ' + infoElement.awayTeam.name +'</h5>' +
                '<img ' + 'src="/images/' + homeTeamImgName + '.png" ' + 'style="display: inline-block" width="100px" height="100px">' +
                '<h2 style="display: inline-block">' + homeTeamScore + '</h2>' +
                '<h2 style="display: inline-block">' + ' : ' + '</h2>' +
                '<h2 style="display: inline-block">' + awayTeamScore + '</h2>' +
                '<img ' + 'src="/images/' + awayTeamImgName + '.png" style="display: inline-block" width="100px" height="100px">' +
                '<h6 className="card-text">Date (UTC):' + infoElement.utcDate + '</h6>' +
                '   </div>' +
                '</div> ';
            $("#matches").append(card);
            }
     }
    );


