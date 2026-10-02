package com.devopsflow.controller;
import com.devopsflow.model.Deployment;
import com.devopsflow.service.DeploymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.*;
@RestController
@RequestMapping("/api")
@CrossOrigin
public class DevOpsController {
 private final DeploymentService s;
 public DevOpsController(DeploymentService s){this.s=s;}
 @GetMapping("/status") public Map<String,Object> status(){
  Map<String,Object> r=new LinkedHashMap<>();
  r.put("application","DevOpsFlow"); r.put("status","OPERATIONAL"); r.put("version",s.currentVersion());
  r.put("build","42"); r.put("docker","READY"); r.put("pipeline","GREEN"); r.put("updatedAt",Instant.now().toString()); return r;
 }
 @GetMapping("/health") public Map<String,Object> health(){return Map.of("status","UP","service","devopsflow-api","timestamp",Instant.now().toString());}
 @GetMapping("/pipeline") public List<Map<String,String>> pipeline(){return List.of(
  Map.of("name","Source Checkout","status","SUCCESS"),Map.of("name","Maven Build","status","SUCCESS"),
  Map.of("name","Automated Tests","status","SUCCESS"),Map.of("name","Docker Build","status","SUCCESS"),
  Map.of("name","Container Deploy","status","SUCCESS"),Map.of("name","Health Check","status","SUCCESS")); }
 @GetMapping("/deployments") public List<Deployment> deployments(){return s.history();}
 @PostMapping("/deploy") public ResponseEntity<Deployment> deploy(@RequestBody DeployRequest r){return ResponseEntity.ok(s.deploy(r.commit(),r.environment()));}
 @PostMapping("/rollback") public ResponseEntity<Deployment> rollback(){return ResponseEntity.ok(s.rollback());}
 public record DeployRequest(String commit,String environment){}
}