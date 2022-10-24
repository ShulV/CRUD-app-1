const requestURL = 'http://localhost:8081/api/books/';

//отправить запрос общего вида
function sendRequest(method, url) {
    const headers = {
        'Content-Type': 'application/json'
    }
    return fetch(url, {
        method: method,
        headers: headers
    }).then(response => {return response.json()})
}


let inputSearch = document.getElementById('book-search')
let booksContainer = document.getElementById('books-container')

//удалить потомков блока
function removeChildren(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}

//обновить спискок книг путем динамического добавления блоков
function generateBooksContainers(books) {
    removeChildren(booksContainer)
    books.forEach(book => {
        let div = document.createElement('div');
        let html = `<a href="/books/${book.id}">
            <span>${book.name}</span>,
            <span>${book.author}</span>,
            <span>${book.year}</span>
        </a>`
        div.insertAdjacentHTML('beforeend', html);
        booksContainer.appendChild(div);
    })
}

//добавить обработчик события для input'a поиска
inputSearch.addEventListener('input', (e) => {
    const fullRequestUrl = requestURL + inputSearch.value;
    sendRequest('GET', fullRequestUrl)
        .then(data => {
            generateBooksContainers(data)
        })
        .catch(err => console.log(err))
})