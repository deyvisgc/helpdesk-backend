package com.helpdesk.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helpdesk.Utils.ApiResponse;
import com.helpdesk.Utils.RolEnum;
import com.helpdesk.exception.ErrorMessage;
import com.helpdesk.exception.UsuarioException;
import com.helpdesk.model.Users;
import com.helpdesk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    @Autowired
    private UserService userService;
    @Autowired
    private static final ApiResponse response = ApiResponse.getInstance();
    @GetMapping("/")
    public ResponseEntity<List<Users>> getAll() {
        List<Users> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Users> getById(@PathVariable Long id) {
        Users users = userService.getUserById(id);
        if (users != null) {
            return ResponseEntity.ok(users);
        } else {
            response.setSuccess(true);
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody @Valid Users users, BindingResult result) {
        if (result.hasErrors()) {
            throw new UsuarioException(this.formatMessage(result));
        }
        try {
            userService.save(users);
            response.setSuccess(true);
            response.setMessage("Usuario creado exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error interno del servidor: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody @Valid Users users, BindingResult result) {
        if (result.hasErrors()) {
            throw new UsuarioException(this.formatMessage(result));
        }
        try {
            userService.update(users, id);
            response.setSuccess(true);
            response.setMessage("Usuario actualizado exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error interno del servidor: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id) {
        try {
            userService.delete(id);
            response.setSuccess(true);
            response.setMessage("Usuario eliminado exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error interno del servidor: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @GetMapping("/it-analyst")
    public ResponseEntity<List<Users>> getRequirementsBySolicitanteId() {
        List<Users> users = userService.getUserByRol(RolEnum.ANALISTA);
        return ResponseEntity.ok(users);
    }
    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
