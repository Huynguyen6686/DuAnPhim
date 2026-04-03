<template>
  <div class="max-w-6xl mx-auto mt-10">
    <div class="flex justify-between items-center mb-6">
      <h1 class="text-3xl font-bold text-gray-700">Book Management</h1>

      <router-link
        to="/books/add"
        class="bg-blue-600 hover:bg-blue-700 text-white px-5 py-2 rounded-lg shadow"
      >
        Add
      </router-link>
    </div>

    <div class="bg-white shadow rounded-lg overflow-hidden">
      <table class="w-full text-left">
        <thead class="bg-gray-100 text-gray-600">
          <tr>
            <th class="p-3">ID</th>
            <th class="p-3">Name</th>
            <th class="p-3">Category</th>
            <th class="p-3">Author</th>
            <th class="p-3">Price</th>
            <th class="p-3 text-center">Action</th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="b in books" :key="b.id" class="border-t hover:bg-gray-50">
            <td class="p-3">{{ b.id }}</td>
            <td class="p-3">{{ b.name }}</td>
            <td class="p-3">{{ b.category }}</td>
            <td class="p-3">{{ b.author }}</td>
            <td class="p-3 font-semibold text-green-600">$ {{ b.price }}</td>

            <td class="p-3 text-center space-x-2">
              <button
                @click="editBook(b.id)"
                class="bg-yellow-500 hover:bg-yellow-600 text-white px-3 py-1 rounded"
              >
                Update
              </button>

              <button
                @click="deleteBook(b.id)"
                class="bg-red-500 hover:bg-red-600 text-white px-3 py-1 rounded"
              >
                Delete
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      books: [],
    };
  },

  mounted() {
    this.loadBooks();
  },

  methods: {
    loadBooks() {
      axios.get("http://localhost:8080/api/books").then((res) => {
        this.books = res.data;
      });
    },

    editBook(id) {
      this.$router.push("/books/edit/" + id);
    },

    deleteBook(id) {
      if (confirm("Are you sure to delete this book?")) {
        axios.delete("http://localhost:8080/api/books/" + id).then(() => {
          alert("Deleted successfully");
          this.loadBooks();
        });
      }
    },
  },
};
</script>
