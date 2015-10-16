package f3;

import javax.xml.namespace.QName;
import java.util.function.Supplier;

import org.fabric3.api.Namespaces;
import org.fabric3.api.annotation.model.Provides;
import org.fabric3.api.model.type.builder.ApplicationResourceBuilder;
import org.fabric3.api.model.type.builder.CompositeBuilder;
import org.fabric3.api.model.type.component.Composite;

/**
 *
 */
public class ResourceTestProvider {

    @Provides
    public static Composite testComposite() {
        QName name = new QName(Namespaces.F3, "ResourceComposite");
        CompositeBuilder builder = CompositeBuilder.newBuilder(name);
        builder.resource(ApplicationResourceBuilder.newBuilder("AppResource", Object::new).build()).deployable();
        return builder.build();
    }

}
