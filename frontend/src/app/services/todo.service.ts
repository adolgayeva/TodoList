import ToDo from '../models/todo.model';
import { Observable } from 'rxjs/Rx';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';


import 'rxjs/add/operator/map';

@Injectable()
export class TodoService {
  api_url = 'http://localhost:8080';
  todoUrl = `${this.api_url}/todo-list-api/todos`;

  constructor(
    private http: HttpClient
  ) { }


  createTodo(todo: ToDo): Observable<any>{
    return this.http.post(`${this.todoUrl}`, todo);
  }

  getToDos(): Observable<ToDo[]>{
    return this.http.get(this.todoUrl)
    .map(res  => {
      return res ? res as ToDo[]: [];
    })
  }

  editTodo(todo:ToDo){
    let editUrl = `${this.todoUrl}/`
    return this.http.put(editUrl + todo.id, todo);
  }

  deleteTodo(id:string):any{
    let deleteUrl = `${this.todoUrl}/${id}`
    return this.http.delete(deleteUrl);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}
