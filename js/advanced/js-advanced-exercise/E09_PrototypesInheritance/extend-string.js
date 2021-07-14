(function solve() {
    String.prototype.ensureStart = function(str) {
        return this.startsWith(str) ? this.toString() : str + this;
    }
    String.prototype.ensureEnd = function(str) {
        return this.endsWith(str) ? this.toString() : this + str;
    }
    String.prototype.isEmpty = function() {
        return this.length === 0;
    }
    String.prototype.truncate = function(n) {
        if (n < 4) {
            return ".".repeat(n);
        }
        if (this.length <= n) {
            return this.toString();
        } else {
            if (this.includes(" ")) {
                let current = this.split(" ");
                while((current.join(" ") + "...").length > n) {
                   current.splice(current.length - 1, 1);
                }
                return current.join(" ") + "...";
               
            } else {
               return this.slice(0, n-3) + "...";
            }
        }
    }
    String.format = function(str, ...params) {
        let arr = str.split(" ");
        let edited = arr.map(e => /\{.+\}/.test(e) ? params.shift() || e : e);
        return edited.join(" ");
    }



})();

str = 'the quick brown fox jumps over the lazy dog';

console.log(str.truncate(43));