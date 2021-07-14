function biggerHalf(arr) {
    arr.sort((l,r) => l -r);
    let secondHalf = arr.slice(arr.length /2);
    return secondHalf;
}

console.log(biggerHalf([4, 7, 2, 5, 26]));