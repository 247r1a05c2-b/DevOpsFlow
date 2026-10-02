package com.devopsflow.model;
import java.time.Instant;
public record Deployment(long id,String version,String commit,String status,String environment,Instant deployedAt,long durationSeconds){}