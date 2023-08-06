<html>

<head>
<title>Instructor Page</title>
</head>
  <style>
      @import url(https://fonts.googleapis.com/css?family=Roboto:300);
     body, h1, h2, p, ul, li, button {
         margin: 0;
         padding: 0;
         box-sizing: border-box;
     }

     body {
         font-family: Arial, sans-serif;
         background-color: #f0f0f0;
     }

     .header {
         background-color: #007BFF;
         color: #fff;
         padding: 10px;
         text-align: center;
     }

     .header h1 {
         font-size: 24px;
         margin: 0;
     }

     .logout-btn {
         color: #fff;
         text-decoration: none;
         font-size: 16px;
         margin-right: 20px;
         margin-top: 20px;
         float: right;
     }

     .info-container {
         background-color: #fff;
         padding: 20px;
         margin: 20px;
         border-radius: 5px;
         box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
     }

     .options {
         list-style: none;
         padding: 0;
         margin: 20px;
     }

     .option-btn {
         background-color: #007BFF;
         color: #fff;
         border: none;
         padding: 10px 20px;
         border-radius: 5px;
         cursor: pointer;
         margin-bottom: 10px;
     }

     .option-btn:hover {
         background-color: #0056b3;
     }

     .result-container {
         background-color: #fff;
         padding: 20px;
         margin: 20px;
         border-radius: 5px;
         box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
     }

     .result-container p {
         margin-bottom: 10px;
     }

     .result-container table {
         border-collapse: collapse;
         width: 100%;
     }

     .result-container th, .result-container td {
         border: 1px solid #ddd;
         padding: 8px;
         text-align: left;
     }

     .result-container th {
         background-color: #f2f2f2;
     }

     .result-container tr:nth-child(even) {
         background-color: #f2f2f2;
     }

     .result-container tr:hover {
         background-color: #ddd;
     }

        .logout-btn {
            background-color: #dc3545;
            color: #fff;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            position: absolute;
            top: 10px;
            right: 10px;
        }

        .logout-btn:hover {
            background-color: #555;
        }
      body {
        background: #76b852; /* fallback for old browsers */
        background: rgb(141,194,111);
        background: linear-gradient(90deg, rgba(141,194,111,1) 0%, rgba(118,184,82,1) 50%);
        font-family: "Roboto", sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
      }
    </style>
 <div class="info-container">
     <p><strong>Instructor ID: <span id="instructorId"></span></strong></p>
     <p><strong>Name: <span id="instructorName"></span></strong></p>
 </div>
 <div class="container">
     <h2>Select an option:</h2>
     <ul class="options">
         <li><a href="#" class="option-btn" onclick="showContent('courseInfoContainer')">See My Courses Information</a></li>
         <li><a href="#" class="option-btn" onclick="showContent('gradesAndStudentsContainer')">See Grades and My Students Information</a></li>
         <!-- Add other options as needed -->
     </ul>
     <a href="logout" class="logout-btn">Log Out</a>
     <div id="courseInfoContainer" class="result-container" style="display: none;">
         <!-- Show course information here -->
         <h2>Course Information</h2>
         <div id="courseList"></div>
     </div>
     <div id="gradesAndStudentsContainer" class="result-container" style="display: block;">
         <!-- Show grades and students information here -->
         <h3>Select a Course:</h3>
         <select name="selectedCourseId" id="courseSelect"></select>
         <button type="button" onclick="showCourseInfo()">Show Course Grades and Students Information</button>

         <h3>Selected Course Information:</h3>
         <p><strong>Course ID:</strong> <span id="selectedCourseId"></span></p>
         <p><strong>Course Name:</strong> <span id="selectedCourseName"></span></p>
         <h3>Students and Remarks:</h3>
         <table>
             <tr>
                 <th>Student ID</th>
                 <th>Student Name</th>
                 <th>Remarks 1</th>
                 <th>Remarks 2</th>
                 <th>Final Grade</th>
                 <th>Total Grade</th>
                 <th>Actions</th>
             </tr>
             <tbody id="studentsTableBody"></tbody>
         </table>
     </div>
 </div>
 <script>
     function showContent(contentId) {
         const allContainers = document.getElementsByClassName('result-container');
         for (let i = 0; i < allContainers.length; i++) {
             allContainers[i].style.display = 'none';
         }
         const containerToShow = document.getElementById(contentId);
         containerToShow.style.display = 'block';
     }

     function showCourseInfo() {
         const selectedCourseId = document.getElementById('courseSelect').value;
         const selectedCourseName = document.getElementById('courseSelect').options[document.getElementById('courseSelect').selectedIndex].text;
         document.getElementById('selectedCourseId').innerText = selectedCourseId;
         document.getElementById('selectedCourseName').innerText = selectedCourseName;

         // Code to fetch course information and students' data from the server using AJAX
         // and update the HTML elements with the fetched data.

         // Use fetch or XMLHttpRequest to send a request to the server and handle the response.
         // For each student data received from the server, create a row in the table and display it.

         // Example:
         // const url = `/instructor/course/${selectedCourseId}`;
         // fetch(url)
         //     .then(response => response.json())
         //     .then(data => {
         //         // Process the received data and update the HTML elements accordingly
         //         // For each student in data.studentsList, create a row in the table and display the data
         //     })
         //     .catch(error => {
         //         console.error('Error:', error);
         //         alert('An error occurred while fetching data.');
         //     });
     }

     async function saveRemarks(studentId) {
         // Code to extract student data from the HTML elements
         // and send the data to the server using AJAX for saving.

         // Use fetch or XMLHttpRequest to send a POST request to the server with the student data.

         // Example:
         // const data = {
         //     selectedStudentId: studentId,
         //     midExam: document.getElementById('midExam_' + studentId).value,
         //     quizzes: document.getElementById('quizzes_' + studentId).value,
         //     firstExam: document.getElementById('firstExam_' + studentId).value,
         //     secondExam: document.getElementById('secondExam_' + studentId).value,
         //     finalExam: document.getElementById('finalExam_' + studentId).value,
         //     totalGrade: document.getElementById('totalGrade_' + studentId).value,
         // };

         // const url = '/instructor/saveRemarks';
         // fetch(url, {
         //     method: 'POST',
         //     headers: {
         //         'Content-Type': 'application/json',
         //     },
         //     body: JSON.stringify(data),
         // })
         // .then(response => response.json())
         // .then(result => {
         //     if (result.success) {
         //         alert('Remarks saved successfully!');
         //     } else {
         //         alert('Error while saving remarks.');
         //     }
         // })
         // .catch(error => {
         //     console.error('Error:', error);
         //     alert('An error occurred while processing the request.');
         // });
     }
 </script>
</body>
</html>