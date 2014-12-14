require(["dojo/parser", "dojo/dom", "dojo/on", "dojo/request", "dojo/json","dijit/form/TextBox", "dijit/form/DateTextBox","dijit/form/ValidationTextBox", "dojox/validate/web","dojox/grid/EnhancedGrid","dojo/data/ItemFileWriteStore", "dojo/domReady!"],onLoad);
var globalContacts;
var globalDom;
var contactURI = "rest/contact";

function onLoad(parser, dom, on, request, JSON, TextBox, DateTextBox, ValidationTextBox, validateweb, EnhancedGrid, ItemFileWriteStore){
	parser.parse();
    globalDom = dom; 
    var updateButton = dom.byId("update");
    var createButton = dom.byId("create");
    var resetButton = dom.byId("reset");
    var deleteButton = dom.byId("delete");
    on(updateButton, "click", function(evt){
       update(request, JSON);
    });
    on(resetButton, "click", function(evt){
        reset();
     });
    on(createButton, "click", function(evt){
        create(request, JSON);
     });
    on(deleteButton, "click", function(evt){
        del(request, JSON);
     });
    request.get(contactURI,
    		{handleAs: "json"} 
    	).then(
            function(response){
            	try{
            		try{
		            	globalContacts = response;
		            	populateGrid(response, ItemFileWriteStore, EnhancedGrid);
            		}
	            	catch(e){
	            		var trace = e.stack || e.stacktrace || "";
	            		console.debug(trace);
	            	}
            	}
            	catch(e){
            		var trace = e.stack || e.stacktrace || "";
            		console.debug(trace);
            	}
            },
            function(error){
                resultDiv.innerHTML = "<div class=\"error\">"+error+"<div>";
            });
}
var GlobalGrid;
var globalSelectedContact;
function populateGrid(contacts, ItemFileWriteStore, EnhancedGrid){
	 var data = {items: []};
	 data.items = contacts;
	 var contactStore = new ItemFileWriteStore({data: data});
	 if(!GlobalGrid){
		var layout = [[
		          {name: "Contact ID", field: 'id', hidden: true},
			      {name: "First name", field: 'firstName', width: "100px"},
			      {name: "Last name", field: 'lastName', width: "100px"},
			      {name: "Email", field: 'email', width: "100px"},
			      {name: "Phone", field: 'phone', width: "75px"},
			      {name: "Date of Birth", field: 'dob', width: "75px"}
			    ]];
		GlobalGrid = new EnhancedGrid({
	         id: 'contactGrid',
	         store: contactStore,
	         structure: layout,
	         autoWidth: true,
	         autoHeight: true,
	         rowSelector: '10px',
	         onSelected: onRowSelect
	         },
	         "grid"
		);
		console.debug("grid: " + GlobalGrid);
	    
		GlobalGrid.startup();
	 }
	 else{
		 GlobalGrid.setStore(contactStore);
		 console.debug("Refresh grid: "); 
	 }
}
function updateItem(contact){
	GlobalGrid.store.setValue(globalSelectedContact, "firstName", contact.firstName);
	GlobalGrid.store.setValue(globalSelectedContact, "lastName", contact.lastName);
	GlobalGrid.store.setValue(globalSelectedContact, "dob", contact.dob);
	GlobalGrid.store.setValue(globalSelectedContact, "email", contact.email);
	GlobalGrid.store.setValue(globalSelectedContact, "phone", contact.phone);
}
function addItem(contact){
	GlobalGrid.store.newItem(contact);
}
function removeItem(contact){
	GlobalGrid.store.deleteItem(contact);
}
function getGridItemById(id){
	for(var i in GlobalGrid.store.data.items){
		var contact = GlobalGrid.store.data.items[i];
		if(contact.id==id){
			return contact;
		}
	}
}
function onRowSelect(index){
	try{
		var contact = GlobalGrid.getItem(index);
		console.debug("contact: " + contact);
		globalSelectedContact = contact;
		console.debug("globalDom : " + globalDom);
		if(contact){
			globalDom.byId("firstName").value = contact.firstName;
			globalDom.byId("lastName").value = contact.lastName;
			globalDom.byId("phone").value = contact.phone;
			globalDom.byId("email").value = contact.email;
			globalDom.byId("dob").value = contact.dob;
		}
	}
	catch(e){
		var trace = e.stack || e.stacktrace || "";
		console.debug(trace);
	}
}

function reset(){
	globalDom.byId("firstName").value = "";
	globalDom.byId("lastName").value = "";
	globalDom.byId("phone").value = "";
	globalDom.byId("email").value = "";
	globalDom.byId("dob").value = "";
}
function update(request, JSON){
	var contact = new Object();
	contact.id = globalSelectedContact.id[0];
	contact.firstName = globalDom.byId("firstName").value;
	contact.lastName = globalDom.byId("lastName").value;
	contact.phone = globalDom.byId("phone").value;
	contact.email = globalDom.byId("email").value;
	contact.dob = globalDom.byId("dob").value;
	var putData = JSON.stringify(contact);
	request.put(contactURI, {
	        data: putData,
	        sync: true,
	        handleAs: "json",
	        headers: {
	                "Content-type": "application/json"
	        }
	    }).then(
	    		function(response){
	    			globalDom.byId("message").innerHTML = "The contact has been successfully updated.";
	    			updateItem(contact);
	    		},
	            function(error){
	                globalDom.byId("message").innerHTML = "There was an error while deleting the contact.";
	            }
	    	);
	    		
}
function create(request, JSON){
	var contact = new Object();
	contact.firstName = globalDom.byId("firstName").value;
	contact.lastName = globalDom.byId("lastName").value;
	contact.phone = globalDom.byId("phone").value;
	contact.email = globalDom.byId("email").value;
	contact.dob = globalDom.byId("dob").value;
	var postData = JSON.stringify(contact);
	request.post(contactURI, {
	        data: postData,
	        sync: true,
	        handleAs: "json",
	        headers: {
	                "Content-type": "application/json"
	        }
	    }).then(
	    		function(response){
	    			addItem(response);
	    			globalDom.byId("message").innerHTML = "The contact has been successfully saved.";
	    		},
	            function(error){
	                globalDom.byId("message").innerHTML = "There was an error while saving the contact.";
	            }
	    	);
	    		
}
function del(request, JSON){
	var contact = globalSelectedContact;
	var id = contact.id[0];
	var url = contactURI+"/"+id;
	request.del(url, {
	        handleAs: "json"
	    }).then(
	    		function(response){
	    				try {
							removeItem(contact);
							globalDom.byId("message").innerHTML = "The contact has been successfully deleted.";
						} catch (e) {
							var trace = e.stack || e.stacktrace || "";
							console.debug(trace);
						}
	    		},
	            function(error){
	    			globalDom.byId("message").innerHTML = "There was an error while deleting the contact.";
	            }
	    	);
	    		
}

