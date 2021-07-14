function cityTaxes(name, population, treasury) {

    let town =  {
        name: name,
        population: population,
        treasury: treasury,
        taxRate: 10,
        collectTaxes() {
            this.treasury += Math.floor(this.taxRate * this.population);
        },
        applyGrowth(percentage) {
            this.population += Math.floor(this.population * percentage/100);
        },
        applyRecession(percentage) {
            this.treasury -= Math.floor(this.treasury * percentage/100);
        }
        
    }

    return town;

}
const city = 
  cityTaxes('Tortuga',
  7000,
  15000);
console.log(city);

