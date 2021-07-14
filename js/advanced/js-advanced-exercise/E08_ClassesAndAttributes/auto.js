function auto(arr) {
    let cars = new Map();

    arr.map(e => e.split(" | ")).forEach(([manuf, model, count]) => {
        if (!cars.has(manuf)) {
            cars.set(manuf, new Map());
        }
        !cars.get(manuf).has(model) ? cars.get(manuf).set(model, Number(count)) : cars.get(manuf).set(model, Number(count) + cars.get(manuf).get(model));
    });

    Array.from(cars.keys()).forEach(key => {
        console.log(key);
       Array.from(cars.get(key).entries()).forEach(([model, count]) => console.log(`###${model} -> ${count}`));
    })
    
}
auto(['Audi | Q7 | 1000',
'Audi | Q6 | 100',
'BMW | X5 | 1000',
'BMW | X6 | 100',
'Citroen | C4 | 123',
'Volga | GAZ-24 | 1000000',
'Lada | Niva | 1000000',
'Lada | Jigula | 1000000',
'Citroen | C4 | 22',
'Citroen | C5 | 10']
);