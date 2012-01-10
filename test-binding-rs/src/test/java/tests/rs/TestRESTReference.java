package tests.rs;

import junit.framework.TestCase;

import org.fabric3.tests.rs.Message;
import org.fabric3.tests.rs.RestReferenceInterface;
import org.oasisopen.sca.annotation.Reference;

public class TestRESTReference extends TestCase {

	@Reference
	private RestReferenceInterface xmlRef;

	@Reference
	private RestReferenceInterface jsonRef;

	public void testReferenceBinding() {

		for (long i = 0; i < 10; i++) {
			Message msg = new Message();
			msg.setId(i);
			msg.setText("this is a test");

			xmlRef.putMsg(msg);

			Message result = xmlRef.retrieve(msg.getId().intValue());

			assertNotNull(result);
			assertEquals(msg.getText(), result.getText());
		}

	}

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
