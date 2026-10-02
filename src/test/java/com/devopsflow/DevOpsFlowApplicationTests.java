package com.devopsflow;
import com.devopsflow.service.DeploymentService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class DevOpsFlowApplicationTests {
 @Test void initialHistory(){DeploymentService s=new DeploymentService();assertEquals(3,s.history().size());assertEquals("v1.0.3",s.currentVersion());}
 @Test void deployWorks(){var d=new DeploymentService().deploy("abc123","staging");assertEquals("SUCCESS",d.status());assertEquals("abc123",d.commit());}
 @Test void rollbackWorks(){var d=new DeploymentService().rollback();assertEquals("ROLLED_BACK",d.status());}
}