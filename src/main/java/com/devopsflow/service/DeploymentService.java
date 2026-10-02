package com.devopsflow.service;
import com.devopsflow.model.Deployment;
import org.springframework.stereotype.Service;
import java.time.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class DeploymentService {
 private final AtomicLong seq=new AtomicLong(4);
 private final List<Deployment> deployments=new ArrayList<>();
 public DeploymentService(){
  Instant n=Instant.now();
  deployments.add(new Deployment(1,"v1.0.1","a81f23d","SUCCESS","production",n.minus(Duration.ofMinutes(28)),38));
  deployments.add(new Deployment(2,"v1.0.2","b72cd1e","FAILED","production",n.minus(Duration.ofMinutes(16)),44));
  deployments.add(new Deployment(3,"v1.0.3","c91ae4f","SUCCESS","production",n.minus(Duration.ofMinutes(4)),42));
 }
 public synchronized List<Deployment> history(){return List.copyOf(deployments);}
 public synchronized Deployment deploy(String commit,String env){
  long id=seq.getAndIncrement();
  Deployment d=new Deployment(id,"v1.0."+id,commit==null||commit.isBlank()?"manual":commit,"SUCCESS",env==null||env.isBlank()?"production":env,Instant.now(),42);
  deployments.add(d); return d;
 }
 public synchronized Deployment rollback(){
  if(deployments.size()<2) throw new IllegalStateException("No previous deployment available");
  Deployment p=deployments.get(deployments.size()-2);
  Deployment d=new Deployment(seq.getAndIncrement(),p.version(),p.commit(),"ROLLED_BACK",p.environment(),Instant.now(),18);
  deployments.add(d); return d;
 }
 public synchronized String currentVersion(){return deployments.get(deployments.size()-1).version();}
}