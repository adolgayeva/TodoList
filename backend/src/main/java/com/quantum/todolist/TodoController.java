package com.quantum.todolist;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;


/**
 * This controller exposes the CRUD for the TodoList
 */
@RestController
@CrossOrigin
@RequestMapping("/todo-list-api/todos")
public class TodoController {

    /**
     * Autowired instance of {@code TodoService}
     */
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * Reads all the items
     *
     * @return reactive stream of items
     */
    @GetMapping
    public Flux<Todo> getAll() {
        return todoService.findAll();
    }

    /**
     * Reads an item with specified id. 404 if it's not found in db
     *
     * @param id id of an item
     * @return mono-wrapped item
     */
    @GetMapping("/{id}")
    public Mono<Todo> get(@PathVariable String id) {
        return todoService.findById(id);
    }

    /**
     * Creates an item
     *
     * @param todo data of an item (without id)
     * @return data of an item after it was saved (with id)
     */
    @PostMapping
    public Mono<Todo> create(@RequestBody Todo todo) {
        return todoService.save(todo);
    }

    /**
     * Updates an item if it exists. 404 if it doesn't
     *
     * @param todo data of an item
     * @return data of an item after it was saved (with id)
     */
    @PutMapping("/{id}")
    public Mono<Todo> update(@PathVariable String id, @RequestBody Todo todo) {
        return todoService.update(id, todo);
    }

    /**
     * Deletes an item if it exists.
     *
     * @param id id of an item
     * @return empty publisher
     */
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return todoService.delete(id);
    }

    /**
     * Deletes several items by their ids
     *
     * @param ids of items to delete
     * @return empty publisher
     */
    @DeleteMapping
    public Mono<Void> deleteBatch(@RequestParam("id") Set<String> ids) {
        return todoService.delete(ids);
    }

}
