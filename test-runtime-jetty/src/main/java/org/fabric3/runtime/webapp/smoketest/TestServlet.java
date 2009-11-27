/*
* Fabric3
* Copyright (c) 2009 Metaform Systems
*
* Fabric3 is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as
* published by the Free Software Foundation, either version 3 of
* the License, or (at your option) any later version, with the
* following exception:
*
* Linking this software statically or dynamically with other
* modules is making a combined work based on this software.
* Thus, the terms and conditions of the GNU General Public
* License cover the whole combination.
*
* As a special exception, the copyright holders of this software
* give you permission to link this software with independent
* modules to produce an executable, regardless of the license
* terms of these independent modules, and to copy and distribute
* the resulting executable under terms of your choice, provided
* that you also meet, for each linked independent module, the
* terms and conditions of the license of that module. An
* independent module is a module which is not derived from or
* based on this software. If you modify this software, you may
* extend this exception to your version of the software, but
* you are not obligated to do so. If you do not wish to do so,
* delete this exception statement from your version.
*
* Fabric3 is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty
* of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
* See the GNU General Public License for more details.
*
* You should have received a copy of the
* GNU General Public License along with Fabric3.
* If not, see <http://www.gnu.org/licenses/>.
*/
package org.fabric3.runtime.webapp.smoketest;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.oasisopen.sca.ComponentContext;

/**
 * @version $Rev$ $Date$
 */
public class TestServlet extends HttpServlet {
    private static final long serialVersionUID = 7698155043124726164L;

    private ServletContext servletContext;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        servletContext = config.getServletContext();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String testName = request.getParameter("test");
        if (testName == null || testName.length() == 0) {
            response.sendError(500, "No test specified");
            return;
        }
        // verify the component context was bound to the session
        HttpSession session = request.getSession();
        ComponentContext context = (ComponentContext) session.getAttribute("org.oasisopen.sca.ComponentContext");
        TestService test = context.getService(TestService.class, testName);
        if (test == null) {
            response.sendError(500, "Unknown test: " + testName);
            return;
        }
        // verify the reference was bound to the servlet context as it is non-conversational
        test = (TestService) servletContext.getAttribute(testName);
        if (test == null) {
            response.sendError(500, "Unknown test: " + testName);
            return;
        }

        test.service(request, response, servletContext);

        CounterService counter = context.getService(CounterService.class, "counter");
        counter.increment();
        if (counter.getCount() != 1) {
            response.sendError(500, "Counter expected to be 1");
            return;
        }
        counter.end();
        if (counter.getCount() != 0) {
            response.sendError(500, "Counter expected to be 0");
            return;
        }
        CounterService sessionCounter = (CounterService) request.getSession().getAttribute("counter");
        if (sessionCounter.getCount() != 0) {
            response.sendError(500, "Session counter expected to be 0");
            return;
        }

    }
}
