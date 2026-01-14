# Homework assignment for lesson “2.3. Spring Web MVC”

As your solution, please send links to your GitHub projects in your personal student account on the website [netology.ru](https://netology.ru).

**Important information**

1. Before you start, please study the links on the main page of the [homework repository](../README.md).
2. If you encounter any problems, please create an issue [according to the established rules](../report-requirements.md).

## How to submit assignments

1. Create a Maven project on your computer.
1. Initialize an empty Git repository in it.
1. Add the ready-made [.gitignore](../.gitignore) file to it.
1. Add the rest of the necessary files to the same directory.
1. Make the necessary commits.
1. Create a public repository on GitHub and link your local repository to the remote one.
1. Push and make sure your code appears on GitHub.
1. Send a link to your project in your personal account on the [netology.ru](https://netology.ru) website.

## Migration

### Legend

The first task is simple: you need to migrate your application on servlets, written in previous homework assignments, to Spring Web MVC with Embed Tomcat in Giga IDE.

### Task

Create a new project in Giga IDE based on Spring MVC and Embed Tomcat and transfer the functionality implemented in previous homework assignments.

Your controller should look exactly as it does in the lecture:

```java
@RestController
@RequestMapping(“/api/posts”)
public class PostController {
  private final PostService service;

  public PostController(PostService service) {
    this.service = service;
  }

  @GetMapping
  public List<Post> all() {
    return service.all();
  }

  @GetMapping(“/{id}”)
  public Post getById(@PathVariable long id) {
    return service.getById(id);
  }

  @PostMapping
  public Post save(@RequestBody Post post) {
    return service.save(post);
  }

  @DeleteMapping(“/{id}”)
  public void removeById(long id) {
    service.removeById(id);
  }
}
```

Please note that all functionality (CRUD) implemented previously should still work.

### Result

As a result, send a link to your GitHub project in your personal student account on the [netology.ru](https://netology.ru) website.

## Data is not deleted* (task with an asterisk)

This is an optional task; completing it does not affect your grade.

### Legend

The worst thing you can do with user data is to delete it permanently. Users always call later asking to restore it and claim that they definitely did not delete anything themselves. Therefore, most often user data is not deleted, but marked for deletion, i.e., a field such as `removed` is added.

For simplicity, we will assume that `/api/posts` is an API for clients, where they will not be able to restore deleted posts or even view deleted posts. There will be a separate API for this later.

It turns out that `removeById` only sets this field. The behavior of the other methods also changes dramatically:

* `all` returns all posts except those with the `removed` flag set;
* `getById` returns a post only if it does not have the `removed` flag set, otherwise it throws a `NotFoundException`*;
* `save` updates an existing post only if it does not have the `removed` flag set, otherwise it throws a `NotFoundException`*.

Note.* There is no perfect solution here, different people may tell you that this is possible or impossible, etc. We will only say that any categorical statement is always bad, and you should understand that there are different options. It all depends on what decision the API designer or team makes.

The only question that remains is about status codes. Logically, `NotFoundException` should result in a 404 status code. Study the documentation on [@ResponseStatus](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/ResponseStatus.html) and think about how to apply it to return a 404 status code when the exception we mentioned occurs.

<details>
<summary>Hint.</summary>

It should be used in the format `@ResponseStatus(code = HttpStatus.NOT_FOUND)`, while importing both `ResponseStatus` and `HttpStatus`.
</details>

### Task

Create a Pull Request with the described functionality for the project from the first task (Migration).

The decision on which layer to implement this logic on is up to you. But it definitely should not be Controller.

Please note that all functionality (CRUD) implemented before this should still work.

### Result

As a result, send:

* a link to your Pull Request in your personal student account on the website [netology.ru](https://netology.ru);
* an explanation of why you implemented the logic in the layer specified in the result.
