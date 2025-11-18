package com.Users.users.Controller;

import com.Users.users.Service.UsersService;
import com.Users.users.Storage.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/users")
@CrossOrigin(origins = "*") // Cho phép file HTML chạy từ nơi khác gọi vào (tránh lỗi CORS)
public class UsersController {
    private final UsersService usersService;
    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    //Read
    @GetMapping // Đường dẫn sẽ là GET /users
    public ResponseEntity<Page<Users>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int pageSize
    ) {
        Page<Users> userPage = usersService.findAllUsers(page, pageSize);
        return ResponseEntity.ok(userPage);
    }

    //Create
    @PostMapping
    public ResponseEntity<Users> createUsers(@RequestBody Users users){
        Users newUser = usersService.createUsers(users);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newUser);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUsers(@PathVariable Integer id, @RequestBody Users existingUsers){
        Users updatedUser = usersService.updateUsers(id, existingUsers);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedUser);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable("id") Integer id){
        usersService.deleteUsers(id);

        //Trả về 204 No Content
        return ResponseEntity
                .noContent()
                .build();
    }

    //Search
    @GetMapping("/search")
    public ResponseEntity<Page<Users>> searchUsers(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        Page<Users> result = usersService.searchUsers(keyword, page, pageSize);
        return ResponseEntity.ok(result); //Trả về 200 OK bất kể có tìm thấy dữ liệu hay không
    }

    //Detail
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUsers(@PathVariable Integer id){
        return usersService.getUsersById(id)
                .map(ResponseEntity::ok) //Nếu có -> Trả về 200 OK
                .orElse(ResponseEntity.notFound().build()); //Nếu rỗng --> Trả về 404 Not Found
    }
}
