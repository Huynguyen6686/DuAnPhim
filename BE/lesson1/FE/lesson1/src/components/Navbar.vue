<template>
  <nav class="bg-white shadow-md border-b border-gray-200">
    <div class="max-w-6xl mx-auto px-4">
      <div class="flex justify-between h-16">
        <div class="flex items-center space-x-8">
          <router-link to="/" class="flex items-center py-4 px-2">
          </router-link>

          <div class="hidden md:flex items-center space-x-4">
            <router-link
              to="/books"
              class="py-4 px-2 text-gray-500 font-semibold hover:text-blue-600 transition duration-300"
              active-class="text-blue-600 border-b-4 border-blue-600"
            >
              Home page
            </router-link>

            <router-link
              to="/pages"
              class="py-4 px-2 text-gray-500 font-semibold hover:text-blue-600 transition duration-300"
              active-class="text-blue-600 border-b-4 border-blue-600"
            >
              Pages
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: "Navbar",
};
</script>
