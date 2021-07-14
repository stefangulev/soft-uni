function rectangle(width, height, color) {

    function capitolize(input) {
     return input[0].toUpperCase() + input.slice(1);
    }

    return {
        width : Number(width),
        height: Number(height),
        color: capitolize(color),
        calcArea: function() {
            return this.width * this.height;
        },
    }

}
let rect = rectangle(4, 5, 'red');
console.log(rect.width);
console.log(rect.height);
console.log(rect.color);
console.log(rect.calcArea());