function solve(data, criteria) {
    let arr = JSON.parse(data);
    
    newArr = arr.filter(x =>  x[criteria.split("-")[0]] === criteria.split("-")[1]);
    let result = newArr.map(x => `${newArr.findIndex(e=> e === x)}. ${x["first_name"]} ${x["last_name"]} - ${x["email"]}`).join('\n');
    
    return result;
}

console.log(solve(`[{
    "id": "1",
    "first_name": "Kaylee",
    "last_name": "Johnson",
    "email": "k0@cnn.com",
    "gender": "Female"
  }, {
    "id": "2",
    "first_name": "Kizzee",
    "last_name": "Johnson",
    "email": "kjost1@forbes.com",
    "gender": "Female"
  }, {
    "id": "3",
    "first_name": "Evanne",
    "last_name": "Maldin",
    "email": "emaldin2@hostgator.com",
    "gender": "Male"
  }, {
    "id": "4",
    "first_name": "Evanne",
    "last_name": "Johnson",
    "email": "ev2@hostgator.com",
    "gender": "Male"
  }]`,
 'last_name-Johnson'
));