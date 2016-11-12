package test2;

import javax.jws.WebService;

@WebService(endpointInterface = "test2",
serviceName = "HelloWorld")
public class SecurityBean implements ISecurityBean{

	public String sayHi(String text) {
        return "Hello " + text;
    }
	
}
