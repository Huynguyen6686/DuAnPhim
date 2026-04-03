<template>
  <div class="max-w-xl mx-auto mt-10 bg-white shadow-lg rounded-lg p-8">
    <h1 class="text-2xl font-bold mb-6 text-gray-700">
      {{ isEdit ? "Update Book" : "Add Book" }}
    </h1>

    <div class="space-y-4">
      <input
        v-model="book.id"
        placeholder="Book ID"
        class="border p-2 w-full rounded"
        :disabled="isEdit"
      />

      <input
        v-model="book.name"
        placeholder="Book Name"
        class="border p-2 w-full rounded"
      />

      <select v-model="book.category" class="border p-2 w-full rounded">
        <option>Children Book</option>
        <option>Comic</option>
        <option>Science Book</option>
      </select>

      <input
        v-model="book.author"
        placeholder="Author"
        class="border p-2 w-full rounded"
      />

      <input
        v-model="book.price"
        type="number"
        placeholder="Price"
        class="border p-2 w-full rounded"
      />

      <div class="flex space-x-3">
        <button
          @click="submitForm"
          class="bg-green-600 hover:bg-green-700 text-white px-5 py-2 rounded"
        >
          {{ isEdit ? "Update" : "Add" }}
        </button>

        <router-link
          to="/books"
          class="bg-gray-500 hover:bg-gray-600 text-white px-5 py-2 rounded"
        >
          Cancel
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      book: {
        id: "",
        name: "",
        category: "Children Book",
        author: "",
        price: "",
      },

      isEdit: false,
    };
  },

  mounted() {
    if (this.$route.params.id) {
      this.isEdit = true;

      axios.get("http://localhost:8080/api/books").then((res) => {
        this.book = res.data.find((b) => b.id === this.$route.params.id);
      });
    }
  },

  methods: {
    submitForm() {
      if (
        !this.book.id ||
        !this.book.name ||
        !this.book.category ||
        !this.book.author ||
        !this.book.price
      ) {
        alert("Please fill all fields");
        return;
      }

      if (this.isEdit) {
        axios
          .put("http://localhost:8080/api/books/" + this.book.id, this.book)
          .then(() => {
            alert("Updated successfully");
            this.$router.push("/books");
          });
      } else {
        axios.post("http://localhost:8080/api/books", this.book).then(() => {
          alert("Added successfully");
          this.$router.push("/books");
        });
      }
    },
  },
};
</script>
