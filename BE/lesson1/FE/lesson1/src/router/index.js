import { createRouter, createWebHistory } from "vue-router";

import BookList from "../components/BookList.vue";
import BookForm from "../components/CreateBook.vue";
import Pages from "../components/Pages.vue";

const routes = [
  { path: "/", redirect: "/books" },
  { path: "/books", component: BookList },
  { path: "/books/add", component: BookForm },
  { path: "/books/edit/:id", component: BookForm },
  { path: "/pages", component: Pages },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
