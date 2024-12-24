<body>
    <h1>REST API with Spring Boot and Redis Caching</h1>
    <p>
        This project demonstrates a simple RESTful API built using Spring Boot with caching capabilities 
        implemented using Redis. It provides basic CRUD functionality for managing users.
    </p>
    
  <h2>Features</h2>
  <ul>
      <li>RESTful API endpoints for creating, retrieving, and deleting users</li>
      <li>Data persistence using JPA and an SQL database</li>
      <li>Redis caching for efficient retrieval of user data</li>
  </ul>
  
  <h2>Technologies Used</h2>
  <ul>
      <li>Spring Boot</li>
      <li>Spring Data JPA</li>
      <li>Redis for caching</li>
      <li>MySQL</li>
      <li>Java 17+</li>
  </ul>
  
  <h2>Endpoints</h2>
  <table border="1">
      <thead>
          <tr>
              <th>HTTP Method</th>
              <th>Endpoint</th>
              <th>Description</th>
          </tr>
      </thead>
      <tbody>
          <tr>
              <td>GET</td>
              <td>/api/users/{id}</td>
              <td>Retrieve a user by their ID (cached).</td>
          </tr>
          <tr>
              <td>POST</td>
              <td>/api/users</td>
              <td>Create a new user.</td>
          </tr>
          <tr>
              <td>DELETE</td>
              <td>/api/users/{id}</td>
              <td>Delete a user by their ID (removes from cache).</td>
          </tr>
      </tbody>
  </table>

  <h2>How Caching Works</h2>
  <p>
      The <code>@Cacheable</code> annotation is used to cache the result of the <code>getUserById</code> method. 
      When the method is called with the same ID, the cached result is returned, avoiding unnecessary database queries. 
      The <code>@CacheEvict</code> annotation ensures that the cache is cleared when a user is deleted.
  </p>

  <h2>Running the Project</h2>
  <ol>
      <li>Clone this repository.</li>
      <li>Ensure Redis is installed and running locally.</li>
      <li>Update the application.properties file with your database and Redis configurations.</li>
      <li>Run the application using <code>mvn spring-boot:run</code> or your preferred IDE.</li>
      <li>Use tools like Postman or cURL to interact with the API endpoints.</li>
  </ol>
</body>
</html>
