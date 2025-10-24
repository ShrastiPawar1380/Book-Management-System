const apiBase = "http://localhost:8080/api/book";

document.addEventListener("DOMContentLoaded", loadBooks);

const form = document.getElementById("bookForm");
const tableBody = document.querySelector("#bookTable tbody");

form.addEventListener("submit", async (e) => {
  e.preventDefault();

  const book = {
    id: document.getElementById("bookId").value || null,
    title: document.getElementById("title").value,
    author: document.getElementById("author").value,
    price: parseFloat(document.getElementById("price").value),
    publisedYear: parseInt(document.getElementById("publisedYear").value),
    genre: document.getElementById("genre").value,
    availablity: document.getElementById("availablity").checked
  };

  const method = book.id ? "PUT" : "POST";
  const url = book.id ? `${apiBase}` : `${apiBase}`;

  await fetch(url, {
    method,
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(book)
  });

  form.reset();
  document.getElementById("bookId").value = "";
  loadBooks();
});

async function loadBooks() {
  const res = await fetch(apiBase);
  const data = await res.json();
  const books = data.data || [];

  tableBody.innerHTML = "";

  books.forEach((book) => {
    const row = document.createElement("tr");

    row.innerHTML = `
      <td>${book.id}</td>
      <td>${book.title}</td>
      <td>${book.author}</td>
      <td>${book.price}</td>
      <td>${book.publisedYear}</td>
      <td>${book.genre}</td>
      <td>${book.availablity ? "✅" : "❌"}</td>
      <td>
        <button class="action-btn" onclick="deleteBook(${book.id})">Delete</button>
      </td>
    `;

    row.addEventListener("click", () => fillForm(book));

    tableBody.appendChild(row);
  });
}

function fillForm(book) {
  document.getElementById("bookId").value = book.id;
  document.getElementById("title").value = book.title;
  document.getElementById("author").value = book.author;
  document.getElementById("price").value = book.price;
  document.getElementById("publisedYear").value = book.publisedYear;
  document.getElementById("genre").value = book.genre;
  document.getElementById("availablity").checked = book.availablity;
}

async function deleteBook(id) {
  if (confirm("Are you sure you want to delete this book?")) {
    await fetch(`${apiBase}/${id}`, { method: "DELETE" });
    loadBooks();
  }
}
