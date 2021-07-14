function table(arr) {
    let [columns, ...table] = arr.map(splitLine);

    function convertIfNum(x) {
        return isNaN(x) ? x : Number(Number(x).toFixed(2));
    }
    
    function splitLine(input) {
        return input.split("|").filter(x => x !== "").map(x => x.trim()).map(x => convertIfNum(x));
    }

    return JSON.stringify(table.map(entry => {
        return columns.reduce((acc, curr, index)=> {
            acc[curr] = entry[index];
            return acc;
        } , {}
        )}));


    
}
console.log(table(['| Town | Latitude | Longitude |',
'| Sofia | 42.696552 | 23.32601 |',
'| Beijing | 39.913818 | 116.363625 |']
));