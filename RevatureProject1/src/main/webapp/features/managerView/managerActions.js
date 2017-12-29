/**
 * 
 */
//<script src="features/managerView/managerActions.js" type="text/javascript"></script>
window.onload = function(){
	console.log('Loading event listeners');
	document.getElementById('viewEmployees').addEventListener("click",listEmployees,false);
	document.getElementById('viewPending').addEventListener("click",listPending,false);
	document.getElementById('viewResolved').addEventListener("click",listResolved,false);
	document.getElementById('viewByEmployee').addEventListener("click",listByEmployee,false);
	document.getElementById('viewReceipts').addEventListener("click",seeReceipts,false);
	document.getElementById('resolveRequests').addEventListener("click",decideRequests,false);
}
function listEmployees(){
	console.log('Loading Manager Actions');
		
		var xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = function(){
			console.log('Received Top Page Fragment');
			if(xhr.readyState == 4 && xhr.status == 200){
					actions = JSON.parse(xhr.responseText);
					employeeSelect.style.display = "";
					for(var i = 0; i < actions.length;i++){
						employeeSelect.add(new Option(actions[i].firstName + " " + actions[i].lastName + " (" + actions[i].email + ")", actions[i].employeeId));
					}
					console.log("Employee List should be visible");
	
					document.getElementById("managerActions").innerText = actions;
				}
			
		}
		xhr.open("GET","viewEmployeesServlet",true);	
		xhr.send();
}
function listPending(){
	
}
function listResolved(){
	
}
function listByEmployee(){
	
}
function seeReceipts(){
	
}
function decideRequests(){
	
}