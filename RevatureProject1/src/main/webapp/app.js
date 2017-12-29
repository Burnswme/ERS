window.onload = function(){
	loadLoginScreen();
	loadTopFile();
	
}
var loginForm = null;
var view = null;
var actions = null;
var loggedIn = null;
var username = "";
var password = "";
var firstname = "";
var lastname = "";
var employeeID = "";

//This function loads the login screen
function loadLoginScreen(){
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
				loginForm = xhr.responseText;
				document.getElementById("login").innerHTML = loginForm;
				/*
				 * Create an EventListener for the submit button
				 */
				document.getElementById('loginButton').addEventListener("click",login,false);
			}
		
	}
	xhr.open("GET","loadLoginFragment",true);	//call the Login Servlet to load the login fragment
	xhr.send(); //only POST requests need parameters here
}
//This function loads the html fragment containing the top of the page
function loadTopFile(){
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
				loginForm = xhr.responseText;
				document.getElementById("top").innerHTML = loginForm;
			}
		
	}
	xhr.open("GET","LoadTopFragment",true);	
	xhr.send(); 
}
//This function logs in the user
function login(user){
	username = document.getElementById("u_name").value;
	password = document.getElementById("p_word").value;
	var employee = {username:username,password:password}
	var json = JSON.stringify(employee);
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
				loggedIn = xhr.responseText;
				loadLogoutFragment();
				loadLoggedInIndicator();
				loadUserAcknowledgement();
				document.getElementById("view").innerHTML = loggedIn;
			}
		
	}
	xhr.open("POST","LoginServlet",true);
	xhr.setRequestHeader('key',json);
	xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
	xhr.send(json); 
}
//This function loads the logout button
function loadLogoutFragment(){
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){

		if(xhr.readyState == 4 && xhr.status == 200){
				loginForm = xhr.responseText;
				document.getElementById("login").innerHTML = loginForm;
		}
		
	}
	xhr.open("GET","LoadLogoutFragmentServlet",true);	//call the Login Servlet to load the login fragment
	xhr.send(); //only POST requests need parameters here
}
//This function logs out the current user
function logout(){
	window.location.href = "http://localhost:7001/RevatureProject1/encomApp.html";
}
//This function loads the html fragment that indicates the user is logged in
function loadLoggedInIndicator(){
		
		var xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = function(){

			if(xhr.readyState == 4 && xhr.status == 200){
					loginForm = xhr.responseText;
					document.getElementById("top").innerHTML = loginForm;
				}
			
		}
		xhr.open("GET","LoadLoggedInIndicatorServlet",true);	
		xhr.send();
}
//This function loads the table to hold all employee information
function loadViewEmployeesTable(){
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){

		if(xhr.readyState == 4 && xhr.status == 200){
				actions = xhr.responseText;

				document.getElementById("managerActions").innerHTML = actions;
				viewEmployees();
			}
		
	}
	xhr.open("GET","loadViewEmployeePanel",true);	
	xhr.send();
}
//This function loads the profile information of all employees into a table
function viewEmployees(){
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
				actions = JSON.parse(xhr.responseText);
				
				for(var i = 0; i<actions.length;i++){
					
					var row =document.createElement("TR");
					
					var empId =document.createElement("TD");
					empId.innerText=actions[i].employeeID;
					
					var firstName =document.createElement("TD");
					firstName.innerText=actions[i].firstName;
					
					var lastName =document.createElement("TD");
					lastName.innerText=actions[i].lastName;
					
					var username =document.createElement("TD");
					username.innerText=actions[i].username;
					
					var email =document.createElement("TD");
					email.innerText=actions[i].email;
	
					row.appendChild(empId);
					row.appendChild(firstName);
					row.appendChild(lastName);
					row.appendChild(username);
					row.appendChild(email);
						
					document.getElementById("currentEmployeesView").appendChild(row);
				}
			}
		
	}
	xhr.open("GET","viewEmployeesServlet",true);	
	xhr.send();
}
//This function loads the html fragment with the view pending requests table
function loadAllPendingRequests(){
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){

		if(xhr.readyState == 4 && xhr.status == 200){
				actions = xhr.responseText;

				document.getElementById("managerActions").innerHTML = actions;
				viewPendingRequests();
			}
		
	}
	xhr.open("GET","loadAllPending",true);	
	xhr.send();
}
//This function loads all pending requests by employees into a table
function viewPendingRequests(){

	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){

		if(xhr.readyState == 4 && xhr.status == 200){

				actions = JSON.parse(xhr.responseText);
				
				for(var i = 0;i<actions.length;i++){
					var row = document.createElement("TR");
					
					var empId =document.createElement("TD");
					empId.innerText=actions[i].employeeID;
					row.appendChild(empId);
					
					var rbId =document.createElement("TD");
					rbId.innerText=actions[i].reimbursementID;
					row.appendChild(rbId);
					
					var status =document.createElement("TD");
					status.innerText=actions[i].status;
					row.appendChild(status)
					
					var amount =document.createElement("TD");
					amount.innerText=actions[i].amount;
					row.appendChild(amount);
					
					var receipt =document.createElement("TD");
					var img = document.createElement("IMG");

					if(actions[i].receipt != null){
							img.src=actions[i].receipt;
							img.style.width='250px';
							img.style.height='250px';
							receipt.appendChild(img);
						}
					else{
						receipt.innerText =("n/a");
					}
					row.appendChild(receipt);
					
					var managerID =document.createElement("TD");
					managerID.innerText=actions[i].managerID;
					row.appendChild(managerID);
					
					var timeRequested =document.createElement("TD");
					timeRequested.innerText=actions[i].timeRequested;
					row.appendChild(timeRequested);
					
					var timeResolved =document.createElement("TD");
					timeResolved.innerText=actions[i].timeResolved;
					row.appendChild(timeResolved);
					
					var description =document.createElement("TD");
					description.innerText=actions[i].description;
					row.appendChild(description);
					
					var type =document.createElement("TD");
					type.innerText=actions[i].type;
					row.appendChild(type);
					
					document.getElementById("allPendingView").appendChild(row);
			}
		}
		
	}
	xhr.open("GET","viewPending",true);	
	xhr.send();
}
//This function loads the html fragment with the table for holding all the resolved requests
function loadResolvedRequests(){
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){

		if(xhr.readyState == 4 && xhr.status == 200){
				actions = xhr.responseText;
				document.getElementById("managerActions").innerHTML = actions;
				viewResolvedRequests();
			}
		
	}
	xhr.open("GET","loadAllResolved",true);	
	xhr.send();
}
//This function loads all resolved requests by all employees into a table
function viewResolvedRequests(){
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){

		if(xhr.readyState == 4 && xhr.status == 200){

				actions = JSON.parse(xhr.responseText);
				
				for(var i = 0;i<actions.length;i++){
					var row = document.createElement("TR");
					
					var empId =document.createElement("TD");
					empId.innerText=actions[i].employeeID;
					row.appendChild(empId);
					
					var rbId =document.createElement("TD");
					rbId.innerText=actions[i].reimbursementID;
					row.appendChild(rbId);
					
					var status =document.createElement("TD");
					status.innerText=actions[i].status;
					row.appendChild(status)
					
					var amount =document.createElement("TD");
					amount.innerText=actions[i].amount;
					row.appendChild(amount);
					
					var receipt =document.createElement("TD");
					var img = document.createElement("IMG");

					if(actions[i].receipt != null){
							img.src=actions[i].receipt;
							img.style.width='250px';
							img.style.height='250px';
							receipt.appendChild(img);
						}
					else{
						receipt.innerText =("n/a");
					}
					row.appendChild(receipt);
					
					var managerID =document.createElement("TD");
					managerID.innerText=actions[i].managerID;
					row.appendChild(managerID);
					
					var timeRequested =document.createElement("TD");
					timeRequested.innerText=actions[i].timeRequested;
					row.appendChild(timeRequested);
					
					var timeResolved =document.createElement("TD");
					timeResolved.innerText=actions[i].timeResolved;
					row.appendChild(timeResolved);
					
					var description =document.createElement("TD");
					description.innerText=actions[i].description;
					row.appendChild(description);
					
					var type =document.createElement("TD");
					type.innerText=actions[i].type;
					row.appendChild(type);
					
					document.getElementById("allResolvedView").appendChild(row);
			}
		}
		
	}
	xhr.open("GET","resolvedRequests",true);	
	xhr.send();
	
}
//This function loads the table for displaying the user's information
function loadProfileTable(){
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){

		if(xhr.readyState == 4 && xhr.status == 200){
				actions = xhr.responseText;

				document.getElementById("employeeActions").innerHTML = actions;
				viewProfile();
			}
		
	}
	xhr.open("GET","loadProfileTable",true);	
	xhr.send();
}
//This function loads the user's information from the back end and displays it in a table
function viewProfile(){
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
				actions = JSON.parse(xhr.responseText);

				var row = document.createElement("TR");
				var empId =document.createElement("TD");
				empId.innerText=actions.employeeID;
				var firstName =document.createElement("TD");
				firstName.innerText=actions.firstName;
				var lastName =document.createElement("TD");
				lastName.innerText=actions.lastName;
				var username =document.createElement("TD");
				username.innerText=actions.username;
				var email =document.createElement("TD");
				email.innerText=actions.email;

				row.appendChild(empId);
				row.appendChild(firstName);
				row.appendChild(lastName);
				row.appendChild(username);
				row.appendChild(email);
					
				document.getElementById("profileView").appendChild(row);
			}
		
	}
	xhr.open("GET","viewProfile",true);	
	xhr.send();
}
//This function loads the html fragment with the table for holding pending requests
function loadPendingTable(){
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){

		if(xhr.readyState == 4 && xhr.status == 200){
				actions = xhr.responseText;

				document.getElementById("employeeActions").innerHTML = actions;
				viewPendingByEmployee();
			}
		
	}
	xhr.open("GET","loadResolvedEmployeePanel",true);	
	xhr.send();
}
//This function retrieves pending requests by a single employee and loads them into the pending table
function viewPendingByEmployee(){
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
				actions = JSON.parse(xhr.responseText);
				for(var i = 0;i<actions.length;i++){
					var row = document.createElement("TR");
					
					var empId =document.createElement("TD");
					empId.innerText=actions[i].employeeID;
					row.appendChild(empId);
					
					var rbId =document.createElement("TD");
					rbId.innerText=actions[i].reimbursementID;
					row.appendChild(rbId);
					
					var status =document.createElement("TD");
					status.innerText=actions[i].status;
					row.appendChild(status)
					
					var amount =document.createElement("TD");
					amount.innerText=actions[i].amount;
					row.appendChild(amount);
					
					var receipt =document.createElement("TD");
					var img = document.createElement("IMG");

					if(actions[i].receipt != null){
							img.src=actions[i].receipt;
							img.style.width='250px';
							img.style.height='250px';
							receipt.appendChild(img);
						}
					else{
						receipt.innerText =("n/a");
					}
					row.appendChild(receipt);
					
					var managerID =document.createElement("TD");
					managerID.innerText=actions[i].managerID;
					row.appendChild(managerID);
					
					var timeRequested =document.createElement("TD");
					timeRequested.innerText=actions[i].timeRequested;
					row.appendChild(timeRequested);
					
					var timeResolved =document.createElement("TD");
					timeResolved.innerText=actions[i].timeResolved;
					row.appendChild(timeResolved);
					
					var description =document.createElement("TD");
					description.innerText=actions[i].description;
					row.appendChild(description);
					
					var type =document.createElement("TD");
					type.innerText=actions[i].type;
					row.appendChild(type);
					
					document.getElementById("resolvedView").appendChild(row);
				}
			}
		
	}
	xhr.open("GET","pendingByEmployee",true);	
	xhr.send();
}
//This function retrieves the html fragment with the table for resolved requests by a single employee
function loadResolvedTable(){
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){

		if(xhr.readyState == 4 && xhr.status == 200){
				actions = xhr.responseText;

				document.getElementById("employeeActions").innerHTML = actions;
				viewResolvedByEmployee();
			}
		
	}
	xhr.open("GET","loadResolvedEmployeePanel",true);	
	xhr.send();
}
//This function loads the resolved reimbursement requests by a particular employee into a table
function viewResolvedByEmployee(){
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
				actions = JSON.parse(xhr.responseText);
				for(var i = 0;i<actions.length;i++){
					var row = document.createElement("TR");
					
					var empId =document.createElement("TD");
					empId.innerText=actions[i].employeeID;
					row.appendChild(empId);
					
					var rbId =document.createElement("TD");
					rbId.innerText=actions[i].reimbursementID;
					row.appendChild(rbId);
					
					var status =document.createElement("TD");
					status.innerText=actions[i].status;
					row.appendChild(status)
					
					var amount =document.createElement("TD");
					amount.innerText=actions[i].amount;
					row.appendChild(amount);
					
					var receipt =document.createElement("TD");
					var img = document.createElement("IMG");

					if(actions[i].receipt != null){
							img.src=actions[i].receipt;
							img.style.width='250px';
							img.style.height='250px';
							receipt.appendChild(img);
						}
					else{
						receipt.innerText =("n/a");
					}
					row.appendChild(receipt);
					
					var managerID =document.createElement("TD");
					managerID.innerText=actions[i].managerID;
					row.appendChild(managerID);
					
					var timeRequested =document.createElement("TD");
					timeRequested.innerText=actions[i].timeRequested;
					row.appendChild(timeRequested);
					
					var timeResolved =document.createElement("TD");
					timeResolved.innerText=actions[i].timeResolved;
					row.appendChild(timeResolved);
					
					var description =document.createElement("TD");
					description.innerText=actions[i].description;
					row.appendChild(description);
					
					var type =document.createElement("TD");
					type.innerText=actions[i].type;
					row.appendChild(type);
					
					document.getElementById("resolvedView").appendChild(row);
					
				}
					
			}
		
	}
	xhr.open("GET","viewEmployeeResolved",true);	
	xhr.send();
}
//This function loads the html fragment for the employee reimbursement request form
function loadReimbursementForm(){
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
				actions = xhr.responseText;
				document.getElementById("employeeActions").innerHTML = actions;
			}
		
	}
	xhr.open("GET","loadReimbursementForm",true);	
	xhr.send();
}
//This function takes an image from the employee and sends it to the back end
function sendReimbursementRequest(){
		
	var image = document.getElementById("receiptFile").files[0];

	if(image){
		let reader = new FileReader();
		reader.readAsDataURL(image);
		reader.onload = function(){
			let rbReceipt = reader.result;
			dispatchRequest(rbReceipt);
		}
	}
	else{
		dispatchRequest(null);
	}

}
//This function takes the employee's request and passes it to the back end
function dispatchRequest(receipt){

	var xhr = new XMLHttpRequest();
	var amount = document.getElementById("amountBtn").value;
	var description = document.getElementById("descriptionBtn").value;
	var code = document.getElementById("codeBtn").value;
	
	var request = {amount:amount,description:description,reimbursement_code:code,receipt:receipt};
	var json = JSON.stringify(request);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
				actions = xhr.responseText;
				document.getElementById("employeeActions").innerHTML = actions;
			
			}
		
	}
	xhr.open("POST","createReimbursement",true);
	xhr.setRequestHeader('key',json);
	xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
	xhr.send(json);
}
//This function loads an acknowledgement that the employee's request has been processed
function loadProcessedFragment(){
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
				actions = xhr.responseText;
				document.getElementById("employeeActions").innerHTML = actions;
			}
		
	}
	xhr.open("GET","loadProcessed",true);	
	xhr.send();
}
//This function loads the user's name from the database and displays it on the screen
function loadUserAcknowledgement(){
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
				var response = xhr.responseText;
				var modified = response.substring(1,response.length-1);
				document.getElementById("userSpace").innerText = modified;
			}
		
	}
	xhr.open("GET","getEmployeeName",true);	
	xhr.send();
}
//This function takes the employeeID entered by the manager and passes it to the back end
//Then calls the viewByEmployee() function
function loadViewByEmployee(){
	var xhr = new XMLHttpRequest();
	employeeID = document.getElementById("employeeIdField");
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
				var response = xhr.responseText;
				document.getElementById("employeeRequestTable").innerHTML = response;
				viewByEmployee();
				}
		
	}
	xhr.open("GET","loadBySingleEmployee",true);	
	xhr.send();
}
//This function loads the requests made by a particular employee to a table in the manager section
function viewByEmployee(){
	var xhr = new XMLHttpRequest();
	var json = JSON.stringify(username);
	

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			empID = document.getElementById("employeeIdField").value;
				actions = JSON.parse(xhr.responseText);
				for(var i = 0;i<actions.length;i++){
					if(actions[i].employeeID == empID){
						var row = document.createElement("TR");
						
						var empId =document.createElement("TD");
						empId.innerText=actions[i].employeeID;
						row.appendChild(empId);
						
						var rbId =document.createElement("TD");
						rbId.innerText=actions[i].reimbursementID;
						row.appendChild(rbId);
						
						var status =document.createElement("TD");
						status.innerText=actions[i].status;
						row.appendChild(status)
						
						var amount =document.createElement("TD");
						amount.innerText=actions[i].amount;
						row.appendChild(amount);
						
						var receipt =document.createElement("TD");
						var img = document.createElement("IMG");
	
						if(actions[i].receipt != null){
								img.src=actions[i].receipt;
								img.style.width='250px';
								img.style.height='250px';
								receipt.appendChild(img);
							}
						else{
							receipt.innerText =("n/a");
						}
						row.appendChild(receipt);
						
						var managerID =document.createElement("TD");
						managerID.innerText=actions[i].managerID;
						row.appendChild(managerID);
						
						var timeRequested =document.createElement("TD");
						timeRequested.innerText=actions[i].timeRequested;
						row.appendChild(timeRequested);
						
						var timeResolved =document.createElement("TD");
						timeResolved.innerText=actions[i].timeResolved;
						row.appendChild(timeResolved);
						
						var description =document.createElement("TD");
						description.innerText=actions[i].description;
						row.appendChild(description);
						
						var type =document.createElement("TD");
						type.innerText=actions[i].type;
						row.appendChild(type);
						
						document.getElementById("singleEmployeeView").appendChild(row);
					}
				}
			}
		
	}
	xhr.open("POST","viewRequestsByEmployee",true);	
	xhr.setRequestHeader('key',json);
	xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
	xhr.send(json); 
}
//This function loads the html fragment for the manager to enter which employee's requests 
//he wishes to view
function retrieveRequestsByEmployee(){
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
				var response = xhr.responseText;
				document.getElementById("managerActions").innerHTML = response;
				}
		
	}
	xhr.open("GET","loadRequestsByEmployee",true);	
	xhr.send();
}
//This function loads the html fragment with the update employee information form
function loadUpdateProfile(){
var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
				var response = xhr.responseText;
				document.getElementById("employeeActions").innerHTML = response;
				}
		
	}
	xhr.open("GET","loadUpdateForm",true);	
	xhr.send();
}
//This function takes the updated information from the client and passes it to the back end
function sendUpdate(){
	
	var id = document.getElementById("idField").value;
	var firstname = document.getElementById("firstNameField").value;
	var lastname = document.getElementById("lastNameField").value;
	username = document.getElementById("usernameField").value;
	password = document.getElementById("passwordField").value;
	var email = document.getElementById("emailField").value;

	var employee = {employeeID: id,firstName:firstname,lastName: lastname,username : username,password:password,email:email}
	var json = JSON.stringify(employee);
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
				var response = xhr.responseText;
				document.getElementById("employeeActions").innerHTML = response;
			}
		
	}
	xhr.open("POST","updateEmployeeInformation",true);
	xhr.setRequestHeader('key',json);
	xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
	xhr.send(json); 
}
//This function loads the panel where the manager can resolve reimbursement requests
function loadResolutionPanel(){
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
				var response = xhr.responseText;
				document.getElementById("managerActions").innerHTML = response;
				}
		
	}
	xhr.open("GET","loadResolutionPanel",true);	
	xhr.send();
}

//This function takes the values entered by the manager and passes them to the back end
function resolveRequest(){
	var requestId = document.getElementById("requestIdForm").value;
	var resolutionCode = document.getElementById("codeForm").value;

	var resolution = {reimbursementID:requestId,status:resolutionCode}
	var json = JSON.stringify(resolution);
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
				var response = xhr.responseText;
				document.getElementById("managerActions").innerHTML = response;
			}
		
	}
	xhr.open("POST","resolveRequestsServlet",true);
	xhr.setRequestHeader('key',json);
	xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
	xhr.send(json); 
}

