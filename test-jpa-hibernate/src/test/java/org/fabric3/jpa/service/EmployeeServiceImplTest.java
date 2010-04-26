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
package org.fabric3.jpa.service;

import java.util.List;

import junit.framework.TestCase;
import org.oasisopen.sca.annotation.Reference;

import org.fabric3.jpa.model.Employee;
import org.fabric3.jpa.model.ExEmployee;

/**
 * @version $Rev$ $Date$
 */
public class EmployeeServiceImplTest extends TestCase {
    @Reference
    protected EmployeeService employeeService;

    @Reference
    protected EmployeeService employeeMultiThreadedService;

    @Reference
    protected EmployeeService employeeEMFService;

    @Reference
    protected ConversationEmployeeService conversationEmployeeService;

    @Reference
    EmployeeService employeeMultiThreadedSessionService;

    @Reference
    EmployeeService employeeSessionService;

    public void testCreateEmployee() {
        employeeService.createEmployee(123L, "Barney Rubble");
        Employee employee = employeeService.findEmployee(123L);
        assertNotNull(employee);
        assertEquals("Barney Rubble", employee.getName());
    }

    public void testCreateEMFEmployee() throws Exception {
        employeeEMFService.createEmployee(123L, "Barney Rubble");
        Employee employee = employeeEMFService.findEmployee(123L);
        assertNotNull(employee);
        assertEquals("Barney Rubble", employee.getName());
    }

    public void testCreateSessionEmployee() {
        employeeSessionService.createEmployee(123L, "Barney Rubble");
        Employee employee = employeeSessionService.findEmployee(123L);
        assertNotNull(employee);
        assertEquals("Barney Rubble", employee.getName());
    }

    public void testCreateMultiThreadedSessionEmployee() {
        employeeMultiThreadedSessionService.createEmployee(123L, "Barney Rubble");
        Employee employee = employeeMultiThreadedService.findEmployee(123L);
        assertNotNull(employee);
        assertEquals("Barney Rubble", employee.getName());
    }

    public void testCreateMultiThreadedEmployee() {
        employeeMultiThreadedService.createEmployee(123L, "Barney Rubble");
        Employee employee = employeeMultiThreadedService.findEmployee(123L);
        assertNotNull(employee);
        assertEquals("Barney Rubble", employee.getName());
    }

    public void testSearchWithName() {
        employeeMultiThreadedService.createEmployee(123L, "Barney");
        List<Employee> employees = employeeService.searchWithCriteria("Barney");
        assertNotNull(employees);
        assertEquals(1, employees.size());
    }

    public void testTwoPersistenceContexts() {
        employeeService.createEmployee(123L, "Barney Rubble");
        employeeService.fire(123L);
    }

    public void testExtendedPersistenceContext() {
        conversationEmployeeService.createEmployee(123L, "Barney Rubble");
        Employee employee = conversationEmployeeService.findEmployee(123L);

        assertNotNull(employee);
        assertEquals("Barney Rubble", employee.getName());
        // verify the object has not be detached
        employee.setName("Fred Flintstone");
        Employee employee2 = conversationEmployeeService.updateEmployee(employee);
        // the merge operation should use the same persistent entity since it is never detached for extended persistence contexts
        assertSame(employee, employee2);
        employee = conversationEmployeeService.findEmployee(123L);
        assertEquals("Fred Flintstone", employee.getName());
        // end the conversation, which should also close the EntityManager/persistence context
        conversationEmployeeService.end();
        employee2 = conversationEmployeeService.findEmployee(123L);
        // employee2 should be loaded in a different persistence context and not the same as the original
        assertNotSame(employee, employee2);
    }

    public void testTwoExtendedPersistenceContexts() {
        conversationEmployeeService.createEmployee(123L, "Barney Rubble");
        conversationEmployeeService.fire(123L);
        conversationEmployeeService.end();
    }


    protected void setUp() throws Exception {
        super.setUp();
        Employee employee = employeeService.findEmployee(123L);
        if (employee != null) {
            employeeService.removeEmployee(123L);
        }
        ExEmployee exEmployee = employeeService.findExEmployee(123L);
        if (exEmployee != null) {
            employeeService.removeExEmployee(123L);
        }
    }


}
