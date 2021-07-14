class ChristmasDinner {
    constructor(budget) {
        this.budget = budget;
        this.dishes = [];
        this.products = [];
        this.guests = {};
    }
    set budget(num) {
        if (num < 0) {
            throw new Error("The budget cannot be a negative number");
        }
        this._budget = num;
    }
    get budget() {
        return this._budget;
    }
    shopping(product) {
        let type = product[0];
        let price = Number(product[1]);
        if (this.budget < price) {
            throw new Error("Not enough money to buy this product");
        }
        this.budget = this.budget - price;
        this.products.push(type);
        return `You have successfully bought ${type}!`
    }
    recipes(recipe) {
        let name = recipe.recipeName;
        let recipeProducts = recipe.productsList;
        recipeProducts.forEach(element => {
            if (!this.products.find(e => e === element)) {
                throw new Error("We do not have this product");
            }
        });
        this.dishes.push(recipe);
        return `${name} has been successfully cooked!`
    }
    inviteGuests(name, dish) {
        if (!this.dishes.find(e => e.recipeName === dish)) {
            throw new Error(`We do not have this dish`);
        }
        if (this.guests[name]) {
            throw new Error(`This guest has already been invited`);
        }
        this.guests[name] = dish;
        return `You have successfully invited ${name}!`
    }
    showAttendance() {
        return Object.entries(this.guests).map(([key, value]) => `${key} will eat ${value}, which consists of ${this.dishes.find(d => d.recipeName === value).productsList.join(', ')}`).join('\n');
    }

}
let dinner = new ChristmasDinner(300);

dinner.shopping(['Salt', 1]);
dinner.shopping(['Beans', 3]);
dinner.shopping(['Cabbage', 4]);
dinner.shopping(['Rice', 2]);
dinner.shopping(['Savory', 1]);
dinner.shopping(['Peppers', 1]);
dinner.shopping(['Fruits', 40]);
dinner.shopping(['Honey', 10]);

dinner.recipes({
    recipeName: 'Oshav',
    productsList: ['Fruits', 'Honey']
});
dinner.recipes({
    recipeName: 'Folded cabbage leaves filled with rice',
    productsList: ['Cabbage', 'Rice', 'Salt', 'Savory']
});
console.log(dinner.recipes({
    recipeName: 'Peppers filled with beans',
    productsList: ['Beans', 'Peppers', 'Salt']
}));

dinner.inviteGuests('Ivan', 'Oshav');
dinner.inviteGuests('Petar', 'Folded cabbage leaves filled with rice');
dinner.inviteGuests('Georgi', 'Peppers filled with beans');

console.log(dinner.showAttendance());
