function smallestN(arr) {
    arr.sort((l, r) => l - r);

    let smallest = arr.slice(0 , 2);

    return smallest.join(" ");
    
}

console.log(smallestN([30, 15, 50, 5]));