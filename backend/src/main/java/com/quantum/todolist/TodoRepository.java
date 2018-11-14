package com.quantum.todolist;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.Set;

/**
 * Reactive repository that contains the items of the TodoList
 */
public interface TodoRepository extends ReactiveCrudRepository<Todo, String> {

    /**
     * Deletes several items by their ids
     *
     * @param ids of items to delete
     * @return {@code Mono} with the number of items to delete
     */
    Mono<Long> deleteByIdIn(Set<String> ids);

}
