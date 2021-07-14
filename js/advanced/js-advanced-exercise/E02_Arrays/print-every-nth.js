function print(arr, n) {
    const newArr = arr.filter((a, i) => i % n ==0);
return newArr
}
console.log(print(['5', 
'20', 
'31', 
'4', 
'20'], 
2
));