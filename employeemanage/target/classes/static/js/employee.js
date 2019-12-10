(function() {
	let temp = undefined;
	let empid = undefined;
	
//******************************* data table ******************************
	var table = $("#dbexample").DataTable({
		"processing": true,
		"serverSide": true,
		"destroy": true,
		"lengthMenu": [[5,7,10], [5,7,10]],
		"displayLength": 5,
		"order": [[0, "asc"]],
		"StateSave": false,
		"searchable": true,
		"ajax": {
			"url": "/employee/emp/getdata",
			"data": function(d) {
				d.extraMsg = "Hello Message";
			},
			"dataSrc": "empList"
		},
		columns: [
            { data: "0","visible":false
            },
            { data: "1" },
            { data: "2" },
            { data: "3" },
            { data: "4" },
            { data: null}
        ],
        "columnDefs": [ 
        	{
            	targets : 5,
            	 "className" : "text-center",
            	 "orderable": false,
            		render: function(data, type, full) {
            			var btn = `<a href="#" class="edit-emp" empid=${full[0]}>Edit<i class="fa fa-pencil"></i></a>/
									<a href="#" class="delete-emp" empid=${full[0]}>Delete<i class="fa fa-pencil"></i></a>`;
            				return btn
            			}
        	}
        	]
	});
	   
	//$("#add-div").show();
	$(document).on('change','#selectct',function(){
		let selectct = $("#selectct").val();
		
		if(selectct == '1')
		{
			$("#add-div").show();
			$("#edit-div").hide();
		}
		else if(selectct == '2')
		{
			$("#add-div").hide();
			$("#edit-div").show();
			
		}
		else if(selectct == '3')
		{
			$("#add-div").hide();
			$("#edit-div").show();
		}
		else if(selectct == '4')
		{
			$("#add-div").hide();
			$("#edit-div").show();
			$("#save-div").hide();
			$("#update-div").hide();
			$("#delete-div").hide();
			$("#uvmsg").text("for View Record");
		}
	});
	
	const validFun = () => {
		let validationStatus = true;
		let jsonObj = {};
		//var regEx = new RegExp("/[0-9]/");

		let name = $("#name").val();
		let city = $("#city").val();
		let address = $("#address").val();
		let mobile = $("#mobile").val();
		
		if(name == "")
		{
			$("#name")[0].setCustomValidity("Enter Name");
			$("#name")[0].reportValidity();
			validationStatus = false;
			return false;
		}
		else
		{	
			jsonObj.empname = name;
		}
		if(city == "")
		{
			$("#city")[0].setCustomValidity("Enter City");
			$("#city")[0].reportValidity();
			validationStatus = false;
			return false;
		}
		else
		{	
			jsonObj.city = city;
		}
		if(address == "")
		{
			$("#address")[0].setCustomValidity("Enter Address");
			$("#address")[0].reportValidity();
			validationStatus = false;
			return false;
		}
		else
		{	
			jsonObj.address = address;
		}
		if(mobile == "")
		{
			$("#mobile")[0].setCustomValidity("Enter Mobile");
			$("#mobile")[0].reportValidity();
			validationStatus = false;
			return false;
		}
		else
		{	
			if(mobile >= 11 && mobile <= 9)
			{
				$("#mobile")[0].setCustomValidity("Enter Valid Mobile no.");
				$("#mobile")[0].reportValidity();
				validationStatus = false;
				return false;
			}
			else
			{
				jsonObj.mobile = mobile;
			}
		}
		
		if(empid)
		{
			jsonObj.empid = empid;
		}
		
		return jsonObj;
	};
	
	const saveFun = () => 
	  {
		const validateForm = validFun();
		
		if (validateForm) {
			
			$.ajax({
				method: "post",
				url: "/employee/emp/save",
				data: JSON.stringify(validateForm),
				contentType: "application/Json",
				success: function(data) {
					alert("saved successfully");
					table.ajax.reload(null,false);
					//window.location.href = "/employee/emp/home";
					$("#add-div").hide();
					
				}
			});
		}
	}	  
	$(document).on('click','#save',saveFun);
	
	/*$("#edit").click(() => {
		sid = $("sid").val();
		
		let editObj = {};
		
		$.ajax({
			method: "post",
			url: "/employee/emp/edit",
			data: JSON.stringify(editObj),
			contentType: "application/json",
			success: function(data) {
				let editdata = data.editemployee;
				console.log(data);
				window.location.href = "/employee/emp/home/modify/"+sid;
				
			}
		});
	});*/
	
	$(document).on('click','.edit-emp',function(){
		empid = $(this).attr("empid");
		let editObj = {};
		if(sid != null && sid != undefined && sid != "")
		{
			$.ajax({
				method: "post",
				url: "/employee/emp/edit/"+empid,
				//data: JSON.stringify(editObj),
				contentType: "application/json",
				success: function(data) {
					let editdata = data.employeeData;
					console.log(data);
					$("#name").val(editdata.empname);
					$("#city").val(editdata.city);
					$("#address").val(editdata.address);
					$("#mobile").val(editdata.mobile);
					$("#add-div").show();
					//window.location.href = "/employee/emp/home/modify/"+sid;
					
				}
			});
		}
		else
		{
			alert("something went wrong.");
		}
	});
	
	$(document).on('click','.delete-emp',function(){
		empid = $(this).attr("empid");
		if(empid)
		{
			/*let delObj = {};
			delObj.id = deleteId;*/
			
			$.ajax({
				method: "post",
				url: "/employee/emp/delete/"+empid,
				//data: JSON.stringify(delObj),
				contentType: "application/Json",
				success: function(data) {
					alert("Delete successfully");
					table.ajax.reload(null,false);
					$("#add-div").hide();
					//window.location.href = "/crudoperation/home";
				}
			});			
		}
		else
		{
			alert("something went wrong.");
		}
	});
	
	$("#add").click(() => {
		empid = undefined;
		$("#name").val("");
		$("#city").val("");
		$("#address").val("");
		$("#mobile").val("");
		$("#add-div").show();
	});
	
})();