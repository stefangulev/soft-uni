function juice(arr) {
    let map = new Map();

    arr.map(e => e.split(" => ")).map(([key, val]) => [key, val/1000]).
    forEach(([key, val]) => {
        if (val >= 1) {
            if (!map.has(key)) {
                map.set(key, 0);
            }
            map.set(key, val + map.get(key));
        }
        
    });

    Array.from(map.entries()).forEach(([key, value]) => console.log(`${key} => ${Math.floor(value)}`));

}
juice(['Kiwi => 234',
'Pear => 2345',
'Watermelon => 3456',
'Kiwi => 4567',
'Pear => 5678',
'Watermelon => 6789']);
juice(['Orange => 2000',
'Peach => 1432',
'Banana => 450',
'Peach => 600',
'Strawberry => 549']
);
