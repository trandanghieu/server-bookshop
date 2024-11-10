# BOOKSHOP BACKEND SERVER
### Technologies
```
mysql
spring boot 3
```
### DB Diagram
![](https://i.imgur.com/IWBSjSH.png)

### End point
### Auth Endpoints
##### Authentication

| HTTP Method | Endpoint                           |
|-------------|------------------------------------|
| **POST**    | `/api/auth/reset-password`         | 
| **POST**    | `/api/auth/register`               | 
| **POST**    | `/api/auth/logout`                 |
| **POST**    | `/api/auth/login`                  | 

##### Book
| HTTP Method | Endpoint                           |
|-------------|------------------------------------|
| **GET**     | `/api/books/{id}`                  | 
| **PUT**     | `/api/books/{id}`                  |
| **DELETE**  | `/api/books/{id}`                  | 
| **GET**     | `/api/books`                       |
| **POST**    | `/api/books`                       | 
| **GET**     | `/api/books/search`                | 

##### Category

| HTTP Method | Endpoint                           |
|-------------|------------------------------------|
| **GET**     | `/api/categories/{id}`             |
| **PUT**     | `/api/categories/{id}`             |
| **DELETE**  | `/api/categories/{id}`             |
| **GET**     | `/api/categories`                  |
| **POST**    | `/api/categories`                  |
| **GET**     | `/api/categories/search`           |


##### Promotion
| HTTP Method | Endpoint                           |
|-------------|------------------------------------|
| **GET**     | `/api/promotions/{id}`             |
| **PUT**     | `/api/promotions/{id}`             |
| **DELETE**  | `/api/promotions/{id}`             |
| **GET**     | `/api/promotions`                  |
| **POST**    | `/api/promotions`                  |
| **GET**     | `/api/promotions/search`           |

##### Reviews
HTTP Method | Endpoint
------------|---------
POST        | /api/reviews
GET         | /api/reviews/book/{bookId}
DELETE      | /api/reviews/{id}
