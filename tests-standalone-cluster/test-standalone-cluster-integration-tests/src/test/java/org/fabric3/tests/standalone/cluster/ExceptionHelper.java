package org.fabric3.tests.standalone.cluster;

import org.fabric3.management.contribution.ArtifactErrorInfo;
import org.fabric3.management.contribution.ErrorInfo;
import org.fabric3.management.contribution.InvalidContributionException;

/**
 * @version $Rev$ $Date$
 */
public class ExceptionHelper {

    private ExceptionHelper() {

    }

    public static void handleValidationError(Exception e) {
        if (e instanceof InvalidContributionException) {
            InvalidContributionException ex = (InvalidContributionException) e;
            System.out.println("Contribution errors: ");
            for (ErrorInfo info : ex.getErrors()) {
                if (info instanceof ArtifactErrorInfo) {
                    ArtifactErrorInfo aei = (ArtifactErrorInfo) info;
                    for (ErrorInfo aInfo : aei.getErrors()) {
                        System.out.println("   " + aInfo.getError());
                    }
                } else {
                    System.out.println("   " + info.getError());
                }
            }
        }
    }

}
