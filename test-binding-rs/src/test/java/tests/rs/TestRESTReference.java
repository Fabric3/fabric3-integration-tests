package tests.rs;

import junit.framework.TestCase;

import org.fabric3.tests.rs.Message;
import org.fabric3.tests.rs.RestReferenceInterface;
import org.oasisopen.sca.annotation.Reference;

public class TestRESTReference extends TestCase {

	@Reference
	protected RestReferenceInterface jsonRef;

	public void testJsonReferenceBinding() {
		
		for (long i = 0; i < 10; i++) {
			Message msg = new Message();
			msg.setId(i);
			msg.setText("this is a json test "+i);

			jsonRef.putMsg(msg);

			Message result = jsonRef.retrieve(msg.getId().intValue());

			assertNotNull(result);
			assertEquals(msg.getText(), result.getText());
		}
		
	}

}
