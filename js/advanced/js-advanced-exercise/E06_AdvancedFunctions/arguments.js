function solve() {

    let obj = {};

    [...arguments].forEach(e => {
        let type = typeof e;
        console.log(`${type}: ${e}`);
        if (obj[type] === undefined) {
            obj[type] = {
                count : 0,
            } 
        }
        obj[type].count += 1;
        
    });
    let result = Object.keys(obj).sort((l,r) => obj[r].count - obj[l].count);
    result.forEach(x => console.log(`${x} = ${obj[x].count}`));
    


}
solve('cat', 42, 34, function () { console.log('Hello world!'); });