function extract(arr) {
    return arr.reduce(function(result, currentElement, index, initialArray){
        if (currentElement >= result[result.length - 1] || result.length === 0) {
            result.push(currentElement);
        }
        return result;
    }, []);
}

console.log(extract([1,
    3,
    8,
    4,
    10,
    12,
    3,
    2,
    24]))