function juice(arr) {
    let map = new Map();
    let resultMap = new Map();

    arr.map(e => e.split(" => ")).
    forEach(([key, val]) => {
        !map.has(key) ? map.set(key, Number(val)) : map.set(key, Number(val) + map.get(key));
        if (map.get(key) / 1000 >= 1) {
            !resultMap.has(key) ? resultMap.set(key, Math.floor(Number(map.get(key) / 1000))) : resultMap.set(key, Math.floor(Number(map.get(key) / 1000)) + resultMap.get(key));
         map.set(key, map.get(key) - (Math.floor(Number(map.get(key) / 1000)) * 1000));
        }
        
    });
    

   Array.from(resultMap.entries()).forEach(([key, value]) => console.log(`${key} => ${Math.floor(value)}`));

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
