function calculatePrice(fruit, weight, pricePerKg) {
    let weightInKg = (weight/1000)
    let priceForCurrentWeight =  weightInKg * pricePerKg;
    return (`I need $${priceForCurrentWeight.toFixed(2)} to buy ${weightInKg.toFixed(2)} kilograms ${fruit}.`)
}

console.log(calculatePrice('apple', 1563, 2.35));