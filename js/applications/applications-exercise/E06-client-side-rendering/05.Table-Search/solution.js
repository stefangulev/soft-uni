import { html, render } 
	from '../node_modules/lit-html/lit-html.js';

   const tableTemplate = (students, match) => html`
   ${students.map(e=> e = tableRow(e, match))}
   `;

   const tableRow = (student, match) => html`
   <tr class=${checkMatch(student, match)} >
      <td>${student.firstName} ${student.lastName}</td>
      <td>${student.email}</td>
      <td>${student.course}</td>
   </tr>
   `
   ;

   let students;

   const body = document.querySelector('tbody');
   initialize();

   const button = document.getElementById('searchBtn').addEventListener('click', () => {
      const searchField = document.getElementById('searchField');
      update(students, searchField.value)
   })

   async function initialize() {
      const response = await fetch('http://localhost:3030/jsonstore/advanced/table');
      students = Object.values(await response.json());
      update(students);
   }
   function update(students, match = "") {
      const template =  tableTemplate(students, match);
      render(template, body);
   }

   function checkMatch(student, match) {
      if (match && (student.firstName.toLowerCase().includes(match.toLowerCase()) || student.lastName.toLowerCase().includes(match.toLowerCase()) || student.email.toLowerCase().includes(match.toLowerCase()) || student.course.toLowerCase().includes(match.toLowerCase()))) {
         return 'select';
      } else {
         return "";
      }
   }