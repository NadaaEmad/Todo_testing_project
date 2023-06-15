import com.fcai.SoftwareTesting.todo.Todo;
import com.fcai.SoftwareTesting.todo.TodoCreateRequest;
import com.fcai.SoftwareTesting.todo.TodoService;
import com.fcai.SoftwareTesting.todo.TodoServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class CodeTests {

    /////////////Create Test cases/////////////////
    @Test(expected = IllegalArgumentException.class) //Todo is null
    public void testCreateTodo_TodoNull() {
        TodoService todoService = new TodoServiceImpl();

        Assertions.assertEquals("Todo cannot be null", todoService.create(null));
    }

    @Test(expected = IllegalArgumentException.class) //Title is empty
    public void testCreateTodo_TitleEmpty() {
        TodoService todoService = new TodoServiceImpl();

        TodoCreateRequest todoCreateRequest = new TodoCreateRequest("", "Todo Description");


        Todo createdTodo = todoService.create(todoCreateRequest);
        String message = "Todo title cannot be empty";
        Assertions.assertEquals(message, createdTodo);

    }

    @Test(expected = IllegalArgumentException.class) //description is empty
    public void testCreateTodo_DescEmpty() {
        TodoService todoService = new TodoServiceImpl();

        TodoCreateRequest todoCreateRequest = new TodoCreateRequest("", "");


        Todo createdTodo = todoService.create(todoCreateRequest);
        String message = "Todo description cannot be empty";
        Assertions.assertEquals(message, createdTodo);

    }

    @Test
    public void testCreateTodo_NormalFlow() {
        TodoService todoService = new TodoServiceImpl();

        TodoCreateRequest todoCreateRequest = new TodoCreateRequest("Todo Title", "Todo Description");


        Todo createdTodo = todoService.create(todoCreateRequest);

        Assertions.assertNotNull(createdTodo);
        Assertions.assertEquals("Todo Title", createdTodo.getTitle());
        Assertions.assertEquals("Todo Description", createdTodo.getDescription());
        Assertions.assertFalse(createdTodo.isCompleted());
    }

    /////////////Read Test cases/////////////////
    @Test(expected = IllegalArgumentException.class) //Id is null
    public void testRead_IdNull(){
        TodoService todoService = new TodoServiceImpl();

        TodoCreateRequest todoCreateRequest = new TodoCreateRequest("Todo Title", "Todo Description");
        Todo createdTodo = todoService.create(todoCreateRequest);
        createdTodo.setId(null);


        String todoId = createdTodo.getId();


        Todo retrievedTodo = todoService.read(todoId);
        String message = "Todo id cannot be null";


        Assertions.assertEquals(message, retrievedTodo);

    }

    @Test(expected = IllegalArgumentException.class) //Id is empty
    public void testRead_IdEmpty(){
        TodoService todoService = new TodoServiceImpl();

        TodoCreateRequest todoCreateRequest = new TodoCreateRequest("Todo Title", "Todo Description");
        Todo createdTodo = todoService.create(todoCreateRequest);
        createdTodo.setId("");


        String todoId = createdTodo.getId();


        Todo retrievedTodo = todoService.read(todoId);
        String message = "Todo id cannot be empty";


        Assertions.assertEquals(message, retrievedTodo);

    }

    @Test(expected = IllegalArgumentException.class) //id not null nor empty but todo is null
    public void testRead_TodoNull(){
        TodoService todoService = new TodoServiceImpl();

        Todo createdTodo = new Todo();
        createdTodo.setId("5");

        String todoId = createdTodo.getId();


        Todo retrievedTodo = todoService.read(todoId);


       String message = "Todo not found";
       Assertions.assertEquals(message, retrievedTodo);

    }

    @Test //Normal flow
    public void testReadTodo_NormalFlow() {
        TodoService todoService = new TodoServiceImpl();

        TodoCreateRequest todoCreateRequest = new TodoCreateRequest("Todo Title", "Todo Description");
        Todo createdTodo = todoService.create(todoCreateRequest);


        String todoId = createdTodo.getId();


        Todo retrievedTodo = todoService.read(todoId);


        Assertions.assertNotNull(retrievedTodo);
        Assertions.assertEquals(createdTodo.getId(), retrievedTodo.getId());
        Assertions.assertEquals(createdTodo.getTitle(), retrievedTodo.getTitle());
        Assertions.assertEquals(createdTodo.getDescription(), retrievedTodo.getDescription());
        Assertions.assertEquals(createdTodo.isCompleted(), retrievedTodo.isCompleted());
    }

    /////////////Update Test cases/////////////////
    @Test //Normal flow
    public void testUpdateTodo_NormalFlow() {
        TodoService todoService = new TodoServiceImpl();

        TodoCreateRequest todoCreateRequest = new TodoCreateRequest("Todo Title", "Todo Description");
        Todo createdTodo = todoService.create(todoCreateRequest);


        String todoId = createdTodo.getId();
        boolean completed = true;


        Todo updatedTodo = todoService.update(todoId, completed);


        Assertions.assertNotNull(updatedTodo);
        Assertions.assertEquals(createdTodo.getId(), updatedTodo.getId());
        Assertions.assertEquals(createdTodo.getTitle(), updatedTodo.getTitle());
        Assertions.assertEquals(createdTodo.getDescription(), updatedTodo.getDescription());
        Assertions.assertTrue(updatedTodo.isCompleted());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateTodoWithNonExistingId() {

        TodoService todoService = new TodoServiceImpl();
        String nonExistingId = "non-existing-id";
        boolean completed = true;


        todoService.update(nonExistingId, completed);
        // Exception is expected to be thrown
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateTodoWithNullId() {

        TodoService todoService = new TodoServiceImpl();

        TodoCreateRequest todoCreateRequest = new TodoCreateRequest("Todo Title", "Todo Description");
        Todo createdTodo = todoService.create(todoCreateRequest);
        createdTodo.setId(null);
        createdTodo.setCompleted(true);

        todoService.update(createdTodo.getId(), createdTodo.isCompleted());
        // Exception is expected to be thrown
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateTodoWithEmptyId() {

        TodoService todoService = new TodoServiceImpl();
        String todoId = "";
        boolean completed = true;


        todoService.update(todoId, completed);

        // Exception is expected to be thrown
    }
    @Test
    public void testUpdateTodoWithFalseCompleted() {

        TodoService todoService = new TodoServiceImpl();
        TodoCreateRequest todoCreateRequest = new TodoCreateRequest("Todo Title", "Todo Description");
        Todo createdTodo = todoService.create(todoCreateRequest);


        String todoId = createdTodo.getId();
        boolean completed = false;


        Todo updatedTodo = todoService.update(todoId, completed);


        Assertions.assertNotNull(updatedTodo);
        Assertions.assertEquals(createdTodo.getId(), updatedTodo.getId());
        Assertions.assertEquals(createdTodo.getTitle(), updatedTodo.getTitle());
        Assertions.assertEquals(createdTodo.getDescription(), updatedTodo.getDescription());
        Assertions.assertFalse(updatedTodo.isCompleted());
    }

    /////////////Delete Test cases/////////////////
    @Test(expected = IllegalArgumentException.class) //Normal flow
    public void testDeleteTodo_NormalFlow() {
        TodoService todoService = new TodoServiceImpl();

        TodoCreateRequest todoCreateRequest = new TodoCreateRequest("Todo Title", "Todo Description");
        Todo createdTodo = todoService.create(todoCreateRequest);
        String todoId = createdTodo.getId();


        todoService.delete(todoId);
        todoService.read(todoId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteTodoWithNonExistingId() {
        TodoService todoService = new TodoServiceImpl();
        String nonExistingId = "non-existing-id";

        todoService.delete(nonExistingId);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteTodo_NullId() {

        TodoService todoService = new TodoServiceImpl();

        Todo createdTodo = new Todo(null,"Title","Description",false);
        todoService.delete(createdTodo.getId());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteTodo_EmptyId() {
        TodoService todoService = new TodoServiceImpl();
        String todoId = "";
        todoService.delete(todoId);
    }


    /////////////List Test cases/////////////////
    @Test
    public void testListTodosNotNull() {
        TodoService todoService =new TodoServiceImpl();

        TodoCreateRequest todoCreateRequest1 = new TodoCreateRequest("Todo 1", "Description 1");
        TodoCreateRequest todoCreateRequest2 = new TodoCreateRequest("Todo 2", "Description 2");
        todoService.create(todoCreateRequest1);
        todoService.create(todoCreateRequest2);


        List<Todo> todos = todoService.list();


        Assertions.assertEquals(2, todos.size());
        Assertions.assertNotNull(todos);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testListTodosNull() {
        TodoService todoService =new TodoServiceImpl();
        todoService.create(null);
        todoService.list();
    }

    @Test
    public void testListTodosEmpty() {
        TodoService todoService =new TodoServiceImpl();

        List<Todo> todos = todoService.list();
        Assertions.assertEquals(0, todos.size());
    }

    /////////////List completed Test cases/////////////////
    @Test
    public void testCompleted_EmptyTodo(){
        TodoService todoService =new TodoServiceImpl();
        todoService.listCompleted();

        Assertions.assertEquals(0,todoService.listCompleted().size());
    }

    @Test
    public void testCompleted_TodosHasOneCompletedTodo(){
        TodoService todoService =new TodoServiceImpl();

        TodoCreateRequest todoCreateRequest = new TodoCreateRequest("study", "study algorithms");
        Todo createdTodo = todoService.create(todoCreateRequest);
        todoService.update(createdTodo.getId(), true);

        Assertions.assertEquals(1, todoService.listCompleted().size());
        Assertions.assertEquals("study", todoService.listCompleted().get(0).getTitle());

    }

    @Test
    public void testCompleted_TodosHasOneNotCompletedTodo(){
        TodoService todoService =new TodoServiceImpl();

        TodoCreateRequest todoCreateRequest = new TodoCreateRequest("study", "study algorithms");
        Todo createdTodo = todoService.create(todoCreateRequest);
        todoService.update(createdTodo.getId(), false);


        Assertions.assertEquals(0, todoService.listCompleted().size());


    }

    @Test
    public void testCompleted_TodosHasOneCompletedTodoAndOneNotCompleted(){
        TodoService todoService =new TodoServiceImpl();

        TodoCreateRequest todoCreateRequest = new TodoCreateRequest("study", "study algorithms");
        Todo createdTodo = todoService.create(todoCreateRequest);
        todoService.update(createdTodo.getId(), false);

        TodoCreateRequest todoCreateRequest2 = new TodoCreateRequest("read", "read 100 books");
        Todo createdTodo2 = todoService.create(todoCreateRequest2);
        todoService.update(createdTodo2.getId(), true);



        Assertions.assertEquals(1, todoService.listCompleted().size());
        Assertions.assertEquals("read", todoService.listCompleted().get(0).getTitle());
        Assertions.assertNotEquals("study",todoService.listCompleted().get(0).getTitle());


    }

    @Test
    public void testCompleted_TodosHasTwoCompletedTodos(){
        TodoService todoService =new TodoServiceImpl();

        TodoCreateRequest todoCreateRequest = new TodoCreateRequest("study", "study algorithms");
        Todo createdTodo = todoService.create(todoCreateRequest);
        todoService.update(createdTodo.getId(), true);

        TodoCreateRequest todoCreateRequest2 = new TodoCreateRequest("read", "read 100 books");
        Todo createdTodo2 = todoService.create(todoCreateRequest2);
        todoService.update(createdTodo2.getId(), true);

        Assertions.assertEquals(2, todoService.listCompleted().size());

    }






}
