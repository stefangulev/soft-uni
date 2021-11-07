$('#loadBooks').click(() => {reloadBooks()})
function reloadBooks() {
$("#authors-container").empty();
fetch('http://localhost:8080/books')
    .then(response => response.json())
    .then(json => json.forEach(book => {
        let tableRow = '<tr>\n' +
            '                <td>' + book.title + '</td>\n' +
            '                <td>' + book.author.name +'</td>\n' +
            '                <td>' + book.isBn + '</td>\n' +
            '                <td>\n' +
            '                    <button class="edit-btn" data-book-id="' + book.id + '">Edit</button>\n' +
            '                    <button class="delete-btn" data-book-id="' + book.id + '">Delete</button>\n' +
            '                </td>\n' +
            '            </tr>'

        $("#authors-container").append(tableRow)
    }))
}
$('body').on('click', 'button.delete-btn', function () {
    let bookId = $(this).data('book-id');
    fetch('http://localhost:8080/books/' + bookId, {
        method: 'DELETE'

    }).then(_ => reloadBooks())
})