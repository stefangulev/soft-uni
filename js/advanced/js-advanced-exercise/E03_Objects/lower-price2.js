function price(arr) {
    let registry = {};

    for(let element of arr) {
        let [town, product, price] = element.split(" | ");
        price = Number(price);
        if (!registry[product]) {
            registry[product] = {price, town};
        } else {
            registry[product] = price >= registry[product].price ? registry[product] : {price, town};
        }
    }
    let result = [];
    for(let key in registry) {
        result.push(`${key} -> ${registry[key].price} (${registry[key].town})`);
    }
    return result.join('\n');
}
console.log(price(['Sample Town | Sample Product | 1000',
'Sample Town | Orange | 2',
'Sample Town | Peach | 1',
'Sofia | Orange | 3',
'Sofia | Peach | 2',
'New York | Sample Product | 1000.1',
'New York | Burger | 10']
));