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
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.oasisopen.sca.annotation.Scope;

import org.fabric3.jpa.model.Employee;
import org.fabric3.jpa.model.ExEmployee;

/**
 * Exercises multi-threaded EntityManager operation
 *
 * @version $Rev$ $Date$
 */
@Scope("COMPOSITE")
public class MultiThreadedEmployeeSessionServiceImpl implements EmployeeService {

    private Session employeeSession;
    private Session exEmployeeSession;

    @PersistenceContext(name = "employeeEmf", unitName = "employee")
    public void setEmployeeSession(Session session) {
        this.employeeSession = session;
    }


    @PersistenceContext(name = "exEmployeeEmf", unitName = "ex-employee")
    public void setExEmployeeSession(Session session) {
        this.exEmployeeSession = session;
    }

    public Employee createEmployee(Long id, String name) {
        Employee employee = new Employee(id, name);
        employeeSession.persist(employee);
        return employee;
    }

    public Employee findEmployee(Long id) {
        return (Employee) employeeSession.get(Employee.class, id);
    }

    public void removeEmployee(Long id) {
        Employee employee = (Employee) employeeSession.get(Employee.class, id);
        employeeSession.delete(employee);
    }

    public List<Employee> searchWithCriteria(String name) {
        return null;
    }

    public void fire(Long id) {
        Employee employee = (Employee) employeeSession.get(Employee.class, id);
        employeeSession.delete(employee);
        ExEmployee exEmployee = new ExEmployee(employee.getId(), employee.getName());
        exEmployeeSession.persist(exEmployee);
    }

    public ExEmployee findExEmployee(Long id) {
        return null;
    }

    public void removeExEmployee(Long id) {

    }

}