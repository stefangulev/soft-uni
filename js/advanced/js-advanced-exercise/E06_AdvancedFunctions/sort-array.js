function solve(arr, str) {
    //return str === "asc" ? arr.sort((l,r) => l - r) : arr.sort((l,r) => r - l);
    const map = {
        'asc': (a,b) => a - b,
        'desc': (a,b) => b - a,
    }
    return arr.sort(map[str]);
}
console.log(solve([14, 7, 17, 6, 8], 'desc'));