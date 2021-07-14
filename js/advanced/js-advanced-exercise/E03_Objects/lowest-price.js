function price(arr) {
    let registry = {};

    for(let element of arr) {
        let [town, product, price] = element.split(" | ");
        price = Number(price);
        if (!registry[product] === undefined) {
            registry[product] = [price, town];
        } else {
            let [currentPrice, currentTown] = registry[product];
            if (town === currentTown || price < currentPrice ) {
                registry[product] = [price, town];
            }
        }
    }
    let result = "";
    for(let key in registry) {
        result += `${key} -> ${registry[key][0]} (${registry[key][1]})` + '\n';
    }
    return result;
}
console.log(price(['Sample Town | Sample Product | 1000',
'Sample Town | Orange | 2',
'Sample Town | Peach | 1',
'Sofia | Orange | 3',
'Sofia | Peach | 2',
'New York | Sample Product | 1000.1',
'New York | Burger | 10']
));