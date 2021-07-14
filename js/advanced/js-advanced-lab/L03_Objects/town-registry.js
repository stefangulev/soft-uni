function registryFunc(townsArr) {
    let registry = {};

    for(let element of townsArr) {
        let [name, population] = element.split(" <-> ");
        let populationInNumbers = Number(population);
        if (registry[name] === undefined) {
            registry[name] =0;
        }
        registry[name] += populationInNumbers;
       
    }
    

    for(let [name, population] of Object.entries(registry)) {
        console.log(`${name} : ${population}`);
    }

}
registryFunc(['Sofia <-> 1200000',
'Montana <-> 20000',
'New York <-> 10000000',
'Washington <-> 2345000',
'Las Vegas <-> 1000000']
);