const ChristmasMovies = require('./movies');
const {expect} = require('chai');


describe('Test movies', () => {
    let emptyInstance = undefined;
    beforeEach(() => {
        emptyInstance = new ChristmasMovies();
    
    });

    it("Test constructor", () => {
        expect(Array.isArray(emptyInstance.movieCollection)).to.be.true;
        expect(emptyInstance.movieCollection.length).to.equal(0);
        expect(typeof emptyInstance.watched).to.equal('object');
        expect(Object.keys(emptyInstance.watched).length === 0).to.be.true;
        expect(Array.isArray(emptyInstance.actors)).to.be.true;
        expect(emptyInstance.actors.length).to.equal(0);
    });
    it('Test buyMovie() with already owned film', () => {
        emptyInstance.buyMovie('a',['first']);
        expect(() => emptyInstance.buyMovie('a',['first'])).to.throw(`You already own a in your collection!`);
    });
    it('Test buyMovie() with new film', () => {
        expect(emptyInstance.buyMovie('a',['first'])).to.equal(`You just got a to your collection in which first are taking part!`);
        expect(emptyInstance.movieCollection.length).to.equal(1);
        expect(emptyInstance.buyMovie('b',['first','second'])).to.equal(`You just got b to your collection in which first, second are taking part!`);
        expect(emptyInstance.movieCollection.length).to.equal(2);
    });
    it ('Test buyMovie() with non-unique actors', () => {
        expect(emptyInstance.buyMovie('a', ['first', 'first'])).to.equal(`You just got a to your collection in which first are taking part!`);
    })
    it("Test discardMovie() with a non-existent film", () => {
        expect(() => emptyInstance.discardMovie('non-existent')).to.throw(`non-existent is not at your collection!`)
    });
    it("Test discardMovie() with existent movie non watched", () => {
        emptyInstance.buyMovie('a', ['first']);
        expect(() => emptyInstance.discardMovie('a')).to.throw('a is not watched!');
        expect(emptyInstance.movieCollection.length).to.equal(0);
    })
    it("Test discardMovie() with existent movie watched", () => {
        emptyInstance.buyMovie('a', ['first']);
        emptyInstance.watchMovie('a');
        expect(emptyInstance.discardMovie('a')).to.equal('You just threw away a!');
        expect(emptyInstance.movieCollection.length).to.equal(0);
        expect(emptyInstance.watched.hasOwnProperty('a')).to.be.false;
    });
    it("Test watchMovie() with non-existent film", () => {
        expect(() => emptyInstance.watchMovie('a')).to.throw('No such movie in your collection!');
    });
    it("Test watchMovie() with existent but not watched film", () => {
        emptyInstance.buyMovie('a', ['first']);
        emptyInstance.watchMovie('a');
        expect(emptyInstance.watched['a']).to.equal(1);
    })
    it("Test watchMovie() with existent and watched film", () => {
        emptyInstance.buyMovie('a', ['first']);
        emptyInstance.watchMovie('a');
        emptyInstance.watchMovie('a');
        expect(emptyInstance.watched['a']).to.equal(2);
    })
    it("Test favouriteMovie() with not movies watched", () => {
        expect(() => emptyInstance.favouriteMovie()).to.throw('You have not watched a movie yet this year!');
    })
    it("Test favouriteMovie() with movie watched", () => {
        emptyInstance.buyMovie('a', ['first']);
        emptyInstance.watchMovie('a');
        expect(emptyInstance.favouriteMovie()).to.equal(`Your favourite movie is a and you have watched it 1 times!`);
    });
    it("Test favouriteMovie() with movie watched overload", () => {
        emptyInstance.buyMovie('a', ['first']);
        emptyInstance.watchMovie('a');
        emptyInstance.buyMovie('b', ['first']);
        emptyInstance.watchMovie('b');
        emptyInstance.watchMovie('b');
        expect(emptyInstance.favouriteMovie()).to.equal(`Your favourite movie is b and you have watched it 2 times!`);
    });
    it("Test actors with no movies", () => {
        expect(() => emptyInstance.mostStarredActor()).to.throw('You have not watched a movie yet this year!');
    })
    it("Test actors with movies", () => {
        emptyInstance.buyMovie('a', ['first']);
        expect(emptyInstance.mostStarredActor()).to.equal(`The most starred actor is first and starred in 1 movies!`)
    })
    it("Test actors with movies overload", () => {
        emptyInstance.buyMovie('a', ['first']);
        emptyInstance.buyMovie('b', ['first']);
        emptyInstance.buyMovie('c', ['second']);
        expect(emptyInstance.mostStarredActor()).to.equal(`The most starred actor is first and starred in 2 movies!`)
    })


})