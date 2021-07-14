class Story {
    constructor(title, creator) {
        this.title = title;
        this.creator = creator;
        this.comments = [];
        this._likes = []; 
    }
    get comments() {
        return this._comments;
    }
    set comments(arr) {
        this._comments = arr;
    }
    get likes() {
        if (this._likes.length === 0) {
           return `${this.title} has 0 likes`
        }
        if (this._likes.length === 1) {
            return `${this._likes[0]} likes this story!`
         }
         return `${this._likes[0]} and ${this._likes.length - 1} others like this story!`

    }
    
    like(username) {
        if (this._likes.some(e => e === username)) {
            throw new Error("You can't like the same story twice!");
        }
        if (username === this.creator) {
            throw new Error("You can't like your own story!");
        }
        this._likes.push(username);
        return `${username} liked ${this.title}!`
    }
    dislike(username) {
        if (!this._likes.some(e => e === username)) {
            throw new Error("You can't dislike this story!");
        }
        let index = this._likes.findIndex(e => e === username);
        this._likes.splice(index, 1);
        return `${username} disliked ${this.title}`
    }
    comment(username, content, id) {
        if (id === undefined || !this.comments.some(c => c.Id === id)) {
            let comment = {Id: this.comments.length + 1, Username: username, Content: content, Replies: []};
            this.comments.push(comment);
            return `${username} commented on ${this.title}`
        } else {
            let existingComment = this.comments.find(e => e.Id === id);
            existingComment.Replies.push({Id: `${existingComment.Id}.${existingComment.Replies.length +1}`, Username: username, Content: content});
            return "You replied successfully";
        }
    
    }
    toString(sortingType) {
        let result = `Title: ${this.title}\nCreator: ${this.creator}\nLikes: ${this._likes.length}\nComments:\n`;
        if (sortingType === 'asc') {
            return result + this.comments.sort((l,r) => l.Id - r.Id).map(e => `-- ${e.Id}. ${e.Username}: ${e.Content}${e.Replies.length === 0? "": e.Replies.sort((l ,r) => l.Id - r.Id).map(e => `\n--- ${e.Id}. ${e.Username}: ${e.Content}`).join("")}`).join('\n');
        }else if (sortingType === 'desc') {
            return result + this.comments.sort((l,r) => r.Id - l.Id).map(e => `-- ${e.Id}. ${e.Username}: ${e.Content}${e.Replies.length === 0? "": e.Replies.sort((l ,r) => r.Id - l.Id).map(e => `\n--- ${e.Id}. ${e.Username}: ${e.Content}`).join("")}`).join('\n');
        } else if (sortingType === 'username') {
            return result + this.comments.sort((l,r) => l.Username.localeCompare(r.Username)).map(e => `-- ${e.Id}. ${e.Username}: ${e.Content}${e.Replies.length === 0? "": e.Replies.sort((l ,r) => l.Username.localeCompare(r.Username)).map(e => `\n--- ${e.Id}. ${e.Username}: ${e.Content}`).join("")}`).join('\n');
        }

    }

}

let art = new Story("My Story", "Anny");
art.like("John");
console.log(art.likes);
art.dislike("John");
console.log(art.likes);
art.comment("Sammy", "Some Content");
console.log(art.comment("Ammy", "New Content"));
art.comment("Zane", "Reply", 1);
art.comment("Jessy", "Nice :)");
console.log(art.comment("SAmmy", "Reply@", 1));
console.log()
console.log(art.toString('asc'));
console.log()
art.like("Zane");
console.log(art.toString('desc'));



