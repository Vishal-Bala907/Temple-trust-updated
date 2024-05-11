let loginPageEye = document.getElementsByClassName("eye")[0];
let passwordField = document.getElementById("InputPassword");
//.innerHTML = "visibility_off"

// Handling remove items age
async function fetchPosts() {
	try {
		const res = await fetch('http://localhost:8080/getAllItems');
		const data = await res.json();
		return data;
	} catch (error) {
		console.error('Error fetching posts:', error);
		throw error; // Re-throwing the error to be handled by the caller
	}
}

// To use the function and retrieve the posts object
let innerhtml = "";

// loading all the items to the Shop
window.addEventListener('load', function() {
	console.log("hello")
	fetchPosts()
		.then(data => {
			console.log(data)
			data.forEach(item => {
				innerhtml += ` 
            <div class="card" style="width: 18rem; height: fit-content;">
                 <img src="/${item.imageUrl}" class="card-img-top" alt="...">
                      <div class="card-body">
                        <h5 class="card-title">${item.name}</h5>
                        <h3 class="card-title">Rs. ${item.price}</h3>
                        <p class="card-text">${item.description}</p>
                        <a href="#" class="btn btn-danger" id="add-to-bag-${item.p_id}">Remove</a>
                      </div>
            </div>`
			});

			document.getElementById('remove-products').innerHTML = innerhtml;
			// Add click event listener to each "Add To Bag" button
			data.forEach(item => {
				document.getElementById(`add-to-bag-${item.p_id}`).addEventListener('click', function(event) {
					removeItemFromList(item);
				});
			});

		})
		.catch(error => {
			// Handle error
		});


});

// Function to handle logout
function logout() {
	fetch('/logout', {
		method: 'GET',
		credentials: 'same-origin', // Ensures cookies are included in the request
		redirect: 'follow'
	}).then(response => {
		if (response.redirected) {
			window.location.href = response.url;
		}
	}).catch(error => {
		console.error('Logout failed:', error);
	});
}

// Toggle eye function
loginPageEye.addEventListener('click', function() {
	if (loginPageEye.innerHTML === "visibility_off") {
		loginPageEye.innerHTML = "visibility";
		passwordField.type = "password"
	}

	else {
		loginPageEye.innerHTML = "visibility_off";
		passwordField.type = "text"
	}
})



