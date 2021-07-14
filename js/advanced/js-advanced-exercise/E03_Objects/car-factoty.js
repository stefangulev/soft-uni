function produceCar(input) {
    

    function getEngine(number) {
        const engineStorage = [
            { power: 90, volume: 1800 },
            { power: 120, volume: 2400 },
            { power: 200, volume: 3500 },
        ]
        engineStorage.sort((l,r) => l.power - r.power);
       return engineStorage.find(x => x.power >= number );
    }
   
    function getCarriage(type, color) {
        return {
            type: type,
            color: color,
        }
    }
    function getWheels(size) {
        let num = size;
        num % 2 ==0 ? num -= 1 : num;
       
        return Array(4).fill(num);
    }



    return {
        model: input.model,
        engine: getEngine(input.power),
        carriage: getCarriage(input.carriage, input.color),
        wheels: getWheels(input.wheelsize),

    }
}

console.log(produceCar({ model: 'VW Golf II',
power: 90,
color: 'blue',
carriage: 'hatchback',
wheelsize: 14 }
));