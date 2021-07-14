function auto(arr) {
    let result = arr.map(line => line.split(' | ')).reduce((storage, cars) => {
        let [brand,model, quantity] = cars;
        if(!storage.has(brand)) {
            storage.set(brand, new Map());
        }
        if (!storage.get(brand).has(model)) {
            storage.get(brand).set(model, 0);
        }
        let value = Number(storage.get(brand).get(model));
        storage.get(brand).set(model, value + Number(quantity));
        return storage;
    }, new Map());
    

    Array.from(result.keys()).forEach(key => {
        console.log(key);
       Array.from(result.get(key).entries()).forEach(([model, count]) => console.log(`###${model} -> ${count}`));
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