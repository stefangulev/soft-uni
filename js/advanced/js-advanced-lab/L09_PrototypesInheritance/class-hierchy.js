function hierchy() {
    class Figure {
        constructor(unit = 'cm') {
            this.units = unit;
        }

        get area() {

        }
        changeUnits(unit) {
            if (unit === 'cm' || unit === 'mm' || unit === "m") {
                this.units = unit;
            }
        }
        toString() {
            return `Figures units: ${this.units}`
        }
    }
    class Circle extends Figure {
        constructor(radius) {
            super();
            this.radius = radius;
        }
        get area() {
            return Math.PI * this.radius * this.radius;
        }
        changeUnits(unit) {
            if (unit === 'cm' || unit === 'mm' || unit === "m") {
                this.units = unit;
            }
            if (this.units == 'mm') {
                this.radius *= 10;
            } else if (this.units === 'm') {
                this.radius /=100;
            }
        }

        toString() {
            return `Figures units: ${this.units} Area: ${this.area} - radius: ${this.radius}`;
        }
    }
    class Rectangle extends Figure {
        constructor(width, height, units) {
            super(units);
            if (this.units === 'cm') {
                this.width = width;
                this.height = height;
            } else if (this.units === 'mm') {
                this.width = width * 10;
            this.height = height * 10;
            } else if (this.units === 'm') {
                this.width = width /100;
            this.height = height / 100;
            }
            
        }
        get area() {
            return this.width * this.height;
        }
        changeUnits(unit) {
    
            if (this.units == 'cm' && unit === 'mm') {
                this.width *= 10;
                this.height *= 10;
            } else if (this.units === 'cm' && unit === "m") {
                this.width /=100;
                this.height /=100;
            } else if (this.units === 'mm' && unit === 'cm') {
                this.width /= 10;
                this.height /= 10;
            } else if (this.units === 'mm' && unit === 'm') {
                this.width /= 1000;
                this.height /= 1000;
            } else if (this.units === 'm' && unit === 'cm') {
                this.width *= 100;
                this.height *= 100;
            } else if (this.units === 'm' && unit === 'mm') {
                this.width *= 1000;
                this.width *= 1000;
            }

             if (unit === 'cm' || unit === 'mm' || unit === "m") {
                this.units = unit;
            }
        }
        
        toString() {
          return  `Figures units: ${this.units} Area: ${this.area} - width: ${this.width}, height: ${this.height}`
    }
}



return {
    Figure,
    Circle,
    Rectangle
}
}
hierchy();