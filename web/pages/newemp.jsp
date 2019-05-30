<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="newEmpForm" action="addemp" method="post">
    <label for="empno">Идентификатор работника</label>
    <input id="empno" type="number" name="empno" required/>
    <br/>
    <label for="ename">Имя работника</label>
    <input id="ename" type="text" name="ename" required/>
    <br/>
    <label for="job">Должность</label>
    <input id="job" type="text" name="job" required/>
    <br/>
    <label for="mgr">Менеджер</label>
    <input id="mgr" type="number" name="mgr"/>
    <br/>
    <label for="hiredate">Дата приема на работу</label>
    <input id="hiredate" type="date" name="hiredate"/>
    <br/>
    <label for="sal">Зарплата</label>
    <input id="sal" type="number" name="sal" step="0.01" min="0" required/>
    <br/>
    <label for="comm">Комиссионные</label>
    <input id="comm" type="number" name="comm" step="0.01" min="0"/>
    <br/>
    <label for="deptno">Номер подразделения</label>
    <select name="deptno" id="deptno" name="deptno">
        <option value="10">10</option>
        <option value="20">20</option>
        <option value="30">30</option>
    </select>
    <br/>
    <br/>
    <input type="submit" value="Добавить">
</form>
