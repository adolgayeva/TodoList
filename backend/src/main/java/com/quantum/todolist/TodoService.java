package com.quantum.todolist;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

/**
 * This component contains business logic for the application's CRUD
 */
@Service
public class TodoService {

    /**
     * Autowired repository instance
     */
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    /**
     * Retrieves all the items in the list
     *
     * @return reactive stream items
     */
    public Flux<Todo> findAll() {
        return todoRepository.findAll();
    }

    /**
     * Looks up an item by id. Generates an error if it's not found
     *
     * @param id id of an item
     * @return item's data, or {@link TodoNotFoundException} if an item is not found
     */
    public Mono<Todo> findById(String id) {
        return todoRepository.findById(id)
                .switchIfEmpty(Mono.error(new TodoNotFoundException()));
    }

    /**
     * Deletes an item with given id
     *
     * @param id id of an item
     * @return empty publisher
     */
    public Mono<Void> delete(String id) {
        return todoRepository.deleteById(id);
    }

    /**
     * Deletes several items by their ids
     *
     * @param ids of items to delete
     * @return empty publisher
     */
    public Mono<Void> delete(Set<String> ids) {
        return todoRepository.deleteByIdIn(ids).then();
    }

    /**
     * Saves the new item
     *
     * @param todo data of an item
     * @return saved data
     */
    public Mono<Todo> save(Todo todo) {
        return todoRepository.save(todo);
    }

    /**
     * Looks up the item and updates it with given data
     *
     * @param id   id of an item
     * @param todo data of an item
     * @return updated data
     */
    public Mono<Todo> update(String id, Todo todo) {
        return findById(id)
                .map(old -> new Todo(
                        old.getId(),
                        todo.getTitle(),
                        todo.getText(),
                        todo.isDone(),
                        todo.getDateTime()
                ))
                .flatMap(todoRepository::save);
    }

}
