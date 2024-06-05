package com.helpdesk.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helpdesk.Utils.ApiResponse;
import com.helpdesk.Utils.EstadoEnum;
import com.helpdesk.exception.ErrorMessage;
import com.helpdesk.exception.PropuestaException;
import com.helpdesk.model.Proposal;
import com.helpdesk.service.ProposalService;
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
@RequestMapping("/api/v1/proposal")
public class PropuestaController {
    @Autowired
    private ProposalService proposalService;
    @Autowired
    private static final ApiResponse response = ApiResponse.getInstance();
    @GetMapping
    public ResponseEntity<List<Proposal>> getAll() {
        List<Proposal> lsproposals = proposalService.getAll();
        return ResponseEntity.ok(lsproposals);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Proposal> getById(@PathVariable Long id) {
        Proposal proposals = proposalService.getById(id);
        if (proposals != null) {
            return ResponseEntity.ok(proposals);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/status")
    public ResponseEntity<List<Proposal>> getRequirementsByStatus(@RequestParam("status") EstadoEnum estado) {
        List<Proposal> proposals = proposalService.getByStatus(estado);
        return ResponseEntity.ok(proposals);
    }
    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody @Valid Proposal proposal, BindingResult result) {
        if (result.hasErrors()) {
            throw new PropuestaException(this.formatMessage(result));
        }
        try {
            proposalService.save(proposal);
            response.setSuccess(true);
            response.setMessage("Propuesta creada exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error interno del servidor: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @PutMapping("/tomar-decision")
    public ResponseEntity<ApiResponse> tomarDecision(@RequestParam("propuestaId") Long propuestaId, @RequestParam("estado") EstadoEnum estadoEnum) {
        try {
            proposalService.tomarDecision(propuestaId, estadoEnum);
            response.setSuccess(true);
            response.setMessage("La decisión se ha registrado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error interno del servidor: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        try {
            proposalService.delete(id);
            response.setSuccess(true);
            response.setMessage("Propuesta eliminada exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error interno del servidor: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
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
