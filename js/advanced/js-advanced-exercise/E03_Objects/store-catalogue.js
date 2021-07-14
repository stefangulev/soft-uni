function catalogue(arr) {

let catalogue = {};
    while(arr.length) {
        let [name, price] = arr.shift().split(" : ");
        price = Number(price);
        if(!catalogue[name[0]]) {
            catalogue[name[0]] = [];
        } 
        catalogue[name[0]].push({name, price});
        catalogue[name[0]].sort((l,r) => l.name.localeCompare(r.name));
    }

    let result = [];

    for(let key in catalogue) {
        let values = catalogue[key].map(entry => `  ${entry.name}: ${entry.price}`);
        let final = `${key}\n${values.join('\n')}`
        result.push(final);
    }
    return result.sort((l,r) => l.localeCompare(r)).join("\n");
    
}
console.log(catalogue(['Appricot : 20.4',
'Fridge : 1500',
'TV : 1499',
'Deodorant : 10',
'Boiler : 300',
'Apple : 1.25',
'Anti-Bug Spray : 15',
'T-Shirt : 10']
));