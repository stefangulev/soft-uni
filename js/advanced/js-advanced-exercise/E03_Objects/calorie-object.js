function solve(arr) {
    const obj = {};
    for(i =0; i < arr.length; i+=2) {
    obj[arr[i]] = Number(arr[i+1]);
    }
    return obj;
}
console.log(solve(['Yoghurt', '48', 'Rise', '138', 'Apple', '52']));
console.log(solve(['Potato', '93', 'Skyr', '63', 'Cucumber', '18', 'Milk', '42']));