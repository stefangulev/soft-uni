function rotate(arr, n) {
    for(i = 0; i < n; i++) {
        element = arr.pop();
        arr.unshift(element);
    }

    return arr.join(" ");
}

console.log(rotate(['Banana', 
'Orange', 
'Coconut', 
'Apple'], 
15
))