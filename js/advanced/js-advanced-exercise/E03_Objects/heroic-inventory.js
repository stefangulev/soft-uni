function heroic(arr) {

    let registy = [];

    for(let hero of arr) {
        let [name, level, items] = hero.split(" / ");
        level = Number(level);
        items = items ? items.split(", ") : [];
        registy.push({name, level, items});
    }

    return JSON.stringify(registy);
}

console.log(heroic(['Isacc / 25 / Apple, GravityGun',
'Derek / 12 / BarrelVest, DestructionSword',
'Hes / 1 / Desolator, Sentinel, Antara']
));